package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bp.AerobicWorkout;
import bp.AnaerobicWorkout;
import bp.ExerciseDay;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.SystemColor;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

	private JFrame frame;
	private JTextField txtPersonName;
	private JTextField txtExerciseName_An;
	private JTextField txtAverageHR_An;
	private JTextField txtDifferential;
	private JTextField txtExerciseName_Ae;
	private JTextField txtAverageHR_Ae;
	private JTextField txtDistance;
	private JTextField txtStartTime;
	private JTextField txtEndTime;
	ExerciseDay myDay = new ExerciseDay();
	private JTextField txtDate;
	private JTextArea txtNotes_Ae;
	private JTextArea txtNotes_An;
	private JTextField comboSets;
	private JTextField comboReps;
	private JTextField comboWeight;
	private JList list_Ae;
	DefaultListModel<String> aerobicExerciseDone = new DefaultListModel<>();
	DefaultListModel<String> anaerobicExerciseDone = new DefaultListModel<>();
	private JList list_An;
	private JButton btnSave;
	private JButton btnAdd_Ae;
	private JButton btnRemove_Ae;
	private JButton btnAdd_An;
	private JButton btnRemove_An;
	private JButton btnClear;
	private JTextField txtTotalTime;
	private JTextField txtTotalTime_Ae;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            args
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
		frame.setBounds(100, 100, 450, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnSave = new JButton("Save");
		btnSave.setBackground(SystemColor.textHighlight);
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnSave.addActionListener(new BtnSaveActionListener());
		btnSave.setToolTipText("The button to save all information. Use ALT + S for shortcut.");
		btnSave.setBounds(171, 401, 89, 23);
		frame.getContentPane().add(btnSave);

		JLabel lblPurposeStatemnet = new JLabel(
				"<html> <center>The purpose of this program is to record the type, amount, and how long of an exercise a person completed. </html>");
		lblPurposeStatemnet.setHorizontalAlignment(SwingConstants.CENTER);
		lblPurposeStatemnet.setBounds(0, 0, 434, 58);
		frame.getContentPane().add(lblPurposeStatemnet);

		JLabel lblPersonName = new JLabel("Person Name:");
		lblPersonName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPersonName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPersonName.setBounds(106, 55, 104, 14);
		frame.getContentPane().add(lblPersonName);

		JTabbedPane tabExercises = new JTabbedPane(JTabbedPane.TOP);
		tabExercises.setBounds(0, 150, 434, 249);
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("The distance in meters a person completes in the workout.");
		tabExercises.addTab("Aerobic Exercises", panel_1);
		tabExercises.setToolTipTextAt(0, "ALT + E for quick switch.");
		tabExercises.setMnemonicAt(0, 69);
		panel_1.setLayout(null);

		JLabel label = new JLabel("Exercise Name:");
		label.setFont(new Font("Tahoma", Font.BOLD, 9));
		label.setBounds(10, 11, 82, 14);
		panel_1.add(label);

		JLabel label_1 = new JLabel("Average HR:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 9));
		label_1.setBounds(10, 36, 82, 14);
		panel_1.add(label_1);

		btnAdd_Ae = new JButton("Add ->");
		btnAdd_Ae
				.setToolTipText("Adds workouts tot he exercises completed list. All fields (except Notes) must be filled out correctly. Use ALT + A for shortcut.");
		btnAdd_Ae.setEnabled(false);
		btnAdd_Ae.setMnemonic(KeyEvent.VK_A);
		btnAdd_Ae.addActionListener(new BtnAdd_AeActionListener());
		btnAdd_Ae.addKeyListener(new BtnAdd_AeKeyListener());
		btnAdd_Ae.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAdd_Ae.setBounds(211, 32, 89, 23);
		btnAdd_Ae.setBackground(new Color(255, 0, 0));
		panel_1.add(btnAdd_Ae);

		JLabel label_2 = new JLabel("Exercises Completed");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setBounds(310, 11, 109, 14);
		panel_1.add(label_2);

		list_Ae = new JList();
		list_Ae.addMouseListener(new List_AeMouseListener());
		list_Ae.setBounds(310, 33, 119, 116);
		panel_1.add(list_Ae);

		btnRemove_Ae = new JButton("<-Remove");
		btnRemove_Ae
				.setToolTipText("Removes workouts from the exercises completed list. Press Save after remove to permanently remove from database. Use ALT + R for shortcut.");
		btnRemove_Ae.setMnemonic(KeyEvent.VK_R);
		btnRemove_Ae.setBackground(new Color(0, 191, 255));
		btnRemove_Ae.addActionListener(new BtnRemove_AeActionListener());
		btnRemove_Ae.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRemove_Ae.setBounds(211, 66, 89, 23);
		panel_1.add(btnRemove_Ae);

		JLabel lblDistancem = new JLabel("Distance (m):");
		lblDistancem.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblDistancem.setBounds(10, 70, 82, 14);
		panel_1.add(lblDistancem);

		JLabel lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTime.setBounds(36, 104, 46, 14);
		panel_1.add(lblTime);

		JLabel lblStartfinish = new JLabel("Start/End");
		lblStartfinish.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblStartfinish.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartfinish.setBounds(86, 104, 71, 14);
		panel_1.add(lblStartfinish);

		JLabel lblie = new JLabel(
				"<html>(<b>Format:</b> 06:30:00 AM - 07:15:30 AM)</htlm>");
		lblie.setToolTipText("");
		lblie.setHorizontalAlignment(SwingConstants.CENTER);
		lblie.setBounds(21, 116, 207, 14);
		panel_1.add(lblie);

		JLabel lblNotes_Ae = new JLabel("Notes:");
		lblNotes_Ae.setBounds(10, 160, 46, 14);
		panel_1.add(lblNotes_Ae);

		txtNotes_Ae = new JTextArea();
		txtNotes_Ae.setTabSize(2);
		txtNotes_Ae.setWrapStyleWord(true);
		txtNotes_Ae.setLineWrap(true);
		txtNotes_Ae
				.setToolTipText("Notes entered to give details about each workout. Press ALT + TAB and TAB again to leave the field.");
		txtNotes_Ae.addKeyListener(new ClearFields());
		txtNotes_Ae.addKeyListener(new BtnAdd_AeKeyListener());
		txtNotes_Ae.setBounds(10, 179, 200, 42);
		panel_1.add(txtNotes_Ae);
		txtNotes_Ae.setColumns(1);

		JLabel lblTotalTime_1 = new JLabel("Total Time:");
		lblTotalTime_1.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblTotalTime_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalTime_1.setBounds(320, 160, 71, 14);
		panel_1.add(lblTotalTime_1);

		txtTotalTime_Ae = new JTextField();
		txtTotalTime_Ae.setEditable(false);
		txtTotalTime_Ae.setBounds(310, 181, 86, 20);
		panel_1.add(txtTotalTime_Ae);
		txtTotalTime_Ae.setColumns(10);

		panel_3 = new JPanel();
		panel_3.setBounds(36, 8, 174, 141);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		txtExerciseName_Ae = new JTextField();
		txtExerciseName_Ae.setBounds(66, 0, 86, 20);
		panel_3.add(txtExerciseName_Ae);
		txtExerciseName_Ae.addKeyListener(new ClearFields());
		txtExerciseName_Ae.addKeyListener(new BtnAdd_AeKeyListener());
		txtExerciseName_Ae
				.setToolTipText("Enter the exercise name for aerobic exercise.");
		txtExerciseName_Ae.setColumns(10);

		txtAverageHR_Ae = new JTextField();
		txtAverageHR_Ae.setBounds(66, 25, 86, 20);
		panel_3.add(txtAverageHR_Ae);
		txtAverageHR_Ae.addKeyListener(new ClearFields());
		txtAverageHR_Ae.addKeyListener(new AverageHRKeyListener());
		txtAverageHR_Ae.addKeyListener(new BtnAdd_AeKeyListener());
		txtAverageHR_Ae
				.setToolTipText("Enter the average heartrate for aerobic exercise.");
		txtAverageHR_Ae.setColumns(10);

		txtDistance = new JTextField();
		txtDistance.setBounds(66, 59, 86, 20);
		panel_3.add(txtDistance);
		txtDistance.addKeyListener(new KeyListener());
		txtDistance.addKeyListener(new ClearFields());
		txtDistance.setColumns(10);

		txtStartTime = new JTextField();
		txtStartTime.setBounds(0, 121, 82, 20);
		panel_3.add(txtStartTime);
		txtStartTime
				.setToolTipText("The beginning time of the workout in the format of HH:MM:SS.");
		txtStartTime.addKeyListener(new BtnAdd_AeKeyListener());
		txtStartTime.addKeyListener(new ClearFields());
		txtStartTime.addKeyListener(new TimeKeyListener());
		txtStartTime.setColumns(10);

		txtEndTime = new JTextField();
		txtEndTime.setBounds(92, 121, 82, 20);
		panel_3.add(txtEndTime);
		txtEndTime
				.setToolTipText("The end time of the workout in the format of HH:MM:SS.");
		txtEndTime.addKeyListener(new ClearFields());
		txtEndTime.addKeyListener(new BtnAdd_AeKeyListener());
		txtEndTime.addKeyListener(new TimeKeyListener());
		txtEndTime.setColumns(10);
		frame.getContentPane().add(tabExercises);
		JPanel panel = new JPanel();
		tabExercises.addTab("Anaerobic Exercises", panel);
		tabExercises.setToolTipTextAt(1, "ALT + N for quick switch.");
		tabExercises.setMnemonicAt(1, 78);
		panel.setLayout(null);

		JLabel lblExerciseName = new JLabel("Exercise Name:");
		lblExerciseName.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblExerciseName.setBounds(10, 11, 82, 14);
		panel.add(lblExerciseName);

		JLabel lblAverageHr = new JLabel("Average HR:");
		lblAverageHr.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblAverageHr.setBounds(10, 36, 82, 14);
		panel.add(lblAverageHr);

		list_An = new JList();
		list_An.addMouseListener(new List_AnMouseListener());
		list_An.setBounds(310, 33, 119, 116);
		panel.add(list_An);

		btnAdd_An = new JButton("Add ->");
		btnAdd_An
				.setToolTipText("Adds workouts tot he exercises completed list. All fields (except Notes) must be filled out correctly. Use ALT + A for shortcut.");
		btnAdd_An.addKeyListener(new BtnAdd_AnKeyListener());
		btnAdd_An.setMnemonic(KeyEvent.VK_A);
		btnAdd_An.setEnabled(false);
		btnAdd_An.addActionListener(new BtnAdd_AnActionListener());
		btnAdd_An.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAdd_An.setBackground(new Color(255, 0, 0));
		btnAdd_An.setBounds(211, 32, 89, 23);
		panel.add(btnAdd_An);

		btnRemove_An = new JButton("<-Remove");
		btnRemove_An
				.setToolTipText("Removes workouts from the exercises completed list. Press Save after remove to permanently remove from database. Use ALT + R for shortcut.");
		btnRemove_An.setMnemonic(KeyEvent.VK_R);
		btnRemove_An.setBackground(new Color(0, 191, 255));
		btnRemove_An.addActionListener(new BtnRemove_AnActionListener());
		btnRemove_An.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRemove_An.setBounds(211, 66, 89, 23);
		panel.add(btnRemove_An);

		JLabel lblExercisesCompleted = new JLabel("Exercises Completed");
		lblExercisesCompleted.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblExercisesCompleted.setBounds(310, 11, 109, 14);
		panel.add(lblExercisesCompleted);

		JLabel lblSets = new JLabel("Sets:");
		lblSets.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSets.setBounds(10, 70, 46, 14);
		panel.add(lblSets);

		JLabel lblReps = new JLabel("Reps:");
		lblReps.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblReps.setBounds(102, 70, 46, 14);
		panel.add(lblReps);

		JLabel lblWeights = new JLabel("Weights (lbs):");
		lblWeights.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblWeights.setBounds(10, 115, 82, 14);
		panel.add(lblWeights);

		JLabel lblDifferential = new JLabel("Differential:");
		lblDifferential.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDifferential.setBounds(102, 115, 86, 14);
		panel.add(lblDifferential);

		JLabel lblNotes_An = new JLabel("Notes:");
		lblNotes_An.setBounds(10, 160, 46, 14);
		panel.add(lblNotes_An);

		txtNotes_An = new JTextArea();
		txtNotes_An.setWrapStyleWord(true);
		txtNotes_An.setTabSize(2);
		txtNotes_An.setLineWrap(true);
		txtNotes_An
				.setToolTipText("Notes entered to give details about each workout.  Press ALT + TAB and TAB again to leave the field.");
		txtNotes_An.addKeyListener(new ClearFields());
		txtNotes_An.setColumns(1);
		txtNotes_An.setBounds(10, 179, 200, 42);
		txtNotes_An.addKeyListener(new BtnAdd_AnKeyListener());
		panel.add(txtNotes_An);

		panel_4 = new JPanel();
		panel_4.setBounds(10, 8, 178, 141);
		panel.add(panel_4);
		panel_4.setLayout(null);

		txtExerciseName_An = new JTextField();
		txtExerciseName_An.setBounds(92, 0, 86, 20);
		panel_4.add(txtExerciseName_An);
		txtExerciseName_An
				.setToolTipText("Enter the exercise name for anaerobic exercise.");
		txtExerciseName_An.addKeyListener(new ClearFields());
		txtExerciseName_An.addKeyListener(new BtnAdd_AnKeyListener());
		txtExerciseName_An.setColumns(10);

		txtAverageHR_An = new JTextField();
		txtAverageHR_An.setBounds(92, 25, 86, 20);
		panel_4.add(txtAverageHR_An);
		txtAverageHR_An.addKeyListener(new AverageHRKeyListener());
		txtAverageHR_An.addKeyListener(new ClearFields());
		txtAverageHR_An
				.setToolTipText("Enter the average heartrate for anaerobic exercise.");
		txtAverageHR_An.setColumns(10);

		comboSets = new JTextField();
		comboSets.setBounds(0, 79, 82, 20);
		panel_4.add(comboSets);
		comboSets.setToolTipText("Enter the amount of sets for each workout.");
		comboSets.setEditable(true);

		comboReps = new JTextField();
		comboReps.setBounds(92, 79, 82, 20);
		panel_4.add(comboReps);
		comboReps.setToolTipText("Enter in the amount of reps for each set.");
		comboReps.setEditable(true);

		comboWeight = new JTextField();
		comboWeight.setBounds(0, 121, 82, 20);
		panel_4.add(comboWeight);
		comboWeight.addKeyListener(new AverageHRKeyListener());
		comboWeight.addKeyListener(new ClearFields());
		comboWeight.setToolTipText("Enter the amout of weigth (pounds).");
		comboWeight.setEditable(true);

		txtDifferential = new JTextField();
		txtDifferential.setBounds(92, 121, 86, 20);
		panel_4.add(txtDifferential);
		txtDifferential.addKeyListener(new KeyListener());
		txtDifferential.addKeyListener(new ClearFields());
		txtDifferential
				.setToolTipText("Enter the differential for the last reps completed.");
		txtDifferential.addKeyListener(new BtnAdd_AnKeyListener());
		txtDifferential.setColumns(10);
		comboReps.addKeyListener(new ClearFields());
		comboReps.addKeyListener(new AverageHRKeyListener());
		comboSets.addKeyListener(new ClearFields());
		comboSets.addKeyListener(new AverageHRKeyListener());
		txtAverageHR_An.addKeyListener(new BtnAdd_AnKeyListener());

		JLabel lblDate = new JLabel("Date:");
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(106, 86, 104, 14);
		frame.getContentPane().add(lblDate);

		JLabel lblformatMmddyyyy = new JLabel(
				"<html>(Format: MM<b>/</b>DD<b>/</b>YYYY)</html>");
		lblformatMmddyyyy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblformatMmddyyyy.setBounds(309, 87, 135, 14);
		frame.getContentPane().add(lblformatMmddyyyy);

		btnClear = new JButton("Clear");
		btnClear.setMnemonic('C');
		btnClear.setToolTipText("<html>Press <b> ESCAPE </b> to clear ALL fields (except Person Name and Date) when in a field.</html>");
		btnClear.addActionListener(new BtnClearActionListener());
		btnClear.setBackground(Color.WHITE);
		btnClear.setBounds(340, 116, 65, 23);
		frame.getContentPane().add(btnClear);

		JButton btnLoad = new JButton("Load");
		btnLoad.setToolTipText("Fill in a correct name and date and press load in order to load exercises that are saved to the database. ALT + L for shortcut.");
		btnLoad.setMnemonic('L');
		btnLoad.setBackground(new Color(0, 204, 255));
		btnLoad.addActionListener(new BtnLoadActionListener());
		btnLoad.setBounds(316, 52, 89, 23);
		frame.getContentPane().add(btnLoad);

		JLabel lblTotalTime = new JLabel("Total Time:");
		lblTotalTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalTime.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalTime.setBounds(134, 125, 76, 14);
		frame.getContentPane().add(lblTotalTime);

		txtTotalTime = new JTextField();
		txtTotalTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTotalTime.setEnabled(false);
		txtTotalTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalTime.setText("<calculated>");
		txtTotalTime.setEditable(false);
		txtTotalTime.setBounds(220, 119, 86, 20);
		frame.getContentPane().add(txtTotalTime);
		txtTotalTime.setColumns(10);

		panel_2 = new JPanel();
		panel_2.setBounds(220, 53, 86, 51);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		txtPersonName = new JTextField();
		txtPersonName.setBounds(0, 0, 86, 20);
		panel_2.add(txtPersonName);
		txtPersonName.addKeyListener(new BtnAdd_AeKeyListener());
		txtPersonName.addKeyListener(new BtnAdd_AnKeyListener());
		txtPersonName
				.setToolTipText("Enter the name of the person doing the exercises.");
		txtPersonName.setColumns(10);

		txtDate = new JTextField();
		txtDate.setBounds(0, 31, 86, 20);
		panel_2.add(txtDate);
		txtDate.addKeyListener(new BtnAdd_AeKeyListener());
		txtDate.addKeyListener(new BtnAdd_AnKeyListener());
		txtDate.setToolTipText("Enter the name of the person doing the exercises.");
		txtDate.addKeyListener(new DateKeyListener());
		txtDate.setColumns(10);

		JLabel lblWhenOnA = new JLabel(
				"<html><center>When on a button, press <br><b>SPACE</b> to click.</html>");
		lblWhenOnA.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhenOnA.setBounds(10, 87, 148, 41);
		frame.getContentPane().add(lblWhenOnA);
	}

	private class BtnAdd_AeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			AerobicWorkout myWorkout = new AerobicWorkout();
			try {
				myWorkout.setAverageHeartRate(Integer.parseInt(txtAverageHR_Ae
						.getText()));
				myWorkout
						.setDistance(Double.parseDouble(txtDistance.getText()));
				myWorkout.setExerciseName(txtExerciseName_Ae.getText());
				myWorkout.setNotes(txtNotes_Ae.getText());
				String dateTimeStart = txtDate.getText() + " "
						+ txtStartTime.getText();
				String dateTimeEnd = txtDate.getText() + " "
						+ txtEndTime.getText();

				myWorkout.setStartTime(new Date(dateTimeStart));
				myWorkout.setEndTime(new Date(dateTimeEnd));
				myDay.addAerobicWorkout(myWorkout);
				clearFields_Ae();

			} catch (Exception e) {
				JOptionPane
						.showMessageDialog(
								null,
								"<html><center><p>Please enter the correct Time Start and End format"
										+ " <br> OR <br> Check the format of your <b>Date</b>.</p></html>");
			}

			// refresh gui
			refreshAerobicList();
		}
	}

	private class BtnSaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// myday.setproperties
			try {
				myDay.setExerciseDate(new Date(txtDate.getText()));
				myDay.setPersonName(txtPersonName.getText());

				myDay.save();
			} catch (IllegalArgumentException e1) {
				JOptionPane
						.showMessageDialog(
								null,
								"<html><center><p>Please <b>Add</b> a workout before saving <br> OR <br> Check the format of your <b>Date</b>.</p></html>");
			}
		}
	}

	private class BtnAdd_AnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AnaerobicWorkout myWorkout = new AnaerobicWorkout();
			try {
				// myWorkout.setPropertiesFromTextBoxes
				myWorkout.setAverageHeartRate(Integer.parseInt(txtAverageHR_An
						.getText()));
				myWorkout.setExerciseName(txtExerciseName_An.getText());
				myWorkout.setNotes(txtNotes_An.getText());
				myWorkout.setDifferential(Integer.parseInt(txtDifferential
						.getText()));
				myWorkout.setReps(Integer.parseInt(comboReps.getText()));
				myWorkout.setSets(Integer.parseInt(comboSets.getText()));
				myWorkout.setWeight(Integer.parseInt(comboWeight.getText()));
				myDay.addAnaerobicWorkout(myWorkout);
				clearFields_An();
			} catch (Exception ea) {
				JOptionPane
						.showMessageDialog(
								null,
								"<html><center><p>Please enter the correct Time Start and End format"
										+ " <br> OR <br> Check the format of your <b>Date</b>.</p></html>");
			}
			// refresh gui
			refreshAnaerobicList();

		}
	}

	private class BtnRemove_AnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (list_An.isSelectionEmpty()) {
				JOptionPane
						.showMessageDialog(null,
								"Please Add or Select a workout before trying to Remove one.");
			} else {
				myDay.removeAnaerobicWorkout(list_An.getSelectedIndex());
				refreshAnaerobicList();

			}
		}
	}

	private class BtnRemove_AeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (list_Ae.isSelectionEmpty()) {
				JOptionPane
						.showMessageDialog(null,
								"Please Add or Select a workout before trying to Remove one.");
			} else {
				myDay.removeAerobicWorkout(list_Ae.getSelectedIndex());
				refreshAerobicList();

			}
		}
	}

	private class BtnAdd_AeKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			setAdd_AeEnabled();
		}
	}

	private class BtnAdd_AnKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			setAdd_AnEnabled();
		}
	}

	/**
	 * Refresh the list of aerobic exercises the user sees from the bp.
	 */
	void refreshAerobicList() {
		aerobicExerciseDone.clear();
		for (int n = 0; n < myDay.getAerobicWorkouts().size(); ++n) {
			aerobicExerciseDone.add(n, myDay.getAerobicWorkout(n)
					.getExerciseName());
		}
		list_Ae.setModel(aerobicExerciseDone);
	}

	void refreshAnaerobicList() {
		anaerobicExerciseDone.clear();
		for (int n = 0; n < myDay.getAnaerobicWorkouts().size(); ++n) {
			anaerobicExerciseDone.add(n, myDay.getAnaerobicWorkout(n)
					.getExerciseName());
		}
		list_An.setModel(anaerobicExerciseDone);
	}

	void clearFields_Ae() {
		txtEndTime.setText("");
		txtExerciseName_Ae.setText("");
		txtAverageHR_Ae.setText("");
		txtDistance.setText("");
		txtNotes_Ae.setText("");
		txtStartTime.setText("");
		txtExerciseName_Ae.requestFocus();
		setAdd_AeEnabled();
	}

	void clearFields_An() {
		comboReps.setText("");
		comboSets.setText("");
		comboWeight.setText("");
		txtExerciseName_An.setText("");
		txtAverageHR_An.setText("");
		txtNotes_An.setText("");
		txtDifferential.setText("");
		txtExerciseName_An.requestFocus();
		setAdd_AnEnabled();
	}

	void setAdd_AeEnabled() {
		int fieldsfull = 0;

		if (txtDate.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtPersonName.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtEndTime.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtExerciseName_Ae.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtAverageHR_Ae.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtDistance.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtStartTime.getText().length() > 0) {
			fieldsfull++;
		}
		if (fieldsfull >= 7) {
			btnAdd_Ae.setEnabled(true);
			btnAdd_Ae.setBackground(new Color(50, 205, 50));
		} else {
			btnAdd_Ae.setEnabled(false);
			btnAdd_Ae.setBackground(new Color(255, 0, 0));
		}
	}

	void setAdd_AnEnabled() {
		int fieldsfull = 0;

		if (txtDate.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtPersonName.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtExerciseName_An.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtAverageHR_An.getText().length() > 0) {
			fieldsfull++;
		}
		if (txtDifferential.getText().length() > 0) {
			fieldsfull++;
		}
		if (comboSets.getText().length() > 0) {
			fieldsfull++;
		}
		if (comboWeight.getText().length() > 0) {
			fieldsfull++;
		}
		if (comboReps.getText().length() > 0) {
			fieldsfull++;
		}
		if (fieldsfull >= 8) {
			btnAdd_An.setEnabled(true);
			btnAdd_An.setBackground(new Color(50, 205, 50));
		} else {
			btnAdd_An.setEnabled(false);
			btnAdd_An.setBackground(new Color(255, 0, 0));
		}
	}

	private class AverageHRKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent a) {
			if (a.getKeyChar() < KeyEvent.VK_0
					|| a.getKeyChar() > KeyEvent.VK_9) {
				a.consume();
			}
		}
	}

	private class KeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent a) {
			if (a.getKeyChar() < KeyEvent.VK_0
					|| a.getKeyChar() > KeyEvent.VK_9) {
				if (a.getKeyChar() != KeyEvent.VK_PERIOD
						|| (a.getKeyChar() == KeyEvent.VK_PERIOD && ((JTextField) a
								.getSource()).getText().contains("."))) {
					a.consume();
				}
			}
		}
	}

	private class TimeKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent a) {
			if (a.getKeyChar() < KeyEvent.VK_0
					|| a.getKeyChar() > KeyEvent.VK_9) {
				if (a.getKeyChar() != (':'))
					a.consume();
			}
		}
	}

	private class DateKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent a) {
			if (a.getKeyChar() < KeyEvent.VK_0
					|| a.getKeyChar() > KeyEvent.VK_9) {
				if (a.getKeyChar() != KeyEvent.VK_SLASH) {
					a.consume();
				}
			}
		}
	}

	private class ClearFields extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent a) {
			if (a.getKeyChar() == KeyEvent.VK_ESCAPE) {
				clearFields_Ae();
				clearFields_An();
			}
		}
	}

	private class BtnClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			clearFields_Ae();
			clearFields_An();
		}
	}

	private class BtnLoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			try {
				myDay.load(txtPersonName.getText(), new Date(txtDate.getText()));
				myDay.getTotalTime();
				txtTotalTime.setText(String.valueOf(myDay.getTotalTime()));
			} catch (Exception e) {
				JOptionPane
						.showMessageDialog(
								null,
								"<html><center>Please <b>Enter</b> a name or the correct date.</html>");
			}
			// refresh gui now
			refreshAerobicList();
			refreshAnaerobicList();
		}
	}

	private class List_AeMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			AerobicWorkout w = myDay.getAerobicWorkout(list_Ae
					.getSelectedIndex());
			txtStartTime.setText(String.valueOf(w.getStartTime()));
			txtEndTime.setText(String.valueOf(w.getEndTime()));
			txtTotalTime_Ae.setText(String.valueOf(w.getTotalTime()));
			txtAverageHR_Ae.setText(String.valueOf(w.getAverageHeartRate()));
			txtExerciseName_Ae.setText(String.valueOf(w.getExerciseName()));
			txtNotes_Ae.setText(String.valueOf(w.getNotes()));
			txtDistance.setText(String.valueOf(w.getDistance()));
		}
	}

	private class List_AnMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			AnaerobicWorkout w = myDay.getAnaerobicWorkout(list_An
					.getSelectedIndex());
			txtAverageHR_An.setText(String.valueOf(w.getAverageHeartRate()));
			txtExerciseName_An.setText(String.valueOf(w.getExerciseName()));
			txtNotes_An.setText(String.valueOf(w.getNotes()));
			comboReps.setText(String.valueOf(w.getReps()));
			comboSets.setText(String.valueOf(w.getSets()));
			comboWeight.setText(String.valueOf(w.getWeight()));
			txtDifferential.setText(String.valueOf(w.getDifferential()));

		}
	}
}
