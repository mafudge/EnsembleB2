package edu.syr.ischool.mafudge.ensemblelib.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.syr.ischool.mafudge.ensemblelib.*;


public class TestHexStringConverter {
	
	@Test
	public void testToHexString() throws Exception {
		HexStringConverter hsc = new HexStringConverter();
		String expected = "00010F10FF".toLowerCase();
		byte[] testByteArray = { (byte)(0), (byte)(1), (byte)(15), (byte)(16), (byte)(255) };
		String actual = hsc.ToHexString(testByteArray);
		assertEquals(expected,actual);
	}
	
	@Test
	public void testToByteArray() throws Exception {
		HexStringConverter hsc = new HexStringConverter();
		String testHexString = "01FF".toLowerCase();
		byte[] expected = { (byte)(1), (byte)(255) };
		byte[] actual = hsc.ToByteArray(testHexString);
		assertEquals(hsc.ToHexString(expected),hsc.ToHexString(actual));
	}

}
