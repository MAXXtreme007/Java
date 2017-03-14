package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.security.InvalidParameterException;

/*
 * Business Logic
 * 
 * PROPERTIES:
 * Include all the variables
 * Add mutators and accessors to keep variables private
 * 
 * PROCESSING:
 * Use recursion to calculate the sum of all terms to the nth term.
 * This is done by initializing the first three terms: 1, 1, 1
 * The formula to calculate the nth term is as follows:
 * P(n) = P(n-2) + P(n-3)
 * 
 * Presentation Class
 * 
 * INPUT:
 * Allow the user to determine what term to calculate.
 * This program allows the user to compute the term of an nth term
 * in the Padovan Sequence.
 * 
 * OUTPUT:
 * Display a message with the calculated sum back to the user.
 */

/**
 * This program allows the user to compute the sum of all terms to a user
 * defined nth term.
 * 
 * @author MAXXtreme
 */
public class GUI {
	/**
	 * This variable initializes the frame for the program.
	 */
	private JFrame frame;
	/**
	 * This variable is the text field to enter the nth term.
	 */
	private JTextField txtTerm;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 * 
	 * This is the main method and contains all the user input fields
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// frame
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblPadovanSequenceCalculator = new JLabel(
				"Padovan Sequence Calculator");
		lblPadovanSequenceCalculator.setFont(new Font("Lucida Grande",
				Font.BOLD, 16));
		lblPadovanSequenceCalculator
				.setHorizontalAlignment(SwingConstants.CENTER);
		lblPadovanSequenceCalculator.setBounds(6, 6, 388, 16);
		frame.getContentPane().add(lblPadovanSequenceCalculator);

		JLabel lblPleaseEnterAn = new JLabel("Please enter an nth term.");
		lblPleaseEnterAn.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterAn.setBounds(6, 51, 388, 16);
		frame.getContentPane().add(lblPleaseEnterAn);

		// calculate button
		JButton btnCalculateProbability = new JButton("Calculate");
		btnCalculateProbability
				.addActionListener(new BtnCalculateProbabilityActionListener());

		// term number text
		JLabel lblFlips = new JLabel("Term:");
		lblFlips.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFlips.setBounds(116, 82, 40, 14);
		frame.getContentPane().add(lblFlips);
		// term number text box
		txtTerm = new JTextField();
		txtTerm.setBounds(163, 79, 115, 20);
		frame.getContentPane().add(txtTerm);
		txtTerm.setColumns(10);
		btnCalculateProbability.setBounds(116, 111, 162, 41);
		frame.getContentPane().add(btnCalculateProbability);
	}

	/**
	 * This class implements a calculate button with an action listener.
	 * 
	 * @author MAXXtreme
	 */
	private class BtnCalculateProbabilityActionListener implements
			ActionListener {
		/**
		 * This method tells the program when action event e occurs.
		 * 
		 * @param e Tries and catches e to trigger an error.
		 */
		public void actionPerformed(final ActionEvent e) {
			Padovan seq = new Padovan();
			try {
				seq.setTermNumber(Integer.parseInt(txtTerm.getText()));
			} catch (InvalidParameterException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}

			// display output
			JOptionPane.showMessageDialog(
					null,
					"The value of the " + seq.getTermNumber()
							+ " term of the Padovan sequence is "
							+ seq.getPadovanValue() + ".");
		}
	}
}