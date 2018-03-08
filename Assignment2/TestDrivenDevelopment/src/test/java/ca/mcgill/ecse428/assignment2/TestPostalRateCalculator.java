package ca.mcgill.ecse428.assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPostalRateCalculator {

	private static final String regular = "REGULAR";
	private static final String xpress = "XPRESS";
	private static final String priority = "PRIORITY";

	private static final String source = "H3G2X1";
	private static final String destination1 = "K1M0V7";
	
	@Test
	public void validInputTest1_Regular() {
		
		// from the lookup table
		assertEquals(10.73, PostalRateCalculator.calculateParcelRate(source, destination1, 0.1, 0.07, 0.01, 0.15, regular), 0);
	}

}
