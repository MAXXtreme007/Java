package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import bp.Circuit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Dimension;

public class Main {
	// gui garbage ***** VIEW *****
	private JFrame frame;
	private JTextField txtVoltage;
	private JTextField txtAmperage;
	private JTextField txtResistance;
	private JButton calculate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Ohm's Law Calculator");
		frame.setLocation(450, 200);
		frame.setSize(460, 290);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblVoltage = new JLabel("Voltage:");
		lblVoltage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltage.setBounds(85, 30, 80, 20);
		frame.getContentPane().add(lblVoltage);

		txtVoltage = new JTextField();
		txtVoltage.setToolTipText("Please enter voltage here.");
		txtVoltage.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (txtVoltage.getText().equals(".")) {
					JOptionPane.showMessageDialog(null, "Please enter a numeric value greater than zero.");
					clearVoltage();
				}
			}
		});
		txtVoltage.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				// checks to see if the other variables have been defined
				if (txtAmperage.getText().length() > 0 && txtResistance.getText().length() > 0) {
					e.consume();
				} else {
					// checks to see if input is a valid key
					if (e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9) {
						// limits input to one decimal
						if (e.getKeyChar() != KeyEvent.VK_PERIOD || e.getKeyChar() == KeyEvent.VK_PERIOD
								&& txtVoltage.getText().contains(".")) {
							// delete input from buffer if invalid key
							e.consume();
						}
					}
				}
				// clear fields if user presses escape
				if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					clearFields();
				}
			}

			public void keyReleased(KeyEvent e) {
				setCalculteEnabled();
			}
		});
		txtVoltage.setColumns(10);
		txtVoltage.setBounds(173, 30, 120, 20);
		frame.getContentPane().add(txtVoltage);

		JLabel lblAmperage = new JLabel("Amperage:");
		lblAmperage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmperage.setBounds(85, 80, 80, 20);
		frame.getContentPane().add(lblAmperage);

		txtAmperage = new JTextField();
		txtAmperage.setToolTipText("Please enter amperage here.");
		txtAmperage.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (txtAmperage.getText().equals(".")) {
					JOptionPane.showMessageDialog(null, "Please enter a numeric value greater than zero.");
					clearAmperage();
				}
			}
		});
		txtAmperage.addKeyListener(new KeyAdapter() {
			// checks to see if input is a valid key
			public void keyTyped(KeyEvent e) {
				// checks to see if the other variables have been defined
				if (txtVoltage.getText().length() > 0 && txtResistance.getText().length() > 0) {
					e.consume();
				} else {
					// checks to see if input is a valid key
					if (e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9) {
						if (e.getKeyChar() != KeyEvent.VK_PERIOD || e.getKeyChar() == KeyEvent.VK_PERIOD
								&& txtAmperage.getText().contains(".")) {
							// delete input from buffer if invalid key
							e.consume();
						}
					}
				}
				// clear fields if user presses escape
				if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					clearFields();
				}
			}

			public void keyReleased(KeyEvent e) {
				setCalculteEnabled();
			}
		});
		txtAmperage.setColumns(10);
		txtAmperage.setBounds(173, 80, 120, 20);
		frame.getContentPane().add(txtAmperage);

		JLabel lblResistance = new JLabel("Resistance:");
		lblResistance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResistance.setBounds(85, 130, 80, 20);
		frame.getContentPane().add(lblResistance);

		txtResistance = new JTextField();
		txtResistance.setToolTipText("Please enter resistance here.");
		txtResistance.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (txtResistance.getText().equals(".")) {
					JOptionPane.showMessageDialog(null, "Please enter a numeric value greater than zero.");
					clearResistance();
				}
			}
		});
		txtResistance.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				// checks to see if the other variables have been defined
				if (txtVoltage.getText().length() > 0 && txtAmperage.getText().length() > 0) {
					e.consume();
				} else {
					// checks to see if input is a valid key
					if (e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9) {
						if (e.getKeyChar() != KeyEvent.VK_PERIOD || e.getKeyChar() == KeyEvent.VK_PERIOD
								&& txtResistance.getText().contains(".")) {
							// delete input from buffer if invalid key
							e.consume();
						}
					}
				}
				// clear fields if user presses escape
				if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					clearFields();
				}
			}

			public void keyReleased(KeyEvent e) {
				setCalculteEnabled();
			}
		});
		txtResistance.setColumns(10);
		txtResistance.setBounds(173, 130, 120, 20);
		frame.getContentPane().add(txtResistance);
		calculate = new JButton("Calculate");
		setCalculteEnabled();
		calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Circuit myCircuit = new Circuit();

				// calculate voltage if it is the empty field
				if (txtVoltage.getText().length() == 0) {
					// set amperage and catch errors
					try {
						myCircuit.setAmperage(Double.parseDouble(txtAmperage.getText()));
						setCalculteEnabled();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter a numeric number!");
						txtAmperage.setText("");
						txtAmperage.requestFocus();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						txtAmperage.setText("");
						txtAmperage.requestFocus();
					}
					// set resistance and catch errors
					try {
						myCircuit.setResistance(Double.parseDouble(txtResistance.getText()));
						setCalculteEnabled();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter a numeric number!");
						txtResistance.setText("");
						txtResistance.requestFocus();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						txtResistance.setText("");
						txtResistance.requestFocus();
					}
					setCalculteEnabled();
					myCircuit.calculateVoltage();
					JOptionPane.showMessageDialog(null, "Given an amperage of " + myCircuit.getAmperage()
							+ " amps and a resistance of " + myCircuit.getResistance() + " ohms, the voltage is "
							+ myCircuit.getVoltage() + " volts.");
				}

				// calculate amperage if it is the empty field
				if (txtAmperage.getText().length() == 0) {
					// set voltage and catch errors
					try {
						myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
						setCalculteEnabled();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter a numeric number!");
						txtVoltage.setText("");
						txtVoltage.requestFocus();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						txtVoltage.setText("");
						txtVoltage.requestFocus();
					}
					// set resistance and catch errors
					try {
						myCircuit.setResistance(Double.parseDouble(txtResistance.getText()));
						setCalculteEnabled();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter a numeric number!");
						txtResistance.setText("");
						txtResistance.requestFocus();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						txtResistance.setText("");
						txtResistance.requestFocus();
					}
					myCircuit.calculateAmperage();
					JOptionPane.showMessageDialog(null, "Given a voltage of " + myCircuit.getVoltage()
							+ " volts and a resistance of " + myCircuit.getResistance() + " ohms, the amperage is "
							+ myCircuit.getAmperage() + " amps.");
				}

				// calculate resistance if it is the empty field
				if (txtResistance.getText().length() == 0) {
					// set voltage and catch errors
					try {
						myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
						setCalculteEnabled();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter a numeric number!");
						txtVoltage.setText("");
						txtVoltage.requestFocus();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						txtVoltage.setText("");
						txtVoltage.requestFocus();
					}
					// set amperage and catch errors
					try {
						myCircuit.setAmperage(Double.parseDouble(txtAmperage.getText()));
						setCalculteEnabled();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter a numeric number!");
						txtAmperage.setText("");
						txtAmperage.requestFocus();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						txtAmperage.setText("");
						txtAmperage.requestFocus();
					}
					myCircuit.calculateResistance();
					JOptionPane.showMessageDialog(null, "Given a voltage of " + myCircuit.getVoltage()
							+ " volts and an amperage of " + myCircuit.getAmperage() + " amps, the resistance is "
							+ myCircuit.getResistance() + " ohms.");
				}

				// reset inputs and default back to first text box
				clearFields();
			}
		});
		calculate.setBounds(152, 175, 162, 41);
		frame.getContentPane().add(calculate);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLocation(0, 0);
		frame.setJMenuBar(menuBar);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"This action will allow the user to undo his last input.");
			}
		});
		btnUndo.setPreferredSize(new Dimension(91, 23));
		btnUndo.setMinimumSize(new Dimension(91, 23));
		btnUndo.setMaximumSize(new Dimension(91, 23));
		mnEdit.add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"This action will allow the user to redo his last undo.");
			}
		});
		btnRedo.setPreferredSize(new Dimension(91, 23));
		btnRedo.setMinimumSize(new Dimension(91, 23));
		btnRedo.setMaximumSize(new Dimension(91, 23));
		mnEdit.add(btnRedo);
		
		JButton btnPreferences = new JButton("Preferences");
		btnPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"This is a temporary place holder where a preferences pane will replace this dialog.");
			}
		});
		mnEdit.add(btnPreferences);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "This program calculates the voltage, amperage, and resistance "
						+ "of a circuit given two of the three variables. \n "
						+ "Please enter in two of the variables then press Enter "
						+ "or Calculate to calculate the unknown variable.");
			}
		});
		mnHelp.add(btnInstructions);
		frame.getRootPane().setDefaultButton(calculate);

		if (txtVoltage.getText().equals(".")) {
			JLabel voltageError = new JLabel(" !");
			voltageError.setOpaque(true);
			voltageError.setToolTipText("Please enter a numeric value greater than zero.");
			voltageError.setBackground(Color.RED);
			voltageError.setBounds(303, 33, 11, 14);
			frame.getContentPane().add(voltageError);
		}

		if (txtAmperage.getText().equals(".")) {
			JLabel amperageError = new JLabel(" !");
			amperageError.setOpaque(true);
			amperageError.setBackground(Color.RED);
			amperageError.setToolTipText("Please enter a numeric value greater than zero.");
			amperageError.setForeground(Color.BLACK);
			amperageError.setBounds(303, 83, 11, 14);
			frame.getContentPane().add(amperageError);
		}

		if (txtResistance.getText().equals(".")) {
			JLabel resistanceError = new JLabel(" !");
			resistanceError.setVisible(false);
			resistanceError.setOpaque(true);
			resistanceError.setToolTipText("Please enter a numeric value greater than zero.");
			resistanceError.setBackground(Color.RED);
			resistanceError.setBounds(303, 133, 11, 14);
			frame.getContentPane().add(resistanceError);
		}
	}

	// event handlers ***** CONTROLLER *****
	void setCalculteEnabled() {
		// initiate counter to count number of values entered
		int valueEntered = 0;
		if (txtVoltage.getText().length() > 0) {
			if (txtVoltage.getText().equals(".")) {
			} else {
				valueEntered = valueEntered + 1;
			}
		}
		if (txtAmperage.getText().length() > 0) {
			if (txtAmperage.getText().equals(".")) {
			} else {
				valueEntered = valueEntered + 1;
			}
		}
		if (txtResistance.getText().length() > 0) {
			if (txtResistance.getText().equals(".")) {
			} else {
				valueEntered = valueEntered + 1;
			}
		}
		// enable calculate button only if two values are entered
		if (valueEntered == 2) {
			calculate.setEnabled(true);
		} else {
			calculate.setEnabled(false);
		}
		// tell user that two values are needed before calculating
		if (calculate.isEnabled() == false) {
			calculate.setToolTipText("Please enter two numeric values before calculating.");
		} else {
			if (txtVoltage.getText().length() == 0) {
				calculate.setToolTipText("Calculate Voltage");
			}
			if (txtAmperage.getText().length() == 0) {
				calculate.setToolTipText("Calculate Amperage");
			}
			if (txtResistance.getText().length() == 0) {
				calculate.setToolTipText("Calculate Resistance");
			}
		}

	}

	void clearVoltage() {
		// reset text boxes after successfully calculating unknown value
		txtVoltage.setText("");
		// request focus for the box
		txtVoltage.requestFocus();
	}

	void clearAmperage() {
		// reset text boxes after successfully calculating unknown value
		txtAmperage.setText("");
		// request focus for the box
		txtAmperage.requestFocus();
	}

	void clearResistance() {
		// reset text boxes after successfully calculating unknown value
		txtResistance.setText("");
		// request focus for the box
		txtResistance.requestFocus();
	}

	void clearFields() {
		// reset text boxes after successfully calculating unknown value
		txtVoltage.setText("");
		txtAmperage.setText("");
		txtResistance.setText("");
		// request focus for the first box - voltage
		txtVoltage.requestFocus();
		// reset calculate button
		setCalculteEnabled();
	}
}