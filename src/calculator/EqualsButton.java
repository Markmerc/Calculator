package calculator;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Represents an equals button on a calculator.
 * 
 * @author Mark Mercurio
 * @version 11/21/13
 */
public class EqualsButton extends CalculatorButton {
	
	private static final long serialVersionUID = -6677203305528354520L;

	/**
	 * Sets the GUI color and size attributes of this button, and links this
	 * button to a calculator.
	 * @param label - the text on the button ex "="
	 * @param name- the name of the button ex: "equals"
	 * @param calculatorChip - the chip the button is associated with
	 */
	EqualsButton(String label, String name, CalculatorChip calculatorChip) {
		super(calculatorChip);
		this.setText(label);
		this.setName(name);
		hoverColor = new Color(238, 238, 0);
		defaultColor = new Color(154, 255, 154);
		this.setBackground(defaultColor);
		this.setOpaque(true);		
		this.setMinimumSize(new Dimension(42, 92));
		this.setMaximumSize(new Dimension(42, 92));
	}
}
