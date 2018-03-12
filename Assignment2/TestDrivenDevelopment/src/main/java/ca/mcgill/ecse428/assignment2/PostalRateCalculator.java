package ca.mcgill.ecse428.assignment2;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PostalRateCalculator {

	private static Sheet rateCodeLookupSheet;
	private static Sheet rateSheet;

	private static final int regularTypeRow = 140;
	private static final int xpressTypeRow = 72;
	private static final int priorityTypeRow = 4;
	private static final int typeRange = 59;

	public static void main(String[] args) throws IOException {

		String source, destination, postType;
		double length, width, height, weight;

		try {
			rateCodeLookupSheet = WorkbookFactory.create(new File("CanadaPostMtlTable.xlsx")).getSheetAt(0);
			rateSheet = WorkbookFactory.create(new File("CanadaPostRates.xlsx")).getSheetAt(0);
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

		try {
			Scanner scanner = new Scanner(System.in);

			System.out.print("Enter source postal code: ");
			source = scanner.nextLine();
			System.out.print("Enter destination postal code: ");
			destination = scanner.nextLine();
			System.out.print("Enter postal delivery type (regular/xpress/priority): ");
			postType = scanner.nextLine();
			System.out.print("Enter parcel length(CM): ");
			length = scanner.nextDouble();
			System.out.print("Enter parcel width(CM): ");
			width = scanner.nextDouble();
			System.out.print("Enter parcel height(CM): ");
			height = scanner.nextDouble();
			System.out.print("Enter parcel weight(KG): ");
			weight = scanner.nextDouble();

			// Check for the input validity
			checkParameters(source, destination, length, width, height, weight, postType);

			// Output the rate of the parcel
			System.out.println("\nThe rate for the given postal delivery is "
					+ calculateParcelRate(source, destination, length, width, height, weight, postType));

			scanner.close();
		} catch (InputMismatchException e) {
			System.out.println("There was an input mismatch! Please enter a numerical value");
			System.exit(1);
		}
	}

	/**
	 * Calculates the rate for the given parcel based on its properties, and the
	 * distance between the source and the destination addresses.
	 * 
	 * @param source Source postal code
	 * @param destination Destination postal code
	 * @param length Parcel length
	 * @param width Parcel width
	 * @param height Parcel height
	 * @param weight Parcel weight
	 * @param postType Post delivery type (regular/xPress/priority)
	 * @return The estimated rate of this specific parcel
	 */
	public static double calculateParcelRate(String source, String destination, double length, double width,
			double height, double weight, String postType) {

		try {
			rateCodeLookupSheet = WorkbookFactory.create(new File("CanadaPostMtlTable.xlsx")).getSheetAt(0);
			rateSheet = WorkbookFactory.create(new File("CanadaPostRates.xlsx")).getSheetAt(0);
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

		int type = 0;
		// determine where to start looking in the sheet
		switch (postType.trim().toLowerCase()) {
		case "regular":
			type = regularTypeRow;
			break;
		case "xpress":
			type = xpressTypeRow;
			break;
		case "priority":
			type = priorityTypeRow;
			break;
		}

		// find the rate code from the postal codes
		String rateCode = findRateCode(source, destination);

		// go through the corresponding rows and compare the weight
		for (int row = type; row <= type + typeRange; row++) {
			if (Double.parseDouble(rateSheet.getRow(row).getCell(0).toString()) >= weight) {
				return getParcelRate(rateCode, row, type);
			}
		}
		// if no matches, then return 0
		return 0;
	}

	/**
	 * This method gets the rate for the given parcel based on its rate code.
	 * 
	 * @param rateCode The rate code for the given destination
	 * @param row The current row
	 * @param rowInit The starting row
	 * @return Parcel rate
	 */
	private static double getParcelRate(String rateCode, int row, int rowInit) {
		// find the corresponding price for the given weight and rate code
		for (Cell cell : rateSheet.getRow(rowInit - 2)) {
			if (cell.toString().equals(rateCode)) {
				return Double.parseDouble(rateSheet.getRow(row).getCell(cell.getColumnIndex()).toString());
			}
		}
		return 0;
	}

	/**
	 * Finds the rate code for the given destination based on the source postal
	 * code.
	 * 
	 * @param source The source postal code
	 * @param destination The destination postal code
	 * @return The rate code for the given destination postal code
	 */
	private static String findRateCode(String source, String destination) {

		for (Row row : rateCodeLookupSheet) {
			if (row.getCell(0).toString().equals(destination.substring(0, 3))) {
				return row.getCell(1).toString();
			}
		}

		return null;
	}

	/**
	 * Checks the validity of the given inputs.
	 * 
	 * @param source Source postal code
	 * @param destination Destination postal code
	 * @param length Parcel length
	 * @param width Parcel width
	 * @param height Parcel height
	 * @param weight Parcel weight
	 * @param postType Post delivery type (regular/xPress/priority)
	 * @throws IOException
	 */
	public static void checkParameters(String source, String destination, double length, double width, double height,
			double weight, String postType) throws IOException {

		String type = postType.toLowerCase();

		if (!type.equals("regular") && !type.equals("xpress") && !type.equals("priority")) {
			throw new IOException("Invalid post type!");
		}
		if (!checkDecimalPoints(length)) {
			throw new IOException("Invalid syntax - Length must be in format 999.99");
		}
		if (!checkDecimalPoints(width)) {
			throw new IOException("Invalid syntax - Width must be in format 999.99");
		}
		if (!checkDecimalPoints(height)) {
			throw new IOException("Invalid syntax - Height must be in format 999.99");
		}
		if (!checkDecimalPoints(weight)) {
			throw new IOException("Invalid syntax - Weight must be in format 999.99");
		}
		if (length < 10) {
			throw new IOException("Invalid length - The minimum length allowed is 10 CM!");
		}
		if (length > 200) {
			throw new IOException("Invalid length - The maximum length allowed is 200 CM!");
		}
		if (width < 7) {
			throw new IOException("Invalid width - The minimum width allowed is 7 CM!");
		}
		if (height < 0.1) {
			throw new IOException("Invalid height - The minimum height allowed is 0.1 CM");
		}
		if ((height + width) * 2 + length > 300) {
			throw new IOException(
					"Invalid height and width combination - The maximum length + girth allowed is 300 CM");
		}
		if (weight < 0.01) {
			throw new IOException("Invalid weight - The minimum weight allowed is 0.01 KG!");
		}
		if (weight > 30) {
			throw new IOException("Invalid weight - The maximum weight allowed is 30.00 KG!");
		}
		if (!checkPostalCodeSyntax(source)) {
			throw new IOException("Invalid source syntax - The source postal code must be in format A8A 8A8");
		}
		if (!checkPostalCodeSyntax(destination)) {
			throw new IOException("Invalid destination syntax - The destination postal code must be in format A8A 8A8");
		}
		if (findRateCode(source, destination) == null) {
			throw new IOException("Invalid source and Destination - Rate not found");
		}
		if (source.charAt(0) != 'H' && source.charAt(0) != 'J') {
			throw new IOException("Invalid source and Destination - Rate not found");
		}
	}

	/**
	 * Checks the number of digits after the decimal point is less than or equal to
	 * 2.
	 * 
	 * @param input The input of type double
	 * @return Boolean value based on the input
	 */
	private static boolean checkDecimalPoints(double input) {

		String text = Double.toString(Math.abs(input));
		int integerPlaces = text.indexOf('.');
		int decimalPlaces = text.length() - integerPlaces - 1;

		return decimalPlaces < 3;
	}

	/**
	 * Checks the validity of the given postal code syntax.
	 * 
	 * @param postalCode The given postal code
	 * @return Boolean value based on the validity
	 */
	private static boolean checkPostalCodeSyntax(String postalCode) {
		postalCode = postalCode.trim();

		if (postalCode.length() != 6) {
			return false;
		}

		for (int i = 0; i < postalCode.length() - 1; i++) {
			if (i % 2 == 0) {
				if (!Character.isLetter(postalCode.charAt(i))) {
					return false;
				}
			} else {
				if (!Character.isDigit(postalCode.charAt(i))) {
					return false;
				}
			}
		}
		return true;
	}
}
