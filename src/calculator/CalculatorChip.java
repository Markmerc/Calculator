package calculator;

import java.lang.Math;

/**
 * Implements the internal workings of a 7 function calculator chip.
 * @author Mark Mercurio
 * @version 11/21/13
 */
public class CalculatorChip {

	//The Three basic "registers" of the calculator
	private double xOperand;
	private double yOperand;
	private String operationFlag;

	//registers to hold data from previous operations
	private double previousX;
	private String previousFlag;
	
	//memory storage
	private double memory;
	
	//flags representing current state of calculator.
	private boolean justCleared;
	private boolean justFlagged;
	private boolean overWriteDisplay;

	//Currently sent to the display
	private String display;

	/**
	 * Sets the default values of the calculator chip.
	 */
	CalculatorChip() {
		justCleared = false;
		display = "0";
		memory = 0;
		xOperand = 0;
		yOperand = 0;
		previousX = 0;
		operationFlag = "nothing";
		previousFlag = "nothing";
		justFlagged = false;
		overWriteDisplay = true;
	}

	/**
	 * Returns the result of yOperand operationFlag xOperation
	 * depending on the state of the machine. Further alters flag values
	 * as appropriate. 
	 * @return value to be displayed.
	 */
	String equals() {
		overWriteDisplay = true;		
		justFlagged = false;

		if (operationFlag.equals("nothing"))
			return equalsWithNoOperationFlag();
		else 
			return equalsWithOperationFlag();
	}

	/**
	 * Returns the result of yOperand operationFlag xOperation
	 * @return value to be displayed
	 */
	private String equalsWithOperationFlag() {
		justCleared = false;
		previousX = xOperand;
		previousFlag = operationFlag;
		xOperand = performBinaryFlag(operationFlag);
		operationFlag = "nothing";
		display = doubleToString(xOperand);
		return display;
	}

	/**
	 * Returns a value given operationFlag is "nothing".
	 * @return value to be displayed
	 */
	private String equalsWithNoOperationFlag() {
		if(previousFlag.equals("nothing") && justCleared) {
			xOperand = 0;
			display = doubleToString(xOperand);
			justCleared = false;
			return display;			
		}
		
		justCleared = false;
		//special sqrt case
		if (previousFlag.equals("sqrt")) {
			xOperand = performUnaryOperation("sqrt");
		}
		//binary operation in previousFlag
		else if(!previousFlag.equals("nothing")){
			yOperand = xOperand;
			xOperand = previousX;
			xOperand = performBinaryFlag(previousFlag);
		}

		display = doubleToString(xOperand);
		return display;
	}

	/**
	 * Changes the operationFlag to the given operation, and performs the operation,
	 * if it is acting as an equals, add.
	 * Example: 5 + 5 +, the final plus should perform the flag, and preps for next number.
	 * @param the binary operation to perform
	 * @return value to be displayed
	 */
	private String binaryOperation(String operation) {
		if (justCleared) {
			xOperand = 0;
			justCleared = false;
		}

		if (justFlagged) {
			operationFlag = operation;
			return display;
		}

		if (!operationFlag.equals("nothing"))
			xOperand = performBinaryFlag(operationFlag);

		chipStateGivenBinaryOperation();
		operationFlag = operation;
		return display;
	}
	
	/**
	 * Alters the flags of the chip to represent that a
	 * binary operator was selected.
	 */
	private void chipStateGivenBinaryOperation() {
		yOperand = xOperand;
		overWriteDisplay = true;
		justFlagged = true;
		display = doubleToString(xOperand);
	}

	/**
	 * Performs the binary operation given on the yOperand and xOperand.
	 * @param the operation to perform.
	 * @return the result of the given operation.
	 */
	private double performBinaryFlag(String operation) {
		if (operation.equals("add"))
			return yOperand + xOperand;

		else if (operation.equals("multiply"))
			return yOperand * xOperand;

		else if (operation.equals("subtract")) {
			return yOperand - xOperand;

		}
		else if (operation.equals("divide")) {
			return yOperand / xOperand;
		}
		else
			return xOperand;
	}

	/**
	 * Performs the operations associated with pressing the add button.
	 * @return value to be displayed.
	 */
	String add() {
		return binaryOperation("add");
	}
	
	/**
	 * Performs the operations associated with pressing the subtract button.
	 * @return value to be displayed.
	 */
	String subtract() {
		return binaryOperation("subtract");
	}

	/**
	 * Performs the operations associated with pressing the multiply button.
	 * @return value to be displayed.
	 */
	String multiply() {
		return binaryOperation("multiply");
	}

	/**
	 * Performs the operations associated with pressing the divide button.
	 * @return value to be displayed.
	 */
	String divide() {
		return binaryOperation("divide");
	}

	/**
	 * Alters the flags representing the state of this CalculatorChip and
	 * returns the appropriate value to be displayed.
	 * @param the unary operation to perform
	 * @return value to be displayed
	 */
	private String unaryOperation(String operation) {
		justFlagged = false;
		if (operation.equals("changeSign")) 
			overWriteDisplay = false;
		else 
			overWriteDisplay = true;
		
		if (justCleared) {
			xOperand = 0;
			justCleared = false;
		}
		xOperand = performUnaryOperation(operation);
		if (operation.equals("sqrt") && operationFlag.equals("nothing")) {
			previousFlag = "sqrt";
			operationFlag = "nothing";
		}
		else if (operationFlag.equals("nothing")) {
			previousFlag = "nothing";
		}
		display = doubleToString(xOperand);
		return display;
	}

	/**
	 * Performs the <b>unary<b> operation given on the xOperand.
	 * @param the operation to perform.
	 * @return the result of the given operation.
	 */
	private double performUnaryOperation(String operation) {
		if (operation.equals("invert"))
			//if infinity, return infinity not 0;
			if(Double.isInfinite(xOperand))
				return xOperand;
			else
				return 1 / xOperand;

		else if (operation.equals("sqrt"))
			return Math.sqrt(xOperand);

		else if (operation.equals("changeSign"))
			return (-1) * xOperand;

		else if (operation.equals("percent")) {
			if (operationFlag.equals("nothing"))
				return xOperand / 100;
			else
				return (xOperand / 100) * yOperand;
		}
		else
			return xOperand;
	}

	/**
	 * Performs the operations associated with pressing the sqrt button.
	 * @return value to be displayed.
	 */
	String sqrt() {
		return unaryOperation("sqrt");
	}

	/**
	 * Performs the operations associated with pressing the percent button.
	 * @return value to be displayed.
	 */
	String percent() {
		return unaryOperation("percent");
	}

	/**
	 * Performs the operations associated with pressing the invert button.
	 * @return value to be displayed.
	 */
	String invert() {
		return unaryOperation("invert");
	}

	/**
	 * Performs the operations associated with pressing the changeSign button.
	 * @return value to be displayed.
	 */
	String changeSign() {
		return unaryOperation("changeSign");
	}

	/**
	 * Performs the operations associated with pressing the memClear button.
	 * @return value to be displayed.
	 */
	String memClear() {
		memory = 0;
		return display;
	}

	/**
	 * Performs the operations associated with pressing the memAdd button.
	 * @return value to be displayed.
	 */
	String memAdd() {
		if (justCleared) {
			memory += 0;
		}
		else {
			memory += xOperand;
		}
		overWriteDisplay = true;
		return display;
	}

	/**
	 * Performs the operations associated with pressing the memSubtract button.
	 * @return value to be displayed.
	 */
	String memSubtract() {
		if (justCleared) {
			memory -= 0;
		}
		else {
			memory -= xOperand;
		}
		overWriteDisplay = true;
		return display;
	}
	
	/**
	 * Performs the operations associated with pressing the memReadbutton.
	 * @return value to be displayed.
	 */
	String memRead() {
		xOperand = memory;
		if (!justFlagged) {
			previousX = 0;
			operationFlag = "nothing";
			yOperand = 0;
		}
		justCleared = false;
		previousFlag = "nothing";
		display = doubleToString(xOperand);
		return display;
	}

	/**
	 * Appends the given digits to least significant location of the display value.
	 * @return value to be displayed.
	 */
	String digit(int digit) {
		if (overWriteDisplay) {
			display = "0";
		}
		overWriteDisplay = false;
		if (isCurrentEntryZero()) {
			display = "" + digit;
		}
		else {
			display += digit;
		}

		if (justCleared) {
			justCleared = false;
		}
		previousFlag = "nothing";
		justFlagged = false;
		xOperand = parseDouble(display);
		return display;
	}

	/**
	 * Appends a decimal to least significant location of the display value,
	 * given there currently is no decimal.
	 * @return value to be displayed.
	 */
	String decimalPoint() {
		if (overWriteDisplay) {
			display = "0";
		}
		overWriteDisplay = false;
		if (!display.contains(".")) {
			display += ".";
		}
		if (justCleared) {
			justCleared = false;
		}
		previousFlag = "nothing";
		justFlagged = false;
		xOperand = parseDouble(display);
		return display;
	}

	/**
	 * Convert a string to a Double.
	 * @return value to be displayed.
	 */
	private Double parseDouble(String s) {
		if (s.endsWith(".")) {
			return Double.parseDouble(s + "0");
		}
		return Double.parseDouble(s);
	}

	/**
	 * Convert a double to a String representation, with no trailing 0s.
	 * If the double is infinite or NaN the string is "Error"
	 * @return value to be displayed.
	 */
	private String doubleToString(double x) {
		if (Double.isInfinite(x) || Double.isNaN(x)) {
			return "Error";
		}
		if (x % 1 == 0) {
			return Long.toString((long) x);
		}
		else {
			return Double.toString(x);
		}
	}

	/**
	 * Clears the display, and performs an allClear if just cleared.
	 * @return value to Display
	 */
	String clear() {
		if (!justCleared) {
			//allClear = true;
			display = "0";
			justCleared = true;
			overWriteDisplay = true;
			return display;
		}
		else {
			return performAllClear();
		}
	}

	/**
	 * Sets this CalculatorChips flags back to defaults.
	 * @return value to Display
	 */
	private String performAllClear() {
		xOperand = 0;
		yOperand = 0;
		previousX = 0;
		operationFlag = "nothing";
		previousFlag = "nothing";
		justFlagged = false;
		overWriteDisplay = true;
		display = doubleToString(xOperand);
		return display;
	}

	/**
	 * Determines if the given display is 0
	 */
	private boolean isCurrentEntryZero() {
		try {
			if (Integer.parseInt(display) == 0) {
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}



	/**
	 * Returns the current operation.
	 */
	String getCurrentFlag() {
		return operationFlag;
	}
}
