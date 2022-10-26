package edu.wit.cs.comp5900.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.Permission;
import java.util.Scanner;

import edu.wit.cs.comp5900.Fraction;
import edu.wit.cs.comp5900.PA5a;
import junit.framework.TestCase;

public class PA5aTestCase extends TestCase {
	
	@SuppressWarnings("serial")
	private static class ExitException extends SecurityException {}
	
	
	@Override
    protected void setUp() throws Exception 
    {
        super.setUp();
    }
	
	@Override
    protected void tearDown() throws Exception 
    {
        super.tearDown();
    }
	
	private static final String E_DEN_ZERO = "Denominator cannot be zero.";
	private static final String E_DIV_ZERO = "Cannot divide by zero.";
	
	private void _validateConstruction(Fraction result, int nOut, int dOut, double dec, String str) {
		assertEquals("Numerator Error!", nOut, result.getNumerator());
		assertEquals("Denominator Error!", dOut, result.getDenominator());
		assertEquals("Decimal Error!", dec, result.toDecimal(), 0.00001);
		assertEquals("String Error!", str, result.toString());
	}
	
	private void _testConstructorGoodDefault(int nOut, int dOut, double dec, String str) {
		Fraction result = null;
		try {
			result = new Fraction();
		} catch (ExitException e) {}
		
		_validateConstruction(result, nOut, dOut, dec, str);
	}
	
	private void _testConstructorGoodInt(int nIn, int dIn, int nOut, int dOut, double dec, String str) {
		Fraction result = null;
		try {
			result = new Fraction(nIn, dIn);
		} catch (ExitException e) {}
		
		_validateConstruction(result, nOut, dOut, dec, str);
	}
	
	private void _testConstructorGoodStr(String input, int nOut, int dOut, double dec, String str) {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		Fraction result = null;
		try {
			result = new Fraction(new Scanner(input));
		} catch (ExitException e) {}
		
		System.setIn(null);
		System.setOut(null);
		
		assertEquals(String.format("Enter numerator: Enter denominator: "), outContent.toString());
		_validateConstruction(result, nOut, dOut, dec, str);
	}
	
	private void _testConstructorGood(int nIn, int dIn, int nOut, int dOut, double dec, String str) {
		_testConstructorGoodInt(nIn, dIn, nOut, dOut, dec, str);
		_testConstructorGoodStr(String.format("%d %d", nIn, dIn), nOut, dOut, dec, str);
		_testConstructorGoodStr(String.format("%d%n%d", nIn, dIn), nOut, dOut, dec, str);
	}
	
	private void _testConstructorBadInt(int nIn, int dIn, String output) {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		Fraction result = null;
		try {
			result = new Fraction(nIn, dIn);
		} catch (ExitException e) {}
		
		assertNull(result);
		assertEquals(String.format("%s%n", output), outContent.toString());
		
		System.setIn(null);
		System.setOut(null);
	}
	
	private void _testConstructorBadStr(String input, String str) {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		Fraction result = null;
		try {
			result = new Fraction(new Scanner(input));
		} catch (ExitException e) {}
		
		System.setIn(null);
		System.setOut(null);
		
		assertEquals(String.format("Enter numerator: Enter denominator: %s%n", str), outContent.toString());
		assertNull(result);
	}
	
	private void _testConstructorBad(int nIn, int dIn, String output) {
		_testConstructorBadInt(nIn, dIn, output);
		_testConstructorBadStr(String.format("%d %d", nIn, dIn), output);
		_testConstructorBadStr(String.format("%d%n%d", nIn, dIn), output);
	}
	
	public void testConstruct() {
		_testConstructorBad(1, 0, E_DEN_ZERO);
		_testConstructorBad(10, 0, E_DEN_ZERO);
		
		_testConstructorGoodDefault(0, 1, 0, "0");
		
		_testConstructorGood(0, 1, 0, 1, 0, "0");
		_testConstructorGood(0, 10, 0, 1, 0, "0");
		_testConstructorGood(0, -7, 0, 1, 0, "0");
		
		_testConstructorGood(1, 1, 1, 1, 1, "1");
		_testConstructorGood(10, 10, 1, 1, 1, "1");
		_testConstructorGood(-7, -7, 1, 1, 1, "1");
		
		_testConstructorGood(-1, 1, -1, 1, -1, "-1");
		_testConstructorGood(1, -1, -1, 1, -1, "-1");
		_testConstructorGood(-7, 7, -1, 1, -1, "-1");
		_testConstructorGood(7, -7, -1, 1, -1, "-1");
		
		_testConstructorGood(289, 17, 17, 1, 17, "17");
		_testConstructorGood(289, -17, -17, 1, -17, "-17");
		_testConstructorGood(-289, 17, -17, 1, -17, "-17");
		_testConstructorGood(-289, -17, 17, 1, 17, "17");
		
		_testConstructorGood(1, 2, 1, 2, 0.5, "1/2 (0.500)");
		_testConstructorGood(2, 4, 1, 2, 0.5, "1/2 (0.500)");
		_testConstructorGood(-2, -4, 1, 2, 0.5, "1/2 (0.500)");
		_testConstructorGood(-1, 2, -1, 2, -0.5, "-1/2 (-0.500)");
		_testConstructorGood(2, -4, -1, 2, -0.5, "-1/2 (-0.500)");
		_testConstructorGood(-2, 4, -1, 2, -0.5, "-1/2 (-0.500)");
		
		_testConstructorGood(17, 289, 1, 17, 0.05882, "1/17 (0.059)");
		_testConstructorGood(-17, 289, -1, 17, -0.05882, "-1/17 (-0.059)");
		_testConstructorGood(17, -289, -1, 17, -0.05882, "-1/17 (-0.059)");
		_testConstructorGood(-17, -289, 1, 17, 0.05882, "1/17 (0.059)");
		
		_testConstructorGood(290, 17, 290, 17, 17.05882, "290/17 (17.059)");
		_testConstructorGood(290, -17, -290, 17, -17.05882, "-290/17 (-17.059)");
		_testConstructorGood(-290, 17, -290, 17, -17.05882, "-290/17 (-17.059)");
		_testConstructorGood(-290, -17, 290, 17, 17.05882, "290/17 (17.059)");
	}
	
	private void _compareFractions(int nE, int dE, double decE, String strE, Fraction fTest) {
		assertEquals("Numerator Error!", nE, fTest.getNumerator());
		assertEquals("Denominator Error!", dE, fTest.getDenominator());
		assertEquals("Decimal Error!", decE, fTest.toDecimal(), 0.00001);
		assertEquals("String Error!", strE, fTest.toString());
	}
	
	private void _testAdd(int n1, int d1, int n2, int d2, int nE, int dE, double decE, String strE) {
		Fraction result = null;
		try {
			result = (new Fraction(n1, d1)).plus(new Fraction(n2, d2));
		} catch (ExitException e) {}
		
		_compareFractions(nE, dE, decE, strE, result);
	}
	
	public void testAdd() {
		_testAdd(0, 1, 0, 1, 0, 1, 0, "0");
		_testAdd(0, 2, 0, 3, 0, 1, 0, "0");
		
		_testAdd(0, 7, 1, 3, 1, 3, (1./3.), "1/3 (0.333)");
		_testAdd(1, 3, 0, 7, 1, 3, (1./3.), "1/3 (0.333)");
		_testAdd(2, 4, 0, 7, 1, 2, 0.5, "1/2 (0.500)");
		
		_testAdd(0, 7, -2, 4, -1, 2, -0.5, "-1/2 (-0.500)");
		_testAdd(0, 7, 2, -4, -1, 2, -0.5, "-1/2 (-0.500)");
		_testAdd(-2, 4, 0, 7, -1, 2, -0.5, "-1/2 (-0.500)");
		_testAdd(2, -4, 0, 7, -1, 2, -0.5, "-1/2 (-0.500)");
		
		_testAdd(1, 2, 1, 2, 1, 1, 1, "1");
		_testAdd(1, 2, 2, 4, 1, 1, 1, "1");
		_testAdd(2, 4, 1, 2, 1, 1, 1, "1");
		_testAdd(3, 6, 4, 8, 1, 1, 1, "1");
		
		_testAdd(1, 3, 1, 3, 2, 3, (2./3.), "2/3 (0.667)");
		_testAdd(1, 7, 1, 7, 2, 7, (2./7.), "2/7 (0.286)");
		_testAdd(1, 7, 2, 7, 3, 7, (3./7.), "3/7 (0.429)");
		_testAdd(2, 7, 1, 7, 3, 7, (3./7.), "3/7 (0.429)");
		_testAdd(2, 7, 4, 7, 6, 7, (6./7.), "6/7 (0.857)");
		_testAdd(4, 7, 2, 7, 6, 7, (6./7.), "6/7 (0.857)");
		
		_testAdd(7, 4, 2, 7, 57, 28, (57./28.), "57/28 (2.036)");
		_testAdd(4, 7, 7, 2, 57, 14, (57./14.), "57/14 (4.071)");
		_testAdd(7, 4, 7, 2, 21, 4, (21./4.), "21/4 (5.250)");
		_testAdd(8, 4, 7, 2, 11, 2, (11./2.), "11/2 (5.500)");
	}
	
	private void _testSubtract(int n1, int d1, int n2, int d2, int nE, int dE, double decE, String strE) {
		Fraction result = null;
		try {
			result = (new Fraction(n1, d1)).minus(new Fraction(n2, d2));
		} catch (ExitException e) {}
		
		_compareFractions(nE, dE, decE, strE, result);
	}
	
	public void testSubtract() {
		_testSubtract(0, 1, 0, 1, 0, 1, 0, "0");
		_testSubtract(0, 2, 0, 3, 0, 1, 0, "0");
		
		_testSubtract(0, 7, 1, 3, -1, 3, (-1./3), "-1/3 (-0.333)");
		_testSubtract(0, 7, 2, 10, -1, 5, (-1./5.), "-1/5 (-0.200)");
		_testSubtract(1, 3, 0, 7, 1, 3, (1./3.), "1/3 (0.333)");
		_testSubtract(2, 4, 0, 7, 1, 2, (1./2.), "1/2 (0.500)");
		
		_testSubtract(1, 3, 2, 6, 0, 1, (0), "0");
		_testSubtract(2, 4, 2, 4, 0, 1, (0), "0");
		
		_testSubtract(0, 4, 1, 4, -1, 4, (-1./4.), "-1/4 (-0.250)");
		_testSubtract(1, 4, 1, 4, 0, 1, (0), "0");
		_testSubtract(1, 2, 1, 4, 1, 4, (0.25), "1/4 (0.250)");
		_testSubtract(3, 4, 1, 4, 1, 2, (0.5), "1/2 (0.500)");
		_testSubtract(1, 1, 1, 4, 3, 4, (0.75), "3/4 (0.750)");
		_testSubtract(5, 4, 1, 4, 1, 1, (1), "1");
		_testSubtract(3, 2, 1, 4, 5, 4, (1.25), "5/4 (1.250)");
		_testSubtract(7, 4, 1, 4, 3, 2, (1.5), "3/2 (1.500)");
		_testSubtract(2, 1, 1, 4, 7, 4, (1.75), "7/4 (1.750)");
		
		_testSubtract(0, 4, -1, 4, 1, 4, (0.25), "1/4 (0.250)");
		_testSubtract(1, 4, -1, 4, 1, 2, (0.5), "1/2 (0.500)");
		_testSubtract(1, 2, -1, 4, 3, 4, (.75), "3/4 (0.750)");
		_testSubtract(3, 4, -1, 4, 1, 1, (1), "1");
		_testSubtract(1, 1, -1, 4, 5, 4, (1.25), "5/4 (1.250)");
		_testSubtract(5, 4, -1, 4, 3, 2, (1.5), "3/2 (1.500)");
		_testSubtract(3, 2, -1, 4, 7, 4, (1.75), "7/4 (1.750)");
		_testSubtract(7, 4, -1, 4, 2, 1, (2), "2");
		_testSubtract(2, 1, -1, 4, 9, 4, (2.25), "9/4 (2.250)");
		
		_testSubtract(0, 4, 1, -4, 1, 4, (.25), "1/4 (0.250)");
		_testSubtract(1, 4, 1, -4, 1, 2, (.5), "1/2 (0.500)");
		_testSubtract(1, 2, 1, -4, 3, 4, (.75), "3/4 (0.750)");
		_testSubtract(3, 4, 1, -4, 1, 1, (1), "1");
		_testSubtract(1, 1, 1, -4, 5, 4, (1.25), "5/4 (1.250)");
		_testSubtract(5, 4, 1, -4, 3, 2, (1.5), "3/2 (1.500)");
		_testSubtract(3, 2, 1, -4, 7, 4, (1.75), "7/4 (1.750)");
		_testSubtract(7, 4, 1, -4, 2, 1, (2), "2");
		_testSubtract(2, 1, 1, -4, 9, 4, (2.25), "9/4 (2.250)");
		
		_testSubtract(1, 4, 0, 4, 1, 4, (.25), "1/4 (0.250)");
		_testSubtract(1, 4, 1, 4, 0, 1, (0), "0");
		_testSubtract(1, 4, 1, 2, -1, 4, (-.25), "-1/4 (-0.250)");
		_testSubtract(1, 4, 3, 4, -1, 2, (-.5), "-1/2 (-0.500)");
		_testSubtract(1, 4, 1, 1, -3, 4, (-.75), "-3/4 (-0.750)");
		_testSubtract(1, 4, 5, 4, -1, 1, (-1), "-1");
		_testSubtract(1, 4, 3, 2, -5, 4, (-1.25), "-5/4 (-1.250)");
		_testSubtract(1, 4, 7, 4, -3, 2, (-1.5), "-3/2 (-1.500)");
		_testSubtract(1, 4, 2, 1, -7, 4, (-1.75), "-7/4 (-1.750)");
		
		_testSubtract(-1, 4, 0, 4, -1, 4, (-.25), "-1/4 (-0.250)");
		_testSubtract(-1, 4, 1, 4, -1, 2, (-.5), "-1/2 (-0.500)");
		_testSubtract(-1, 4, 1, 2, -3, 4, (-.75), "-3/4 (-0.750)");
		_testSubtract(-1, 4, 3, 4, -1, 1, (-1), "-1");
		_testSubtract(-1, 4, 1, 1, -5, 4, (-1.25), "-5/4 (-1.250)");
		_testSubtract(-1, 4, 5, 4, -3, 2, (-1.5), "-3/2 (-1.500)");
		_testSubtract(-1, 4, 3, 2, -7, 4, (-1.75), "-7/4 (-1.750)");
		_testSubtract(-1, 4, 7, 4, -2, 1, (-2), "-2");
		_testSubtract(-1, 4, 2, 1, -9, 4, (-2.25), "-9/4 (-2.250)");
	}
	
	private void _testMultiply(int n1, int d1, int n2, int d2, int nE, int dE, double decE, String strE) {
		Fraction result = null;
		try {
			result = (new Fraction(n1, d1)).times(new Fraction(n2, d2));
		} catch (ExitException e) {}
		
		_compareFractions(nE, dE, decE, strE, result);
	}
	
	public void testMultiply() {
		_testMultiply(0, 7, 5, 8, 0, 1, (0), "0");
		_testMultiply(0, 7, 0, 8, 0, 1, (0), "0");
		
		_testMultiply(1, 7, 1, 8, 1, 56, (1./56.), "1/56 (0.018)");
		_testMultiply(-1, 7, 1, 8, -1, 56, (-1./56.), "-1/56 (-0.018)");
		_testMultiply(1, -7, 1, 8, -1, 56, (-1./56.), "-1/56 (-0.018)");
		_testMultiply(1, 7, -1, 8, -1, 56, (-1./56.), "-1/56 (-0.018)");
		_testMultiply(1, 7, 1, -8, -1, 56, (-1./56.), "-1/56 (-0.018)");
		_testMultiply(-1, 7, 1, -8, 1, 56, (1./56.), "1/56 (0.018)");
		_testMultiply(1, -7, -1, 8, 1, 56, (1./56.), "1/56 (0.018)");
		_testMultiply(-1, -7, -1, -8, 1, 56, (1./56.), "1/56 (0.018)");
		
		_testMultiply(2, 5, 50, 20, 1, 1, (1), "1");
	}
	
	private void _testDivide(int n1, int d1, int n2, int d2, int nE, int dE, double decE, String strE) {
		Fraction result = null;
		try {
			result = (new Fraction(n1, d1)).divides(new Fraction(n2, d2));
		} catch (ExitException e) {}
		
		_compareFractions(nE, dE, decE, strE, result);
	}
	
	public void testDivide() {
		_testDivide(0, 7, 5, 8, 0, 1, (0), "0");
		
		_testDivide(2, 5, 50, 20, 4, 25, (0.16), "4/25 (0.160)");
		_testDivide(2, 5, 20, 50, 1, 1, (1), "1");
		
		_testDivide(1, 7, 1, 8, 8, 7, (8./7.), "8/7 (1.143)");
		_testDivide(-1, 7, 1, 8, -8, 7, (-8./7.), "-8/7 (-1.143)");
		_testDivide(1, -7, 1, 8, -8, 7, (-8./7.), "-8/7 (-1.143)");
		_testDivide(1, 7, -1, 8, -8, 7, (-8./7.), "-8/7 (-1.143)");
		_testDivide(1, 7, 1, -8, -8, 7, (-8./7.), "-8/7 (-1.143)");
		_testDivide(-1, 7, 1, -8, 8, 7, (8./7.), "8/7 (1.143)");
		_testDivide(1, -7, -1, 8, 8, 7, (8./7.), "8/7 (1.143)");
		_testDivide(-1, -7, -1, -8, 8, 7, (8./7.), "8/7 (1.143)");
	}
	
	private void _testDivide(int n1, int d1, int n2, int d2, String output) {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		Fraction result = null;
		try {
			result = (new Fraction(n1, d1)).divides(new Fraction(n2, d2));
		} catch (ExitException e) {}
		
		assertNull(result);
		assertEquals(String.format("%s%n", output), outContent.toString());
		
		System.setIn(null);
		System.setOut(null);
	}
	
	public void testDivideBad() {
		_testDivide(1, 7, 0, 8, E_DIV_ZERO);
		_testDivide(-1, 7, 0, 8, E_DIV_ZERO);
		_testDivide(1, -7, 0, 8, E_DIV_ZERO);
		_testDivide(1, 7, 0, 8, E_DIV_ZERO);
		_testDivide(1, 7, 0, -8, E_DIV_ZERO);
		_testDivide(-1, 7, 0, -8, E_DIV_ZERO);
		_testDivide(1, -7, 0, 8, E_DIV_ZERO);
		_testDivide(-1, -7, 0, -8, E_DIV_ZERO);
	}
	
	private void _test(String[] values, String[] result) {
		final String input = String.join(" ", values);
		
		final String output = TestSuite.stringOutput(new String[] {
			String.format("== F1 ==%n"),
			"Enter numerator: ",
			"Enter denominator: ",
			String.format("== F2 ==%n"),
			"Enter numerator: ",
			"Enter denominator: ",
			String.format("%n"),
			"%s" }, new String[] {TestSuite.stringOutput(result, new Object[] {})});
		
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(outContent));
		
		try {
			PA5a.main(new String[] { "foo" });
		} catch (ExitException e) {}
		assertEquals(output, outContent.toString());
		
		System.setIn(null);
		System.setOut(null);
	}
	
	private void _testGood(String[] values, String f1, String f2, 
			String f1Pf1, String f2Pf2, String f1Pf2, String f2Pf1,
			String f1Mf1, String f2Mf2, String f1Mf2, String f2Mf1,
			String f1Tf1, String f2Tf2, String f1Tf2, String f2Tf1,
			String f1Df1, String f2Df2, String f1Df2, String f2Df1,
			String f1Tf1Mf2) {
		_test(values, new String[] {
			String.format("F1: %s%n", f1),
			String.format("F2: %s%n", f2),
			String.format("F1+F1: %s%n", f1Pf1),
			String.format("F2+F2: %s%n", f2Pf2),
			String.format("F1+F2: %s%n", f1Pf2),
			String.format("F2+F1: %s%n", f2Pf1),
			String.format("F1-F1: %s%n", f1Mf1),
			String.format("F2-F2: %s%n", f2Mf2),
			String.format("F1-F2: %s%n", f1Mf2),
			String.format("F2-F1: %s%n", f2Mf1),
			String.format("F1*F1: %s%n", f1Tf1),
			String.format("F2*F2: %s%n", f2Tf2),
			String.format("F1*F2: %s%n", f1Tf2),
			String.format("F2*F1: %s%n", f2Tf1),
			String.format("F1/F1: %s%n", f1Df1),
			String.format("F2/F2: %s%n", f2Df2),
			String.format("F1/F2: %s%n", f1Df2),
			String.format("F2/F1: %s%n", f2Df1),
			String.format("F1*F1-F2: %s%n", f1Tf1Mf2),
		});
	}
	
	public void testProg() {
		_testGood(
			new String[] { "1", "2", "3", "5" },
			"1/2 (0.500)",
			"3/5 (0.600)",
			"1",
			"6/5 (1.200)",
			"11/10 (1.100)",
			"11/10 (1.100)",
			"0",
			"0",
			"-1/10 (-0.100)",
			"1/10 (0.100)",
			"1/4 (0.250)",
			"9/25 (0.360)",
			"3/10 (0.300)",
			"3/10 (0.300)",
			"1",
			"1",
			"5/6 (0.833)",
			"6/5 (1.200)",
			"-7/20 (-0.350)"
		);
		
		_testGood(
			new String[] { "1", "2", "6", "-10" },
			"1/2 (0.500)",
			"-3/5 (-0.600)",
			"1",
			"-6/5 (-1.200)",
			"-1/10 (-0.100)",
			"-1/10 (-0.100)",
			"0",
			"0",
			"11/10 (1.100)",
			"-11/10 (-1.100)",
			"1/4 (0.250)",
			"9/25 (0.360)",
			"-3/10 (-0.300)",
			"-3/10 (-0.300)",
			"1",
			"1",
			"-5/6 (-0.833)",
			"-6/5 (-1.200)",
			"17/20 (0.850)"
		);
	}

}
