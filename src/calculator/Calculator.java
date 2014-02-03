package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * A GUI implementation of a calculator.
 * @author Mark Mercurio
 * @version 11/21/13
 */
public class Calculator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -446431802337175841L;

	/**
	 * Creates the calculator GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Calculator().createGUI();
	}

	// The calculator chip that will implement the functions of this calculator
	private CalculatorChip chip = new CalculatorChip();

	// ArrayLists of Buttons by Type (equals and clear are their own types)
	private ArrayList<BinaryOperationButton> binaryButtons;


	// Calculator Display
	private CalculatorDisplay display;

	// Number Buttons
	private NumericalButton num0;
	private NumericalButton num1;
	private NumericalButton num2;
	private NumericalButton num3;
	private NumericalButton num4;
	private NumericalButton num5;
	private NumericalButton num6;
	private NumericalButton num7;
	private NumericalButton num8;
	private NumericalButton num9;
	private NumericalButton decimal;

	// Binary Operation Buttons
	private BinaryOperationButton add;
	private BinaryOperationButton subtract;
	private BinaryOperationButton multiply;
	private BinaryOperationButton divide;

	// Unary Operation Buttons
	private UnaryOperationButton changeSign;
	private UnaryOperationButton sqrt;
	private UnaryOperationButton percent;
	private UnaryOperationButton invert;

	// Equals button
	private EqualsButton equals;

	// Clear/AC button
	private ClearButton clear;

	// Memory Buttons
	private MemoryButton memClear;
	private MemoryButton memRead;
	private MemoryButton memAdd;
	private MemoryButton memSubtract;

	// Background color of all the panels
	private Color backgroundColor = new Color(196, 196, 196);

	/**
	 * Creates the GUI
	 */
	private void createGUI() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Set attributes of the JFrame
		this.getContentPane().setPreferredSize(new Dimension(260, 350));
		this.setBackground(new Color(205, 104, 57));
		this.setMaximumSize(new Dimension(260, 374));
		this.setMinimumSize(new Dimension(260, 374));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new BorderLayout());

		createCalculatorButtons();
		JPanel buttonPanel = createButtonPanel();

		// Add two major components to JFrame
		this.add(createCalculatorDisplayPanel(), BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);

	}

	/**
	 * Creates all calculator buttons
	 */
	private void createCalculatorButtons() {
		binaryButtons = createBinaryOperationButtons();
		createNumberButtons();
		createUnaryOperationButtons();
		createCalculatorDisplay();
		createMemoryButtons();
		createEqualsButton();
		createClearButton();
	}

	/**
	 * Creates display for the calculator
	 */
	private void createCalculatorDisplay() {
		display = new CalculatorDisplay();
	}

	/**
	 * Create a JPanel with all the calculator buttons
	 * 
	 * @return
	 */
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setBackground(backgroundColor);
		buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
		buttonPanel.add(createColumnOnePanel());
		buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
		buttonPanel.add(createColumnTwoPanel());
		buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
		buttonPanel.add(createColumnThreePanel());
		buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
		buttonPanel.add(createColumnFourPanel());
		buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
		buttonPanel.add(createColumnFivePanel());
		buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));

		return buttonPanel;
	}

	/**
	 * Creates a panel with the calculator display
	 * 
	 * @return
	 */
	private JPanel createCalculatorDisplayPanel() {
		JPanel p1 = new JPanel();
		p1.setBackground(backgroundColor);
		p1.add(display);
		return p1;
	}

	/**
	 * Initializes the NumericalButtons of a calculator and adds Action and
	 * MouseListeners
	 * 
	 * @return the NumericalButtons
	 */
	private ArrayList<NumericalButton> createNumberButtons() {
		ArrayList<NumericalButton> temp = new ArrayList<NumericalButton>();
		NumberActionListener numListener = new NumberActionListener();

		num0 = new NumericalButton("0", chip);
		temp.add(num0);
		num1 = new NumericalButton("1", chip);
		temp.add(num1);
		num2 = new NumericalButton("2", chip);
		temp.add(num2);
		num3 = new NumericalButton("3", chip);
		temp.add(num3);
		num4 = new NumericalButton("4", chip);
		temp.add(num4);
		num5 = new NumericalButton("5", chip);
		temp.add(num5);
		num6 = new NumericalButton("6", chip);
		temp.add(num6);
		num7 = new NumericalButton("7", chip);
		temp.add(num7);
		num8 = new NumericalButton("8", chip);
		temp.add(num8);
		num9 = new NumericalButton("9", chip);
		temp.add(num9);
		decimal = new NumericalButton(".", chip);
		temp.add(decimal);

		// add action listener
		for (NumericalButton x : temp) {
			x.addActionListener(numListener);
			x.addMouseListener(new NumberButtonHoverListener());
		}

		return temp;
	}

	/**
	 * Initializes the UnaryOperations of a calculator and adds Action and
	 * MouseListeners
	 * 
	 * @return the UonaryOperationButtons
	 */
	private ArrayList<UnaryOperationButton> createUnaryOperationButtons() {
		ArrayList<UnaryOperationButton> temp = new ArrayList<UnaryOperationButton>();
		changeSign = new UnaryOperationButton("\u00B1", "changeSign", chip);
		temp.add(changeSign);
		percent = new UnaryOperationButton("%", "percent", chip);
		temp.add(percent);
		invert = new UnaryOperationButton("1/x", "invert", chip);
		temp.add(invert);
		sqrt = new UnaryOperationButton("\u221A", "sqrt", chip);
		temp.add(sqrt);
		for (UnaryOperationButton x : temp) {
			x.addActionListener(new UnaryOperationActionListener());
			x.addMouseListener(new UnaryButtonHoverListener());
		}
		return temp;
	}

	/**
	 * Initializes the clear button of a calculator and adds Action and
	 * MouseListeners
	 */
	private void createClearButton() {
		clear = new ClearButton("clear", "clear", chip);
		clear.addActionListener(new ClearActionListener());
		clear.addMouseListener(new ClearButtonHoverListener());
	}

	/**
	 * Initializes the equal button of a calculator and adds Action and
	 * MouseListeners
	 */
	private void createEqualsButton() {
		equals = new EqualsButton("=", "equals", chip);
		equals.addActionListener(new EqualsActionListener());
		equals.addMouseListener(new EqualsButtonHoverListener());
	}

	/**
	 * Initializes the BinaryOperationButtons of a calculator and adds Action
	 * and MouseListeners
	 * 
	 * @return the BinaryOperationButtons
	 */
	private ArrayList<BinaryOperationButton> createBinaryOperationButtons() {
		ArrayList<BinaryOperationButton> temp = new ArrayList<BinaryOperationButton>();
		add = new BinaryOperationButton("\u002B", "add", chip);
		temp.add(add);
		subtract = new BinaryOperationButton("\u2212", "subtract", chip);
		temp.add(subtract);
		multiply = new BinaryOperationButton("\u00D7", "multiply", chip);
		temp.add(multiply);
		divide = new BinaryOperationButton("\u00F7", "divide", chip);
		temp.add(divide);

		for (BinaryOperationButton x : temp) {
			x.addActionListener(new BinaryOperationActionListener());
			x.addMouseListener(new BinaryButtonHoverListener());
		}
		return temp;
	}

	/**
	 * Initializes the MemoryButtons of a calculator and adds Action and
	 * MouseListeners
	 * 
	 * @return the MemoryButtons
	 */
	private ArrayList<MemoryButton> createMemoryButtons() {
		ArrayList<MemoryButton> temp = new ArrayList<MemoryButton>();

		// memClear Button
		memClear = new MemoryButton("MC", chip);
		memClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				memRead.setSelectedFalse();
				display.setText(chip.memClear());
			}
		});
		memClear.addMouseListener(new MemoryButtonHoverListener());
		temp.add(memClear);

		// memRead Button
		memRead = new MemoryButton("MR", chip);
		memRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				clear.setAllClear(false);
				display.setText(chip.memRead());
			}
		});
		temp.add(memRead);

		// memAdd button
		memAdd = new MemoryButton("M+", chip);
		memAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				clear.setAllClear(false);
				memRead.setSelectedTrue();
				display.setText(chip.memAdd());
			}
		});
		memAdd.addMouseListener(new MemoryButtonHoverListener());
		temp.add(memAdd);

		// memSubtract button
		memSubtract = new MemoryButton("M-", chip);
		memSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				memRead.setSelectedTrue();
				display.setText(chip.memSubtract());
			}
		});
		memSubtract.addMouseListener(new MemoryButtonHoverListener());
		temp.add(memSubtract);

		return temp;
	}

	/**
	 * Creates JPanel with the first column of buttons on the calculator
	 */
	private JPanel createColumnOnePanel() {
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setBackground(backgroundColor);
		p1.add(Box.createRigidArea(new Dimension(0, 29)));
		p1.add(memClear);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num7);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num4);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num1);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num0);
		p1.add(Box.createRigidArea(new Dimension(0, 3)));
		return p1;
	}

	/**
	 * Creates JPanel with the second column of buttons on the calculator
	 */
	private JPanel createColumnTwoPanel() {
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setBackground(backgroundColor);
		p1.add(Box.createRigidArea(new Dimension(0, 29)));
		p1.add(memRead);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num8);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num5);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num2);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(decimal);
		p1.add(Box.createRigidArea(new Dimension(0, 3)));
		return p1;
	}

	/**
	 * Creates JPanel with the third column of buttons on the calculator
	 */
	private JPanel createColumnThreePanel() {
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setBackground(backgroundColor);
		p1.add(Box.createRigidArea(new Dimension(0, 29)));
		p1.add(memAdd);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num9);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num6);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(num3);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(changeSign);
		p1.add(Box.createRigidArea(new Dimension(0, 3)));
		return p1;
	}

	/**
	 * Creates JPanel with the fourth column of buttons on the calculator
	 */
	private JPanel createColumnFourPanel() {
		JPanel p1 = new JPanel();
		p1.setBackground(backgroundColor);
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.add(Box.createRigidArea(new Dimension(0, 29)));
		p1.add(memSubtract);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(divide);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(multiply);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(subtract);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(add);
		p1.add(Box.createRigidArea(new Dimension(0, 3)));
		return p1;
	}

	/**
	 * Creates JPanel with the fifth column of buttons on the calculator
	 */
	private JPanel createColumnFivePanel() {
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setBackground(backgroundColor);
		p1.add(clear);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(sqrt);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(percent);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(invert);
		p1.add(Box.createRigidArea(new Dimension(0, 8)));
		p1.add(equals);
		p1.add(Box.createRigidArea(new Dimension(0, 3)));
		return p1;
	}

	/**
	 * Sets the selected boolean for all Binary Buttons as false
	 */
	private void deSelectBinaryButtons() {
		for (BinaryOperationButton x : binaryButtons) {
			x.setSelectedFalse();
		}
	}

	/**
	 * sets the given BinaryButton's selected value to true
	 */
	private void selectBinaryButtonByOperation(String operation) {
		for (BinaryOperationButton x : binaryButtons) {
			if (x.getName().equals(operation)) {
				x.setSelectedTrue();
			}
		}
	}

	/**
	 * Implements the action Listener for a NumericalButton
	 */
	class NumberActionListener implements ActionListener {
		/**
		 * Informs the calculatorChip that the given button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			NumericalButton button = (NumericalButton) e.getSource();
			clear.setAllClear(false);
			deSelectBinaryButtons();

			if (button.getText() == ".") {
				display.setText(chip.decimalPoint());
			}
			else {
				display.setText(chip.digit(Integer.parseInt(button.getText())));
			}
		}
	}

	/**
	 * Implements the action Listener for a BinaryOperationButton
	 */
	class BinaryOperationActionListener implements ActionListener {
		/**
		 * Informs the calculatorChip that the given button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			BinaryOperationButton button = (BinaryOperationButton) e.getSource();
			clear.setAllClear(false);
			deSelectBinaryButtons();
			button.setSelectedTrue();

			switch (button.getName()) {
			case "add":
				display.setText(chip.add());
				break;
			case "multiply":
				display.setText(chip.multiply());
				break;
			case "subtract":
				display.setText(chip.subtract());
				break;
			case "divide":
				display.setText(chip.divide());
				break;
			}
		}
	}

	/**
	 * Implements the action Listener for a UnaryOperationButton
	 */
	class UnaryOperationActionListener implements ActionListener {
		/**
		 * Informs the calculatorChip that the given button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			UnaryOperationButton button = (UnaryOperationButton) e.getSource();

			switch (button.getName()) {
			case "invert":
				display.setText(chip.invert());
				break;
			case "changeSign":
				display.setText(chip.changeSign());
				break;
			case "percent":
				display.setText(chip.percent());
				break;
			case "sqrt":
				display.setText(chip.sqrt());
				break;
			}
			clear.setAllClear(false);
		}
	}

	/**
	 * Implements the action Listener for an EqualsActionListener
	 */
	class EqualsActionListener implements ActionListener {
		/**
		 * Informs the calculatorChip that the given button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			deSelectBinaryButtons();
			clear.setAllClear(false);
			display.setText(chip.equals());
		}
	}
	
	
	/**
	 * Implements the action Listener for an ClearActionListener
	 */
	class ClearActionListener implements ActionListener {
		/**
		 * Informs the calculatorChip that the given button has been pressed
		 */
		public void actionPerformed(ActionEvent e) {
			ClearButton button = (ClearButton) e.getSource();
			boolean justCleared = button.getAllClear();
			if (justCleared) {
				deSelectBinaryButtons();
			}
			else {
				String operation = chip.getCurrentFlag();
				selectBinaryButtonByOperation(operation);
				button.setAllClear(true);
			}

			display.setText(chip.clear());
		}
	}

	/**
	 * Implements the MouseListener for NumericalButtons
	 */
	class NumberButtonHoverListener implements MouseListener {
		
		/**
		 * change background color on hover
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			NumericalButton button = (NumericalButton) e.getSource();
			button.setHoverBackground();
		}

		/**
		 * change background color on exit
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			NumericalButton button = (NumericalButton) e.getSource();
			button.setDefaultBackground();
		}

		/**
		 * do nothing on click
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		/**
		 * do nothing press
		 */
		@Override
		public void mousePressed(MouseEvent e) {
		}

		/**
		 * do nothing on release
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}

	/**
	 * Implements the MouseListener for UnaryButtons
	 */
	class UnaryButtonHoverListener implements MouseListener {

		/**
		 * change background color on hover
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			UnaryOperationButton button = (UnaryOperationButton) e.getSource();
			button.setHoverBackground();
		}

		/**
		 * change background color on exit
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			UnaryOperationButton button = (UnaryOperationButton) e.getSource();
			button.setDefaultBackground();
		}

		/**
		 * do nothing on click
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		/**
		 * do nothing press
		 */
		@Override
		public void mousePressed(MouseEvent e) {
		}

		/**
		 * do nothing on release
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	/**
	 * Implements the MouseListener for NumericalButtons
	 */
	class MemoryButtonHoverListener implements MouseListener {

		/**
		 * change background color on hover
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			MemoryButton button = (MemoryButton) e.getSource();
			button.setHoverBackground();
		}

		/**
		 * change background color on exit
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			MemoryButton button = (MemoryButton) e.getSource();
			button.setDefaultBackground();
		}

		/**
		 * do nothing on click
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		/**
		 * do nothing press
		 */
		@Override
		public void mousePressed(MouseEvent e) {
		}

		/**
		 * do nothing on release
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	/**
	 * Implements the MouseListener for EqualButton
	 */
	class EqualsButtonHoverListener implements MouseListener {

		/**
		 * change background color on hover
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			EqualsButton button = (EqualsButton) e.getSource();
			button.setHoverBackground();
		}

		/**
		 * change background color on exit
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			EqualsButton button = (EqualsButton) e.getSource();
			button.setDefaultBackground();
		}

		/**
		 * do nothing on click
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		/**
		 * do nothing press
		 */
		@Override
		public void mousePressed(MouseEvent e) {
		}

		/**
		 * do nothing on release
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	/**
	 * Implements the MouseListener for ClearButton
	 */
	class ClearButtonHoverListener implements MouseListener {

		/**
		 * change background color on hover
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			ClearButton button = (ClearButton) e.getSource();
			button.setHoverBackground();
		}

		/**
		 * change background color on exit
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			ClearButton button = (ClearButton) e.getSource();
			button.setDefaultBackground();
		}

		/**
		 * do nothing on click
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		/**
		 * do nothing press
		 */
		@Override
		public void mousePressed(MouseEvent e) {
		}

		/**
		 * do nothing on release
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	/**
	 * Implements the MouseListener for BinaryOperationButtons
	 */
	class BinaryButtonHoverListener implements MouseListener {

		/**
		 * change background color on hover
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			BinaryOperationButton button = (BinaryOperationButton) e
					.getSource();
			if (!button.isSelected()) {
				button.setHoverBackground();
			}
		}

		/**
		 * change background color on exit
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			BinaryOperationButton button = (BinaryOperationButton) e
					.getSource();
			if (!button.isSelected()) {
				button.setDefaultBackground();
			}
		}

		/**
		 * do nothing on click
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		/**
		 * do nothing press
		 */
		@Override
		public void mousePressed(MouseEvent e) {
		}

		/**
		 * do nothing on release
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}