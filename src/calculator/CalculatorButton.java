package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;

/**
 * Implements general features of a calculator button
 * @author Mark Mercurio
 * @version 11/21/13
 */
abstract class CalculatorButton extends JButton {


	private static final long serialVersionUID = -2451744495793437797L;

	CalculatorChip calculatorChip;
	Color hoverColor;
	Color defaultColor;
	
	/**
	 * Sets the default GUI Font and size for calculator button.
	 * @param calculatorChip
	 */
	CalculatorButton(CalculatorChip calculatorChip) {
		this.calculatorChip = calculatorChip;
		this.setFont(new Font("Ariel",Font.BOLD, 20));
		this.setMinimumSize(new Dimension(42, 42));
		this.setMaximumSize(new Dimension(42, 42));
		this.setMargin(new Insets(0,0,0,0));
	}	
	
	/**
	 * sets the background color for hovering
	 */
	void setHoverBackground() {
		this.setBackground(hoverColor);
	}
	
	/**
	 * sets the default background color
	 */	
	void setDefaultBackground() {
		this.setBackground(defaultColor);
	}
}
