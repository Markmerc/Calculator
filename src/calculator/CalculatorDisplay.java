package calculator;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * Implements the features of a calculator Display field
 * @author Mark Mercurio
 * @version 11/21/13
 */
public class CalculatorDisplay extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8183477735234364905L;

	/**
	 * Sets the default GUI attributes of this CalculatorDisplay.
	 */
	CalculatorDisplay() {
		super(10);
		this.setOpaque(true);
		this.setFont(new Font("Arial", Font.PLAIN, 30));
		this.setText("0");
		this.setHorizontalAlignment(JTextField.RIGHT);
		this.setEditable(false);
		this.setBackground(new Color(224, 255, 255));
		this.setBorder(BorderFactory.createLineBorder(new Color(145, 145, 145), 2));

	}
}
