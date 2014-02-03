package calculator;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Represents a unary operation button on a calculator (ex: "1/x")
 * 
 * @author Mark Mercurio
 * @version 11/21/13
 */
public class UnaryOperationButton extends CalculatorButton {

	private static final long serialVersionUID = 7791875038057467305L;

	/**
	 * Sets the GUI color and size attributes of this button, and links this
	 * button to a calculator.
	 * @param label - the text on the button ex "1/x"
	 * @param name - the name of the operation ex: "invert";
	 * @param calculatorChip - the chip the button is associated with
	 */
	UnaryOperationButton(String label, String name, CalculatorChip calculatorChip) {
		super(calculatorChip);
		this.setText(label);
		this.setName(name);
		hoverColor = new Color(238, 238, 0);
		defaultColor = new Color(154, 255, 154);
		this.setBackground(defaultColor);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(50, 50));
	}
}
