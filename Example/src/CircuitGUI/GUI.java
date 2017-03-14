package CircuitGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GUI {

	private JFrame frame;
	private JTextField txtVoltage;
	private JTextField txtAmperage;
	private JTextField textResistance;

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
		// frame
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// voltage text
		JLabel lblFlips = new JLabel("Voltage:");
		lblFlips.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFlips.setBounds(76, 25, 80, 14);
		frame.getContentPane().add(lblFlips);
		// voltage text box
		txtVoltage = new JTextField();
		txtVoltage.setBounds(157, 22, 121, 20);
		frame.getContentPane().add(txtVoltage);
		txtVoltage.setColumns(10);

		// amperage text
		JLabel lblHeads = new JLabel("Amperage:");
		lblHeads.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeads.setBounds(76, 51, 80, 14);
		frame.getContentPane().add(lblHeads);
		// amperage text box
		txtAmperage = new JTextField();
		txtAmperage.setColumns(10);
		txtAmperage.setBounds(157, 48, 121, 20);
		frame.getContentPane().add(txtAmperage);

		// resistance text
		JLabel lblResistance = new JLabel("Resistance:");
		lblResistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResistance.setBounds(76, 80, 80, 14);
		frame.getContentPane().add(lblResistance);
		// resistance text box
		textResistance = new JTextField();
		textResistance.setColumns(10);
		textResistance.setBounds(157, 77, 121, 20);
		frame.getContentPane().add(textResistance);
		
		// calculate button
		JButton btnCalculateProbability = new JButton("Calculate");
		btnCalculateProbability.addActionListener(new BtnCalculateProbabilityActionListener());
		btnCalculateProbability.setBounds(116, 111, 162, 41);
		frame.getContentPane().add(btnCalculateProbability);
	}

	private class BtnCalculateProbabilityActionListener implements
			ActionListener {
		public void actionPerformed(ActionEvent e) {
			Circuit main = new Circuit();
			try {
				main.setVoltage(Integer.parseInt(txtVoltage.getText()));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}
			try {
				main.setAmperage(Integer.parseInt(txtAmperage.getText()));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}
			try {
				main.setResistance(Integer.parseInt(txtAmperage.getText()));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}

			JOptionPane.showMessageDialog(null,
					"Voltage: " + main.getVoltage() +
					"\nAmperage: " + main.getAmperage() +
					"\nResistance: " + main.calculateResistance()
			);
		}
	}
}
