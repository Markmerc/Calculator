package calculator;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Represents a binary operation (add, subtract, divide, multiply) button on a
 * calculator.
 * 
 * @author Mark Mercurio
 * @version 11/21/13
 */
public class BinaryOperationButton extends CalculatorButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4890629030407914959L;
	Color pressedColor;

	/**
	 * Sets the GUI color and size attributes of this button, and links this
	 * button to a calculator.
	 * @param label - the text on the button ex "+"
	 * @param name- the name of the button ex: "add"
	 * @param calculatorChip - the chip the button is associated with
	 */
	BinaryOperationButton(String label, String name,
			CalculatorChip calculatorChip) {
		super(calculatorChip);
		this.setText(label);
		this.setName(name);
		pressedColor = new Color(240, 128, 128);
		hoverColor = new Color(238, 238, 0);
		defaultColor = new Color(154, 255, 154);
		this.setBackground(defaultColor);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(50, 50));
	}

	/**
	 * Deselects this button, and changes the background color to default
	 */
	void setSelectedFalse() {
		this.setSelected(false);
		this.setBackground(defaultColor);
	}
	/**
	 * Selects this button, and changes the background color to appropriate color
	 */
	void setSelectedTrue() {
		this.setSelected(true);
		this.setBackground(pressedColor);
	}

}
