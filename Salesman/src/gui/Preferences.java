package gui;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Preferences {

	private JFrame frmPreferences;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private String yesOrNo = "";
	private String nameOrToy = "";
	private String saveOrDelete = "";
	private JLabel lblClearText;
	private JRadioButton rdbtnName;
	private JRadioButton rdbtnToyID;
	private JLabel lblFieldText;
	private JRadioButton rdbtnSave;
	private JRadioButton rdbtnDelete;
	private JLabel lblKeyText;
	private JButton btnOK;
	private Main myMain;

	public final boolean clear() {
		return rdbtnYes.isSelected();
	}

	public final boolean defaultField() {
		return rdbtnName.isSelected();
	}

	public final boolean defaultEnter() {
		return rdbtnSave.isSelected();
	}

	public final void show(boolean doesShow) {
		frmPreferences.setVisible(doesShow);
	}

	/**
	 * Create the application.
	 * @param main /**
	 * @wbp.parser.constructor
	 */
	public Preferences(Main main) {
		myMain = main;
		initialize();
	}

	public Preferences() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmPreferences = new JFrame();
		frmPreferences.setResizable(false);
		frmPreferences.setTitle("Preferences");
		frmPreferences.setBounds(100, 100, 400, 330);
		frmPreferences.getContentPane().setLayout(null);

		JLabel lblClearAll = new JLabel("Clear All: ");
		lblClearAll.setToolTipText("Clear inspector's name when clearing all?");
		lblClearAll.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClearAll.setBounds(33, 20, 110, 20);
		frmPreferences.getContentPane().add(lblClearAll);

		if (myMain.isClearAllButton) {
			rdbtnYes = new JRadioButton("Yes", true);
			rdbtnYes.addActionListener(new clearAllActionListener());
			rdbtnNo = new JRadioButton("No", false);
		} else {
			rdbtnYes = new JRadioButton("Yes", false);
			rdbtnYes.addActionListener(new clearAllActionListener());
			rdbtnNo = new JRadioButton("No", true);
		}

		lblClearText = new JLabel("Clearing all will clear inspector's name as well.");
		lblClearText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblClearText.setHorizontalAlignment(SwingConstants.CENTER);
		lblClearText.setBounds(40, 50, 314, 18);
		frmPreferences.getContentPane().add(lblClearText);
		
		rdbtnYes.setToolTipText("Will clear inspector's name when clearing all.");
		rdbtnYes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnYes.setBounds(176, 20, 75, 20);
		frmPreferences.getContentPane().add(rdbtnYes);

		rdbtnNo.addActionListener(new clearAllActionListener());
		rdbtnNo.setToolTipText("Will keep inspector's name when clearing all.");
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNo.setBounds(284, 20, 75, 20);
		frmPreferences.getContentPane().add(rdbtnNo);

		JLabel lblField = new JLabel("Default Field:");
		lblField.setToolTipText("Default field when clearing fields.");
		lblField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblField.setBounds(33, 99, 110, 20);
		frmPreferences.getContentPane().add(lblField);

		lblFieldText = new JLabel("Clearing fields will default back to name.");
		lblFieldText.setHorizontalAlignment(SwingConstants.CENTER);
		lblFieldText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFieldText.setBounds(45, 130, 314, 18);
		frmPreferences.getContentPane().add(lblFieldText);
		
		if (myMain.isNameButton) {
			rdbtnName = new JRadioButton("Name", true);
			rdbtnName.addActionListener(new defaultClearActionListener());
			rdbtnToyID = new JRadioButton("Toy ID", false);
		} else {
			rdbtnName = new JRadioButton("Name", false);
			rdbtnName.addActionListener(new defaultClearActionListener());
			rdbtnToyID = new JRadioButton("Toy ID", true);
		}
		
		rdbtnName.setToolTipText("Will automatically go to name after clearing fields.");
		rdbtnName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnName.setBounds(176, 100, 75, 20);
		frmPreferences.getContentPane().add(rdbtnName);

		rdbtnToyID.addActionListener(new defaultClearActionListener());
		rdbtnToyID.setToolTipText("Will automatically go to Toy ID after clearing fields.");
		rdbtnToyID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnToyID.setBounds(284, 100, 75, 20);
		frmPreferences.getContentPane().add(rdbtnToyID);

		JLabel lblDefaultKey = new JLabel("Default Key:");
		lblDefaultKey.setToolTipText("Default action with enter key.");
		lblDefaultKey.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblDefaultKey.setBounds(33, 172, 110, 20);
		frmPreferences.getContentPane().add(lblDefaultKey);

		lblKeyText = new JLabel("Pressing enter will save data to database.");
		lblKeyText.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeyText.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblKeyText.setBounds(45, 204, 314, 18);
		frmPreferences.getContentPane().add(lblKeyText);
		
		if (myMain.isSaveButton) {
			rdbtnSave = new JRadioButton("Save", true);
			rdbtnSave.addActionListener(new defaultEnterActionListener());
			rdbtnDelete = new JRadioButton("Delete", false);
		} else {
			rdbtnSave = new JRadioButton("Save", false);
			rdbtnSave.addActionListener(new defaultEnterActionListener());
			rdbtnDelete = new JRadioButton("Delete", true);
		}
		
		rdbtnSave.setToolTipText("Pressing enter will save data to database.");
		rdbtnSave.setFont(new Font("Dialog", Font.PLAIN, 16));
		rdbtnSave.setBounds(176, 172, 75, 20);
		frmPreferences.getContentPane().add(rdbtnSave);
		
		rdbtnDelete.addActionListener(new defaultEnterActionListener());
		rdbtnDelete.setToolTipText("Pressing enter will delete data from database.");
		rdbtnDelete.setFont(new Font("Dialog", Font.PLAIN, 16));
		rdbtnDelete.setBounds(284, 172, 75, 20);
		frmPreferences.getContentPane().add(rdbtnDelete);

		btnOK = new JButton("OK");
		btnOK.addActionListener(new closeActionListener());
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOK.setBounds(147, 250, 100, 30);
		frmPreferences.getContentPane().add(btnOK);

		ButtonGroup clearAllGroup = new ButtonGroup();
		clearAllGroup.add(rdbtnYes);
		clearAllGroup.add(rdbtnNo);

		ButtonGroup defaultClear = new ButtonGroup();
		defaultClear.add(rdbtnName);
		defaultClear.add(rdbtnToyID);

		ButtonGroup defaultEnter = new ButtonGroup();
		defaultEnter.add(rdbtnSave);
		defaultEnter.add(rdbtnDelete);
		
		frmPreferences.getRootPane().setDefaultButton(btnOK);
		
	}

	private class clearAllActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (rdbtnYes.isSelected()) {
				yesOrNo = "Clearing all will clear inspector's name as well.";
				myMain.isClearAllButton = true;
			} else {
				yesOrNo = "Clearing all will not clear inspector's name.";
				myMain.isClearAllButton = false;
			}
			lblClearText.setText(yesOrNo);
		}
	}

	private class defaultClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (rdbtnName.isSelected()) {
				nameOrToy = "Clearing fields will default back to inspector's name.";
				myMain.isNameButton = true;
			} else {
				nameOrToy = "Clearing fields will default back to Toy ID.";
				myMain.isNameButton = false;
			}
			lblFieldText.setText(nameOrToy);
		}
	}

	private class defaultEnterActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (rdbtnSave.isSelected()) {
				saveOrDelete = "Pressing enter will save data to database.";
				myMain.isSaveButton = true;
			} else {
				saveOrDelete = "Pressing enter will delete data from database.";
				myMain.isSaveButton = false;
			}
			lblKeyText.setText(saveOrDelete);
		}
	}
	
	private class closeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frmPreferences.dispose();
			myMain.setButton();
		}
	}
}
