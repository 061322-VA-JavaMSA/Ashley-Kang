package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.DivideBy0;
import com.revature.exceptions.Illegal13;


public class CalcTest {
	private static Calculator sut;
	
	@BeforeAll
	public static void setUp() {
		sut = new Calculator();
	}
	
	@Test
	public void addOneAndTwo() {
		int expected = 3;
		int actual = sut.add(1, 2);
		assertEquals(expected, actual);
	}
	
	@Test
	public void add54And93476() {
		int expected = 93530;
		int actual = sut.add(54, 93476);
		assertEquals(expected, actual);
	}
	
	@Test
	public void addNegatives() {
		int expected = -14;
		int actual = sut.add(0, -14);
		assertEquals(expected, actual);
	}
	
	@Test
	public void addNegatives2() {
		int expected = -28;
		int actual = sut.add(-14, -14);
		assertEquals(expected, actual);
	}
	
	@Test
	public void subtractOneand4() {
		int expected = -3;
		int actual = sut.subtract(1, 4);
		assertEquals(expected, actual);
	}
	
	@Test
	public void subtractNegatives() {
		int expected = -10;
		int actual = sut.subtract(-5, 5);
		assertEquals(expected, actual);
	}
	
	@Test
	public void subtractNegatives2() {
		int expected = 0;
		int actual = sut.subtract(-5, -5);
		assertEquals(expected, actual);
	}
	
	@Test
	public void multiplyPowers() {
		int expected = 25;
		int actual = sut.multiply(5, 5);
		assertEquals(expected, actual);
	}
	
	@Test
	public void multiplyPowersNegatives() {
		int expected = 25;
		int actual = sut.multiply(-5, -5);
		assertEquals(expected, actual);
	}
	
	@Test
	public void multiplyPowersNegatives2() {
		int expected = -25;
		int actual = sut.multiply(-5, 5);
		assertEquals(expected, actual);
	}
	
	@Test
	public void dividePowers() {
		int expected = 6;
		int actual = sut.divide(36, 6);
		assertEquals(expected, actual);
	}
	
	@Test
	public void divideNegatives() {
		int expected = -6;
		int actual = sut.divide(-36, 6);
		assertEquals(expected, actual);
	}
	
	@Test
	public void sumArryofPos() {
		int[] arr1 = {10,10,10};
		int expected = 30;
		int actual = sut.sumArray(arr1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void sumArryofNegs() {
		int[] arr1 = {-10,-10,-10};
		int expected = -30;
		int actual = sut.sumArray(arr1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void sumArryofBoth() {
		int[] arr1 = {10,-10,-10};
		int expected = -10;
		int actual = sut.sumArray(arr1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void sumTo13() {
		int[] arr1 = {13,0,0,1,-1,-13,13};
		assertThrows(Illegal13.class, () -> sut.sumArray(arr1));
	}
	
	@Test
	public void divideTo13() {
		assertThrows(Illegal13.class, () -> sut.divide(65, 5));
	}
	
	@Test
	public void addZeroAndThirteen() {
		assertThrows(Illegal13.class, () -> sut.add(0, 13));
	}
	
	@Test
	public void addThirteenAndZero() {
		assertThrows(Illegal13.class, () -> sut.add(13, 0));
	}
	
	@Test
	public void addTwelveAndOne() {
		assertThrows(Illegal13.class, () -> sut.add(12, 1));
	}
	
	@Test
	public void divideByZero() {
		assertThrows(DivideBy0.class, () -> sut.divide(1, 0));
	}
	
	@Test
	public void divideByZero1() {
		assertThrows(DivideBy0.class, () -> sut.divide(1990, 0));
	}
	
	@Test
	public void divideByZero2() {
		assertThrows(DivideBy0.class, () -> sut.divide(45087, 0));
	}
	
	@Test
	public void divideByZero3() {
		assertThrows(DivideBy0.class, () -> sut.divide(9999999, 0));
	}

	
}
