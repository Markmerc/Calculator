package calculator;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Represents a memory button on a calculator (ex: memRead, memAdd)
 * 
 * @author Mark Mercurio
 * @version 11/21/13
 */
public class MemoryButton extends CalculatorButton {

	private static final long serialVersionUID = -8180260125924846601L;
	Color pressedColor;
	
	/**
	 * Sets the GUI color and size attributes of this button, and links this
	 * button to a calculator.
	 * @param label - the text on the button ex "M+"
	 * @param calculatorChip - the chip the button is associated with
	 */
	MemoryButton(String label, CalculatorChip calculatorChip) {
        super(calculatorChip);
		this.setText(label);
		hoverColor = new Color(238, 238, 0);
		defaultColor = new Color(238, 238, 0);
		pressedColor = new Color(240, 128, 128);
		this.setBackground(defaultColor);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(50, 50));
	}
	
	/**
	 * Deselects this button, and restores default background color
	 */
	void setSelectedFalse() {
		this.setSelected(false);
		this.setBackground(defaultColor);		
	}

	/**
	 * Selects this button, and changes the background color
	 */
	void setSelectedTrue() {
		this.setSelected(true);
		this.setBackground(pressedColor);
	}
}
