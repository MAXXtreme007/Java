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

import bp.Newton;

public class Main {

	private JFrame frmMomentumCalculator;
	private JTextField txtMass;
	private JTextField txtVelocity;
	private JButton btnCalculate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmMomentumCalculator.setVisible(true);
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
		frmMomentumCalculator = new JFrame();
		frmMomentumCalculator.setResizable(false);
		frmMomentumCalculator.setTitle("Momentum Calculator");
		frmMomentumCalculator.setBounds(100, 100, 500, 275);
		frmMomentumCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMomentumCalculator.getContentPane().setLayout(null);

		JLabel lblMass = new JLabel("Mass:");
		lblMass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMass.setBounds(120, 60, 90, 30);
		frmMomentumCalculator.getContentPane().add(lblMass);

		JLabel lblVelocity = new JLabel("Velocity");
		lblVelocity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVelocity.setBounds(120, 120, 90, 30);
		frmMomentumCalculator.getContentPane().add(lblVelocity);

		txtMass = new JTextField();
		txtMass.addKeyListener(new textListener(txtMass));
		txtMass.setToolTipText("Please enter the object's mass here.");
		txtMass.setColumns(10);
		txtMass.setBounds(248, 60, 165, 25);
		frmMomentumCalculator.getContentPane().add(txtMass);

		txtVelocity = new JTextField();
		txtVelocity.addKeyListener(new textListener(txtVelocity));
		txtVelocity.setToolTipText("Please enter the object's velocity here.");
		txtVelocity.setColumns(10);
		txtVelocity.setBounds(248, 124, 165, 25);
		frmMomentumCalculator.getContentPane().add(txtVelocity);

		btnCalculate = new JButton("Calculate");
		btnCalculate.setToolTipText("Calculate the probability");
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCalculate.setEnabled(false);
		btnCalculate.setBounds(182, 175, 120, 40);
		btnCalculate.addActionListener(new BtnCalculateActionListener());
		frmMomentumCalculator.getContentPane().add(btnCalculate);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 500, 21);
		frmMomentumCalculator.getContentPane().add(menuBar);

		JMenu Help = new JMenu("Help");
		menuBar.add(Help);

		JButton Instructions = new JButton("Instructions");
		Instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(null,
								"This program calculates the momentum & kinetic energy of an object given the mass and velocity.");
			}
		});
		Help.add(Instructions);

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new GlobalKeyListener());
		frmMomentumCalculator.getRootPane().setDefaultButton(btnCalculate);
		setCalculteEnabled();
	}

	// event handlers
	void setCalculteEnabled() {
		// initiate counter to count number of values entered
		String failed = "";
		int valueEntered = 0;
		if (txtMass.getText().length() > 0) {
			if (Double.parseDouble(txtMass.getText()) <= 0) {
				failed = "Mass must be greater than zero.";
				txtMass.setToolTipText(failed);
			} else {
				valueEntered++;
				txtMass.setToolTipText("Please enter the object's mass here.");
			}
		}
		if (txtMass.getText().length() == 1 && Double.parseDouble(txtMass.getText()) == 0) {
			failed = "You must enter a decimal after a zero.";
			txtMass.setToolTipText(failed);
		}
		if (txtVelocity.getText().length() > 0) {
			valueEntered++;
			txtVelocity.setToolTipText("Please enter the object's velocity here.");
		}
		if (txtVelocity.getText().length() == 1 && Double.parseDouble(txtVelocity.getText()) == 0) {
			failed = "You must enter a decimal after a zero.";
			txtVelocity.setToolTipText(failed);
		}

		// enable calculate button only if two values are entered
		if (valueEntered == 2) {
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
	}

	void clearFields() {
		// reset text boxes after successfully calculating unknown value
		txtMass.setText("");
		txtVelocity.setText("");
		// request focus for the first box
		txtMass.requestFocus();
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
			// checks to see if input is a valid key
			txtField.setForeground(Color.BLACK);
			if (e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9) {
				// limits input to one decimal
				if (e.getKeyChar() != KeyEvent.VK_PERIOD || e.getKeyChar() == KeyEvent.VK_PERIOD
						&& txtField.getText().contains(".")) {
					// delete input from buffer if invalid key
					e.consume();
				}
			}
			// limits input to one zero
			if (txtField.getText().length() == 1 && txtField.getText().contains("0")) {
				if (e.getKeyChar() == KeyEvent.VK_0) {
					// delete input from buffer if invalid key
					e.consume();
				}
			}
			// only allow a decimal after a zero
			if (txtField.getText().length() == 1 && Double.parseDouble(txtField.getText()) == 0) {
				if (e.getKeyChar() != KeyEvent.VK_PERIOD) {
					// delete input from buffer if invalid key
					e.consume();
				}
			}
		}

		public void keyReleased(KeyEvent e) {
			if (txtMass.getText().length() > 0) {
				if (txtMass.getText().equals(".") || Double.parseDouble(txtMass.getText()) == 0) {
					txtMass.setForeground(Color.RED);
				}
			}
			if (txtVelocity.getText().length() > 0) {
				if (txtVelocity.getText().equals(".")) {
					txtVelocity.setForeground(Color.RED);
				}
			}
			setCalculteEnabled();
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
		Newton myMomentum = new Newton();
		myMomentum.setMass(Double.parseDouble(txtMass.getText()));
		myMomentum.setVelocity(Double.parseDouble(txtVelocity.getText()));
		myMomentum.calculateMomentum();
		myMomentum.calculateEnergy();

		String output = "";
		output += "Given an object with a mass of " + myMomentum.getMass();
		output += " and a velocity of " + myMomentum.getVelocity() + "\n";
		output += "---------------------------------------------------------------------------\n";
		output += "Momentum: " + myMomentum.calculateMomentum() + "\n";
		output += "Kinetic Enery: " + myMomentum.calculateEnergy();

		JOptionPane.showMessageDialog(null, output);

		// clear all fields to enter new data
		clearFields();
	}

	private class BtnCalculateActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			runCalculate();
		}
	}
}