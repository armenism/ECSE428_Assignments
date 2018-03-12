package ca.mcgill.ecse428.assignment2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class TestPostalRateCalculator {

	// valid input variables
	private static final String regular = "REGULAR";
	private static final String xpress = "XPRESS";
	private static final String priority = "PRIORITY";

	private static final String source = "H3G2X1";
	private static final String destination1 = "K1M0V7";
	private static final String destination2 = "S0A1A0";
	private static final String destination3 = "Y0B1G0";
	
	// invalid input error messages
	private static final String invalidSource = "K1M0V7";
	private static final String invalidDestination = "H1D0Z2";
	private static final String invalidSyntaxSD = "HHJ777";
	private static final String standard = "STANDARD";

	private static final String errorInvalidPostType = "Invalid post type!";
	private static final String errorInvalidLength1 = "Invalid length - The minimum length allowed is 10 CM!";
	private static final String errorInvalidLength2 = "Invalid length - The maximum length allowed is 200 CM!";

	private static final String errorInvalidWidth = "Invalid width - The minimum width allowed is 7 CM!";
	private static final String errorInvalidHeight = "Invalid height - The minimum height allowed is 0.1 CM";
	private static final String errorInvalidGirth = "Invalid height and width combination - The maximum length + girth allowed is 300 CM";

	private static final String errorInvalidWeight1 = "Invalid weight - The minimum weight allowed is 0.01 KG!";
	private static final String errorInvalidWeight2 = "Invalid weight - The maximum weight allowed is 30.00 KG!";

	private static final String errorInvalidSyntaxLength = "Invalid syntax - Length must be in format 999.99";
	private static final String errorInvalidSyntaxWidth = "Invalid syntax - Width must be in format 999.99";
	private static final String errorInvalidSyntaxHeight = "Invalid syntax - Height must be in format 999.99";
	private static final String errorInvalidSyntaxWeight = "Invalid syntax - Weight must be in format 999.99";

	private static final String errorInvalidSourceDestination = "Invalid source and Destination - Rate not found";
	private static final String errorInvalidSyntaxSource = "Invalid source syntax - The source postal code must be in format A8A 8A8";
	private static final String errorInvalidSyntaxDestination = "Invalid destination syntax - The destination postal code must be in format A8A 8A8";
	private static final String errorNullInput = "One or more than one of the inputs has been left blank!";
	
	@Test
	public void validInputTest1_Regular() {
		// from the lookup table
		assertEquals(10.73, PostalRateCalculator.calculateParcelRate(source, destination1, 10, 7, 1, 0.15, regular), 0);
	}
	
	@Test
	public void validInputTest2_Regular() {
		// from the lookup table
		assertEquals(23.14, PostalRateCalculator.calculateParcelRate(source, destination2, 10, 7, 1, 2.5, regular), 0);
	}

	@Test
	public void validInputTest3_Regular() {
		// from the lookup table
		assertEquals(51.00, PostalRateCalculator.calculateParcelRate(source, destination3, 10, 7, 1, 17.69, regular), 0);
	}
	
	@Test
	public void validInputTest4_Regular() {
		// from the lookup table
		assertEquals(25.30, PostalRateCalculator.calculateParcelRate(source, destination1, 10, 7, 1, 17.69, regular), 0);
	}
	
	@Test
	public void validInputTest5_XPress() {
		// from the lookup table
		assertEquals(17.40, PostalRateCalculator.calculateParcelRate(source, destination1, 10, 7, 1, 5.0, xpress), 0);
	}
	
	@Test
	public void validInputTest6_XPress() {
		// from the lookup table
		assertEquals(68.31, PostalRateCalculator.calculateParcelRate(source, destination2, 10, 7, 1, 5.0, xpress), 0);
	}
	
	@Test
	public void validInputTest7_XPress() {
		// from the lookup table
		assertEquals(68.23, PostalRateCalculator.calculateParcelRate(source, destination3, 10, 7, 1, 5.0, xpress), 0);
	}
	
	@Test
	public void validInputTest8_Priority() {
		// from the lookup table
		assertEquals(41.85, PostalRateCalculator.calculateParcelRate(source, destination1, 10, 7, 1, 15.77, priority), 0);
	}
	
	@Test
	public void validInputTest9_Priority() {
		// from the lookup table
		assertEquals(173.12, PostalRateCalculator.calculateParcelRate(source, destination2, 10, 7, 1, 15.77, priority), 0);
	}
	
	@Test
	public void validInputTest10_Priority() {
		// from the lookup table
		assertEquals(176.48, PostalRateCalculator.calculateParcelRate(source, destination3, 10, 7, 1, 15.77, priority), 0);
	}
	
	@Test
	public void test11InvalidPostType() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10, 7, 0.1, 15.77, standard);
			fail("Should have thrown an error message:" + errorInvalidPostType);
		} catch (IOException e) {
			assertEquals(errorInvalidPostType, e.getMessage());
		}
	}
	
	@Test
	public void test12InvalidLengthBelowMin() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 8, 7, 0.1, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidLength1);
		} catch (IOException e) {
			assertEquals(errorInvalidLength1, e.getMessage());
		}
	}
	
	@Test
	public void test13InvalidLengthAboveMax() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 250, 7, 0.1, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidLength2);
		} catch (IOException e) {
			assertEquals(errorInvalidLength2, e.getMessage());
		}
	}
	
	@Test
	public void test14InvalidLengthSyntax() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10.555, 7, 0.1, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidSyntaxLength);
			} catch (IOException e) {
				assertEquals(errorInvalidSyntaxLength, e.getMessage());
			}
	}
	
	@Test
	public void test15InvalidWidth() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10, 6, 0.1, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidWidth);
		} catch (IOException e) {
			assertEquals(errorInvalidWidth, e.getMessage());
		}
	}
	
	@Test
	public void test16InvalidHeight() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10, 7, 0.05, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidHeight);
		} catch (IOException e) {
			assertEquals(errorInvalidHeight, e.getMessage());
		}
	}
	
	@Test
	public void test17InvalidGirth() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10, 100, 100, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidGirth);
		} catch (IOException e) {
			assertEquals(errorInvalidGirth, e.getMessage());
		}
	}
	
	@Test
	public void test18InvalidSyntaxWidth() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10, 7.999, 0.1, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidSyntaxWidth);
		} catch (IOException e) {
			assertEquals(errorInvalidSyntaxWidth, e.getMessage());
		}
	}
	
	@Test
	public void test19InvalidSyntaxHeight() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10, 7, 1.00005, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidSyntaxHeight);
		} catch (IOException e) {
			assertEquals(errorInvalidSyntaxHeight, e.getMessage());
		}
	}
	
	@Test
	public void test20InvalidWeightMin() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10, 7, 0.1, 0, priority);
			fail("Should have thrown an error message:" + errorInvalidWeight1);
		} catch (IOException e) {
			assertEquals(errorInvalidWeight1, e.getMessage());
		}
	}
	
	@Test
	public void test21InvalidWeightMax() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10, 7, 0.1, 40, priority);
			fail("Should have thrown an error message:" + errorInvalidWeight2);
		} catch (IOException e) {
			assertEquals(errorInvalidWeight2, e.getMessage());
		}
	}
	
	@Test
	public void test22InvalidSyntaxWeight() {
		try {
			PostalRateCalculator.checkParameters(source, destination1, 10, 7, 0.1, 15.7777, priority);
			fail("Should have thrown an error message:" + errorInvalidSyntaxWeight);
		} catch (IOException e) {
			assertEquals(errorInvalidSyntaxWeight, e.getMessage());
		}
	}
	
	@Test
	public void test23InvalidDestination() {
		try {
			PostalRateCalculator.checkParameters(source, invalidDestination, 10, 7, 0.1, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidSourceDestination);
		} catch (IOException e) {
			assertEquals(errorInvalidSourceDestination, e.getMessage());
		}
	}
	
	@Test
	public void test24InvalidSource() {
		try {
			PostalRateCalculator.checkParameters(invalidSource, destination2, 10, 7, 0.1, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidSourceDestination);
		} catch (IOException e) {
			assertEquals(errorInvalidSourceDestination, e.getMessage());
		}
	}

	@Test
	public void test25InvalidSyntaxSource() {
		try {
			PostalRateCalculator.checkParameters(invalidSyntaxSD, destination1, 10, 7, 0.1, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidSyntaxSource);
		} catch (IOException e) {
			assertEquals(errorInvalidSyntaxSource, e.getMessage());
		}
	}

	@Test
	public void test26InvalidSyntaxDestination() {
		try {
			PostalRateCalculator.checkParameters(source, invalidSyntaxSD, 10, 7, 0.1, 15.77, priority);
			fail("Should have thrown an error message:" + errorInvalidSyntaxDestination);
		} catch (IOException e) {
			assertEquals(errorInvalidSyntaxDestination, e.getMessage());
		}
	}
}
