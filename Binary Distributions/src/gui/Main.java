package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import bp.Probability;

public class Main {

	private JFrame frmProbabilityCalculator;
	private JTextField txtFlips;
	private JTextField txtHeads;
	private JButton btnCalculate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmProbabilityCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProbabilityCalculator = new JFrame();
		frmProbabilityCalculator.setResizable(false);
		frmProbabilityCalculator.setTitle("Probability Calculator");
		frmProbabilityCalculator.setBounds(100, 100, 500, 275);
		frmProbabilityCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProbabilityCalculator.getContentPane().setLayout(null);

		JLabel lblFlips = new JLabel("Number of Flips:");
		lblFlips.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFlips.setBounds(78, 60, 153, 30);
		frmProbabilityCalculator.getContentPane().add(lblFlips);

		JLabel lblHeads = new JLabel("Number of Heads:");
		lblHeads.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHeads.setBounds(78, 120, 153, 30);
		frmProbabilityCalculator.getContentPane().add(lblHeads);

		txtFlips = new JTextField();
		txtFlips.addKeyListener(new textListener(txtFlips));
		txtFlips.setToolTipText("Please enter the number of flips here.");
		txtFlips.setColumns(10);
		txtFlips.setBounds(241, 60, 165, 25);
		frmProbabilityCalculator.getContentPane().add(txtFlips);

		txtHeads = new JTextField();
		txtHeads.addKeyListener(new textListener(txtHeads));
		txtHeads.setToolTipText("Please enter the number of heads here.");
		txtHeads.setColumns(10);
		txtHeads.setBounds(241, 124, 165, 25);
		frmProbabilityCalculator.getContentPane().add(txtHeads);

		btnCalculate = new JButton("Calculate");
		btnCalculate.setToolTipText("Calculate the probability");
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCalculate.setEnabled(false);
		btnCalculate.setBounds(182, 175, 120, 40);
		btnCalculate.addActionListener(new BtnCalculateActionListener());
		frmProbabilityCalculator.getContentPane().add(btnCalculate);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 500, 21);
		frmProbabilityCalculator.getContentPane().add(menuBar);

		JMenu Help = new JMenu("Help");
		menuBar.add(Help);

		JButton Instructions = new JButton("Instructions");
		Instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"This program calculates the probability of getting a defined number of heads from a defined number of flips."
										+ "\n Enter how many times you wish to toss the coin then enter how many times you want it to land on heads."
										+ "\n The number of heads must be less than the number of tosses thrown.");
			}
		});
		Help.add(Instructions);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new GlobalKeyListener());
		frmProbabilityCalculator.getRootPane().setDefaultButton(btnCalculate);
		setCalculteEnabled();
	}

	// event handlers
	void setCalculteEnabled() {
		// initiate counter to count number of values entered
		String failed = "";
		int valueEntered = 0;
		if (txtFlips.getText().length() <= 0) {
			failed = "Number of flips must be greater than zero.";
		}
		if (txtHeads.getText().length() <= 0) {
			failed = "Number of heads must be greater than zero.";
		}
		// only increment the counter if flips is greater than or equal to heads
		if (txtFlips.getText().length() > 0 && txtHeads.getText().length() > 0) {
			if (Integer.parseInt(txtFlips.getText()) >= Integer.parseInt(txtHeads.getText())) {
				valueEntered++;	
			}
		}

		// enable calculate button only if two values are entered
		if (valueEntered == 1) {
			btnCalculate.setEnabled(true);
		} else {
			btnCalculate.setEnabled(false);
		}

		// tell user that two values are needed before calculating
		if (btnCalculate.isEnabled() == false) {
			btnCalculate.setToolTipText(failed);
		} else {
			btnCalculate.setToolTipText("Calculate");
		}

		// don't calculate if flips is less than heads
		if (valueEntered == 2 && Integer.parseInt(txtFlips.getText()) < Integer.parseInt(txtHeads.getText())) {
			failed = "Number of flips must greater than the number of heads.";
			btnCalculate.setEnabled(false);
			btnCalculate.setToolTipText(failed);
			txtFlips.setToolTipText("Number of flips must greater than the number of heads.");
			txtHeads.setToolTipText("Number of heads must less than the number of flips.");
			// error color text if invalid input
			txtFlips.setForeground(Color.RED);
			txtHeads.setForeground(Color.RED);
		}
		if (valueEntered == 2 && Integer.parseInt(txtFlips.getText()) >= Integer.parseInt(txtHeads.getText())) {
			failed = "Number of flips must greater than the number of heads.";
			btnCalculate.setEnabled(true);
			btnCalculate.setToolTipText("Calculate");
			txtFlips.setToolTipText("Please enter the number of flips here.");
			txtHeads.setToolTipText("Please enter the number of heads here.");
			// default color text if valid input
			txtFlips.setForeground(Color.BLACK);
			txtHeads.setForeground(Color.BLACK);
		}
	}

	void clearFields() {
		// reset text boxes after successfully calculating unknown value
		txtFlips.setText("");
		txtHeads.setText("");
		// request focus for the first box
		txtFlips.requestFocus();
		// reset calculate button
		setCalculteEnabled();
	}

	private class textListener extends KeyAdapter {
		private JTextField txtField = null;

		private textListener(JTextField pTxtField) {
			txtField = pTxtField;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (txtFlips.getText().length() == 0) {
				// checks to see if input is a valid key
				if (e.getKeyChar() <= KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9) {
					// delete input from buffer if invalid key
					e.consume();
				}
				setCalculteEnabled();
			}
			// set input parameters on flips
			if (txtFlips.getText().length() > 0) {
				// checks to see if input is a valid key
				if (e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9) {
					// delete input from buffer if invalid key
					e.consume();
				}
				// limits input to one zero
				if (txtField.getText().length() == 1 && txtField.getText().contains("0")) {
					if (e.getKeyChar() == KeyEvent.VK_0) {
						// delete input from buffer if invalid key
						e.consume();
					}
				}
				// set input parameters on heads
				if (txtFlips.getText().length() >= 0) {
					// checks to see if input is a valid key
					if (e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9) {
						// delete input from buffer if invalid key
						e.consume();
					}
				}
				setCalculteEnabled();
			}

		}

		public void keyReleased(KeyEvent e) {
			if (txtFlips.getText().length() > 0 && txtHeads.getText().length() > 0) {
				if (Double.parseDouble(txtFlips.getText()) < Double.parseDouble(txtHeads.getText())) {
					// error color text if invalid input
					txtField.setForeground(Color.RED);
					txtFlips.setToolTipText("Number of flips must greater than the number of heads.");
					txtHeads.setToolTipText("Number of heads must less than the number of flips.");
				} else {
					// default color text if valid input
					txtField.setForeground(Color.BLACK);
					setCalculteEnabled();
				}
			}

		}
	}

	public class GlobalKeyListener implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
				clearFields();
			}
			return false;
		}
	}

	private void runCalculate() {
		Probability myProbability = new Probability();
		myProbability.setFlips(Integer.parseInt(txtFlips.getText()));
		myProbability.setHeads(Integer.parseInt(txtHeads.getText()));
		myProbability.calculateProbability();

		JOptionPane.showMessageDialog(null, "The probability of getting " + myProbability.getHeads() + " heads "
				+ "from " + myProbability.getFlips() + " flips is " + myProbability.calculateProbability() + "%.");

		// clear all fields to enter new data
		clearFields();
	}

	private class BtnCalculateActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			runCalculate();
		}
	}
}