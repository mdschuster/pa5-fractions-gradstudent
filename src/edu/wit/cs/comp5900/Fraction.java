package edu.wit.cs.comp5900;

import java.util.Scanner;

// TODO: document this class
public class Fraction {
	
	/**
	 * Error to output if denominator is zero
	 */
	private static final String E_DEN_ZERO = "Denominator cannot be zero.";
	
	/**
	 * Error to output if dividing by zero
	 */
	private static final String E_DIV_ZERO = "Cannot divide by zero.";
	
	/**
	 * Returns the greatest common divisor (gcd) of two integers
	 * 
	 * @param num1 integer 1
	 * @param num2 integer 2
	 * @return gcd of integers 1 and 2
	 */
	private static int gcd(int num1, int num2) {
		int t;
		while (num2 != 0) {
			t = num2;
			num2 = num1 % num2;
			num1 = t;
		}
		
		return num1;
	}
	
	/**
	 * Returns the simplified form of a fraction
	 * 
	 * @param f fraction (numerator=[0], denominator=[1])
	 * @return simplified fraction (numerator=[0], denominator=[1])
	 */
	private static int[] simplifyFraction(int[] f) {
		final int gcd = gcd(f[0], f[1]);
		int[] result = {f[0]/gcd, f[1]/gcd};
		
		if ((result[0]<0 && result[1]<0) || (result[1]<0)) {
			result[0] = -result[0];
			result[1] = -result[1];
		}
		
		return result;
	}
	
	/**
	 * Constructs a fraction given a numerator/denominator
	 * 
	 * If denominator = 0, exit with message
	 * 
	 * @param n numerator
	 * @param d denominator
	 */
	public Fraction(int n, int d) {
		// TODO: write your code here
	}
	
	/**
	 * Constructs a fraction as 0/1
	 */
	public Fraction() {
		// TODO: write your code here
	}
	
	/**
	 * Constructs a fraction given input from a supplied Scanner
	 * 
	 * If denominator = 0, exit with message
	 * 
	 * @param s scanner from which to read fraction components
	 */
	public Fraction(Scanner s) {
		// TODO: write your code here
	}
	
	/**
	 * Gets the decimal value of a fraction
	 * 
	 * @return decimal version
	 */
	public double toDecimal() {
		return 0; // TODO: replace with your code
	}
	
	/**
	 * Returns the string value of a fraction:
	 * - if denominator is 1, "numerator"
	 * - else, "numerator/denominator (decimal with three decimal places)"
	 * 
	 * @return string formatted fraction
	 */
	public String toString() {
		return ""; // TODO: replace with your code
	}
	
	/**
	 * Gets the fraction numerator
	 * 
	 * @return numerator
	 */
	public int getNumerator() {
		return 0; // TODO: replace with your code
	}
	
	/**
	 * Gets the fraction denominator
	 * 
	 * @return denominator
	 */
	public int getDenominator() {
		return 0; // TODO: replace with your code
	}
	
	/**
	 * Adds a fraction to this fraction
	 * 
	 * @param f fraction to add
	 * @return a new fraction that sums this and the supplied parameter
	 */
	public Fraction plus(Fraction f) {
		return new Fraction(); // TODO: replace with your code
	}
	
	/**
	 * Subtracts a fraction from this fraction
	 * 
	 * @param f fraction to subtract
	 * @return a new fraction that results from subtracting the supplied parameter from this
	 */
	public Fraction minus(Fraction f) {
		return new Fraction(); // TODO: replace with your code
	}
	
	/**
	 * Multiplies a fraction with this fraction
	 * 
	 * @param f fraction to multiply
	 * @return a new fraction that is the product of this and the supplied parameter
	 */
	public Fraction times(Fraction f) {
		return new Fraction(); // TODO: replace with your code
	}
	
	/**
	 * Divides a fraction into this fraction
	 * 
	 * @param f fraction with which to divide
	 * @return a new fraction that is the quotient of this and the supplied parameter
	 */
	public Fraction divides(Fraction f) {
		return new Fraction(); // TODO: replace with your code
	}

}
