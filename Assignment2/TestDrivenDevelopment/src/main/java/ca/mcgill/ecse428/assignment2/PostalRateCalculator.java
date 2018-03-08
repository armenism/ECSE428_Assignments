package ca.mcgill.ecse428.assignment2;

import java.io.File;
import java.io.IOException;

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

	public static void main(String[] args) {

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
		switch(postType.trim().toLowerCase()) {
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
}
