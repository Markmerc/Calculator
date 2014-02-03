package calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorChipTest {

	CalculatorChip chip;

	@Before
	public void setUp() throws Exception {
		chip = new CalculatorChip();
	}

	@Test
	public void testDigit() {
		assertTrue(chip.digit(1).equals("1"));
		assertTrue(chip.digit(1).equals("11"));
		assertTrue(chip.digit(3).equals("113"));
		assertTrue(chip.digit(0).equals("1130"));
	}

	@Test
	public void testDecimalPoint() {
		assertTrue(chip.digit(1).equals("1"));
		assertTrue(chip.digit(1).equals("11"));
		assertTrue(chip.decimalPoint().equals("11."));
		assertTrue(chip.digit(0).equals("11.0"));
		assertTrue(chip.digit(0).equals("11.00"));
		assertTrue(chip.decimalPoint().equals("11.00"));

		chip.clear();
		chip.clear();

		assertTrue(chip.decimalPoint().equals("0."));
	}

	@Test
	public void testClear() {
		chip.digit(1);
		chip.digit(1);
		chip.digit(1);
		chip.digit(1);
		chip.add();
		assertTrue(chip.clear().equals("0"));
		assertTrue(chip.getCurrentFlag().equals("add"));
		assertTrue(chip.clear().equals("0"));
		assertTrue(chip.clear().equals("0"));
		assertTrue(chip.getCurrentFlag().equals("nothing"));
	}

	@Test
	public void testAdd() {
		chip.digit(1);
		assertTrue(chip.add().equals("1"));
		assertTrue(chip.getCurrentFlag().equals("add"));
		assertTrue(chip.add().equals("1"));
		chip.digit(2);
		assertTrue(chip.add().equals("3"));
	}

	@Test
	public void testSubtract() {
		chip.digit(1);
		assertTrue(chip.subtract().equals("1"));
		assertTrue(chip.getCurrentFlag().equals("subtract"));
		assertTrue(chip.subtract().equals("1"));
		chip.digit(2);
		assertTrue(chip.subtract().equals("-1"));
		chip.equals();
		chip.add();
		assertTrue(chip.subtract().equals("0"));
		assertTrue(chip.getCurrentFlag().equals("subtract"));
	}

	@Test
	public void testMultiply() {
		chip.digit(1);
		assertTrue(chip.multiply().equals("1"));
		assertTrue(chip.getCurrentFlag().equals("multiply"));
		assertTrue(chip.multiply().equals("1"));
		chip.digit(2);
		assertTrue(chip.multiply().equals("2"));
		chip.equals();
		chip.add();
		assertTrue(chip.multiply().equals("4"));
		assertTrue(chip.getCurrentFlag().equals("multiply"));
	}

	@Test
	public void testDivide() {
		chip.digit(6);
		assertTrue(chip.divide().equals("6"));
		assertTrue(chip.getCurrentFlag().equals("divide"));
		assertTrue(chip.divide().equals("6"));
		chip.digit(2);
		assertTrue(chip.divide().equals("3"));
		chip.equals();
		chip.add();
		assertTrue(chip.divide().equals("1"));
		assertTrue(chip.getCurrentFlag().equals("divide"));
		chip.divide();
		chip.digit(0);
		assertTrue(chip.equals().equals("Error"));
	}

	@Test
	public void testSqrt() {
		chip.digit(10000);

		assertTrue(chip.sqrt().equals("100"));
		assertTrue(chip.sqrt().equals("10"));

		chip.digit(15);
		chip.add();
		chip.digit(100);
		chip.sqrt();
		assertTrue(chip.equals().equals("25"));
	}

	@Test
	public void testPercent() {
		chip.digit(50);

		assertTrue(chip.percent().equals("0.5"));
		assertTrue(chip.percent().equals("0.005"));

		chip.digit(60);
		chip.add();
		chip.digit(25);
		chip.percent();
		assertTrue(chip.equals().equals("75"));
	}

	@Test
	public void testInvert() {
		chip.digit(10);

		assertTrue(chip.invert().equals("0.1"));
		assertTrue(chip.invert().equals("10"));

		chip.digit(60);
		chip.add();
		chip.digit(10);
		chip.invert();
		assertTrue(chip.equals().equals("60.1"));
	}

	@Test
	public void testChangeSign() {
		chip.digit(10);

		assertTrue(chip.changeSign().equals("-10"));
		assertTrue(chip.changeSign().equals("10"));
		
		chip.clear();
		chip.digit(60);
		chip.add();
		chip.digit(10);
		chip.changeSign();
		assertTrue(chip.equals().equals("50"));
	}

	@Test
	public void testEquals() {
		chip.digit(100);
		chip.add();
		chip.digit(10);
		assertTrue(chip.equals().equals("110"));
		assertTrue(chip.equals().equals("120"));
		assertTrue(chip.equals().equals("130"));
		chip.clear();
		assertTrue(chip.equals().equals("140"));
		chip.clear();
		chip.clear();
		assertTrue(chip.equals().equals("0"));
		
		chip.digit(100000000);
		chip.sqrt();
		assertTrue(chip.equals().equals("100"));
		chip.clear();
		assertTrue(chip.equals().equals("10"));

		chip.digit(100);
		chip.invert();
		assertTrue(chip.equals().equals("0.01"));
		
		chip.digit(100);
		chip.percent();
		assertTrue(chip.equals().equals("1"));
		
		chip.digit(100);
		chip.changeSign();
		assertTrue(chip.equals().equals("-100"));

	}

	@Test
	public void testMemAdd() {
		chip.digit(100);
		assertTrue(chip.memAdd().equals("100"));
		chip.add();
		chip.digit(50);
		assertTrue(chip.memAdd().equals("50"));
		assertTrue(chip.equals().equals("150"));
		chip.digit(10);
		chip.memAdd();
		assertTrue(chip.memRead().equals("160"));


	}

	@Test
	public void testMemSubtract() {
		chip.digit(100);
		assertTrue(chip.memSubtract().equals("100"));
		chip.add();
		chip.digit(50);
		assertTrue(chip.memSubtract().equals("50"));
		assertTrue(chip.equals().equals("150"));
		chip.digit(10);
		chip.memSubtract();
		assertTrue(chip.memRead().equals("-160"));
	}

	@Test
	public void testMemRead() {
		chip.digit(100);
		chip.memAdd();
		chip.add();
		chip.digit(10);
		chip.memSubtract();
		assertTrue(chip.memRead().equals("90"));
		chip.add();
		chip.digit(10);
		assertTrue(chip.equals().equals("100"));
		chip.add();
		chip.memRead();
		assertTrue(chip.equals().equals("190"));
	}
	@Test
	public void testMemClear() {
		chip.digit(100);
		chip.memAdd();
		chip.clear();
		assertTrue(chip.memRead().equals("100"));
		assertTrue(chip.memClear().equals("100"));
		assertTrue(chip.memRead().equals("0"));
		
	}

	@Test
	public void testGetCurrentFlag() {
		chip.add();
		assertTrue(chip.getCurrentFlag().equals("add"));
		chip.subtract();
		assertTrue(chip.getCurrentFlag().equals("subtract"));

	}

}
