package ca.mcgill.ecse428.assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPostalRateCalculator {

	private static final String regular = "REGULAR";
	private static final String xpress = "XPRESS";
	private static final String priority = "PRIORITY";

	private static final String source = "H3G2X1";
	private static final String destination1 = "K1M0V7";
	private static final String destination2 = "S0A1A0";
	private static final String destination3 = "Y0B1G0";
	
	@Test
	public void validInputTest1_Regular() {
		// from the lookup table
		assertEquals(10.73, PostalRateCalculator.calculateParcelRate(source, destination1, 0.1, 0.07, 0.01, 0.15, regular), 0);
	}
	
	@Test
	public void validInputTest2_Regular() {
		// from the lookup table
		assertEquals(23.14, PostalRateCalculator.calculateParcelRate(source, destination2, 0.1, 0.7, 0.1, 2.5, regular), 0);
	}
	
	@Test
	public void validInputTest3_Regular() {
		// from the lookup table
		assertEquals(51.00, PostalRateCalculator.calculateParcelRate(source, destination3, 0.1, 0.7, 0.1, 17.69, regular), 0);
	}
	
	@Test
	public void validInputTest4_Regular() {
		// from the lookup table
		assertEquals(25.30, PostalRateCalculator.calculateParcelRate(source, destination1, 0.1, 0.7, 0.1, 17.69, regular), 0);
	}
	
	@Test
	public void validInputTest5_XPress() {
		// from the lookup table
		assertEquals(17.40, PostalRateCalculator.calculateParcelRate(source, destination1, 0.1, 0.7, 0.1, 5.0, xpress), 0);
	}
	
	@Test
	public void validInputTest6_XPress() {
		// from the lookup table
		assertEquals(68.31, PostalRateCalculator.calculateParcelRate(source, destination2, 0.1, 0.7, 0.1, 5.0, xpress), 0);
	}
	
	@Test
	public void validInputTest7_XPress() {
		// from the lookup table
		assertEquals(68.23, PostalRateCalculator.calculateParcelRate(source, destination3, 0.1, 0.7, 0.1, 5.0, xpress), 0);
	}

}
