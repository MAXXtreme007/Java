package ProbabilityGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JTextField txtFlips;
	private JTextField txtHeads;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblFlips = new JLabel("Flips:");
		lblFlips.setBounds(116, 25, 46, 14);
		frame.getContentPane().add(lblFlips);

		txtFlips = new JTextField();
		txtFlips.setBounds(157, 22, 121, 20);
		frame.getContentPane().add(txtFlips);
		txtFlips.setColumns(10);

		JLabel lblHeads = new JLabel("Heads:");
		lblHeads.setBounds(116, 51, 46, 14);
		frame.getContentPane().add(lblHeads);

		txtHeads = new JTextField();
		txtHeads.setColumns(10);
		txtHeads.setBounds(157, 48, 121, 20);
		frame.getContentPane().add(txtHeads);

		JButton btnCalculateProbability = new JButton("Calculate Probability");
		btnCalculateProbability
				.addActionListener(new BtnCalculateProbabilityActionListener());
		btnCalculateProbability.setBounds(116, 97, 162, 41);
		frame.getContentPane().add(btnCalculateProbability);
	}

	private class BtnCalculateProbabilityActionListener implements
			ActionListener {
		public void actionPerformed(ActionEvent e) {
			Probability coinFlips = new Probability();
			try {
				coinFlips
						.setNumberOfFlips(Integer.parseInt(txtFlips.getText()));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}
			try {
				coinFlips.setTotalHeadsFlipped(Integer.parseInt(txtHeads
						.getText()));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}

			JOptionPane.showMessageDialog(
					null,
					"Probability of " + coinFlips.getTotalHeadsFlipped()
							+ " heads on a total of "
							+ coinFlips.getNumberOfFlips() + " flips is "
							+ coinFlips.getProbability()
			);
		}
	}
}
