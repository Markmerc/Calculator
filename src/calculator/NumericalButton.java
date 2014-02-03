package calculator;

import java.awt.*;


/**
 * Represents a number button on a calculator (ex: 1, 2, 3, .)
 * 
 * @author Mark Mercurio
 * @version 11/21/13
 */
public class NumericalButton extends CalculatorButton {

	private static final long serialVersionUID = -6526323193930396243L;

	/**
	 * Sets the GUI color and size attributes of this button, and links this
	 * button to a calculator.
	 * @param label - the text on the button ex "1"
	 * @param calculatorChip - the chip the button is associated with
	 */
	NumericalButton(String label, CalculatorChip calculatorChip) {
		super(calculatorChip);
		this.setText(label);
		defaultColor = new Color(198, 226, 255);
		hoverColor = new Color(238, 238, 0);
		this.setBackground(defaultColor);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(50, 50));
	}
}
