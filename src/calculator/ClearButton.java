package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Represents a clear button on a calculator.
 * 
 * @author Mark Mercurio
 * @version 11/21/13
 */
public class ClearButton extends CalculatorButton {

	private static final long serialVersionUID = 2030277009663737703L;

	boolean allClear;
	
	/**
	 * Sets the GUI color and size attributes of this button, and links this
	 * button to a calculator.
	 * @param label - the text on the button ex "clear"
	 * @param name- the name of the button ex: "clear"
	 * @param calculatorChip - the chip the button is associated with
	 */
	ClearButton(String label, String name, CalculatorChip calculatorChip) {
        super(calculatorChip);
		allClear = false;
		this.setText(label);
		hoverColor = new Color(238, 238, 0);
		defaultColor = new Color(238, 238, 0);
		this.setBackground(defaultColor);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(41, 21));
		this.setMinimumSize(new Dimension(41, 21));
		this.setMaximumSize(new Dimension(41, 21));
		this.setFont(new Font("Ariel", Font.BOLD, 12));
	}
	
	/**
	 * returns the allClear value
	 */
	boolean getAllClear() {
		return allClear;
	}
	/**
	 * sets the allClear value, and changes this button's text.
	 * If true, text is "A/C" else "clear".
	 * @param allClear
	 */
	void setAllClear(boolean allClear) {
		if(allClear) {
			this.setText("A/C");
			this.allClear = allClear;
		}
		else {
			this.setText("Clear");
			this.allClear = allClear;
		}
	}
}
