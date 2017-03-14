package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bp.Toy;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Main {

	private JFrame frmTalkingSalesmanQuality;
	private JTextField txtInspector;
	private JTextField txtToyID;
	private JTextField txtInspectionTime;
	private JTextField txtVoltage1;
	private JTextField txtResistance1;
	private JTextField txtVoltage2;
	private JTextField txtResistance2;
	private JComboBox<Object> comboBoxLocation1;
	private JComboBox<Object> comboBoxLocation2;
	private Date creationTime = new Date();
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnFileSave;
	private JButton btnFileLoad;
	private Preferences myPrefs = new Preferences(this);
	private JButton btnDelete;
	private JButton btnFileDelete;
	private int loadPassed = 0;
	public boolean defaultButtonSave = true;
	private Main thisMain = this;
	public boolean isSaveButton = true;
	public boolean isNameButton = true;
	public boolean isClearAllButton = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmTalkingSalesmanQuality.setVisible(true);
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
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new GlobalKeyListener());

		frmTalkingSalesmanQuality = new JFrame();
		frmTalkingSalesmanQuality.setResizable(false);
		frmTalkingSalesmanQuality.setTitle("Talking Salesman Toy Quality Control");
		frmTalkingSalesmanQuality.setBounds(100, 100, 550, 400);
		frmTalkingSalesmanQuality.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTalkingSalesmanQuality.getContentPane().setLayout(null);

		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		infoPanel.setBounds(34, 8, 474, 120);
		frmTalkingSalesmanQuality.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);

		JLabel lblInspector = new JLabel("Inspector: ");
		lblInspector.setBounds(24, 15, 200, 20);
		infoPanel.add(lblInspector);
		lblInspector.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInspector.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtInspector = new JTextField();
		txtInspector.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtInspector.setBounds(248, 15, 200, 20);
		infoPanel.add(txtInspector);
		txtInspector.addKeyListener(new TxtInspectorKeyListener());
		txtInspector.setToolTipText("Please enter inspector's name");
		txtInspector.setColumns(10);

		JLabel lblToyId = new JLabel("Toy ID: ");
		lblToyId.setBounds(24, 50, 200, 20);
		infoPanel.add(lblToyId);
		lblToyId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblToyId.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtToyID = new JTextField();
		txtToyID.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtToyID.setBounds(248, 50, 200, 20);
		infoPanel.add(txtToyID);
		txtToyID.addKeyListener(new ToyIDListener());
		txtToyID.setToolTipText("Please enter the Toy ID");
		txtToyID.setColumns(10);

		JLabel lblTimedate = new JLabel("Inspection Time/Date: ");
		lblTimedate.setBounds(24, 85, 200, 20);
		infoPanel.add(lblTimedate);
		lblTimedate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimedate.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		txtInspectionTime = new JTextField(creationTime.toString());
		txtInspectionTime.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtInspectionTime.setBounds(248, 85, 200, 20);
		infoPanel.add(txtInspectionTime);
		txtInspectionTime.setEnabled(false);
		txtInspectionTime.setColumns(10);

		JPanel circuit1Panel = new JPanel();
		circuit1Panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		circuit1Panel.setBounds(34, 136, 220, 130);
		frmTalkingSalesmanQuality.getContentPane().add(circuit1Panel);
		circuit1Panel.setLayout(null);

		JLabel lblCircuit1 = new JLabel("Circuit 1");
		lblCircuit1.setBounds(87, 0, 46, 14);
		circuit1Panel.add(lblCircuit1);
		lblCircuit1.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JLabel lblVoltage1 = new JLabel("Voltage:");
		lblVoltage1.setBounds(16, 22, 84, 14);
		circuit1Panel.add(lblVoltage1);
		lblVoltage1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblVoltage1.setHorizontalAlignment(SwingConstants.RIGHT);

		txtVoltage1 = new JTextField();
		txtVoltage1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtVoltage1.setBounds(116, 17, 86, 20);
		circuit1Panel.add(txtVoltage1);
		txtVoltage1.addKeyListener(new textListener(txtVoltage1));
		txtVoltage1.setToolTipText("Please enter the voltage for circuit 1");
		txtVoltage1.setColumns(10);

		JLabel lblResistance1 = new JLabel("Resistance:");
		lblResistance1.setBounds(16, 58, 84, 14);
		circuit1Panel.add(lblResistance1);
		lblResistance1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblResistance1.setHorizontalAlignment(SwingConstants.RIGHT);

		txtResistance1 = new JTextField();
		txtResistance1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtResistance1.setBounds(116, 54, 86, 20);
		circuit1Panel.add(txtResistance1);
		txtResistance1.addKeyListener(new textListener(txtResistance1));
		txtResistance1.setToolTipText("Please enter the resistance for circuit 1");
		txtResistance1.setColumns(10);

		JLabel lblMfgLocation1 = new JLabel("Mfg. Location:");
		lblMfgLocation1.setBounds(16, 94, 84, 14);
		circuit1Panel.add(lblMfgLocation1);
		lblMfgLocation1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMfgLocation1.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		comboBoxLocation1 = new JComboBox<Object>();
		comboBoxLocation1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxLocation1.setBounds(116, 91, 86, 20);
		circuit1Panel.add(comboBoxLocation1);
		comboBoxLocation1.setModel(new DefaultComboBoxModel<Object>(new String[] { "U.S.A.", "China", "Germany" }));
		comboBoxLocation1.setSelectedIndex(0);
		comboBoxLocation1.setToolTipText("Please select a location");

		JPanel circuit2Panel = new JPanel();
		circuit2Panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		circuit2Panel.setBounds(288, 136, 220, 130);
		frmTalkingSalesmanQuality.getContentPane().add(circuit2Panel);
		circuit2Panel.setLayout(null);

		JLabel lblCircuit2 = new JLabel("Circuit 2");
		lblCircuit2.setBounds(87, 0, 46, 14);
		circuit2Panel.add(lblCircuit2);
		lblCircuit2.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JLabel lblVoltage2 = new JLabel("Voltage:");
		lblVoltage2.setBounds(16, 22, 84, 14);
		circuit2Panel.add(lblVoltage2);
		lblVoltage2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVoltage2.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		txtVoltage2 = new JTextField();
		txtVoltage2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtVoltage2.setBounds(116, 17, 86, 20);
		circuit2Panel.add(txtVoltage2);
		txtVoltage2.addKeyListener(new textListener(txtVoltage2));
		txtVoltage2.setToolTipText("Please enter the voltage for circuit 2");
		txtVoltage2.setColumns(10);

		JLabel lblResistance2 = new JLabel("Resistance:");
		lblResistance2.setBounds(16, 58, 84, 14);
		circuit2Panel.add(lblResistance2);
		lblResistance2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResistance2.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		txtResistance2 = new JTextField();
		txtResistance2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtResistance2.setBounds(116, 54, 86, 20);
		circuit2Panel.add(txtResistance2);
		txtResistance2.addKeyListener(new textListener(txtResistance2));
		txtResistance2.setToolTipText("Please enter the resistance for circuit 2");
		txtResistance2.setColumns(10);

		JLabel lblMfgLocation2 = new JLabel("Mfg. Location:");
		lblMfgLocation2.setBounds(16, 94, 84, 14);
		circuit2Panel.add(lblMfgLocation2);
		lblMfgLocation2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMfgLocation2.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		comboBoxLocation2 = new JComboBox<Object>();
		comboBoxLocation2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBoxLocation2.setBounds(116, 91, 86, 20);
		circuit2Panel.add(comboBoxLocation2);
		comboBoxLocation2.setModel(new DefaultComboBoxModel<Object>(new String[] { "U.S.A.", "China", "Germany" }));
		comboBoxLocation2.setSelectedIndex(0);
		comboBoxLocation2.setToolTipText("Please select a location");

		JMenuBar menuBar = new JMenuBar();
		frmTalkingSalesmanQuality.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		menuBar.add(mnFile);

		btnFileSave = new JButton("Save");
		btnFileSave.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnFileSave.setPreferredSize(new Dimension(70, 23));
		btnFileSave.setMinimumSize(new Dimension(63, 23));
		btnFileSave.setMaximumSize(new Dimension(70, 23));
		btnFileSave.addActionListener(new BtnSaveActionListener());
		btnFileSave.setEnabled(false);
		mnFile.add(btnFileSave);
		btnFileSave.setToolTipText("Please enter all fields first.");

		btnFileLoad = new JButton("Load");
		btnFileLoad.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnFileLoad.setPreferredSize(new Dimension(70, 23));
		btnFileLoad.setMinimumSize(new Dimension(70, 23));
		btnFileLoad.setMaximumSize(new Dimension(70, 23));
		btnFileLoad.addActionListener(new BtnLoadActionListener());
		btnFileLoad.setEnabled(false);
		mnFile.add(btnFileLoad);
		btnFileLoad.setToolTipText("Please enter only Toy ID first.");

		btnFileDelete = new JButton("Delete");
		btnFileDelete.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnFileDelete.setMaximumSize(new Dimension(70, 23));
		btnFileDelete.setEnabled(false);
		btnFileDelete.setPreferredSize(new Dimension(70, 23));
		btnFileDelete.setMinimumSize(new Dimension(70, 23));
		btnFileDelete.setPreferredSize(new Dimension(70, 23));
		btnFileDelete.addActionListener(new BtnDeleteActionListener());
		mnFile.add(btnFileDelete);
		btnFileDelete.setToolTipText("Please enter only Toy ID first.");

		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		menuBar.add(mnEdit);

		JButton btnPreferences = new JButton("Preferences");
		btnPreferences.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnPreferences.addActionListener(new BtnPreferencesActionListener());
		mnEdit.add(btnPreferences);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		menuBar.add(mnHelp);

		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnInstructions.addActionListener(new BtnInstructionsActionListener());
		mnHelp.add(btnInstructions);

		btnSave = new JButton("Save");
		btnSave.setBounds(217, 274, 110, 29);
		btnSave.setEnabled(false);
		btnSave.setToolTipText("Please enter all fields first.");
		btnSave.addActionListener(new BtnSaveActionListener());

		btnLoad = new JButton("Load");
		btnLoad.setBounds(217, 274, 110, 29);
		btnLoad.setVisible(false);
		btnLoad.setEnabled(false);
		btnLoad.setToolTipText("Load information of Toy ID");
		btnLoad.addActionListener(new BtnLoadActionListener());

		frmTalkingSalesmanQuality.getContentPane().add(btnLoad);

		btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnDelete.setBounds(217, 311, 110, 29);
		btnDelete.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setToolTipText("Warning! Deleting will permanently delete the data. Do you wish to continue?");
		btnDelete.addActionListener(new BtnDeleteActionListener());

		frmTalkingSalesmanQuality.getContentPane().add(btnDelete);
		frmTalkingSalesmanQuality.getContentPane().add(btnSave);

		setButton();
		setSaveEnabled();

	}

	public void setButton() {

		// set default action for enter button
		if (myPrefs.defaultEnter()) {
			frmTalkingSalesmanQuality.getRootPane().setDefaultButton(btnSave);
		} else {
			frmTalkingSalesmanQuality.getRootPane().setDefaultButton(btnDelete);
		}
	}

	// event handlers
	void setSaveEnabled() {
		// initiate counter to count number of values entered
		String failed = "Please enter all fields to save.";
		int valueEntered = 0;
		int loadToyID = 0;
		// increment counter for inspector
		if (txtInspector.getText().length() > 0) {
			if (!txtInspector.getText().equals(".")) {
				valueEntered++;
			} else {
				failed = "Insert inspector name";
			}
		}
		// enable load button only if toy ID is entered
		if (txtToyID.getText().length() > 0) {
			if (!txtToyID.getText().equals(".")) {
				valueEntered++;
				loadToyID++;
			} else {
				failed = "Insert Toy ID";
			}
		}
		// increment counter for voltage 1
		if (txtVoltage1.getText().length() > 0) {
			if (!txtVoltage1.getText().equals(".") && Double.parseDouble(txtVoltage1.getText()) != 0)
				valueEntered++;
			else if (txtVoltage1.getText().equals("."))
				failed = "Insert circuit 1's voltage";
			else if (Double.parseDouble(txtVoltage1.getText()) == 0)
				failed = "Voltage 1 must be greater than zero";
		}
		// increment counter for resistance 1
		if (txtResistance1.getText().length() > 0) {
			if (!txtResistance1.getText().equals(".") && Double.parseDouble(txtResistance1.getText()) != 0)
				valueEntered++;
			else if (txtResistance1.getText().equals("."))
				failed = "Insert circuit 1's resistance";
			else if (Double.parseDouble(txtResistance1.getText()) == 0)
				failed = "Resistance 1 must be greater than zero";
		}
		// increment counter for voltage 2
		if (txtVoltage2.getText().length() > 0) {
			if (!txtVoltage2.getText().equals(".") && Double.parseDouble(txtVoltage2.getText()) != 0)
				valueEntered++;
			else if (txtVoltage2.getText().equals("."))
				failed = "Insert circuit 2's voltage";
			else if (Double.parseDouble(txtVoltage2.getText()) == 0)
				failed = "Voltage 2 must be greater than zero";
		}
		// increment counter for resistance 2
		if (txtResistance2.getText().length() > 0) {
			if (!txtResistance2.getText().equals(".") && Double.parseDouble(txtResistance2.getText()) != 0)
				valueEntered++;
			else if (txtResistance2.getText().equals("."))
				failed = "Insert circuit 2's resistance";
			else if (Double.parseDouble(txtResistance2.getText()) == 0)
				failed = "Resistance 2 must be greater than zero";
		}

		// tell user that two values are needed before calculating
		if (btnSave.isEnabled() == false) {
			btnSave.setToolTipText(failed);
			btnFileSave.setToolTipText(failed);
		} else {
			btnSave.setToolTipText("Save");
			btnFileSave.setToolTipText("Save");
		}

		// enable save button only if two values are entered
		if (valueEntered == 6) {
			frmTalkingSalesmanQuality.getRootPane().setDefaultButton(btnSave);
			btnSave.setEnabled(true);
			btnFileSave.setEnabled(true);
		} else {
			btnSave.setEnabled(false);
			btnFileSave.setEnabled(false);
		}

		// enable load button only if toy ID is entered
		if (loadToyID == 1 && valueEntered == 1) {
			frmTalkingSalesmanQuality.getRootPane().setDefaultButton(btnLoad);
			btnLoad.setVisible(true);
			btnLoad.setEnabled(true);
			btnFileLoad.setEnabled(true);
			btnFileLoad.setToolTipText("Load the Toy ID's data.");
		} else {
			btnLoad.setVisible(false);
			btnLoad.setEnabled(false);
			btnFileLoad.setEnabled(false);
			btnFileLoad.setToolTipText("Please enter only Toy ID first.");
		}

		// enable delete button only after data is loaded
		if (loadPassed == 1 && valueEntered == 6) {
			btnDelete.setVisible(true);
			btnDelete.setEnabled(true);
			btnFileDelete.setEnabled(true);
		} else {
			btnDelete.setVisible(false);
			btnDelete.setEnabled(false);
			btnFileDelete.setEnabled(false);
		}
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
		}

		public void keyReleased(KeyEvent e) {
			if (txtField.getText().length() > 0)
				if (txtField.getText().equals(".") || Double.parseDouble(txtField.getText()) == 0)
					txtField.setForeground(Color.RED);
			setSaveEnabled();
		}
	}

	private class ToyIDListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			// checks to see if input is a valid key
			if (e.getKeyChar() < KeyEvent.VK_0 || e.getKeyChar() > KeyEvent.VK_9) {
				e.consume();
			}
		}

		public void keyReleased(KeyEvent e) {
			setSaveEnabled();
		}
	}

	private class GlobalKeyListener implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
				clearFields();
			}
			return false;
		}
	}

	private class BtnPreferencesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myPrefs = new Preferences(thisMain);
			myPrefs.show(true);
		}
	}

	private class BtnSaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			runSave();
		}
	}

	private class BtnLoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			runLoad();
		}
	}

	private class BtnDeleteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			runDelete();
		}
	}

	private class TxtInspectorKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			setSaveEnabled();
		}
	}

	public boolean getBtnLoadVisible() {
		return btnLoad.isVisible();
	}

	public void setBtnLoadVisible(boolean visible) {
		btnLoad.setVisible(visible);
	}

	void clearFields() {
		// call preference in preferences
		if (myPrefs.clear()) {
			// clear inspector's name if preference is set to yes
			txtInspector.setText("");
		}
		// reset text boxes
		txtToyID.setText("");
		txtVoltage1.setText("");
		txtResistance1.setText("");
		txtVoltage2.setText("");
		txtResistance2.setText("");
		// request focus for preferred field
		if (myPrefs.defaultField()) {
			txtInspector.requestFocus();
		} else {
			txtToyID.requestFocus();
		}
		// reset calculate button
		setSaveEnabled();
	}

	private class BtnInstructionsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String output = "";
			output += "This form allows data entry on the Talking Salesman toy for quality control purposes.\n";
			output += "-------------------------------------------------------";
			output += "-------------------------------------------------------\n";
			output += "\n";
			output += "To save to the database, please enter all fields.\n";
			output += "\n";
			output += "-------------------------------------------------------";
			output += "-------------------------------------------------------\n";
			output += "\n";
			output += "To load information from database, please only enter the Toy ID.\n";
			output += "\n";
			output += "-------------------------------------------------------";
			output += "-------------------------------------------------------\n";
			output += "All information is property of Buchan Berkstresser Enterprises.\n";
			output += "Only authorized employees may use this form.";
			JOptionPane.showMessageDialog(null, output);
		}
	}

	private void runSave() {
		Toy myToy = new Toy();
		myToy.setInspector(txtInspector.getText());
		myToy.setInspectionDateTime(creationTime);
		myToy.setToyID(Integer.parseInt(txtToyID.getText()));
		// SET ALL PROPERTIES FOR CIRCUIT1 ITSELF
		myToy.getCircuit1().setVoltage(Double.parseDouble(txtVoltage1.getText()));
		myToy.getCircuit1().setResistance(Double.parseDouble(txtResistance1.getText()));
		myToy.getCircuit1().setLocation(comboBoxLocation1.getSelectedItem().toString());
		myToy.getCircuit1().setToyID(Integer.parseInt(txtToyID.getText()));
		myToy.getCircuit1().setCircuitID(1);
		myToy.getCircuit1().calculateAmperage();
		// SET ALL PROPERTIES FOR CIRCUIT2 ITSELF
		myToy.getCircuit2().setVoltage(Double.parseDouble(txtVoltage2.getText()));
		myToy.getCircuit2().setResistance(Double.parseDouble(txtResistance2.getText()));
		myToy.getCircuit2().setLocation(comboBoxLocation2.getSelectedItem().toString());
		myToy.getCircuit2().setToyID(Integer.parseInt(txtToyID.getText()));
		myToy.getCircuit2().setCircuitID(2);
		myToy.getCircuit2().calculateAmperage();

		String output = "";
		output += "-----------------------------------------------------------------\n";
		output += "Inspector: " + myToy.getInspector() + "\n";
		output += "Inspection Date/Time: " + myToy.getInspectionDateTime() + "\n";
		output += "Toy ID: " + myToy.getToyID() + "\n";
		output += "-----------------------------------------------------------------\n";
		output += "Circuit 1 Information\n";
		output += "Circuit ID: " + myToy.getCircuit1().getCircuitID() + "\n";
		output += "Voltage: " + myToy.getCircuit1().getVoltage() + "\n";
		output += "Amperage: " + myToy.getCircuit1().getAmperage() + "\n";
		output += "Resistance: " + myToy.getCircuit1().getResistance() + "\n";
		output += "-----------------------------------------------------------------\n";
		output += "Circuit 2 Information\n";
		output += "Circuit ID: " + myToy.getCircuit2().getCircuitID() + "\n";
		output += "Voltage: " + myToy.getCircuit2().getVoltage() + "\n";
		output += "Amperage: " + myToy.getCircuit2().getAmperage() + "\n";
		output += "Resistance: " + myToy.getCircuit2().getResistance() + "\n";
		JOptionPane.showMessageDialog(null, output);

		// allow program to clear all fields if can't connect to database
		try {
			// save values to database
			myToy.save();
		} catch (Exception e) {

		}
		// disallow delete box after delete
		loadPassed = 0;

		// clear all fields to enter new data
		clearFields();
	}

	private void runLoad() {
		Toy myToy = new Toy();
		// allow program to clear all fields if can't connect to database
		try {
			// load values given toy ID
			myToy.load(Integer.parseInt(txtToyID.getText()));

			// put values into text boxes
			txtInspector.setText(myToy.getInspector());
			txtInspectionTime.setText(String.valueOf(myToy.getInspectionDateTime()));

			// circuit 1 properties
			txtVoltage1.setText(String.valueOf(myToy.getCircuit1().getVoltage()));
			txtResistance1.setText(String.valueOf(myToy.getCircuit1().getResistance()));
			comboBoxLocation1.setSelectedItem(myToy.getCircuit1().getManufactureLocation());

			// circuit 2 properties
			txtResistance2.setText(String.valueOf(myToy.getCircuit2().getResistance()));
			txtVoltage2.setText(String.valueOf(myToy.getCircuit2().getVoltage()));
			comboBoxLocation2.setSelectedItem(myToy.getCircuit2().getManufactureLocation());

			// pop up delete box if data is loaded
			loadPassed = 1;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error! Toy ID does not exist.");
			clearFields();
		}
	}

	private void runDelete() {
		Toy myToy = new Toy();

		// allow program to clear all fields if can't connect to database
		try {
			// set toy ID
			myToy.setToyID(Integer.parseInt(txtToyID.getText()));

			// delete all fields in database
			myToy.delete();

			// disallow delete box after delete
			loadPassed = 0;

			// tell user data has been deleted
			JOptionPane.showMessageDialog(null, "Toy ID data has been deleted from database.");

			// clear all fields to enter new data
			clearFields();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Sorry, Toy ID data could not be deleted from database at this time.\nPlease try again later.");
		}
	}
}