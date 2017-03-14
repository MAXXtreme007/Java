package gui;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.time.LocalDate;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JComboBox;

import bp.ExerciseCardio;
import bp.ExerciseCompetitive;
import bp.ExerciseDay;
import bp.ExerciseStrength;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;

public class Main {

	private JFrame frmFitnessTracker;
	private JTextField txtName;
	private JTextField txtDate;
	private Date creationTime = new Date();
	private JTextField txtRestingHR;
	private JTextField txtWeight;
	private JTextField txtReps;
	private JTextField txtSets;
	private JTextField txtWeightLifted;
	private JTextField txtEventName1;
	private JTextField txtEventName2;
	private JTextField txtEventName3;
	private JTextField txtExerciseTime1;
	private JTextField txtDistance;
	private JTextField txtHeartRate;
	private JButton btnAdd1;
	private JButton btnRemove1;
	private JButton btnSave1;
	private JButton btnLoad1;
	private JButton btnDelete1;
	private JButton btnAdd2;
	private JButton btnRemove2;
	private JButton btnSave2;
	private JButton btnLoad2;
	private JButton btnDelete2;
	private JButton btnAdd3;
	private JButton btnRemove3;
	private JButton btnSave3;
	private JButton btnLoad3;
	private JButton btnDelete3;
	private JList<?> listFitnessToday1;
	private JList<?> listFitnessToday3;
	private JList<?> listFitnessToday2;
	ExerciseDay myDay = new ExerciseDay();
	ExerciseCardio myCardio = new ExerciseCardio();
	ExerciseCompetitive myCompetitive = new ExerciseCompetitive();
	ExerciseStrength myStrength = new ExerciseStrength();
	private JComboBox<Object> comboBoxDistance;
	private JComboBox<Object> comboBoxWeight;
	@SuppressWarnings("rawtypes")
	private DefaultListModel cardioModel = new DefaultListModel();
	@SuppressWarnings("rawtypes")
	private DefaultListModel strengthModel = new DefaultListModel();
	@SuppressWarnings("rawtypes")
	private DefaultListModel competitiveModel = new DefaultListModel();
	private JComboBox<Object> comboBoxTime1;
	// RADIO BUTTONS
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JRadioButton rdbtnDefaultClearAll;
	private JRadioButton rdbtnDefaultClearPersonal;
	private JRadioButton rdbtnDefaultClearCardio;
	private JRadioButton rdbtnDefaultClearStrength;
	private JRadioButton rdbtnDefaultClearCompetitive;
	private JRadioButton rdbtnName;
	private JRadioButton rdbtnRestingHr;
	private JRadioButton rdbtnWeight;
	private JRadioButton rdbtnCardioEventName;
	private JRadioButton rdbtnCardioTime;
	private JRadioButton rdbtnCardioDistance;
	private JRadioButton rdbtnCardioHeartRate;
	private JRadioButton rdbtnActionAdd;
	private JRadioButton rdbtnActionRemove;
	private JRadioButton rdbtnActionSave;
	private JRadioButton rdbtnActionLoad;
	private JRadioButton rdbtnActionDelete;
	// BUTTON GROUPS
	private ButtonGroup unitsGroup;
	private ButtonGroup victoriousGroup;
	private ButtonGroup defaultClearGroup;
	private ButtonGroup defaultFieldPersonalGroup;
	private ButtonGroup defaultFieldCardioGroup;
	private ButtonGroup defaultFieldStrengthGroup;
	private ButtonGroup defaultFieldCompetitiveGroup;
	private ButtonGroup defaultActionGroup;
	// VALUE CHANGERS
	private int loadPassed = 0;
	protected int personalFieldCounter;
	protected int cardioFieldCounter;
	protected int strengthFieldCounter;
	protected int competitiveFieldCounter;
	protected int victoriousInt;
	protected String victorious;
	protected int defaultClear = 1;
	protected int defaultAction = 1;
	protected int defaultPersonalField = 1;
	protected int defaultCardioField = 1;
	protected int defaultStrengthField = 1;
	protected int defaultCompetitiveField = 1;
	protected int defaultUnit = 1;
	private JTextField txtInfo;
	private JPanel infoPanel;
	private double totalTime;
	private JTextField txtExerciseTime2;
	private JTextField txtExerciseTime3;
	private JLabel lblTime2;
	private JComboBox<Object> comboBoxTime2;
	private JLabel lblTime3;
	private JComboBox<Object> comboBoxTime3;
	private JRadioButton rdbtnStrengthTime;
	private JRadioButton rdbtnImperial;
	private JRadioButton rdbtnMetric;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmFitnessTracker.setVisible(true);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new GlobalKeyListener());

		frmFitnessTracker = new JFrame();
		frmFitnessTracker.setResizable(false);
		frmFitnessTracker.setTitle("Fitness Tracker");
		frmFitnessTracker.setBounds(100, 100, 650, 500);
		frmFitnessTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFitnessTracker.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 644, 21);
		frmFitnessTracker.getContentPane().add(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(mnFile);

		JMenu mnClear = new JMenu("Clear");
		mnClear.setPreferredSize(new Dimension(100, 22));
		mnFile.add(mnClear);

		JButton btnClearPersonal = new JButton("Personal");
		btnClearPersonal.addActionListener(new BtnPersonalActionListener());

		JButton btnClearAll = new JButton("All");
		btnClearAll.addActionListener(new BtnClearAllActionListener());
		btnClearAll.setToolTipText("This will clear all fields");
		btnClearAll.setPreferredSize(new Dimension(100, 23));
		btnClearAll.setMinimumSize(new Dimension(100, 23));
		btnClearAll.setMaximumSize(new Dimension(100, 23));
		btnClearAll.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnClear.add(btnClearAll);
		btnClearPersonal.setToolTipText("This will clear all personal fields");
		btnClearPersonal.setPreferredSize(new Dimension(100, 23));
		btnClearPersonal.setMinimumSize(new Dimension(100, 23));
		btnClearPersonal.setMaximumSize(new Dimension(100, 23));
		btnClearPersonal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnClear.add(btnClearPersonal);

		JButton btnClearCardio = new JButton("Cardio");
		btnClearCardio.addActionListener(new BtnClearCardioActionListener());
		btnClearCardio.setToolTipText("This will clear all cardio fields");
		btnClearCardio.setPreferredSize(new Dimension(100, 23));
		btnClearCardio.setMinimumSize(new Dimension(100, 23));
		btnClearCardio.setMaximumSize(new Dimension(100, 23));
		btnClearCardio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnClear.add(btnClearCardio);

		JButton btnClearStrength = new JButton("Strength");
		btnClearStrength.addActionListener(new BtnClearStrengthActionListener());
		btnClearStrength.setToolTipText("This will clear all strength fields");
		btnClearStrength.setPreferredSize(new Dimension(100, 23));
		btnClearStrength.setMinimumSize(new Dimension(100, 23));
		btnClearStrength.setMaximumSize(new Dimension(100, 23));
		btnClearStrength.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnClear.add(btnClearStrength);

		JButton btnClearCompetitive = new JButton("Competitive");
		btnClearCompetitive.addActionListener(new BtnClearCompetitiveActionListener());
		btnClearCompetitive.setToolTipText("This will clear all competitive fields");
		btnClearCompetitive.setPreferredSize(new Dimension(100, 23));
		btnClearCompetitive.setMinimumSize(new Dimension(100, 23));
		btnClearCompetitive.setMaximumSize(new Dimension(100, 23));
		btnClearCompetitive.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnClear.add(btnClearCompetitive);

		JButton btnClearLists = new JButton("Lists");
		btnClearLists.addActionListener(new BtnClearListsActionListener());
		btnClearLists.setPreferredSize(new Dimension(100, 23));
		btnClearLists.setMinimumSize(new Dimension(100, 23));
		btnClearLists.setMaximumSize(new Dimension(100, 23));
		btnClearLists.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnClear.add(btnClearLists);

		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(mnEdit);

		JMenu mnPreferences = new JMenu("Preferences");
		mnPreferences.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnEdit.add(mnPreferences);

		JMenu mnDefaultClear = new JMenu("Default Clear");
		mnDefaultClear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnPreferences.add(mnDefaultClear);

		rdbtnDefaultClearAll = new JRadioButton("All");
		rdbtnDefaultClearAll.setSelected(true);
		rdbtnDefaultClearAll.setToolTipText("Pressing enter will clear all fields");
		rdbtnDefaultClearAll.setPreferredSize(new Dimension(100, 23));
		rdbtnDefaultClearAll.setMinimumSize(new Dimension(100, 23));
		rdbtnDefaultClearAll.setMaximumSize(new Dimension(100, 23));
		rdbtnDefaultClearAll.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultClear.add(rdbtnDefaultClearAll);

		rdbtnDefaultClearPersonal = new JRadioButton("Personal");
		rdbtnDefaultClearPersonal.setToolTipText("Pressing enter will only clear personal fields");
		rdbtnDefaultClearPersonal.setPreferredSize(new Dimension(100, 23));
		rdbtnDefaultClearPersonal.setMinimumSize(new Dimension(100, 23));
		rdbtnDefaultClearPersonal.setMaximumSize(new Dimension(100, 23));
		rdbtnDefaultClearPersonal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultClear.add(rdbtnDefaultClearPersonal);

		rdbtnDefaultClearCardio = new JRadioButton("Cardio");
		rdbtnDefaultClearCardio.setToolTipText("Pressing enter will only clear cardio fields");
		rdbtnDefaultClearCardio.setPreferredSize(new Dimension(100, 23));
		rdbtnDefaultClearCardio.setMinimumSize(new Dimension(100, 23));
		rdbtnDefaultClearCardio.setMaximumSize(new Dimension(100, 23));
		rdbtnDefaultClearCardio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultClear.add(rdbtnDefaultClearCardio);

		rdbtnDefaultClearStrength = new JRadioButton("Strength");
		rdbtnDefaultClearStrength.setToolTipText("Pressing enter will only clear strength fields");
		rdbtnDefaultClearStrength.setPreferredSize(new Dimension(100, 23));
		rdbtnDefaultClearStrength.setMinimumSize(new Dimension(100, 23));
		rdbtnDefaultClearStrength.setMaximumSize(new Dimension(100, 23));
		rdbtnDefaultClearStrength.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultClear.add(rdbtnDefaultClearStrength);

		rdbtnDefaultClearCompetitive = new JRadioButton("Competitive");
		rdbtnDefaultClearCompetitive.setToolTipText("Pressing enter will only clear competitive fields");
		rdbtnDefaultClearCompetitive.setPreferredSize(new Dimension(100, 23));
		rdbtnDefaultClearCompetitive.setMinimumSize(new Dimension(100, 23));
		rdbtnDefaultClearCompetitive.setMaximumSize(new Dimension(100, 23));
		rdbtnDefaultClearCompetitive.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultClear.add(rdbtnDefaultClearCompetitive);

		defaultClearGroup = new ButtonGroup();
		defaultClearGroup.add(rdbtnDefaultClearAll);
		defaultClearGroup.add(rdbtnDefaultClearPersonal);
		defaultClearGroup.add(rdbtnDefaultClearCardio);
		defaultClearGroup.add(rdbtnDefaultClearStrength);
		defaultClearGroup.add(rdbtnDefaultClearCompetitive);
		if (rdbtnDefaultClearAll.isSelected()) {
			defaultClear = 1;
		} else if (rdbtnDefaultClearPersonal.isSelected()) {
			defaultClear = 2;
		} else if (rdbtnDefaultClearCardio.isSelected()) {
			defaultClear = 3;
		} else if (rdbtnDefaultClearStrength.isSelected()) {
			defaultClear = 4;
		} else if (rdbtnDefaultClearCompetitive.isSelected()) {
			defaultClear = 5;
		}

		JMenu mnDefaultField = new JMenu("Default Field");
		mnDefaultField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnPreferences.add(mnDefaultField);

		JMenu mnPersonal = new JMenu("Personal");
		mnDefaultField.add(mnPersonal);

		rdbtnName = new JRadioButton("Name");
		rdbtnName.setSelected(true);
		mnPersonal.add(rdbtnName);
		rdbtnName.setToolTipText("Upon clearing, the default field will be name");
		rdbtnName.setPreferredSize(new Dimension(100, 23));
		rdbtnName.setMinimumSize(new Dimension(100, 23));
		rdbtnName.setMaximumSize(new Dimension(100, 23));
		rdbtnName.setFont(new Font("Tahoma", Font.PLAIN, 12));

		rdbtnRestingHr = new JRadioButton("Resting HR");
		mnPersonal.add(rdbtnRestingHr);
		rdbtnRestingHr.setToolTipText("Upon clearing, the default field will be resting HR");
		rdbtnRestingHr.setPreferredSize(new Dimension(100, 23));
		rdbtnRestingHr.setMinimumSize(new Dimension(100, 23));
		rdbtnRestingHr.setMaximumSize(new Dimension(100, 23));
		rdbtnRestingHr.setFont(new Font("Tahoma", Font.PLAIN, 12));

		rdbtnWeight = new JRadioButton("Weight");
		mnPersonal.add(rdbtnWeight);
		rdbtnWeight.setToolTipText("Upon clearing, the default field will be weight");
		rdbtnWeight.setPreferredSize(new Dimension(100, 23));
		rdbtnWeight.setMinimumSize(new Dimension(100, 23));
		rdbtnWeight.setMaximumSize(new Dimension(100, 23));
		rdbtnWeight.setFont(new Font("Tahoma", Font.PLAIN, 12));

		defaultFieldPersonalGroup = new ButtonGroup();
		defaultFieldPersonalGroup.add(rdbtnName);
		defaultFieldPersonalGroup.add(rdbtnRestingHr);
		defaultFieldPersonalGroup.add(rdbtnWeight);
		if (rdbtnName.isSelected()) {
			defaultPersonalField = 1;
		} else if (rdbtnRestingHr.isSelected()) {
			defaultPersonalField = 2;
		} else if (rdbtnWeight.isSelected()) {
			defaultPersonalField = 3;
		}

		JMenu mnCardio = new JMenu("Cardio");
		mnDefaultField.add(mnCardio);

		rdbtnCardioEventName = new JRadioButton("Event Name");
		rdbtnCardioEventName.setSelected(true);
		rdbtnCardioEventName.setToolTipText("Upon clearing, the default field will be name");
		rdbtnCardioEventName.setPreferredSize(new Dimension(100, 23));
		rdbtnCardioEventName.setMinimumSize(new Dimension(100, 23));
		rdbtnCardioEventName.setMaximumSize(new Dimension(100, 23));
		rdbtnCardioEventName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCardio.add(rdbtnCardioEventName);

		rdbtnCardioTime = new JRadioButton("Time");
		rdbtnCardioTime.setToolTipText("Upon clearing, the default field will be resting HR");
		rdbtnCardioTime.setPreferredSize(new Dimension(100, 23));
		rdbtnCardioTime.setMinimumSize(new Dimension(100, 23));
		rdbtnCardioTime.setMaximumSize(new Dimension(100, 23));
		rdbtnCardioTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCardio.add(rdbtnCardioTime);

		rdbtnCardioDistance = new JRadioButton("Distance");
		rdbtnCardioDistance.setToolTipText("Upon clearing, the default field will be weight");
		rdbtnCardioDistance.setPreferredSize(new Dimension(100, 23));
		rdbtnCardioDistance.setMinimumSize(new Dimension(100, 23));
		rdbtnCardioDistance.setMaximumSize(new Dimension(100, 23));
		rdbtnCardioDistance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCardio.add(rdbtnCardioDistance);

		rdbtnCardioHeartRate = new JRadioButton("Heart Rate");
		rdbtnCardioHeartRate.setToolTipText("Upon clearing, the default field will be weight");
		rdbtnCardioHeartRate.setPreferredSize(new Dimension(100, 23));
		rdbtnCardioHeartRate.setMinimumSize(new Dimension(100, 23));
		rdbtnCardioHeartRate.setMaximumSize(new Dimension(100, 23));
		rdbtnCardioHeartRate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCardio.add(rdbtnCardioHeartRate);

		defaultFieldCardioGroup = new ButtonGroup();
		defaultFieldCardioGroup.add(rdbtnCardioEventName);
		defaultFieldCardioGroup.add(rdbtnCardioTime);
		defaultFieldCardioGroup.add(rdbtnCardioDistance);
		defaultFieldCardioGroup.add(rdbtnCardioHeartRate);
		if (rdbtnCardioEventName.isSelected()) {
			defaultCardioField = 1;
		} else if (rdbtnCardioTime.isSelected()) {
			defaultCardioField = 2;
		} else if (rdbtnCardioDistance.isSelected()) {
			defaultCardioField = 3;
		} else if (rdbtnCardioHeartRate.isSelected()) {
			defaultCardioField = 4;
		}

		JMenu mnStrength = new JMenu("Strength");
		mnDefaultField.add(mnStrength);

		JRadioButton rdbtnStrengthEventName = new JRadioButton("Event Name");
		rdbtnStrengthEventName.setSelected(true);
		rdbtnStrengthEventName.setToolTipText("Upon clearing, the default field will be name");
		rdbtnStrengthEventName.setPreferredSize(new Dimension(100, 23));
		rdbtnStrengthEventName.setMinimumSize(new Dimension(100, 23));
		rdbtnStrengthEventName.setMaximumSize(new Dimension(100, 23));
		rdbtnStrengthEventName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnStrength.add(rdbtnStrengthEventName);
		
		rdbtnStrengthTime = new JRadioButton("Time");
		rdbtnStrengthTime.setToolTipText("Upon clearing, the default field will be resting HR");
		rdbtnStrengthTime.setPreferredSize(new Dimension(100, 23));
		rdbtnStrengthTime.setMinimumSize(new Dimension(100, 23));
		rdbtnStrengthTime.setMaximumSize(new Dimension(100, 23));
		rdbtnStrengthTime.setFont(new Font("", Font.PLAIN, 12));
		mnStrength.add(rdbtnStrengthTime);

		JRadioButton rdbtnStrengthReps = new JRadioButton("Reps");
		rdbtnStrengthReps.setToolTipText("Upon clearing, the default field will be resting HR");
		rdbtnStrengthReps.setPreferredSize(new Dimension(100, 23));
		rdbtnStrengthReps.setMinimumSize(new Dimension(100, 23));
		rdbtnStrengthReps.setMaximumSize(new Dimension(100, 23));
		rdbtnStrengthReps.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnStrength.add(rdbtnStrengthReps);

		JRadioButton rdbtnStrengthSets = new JRadioButton("Sets");
		rdbtnStrengthSets.setToolTipText("Upon clearing, the default field will be weight");
		rdbtnStrengthSets.setPreferredSize(new Dimension(100, 23));
		rdbtnStrengthSets.setMinimumSize(new Dimension(100, 23));
		rdbtnStrengthSets.setMaximumSize(new Dimension(100, 23));
		rdbtnStrengthSets.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnStrength.add(rdbtnStrengthSets);

		JRadioButton rdbtnStrengthWeight = new JRadioButton("Weight");
		rdbtnStrengthWeight.setToolTipText("Upon clearing, the default field will be weight");
		rdbtnStrengthWeight.setPreferredSize(new Dimension(100, 23));
		rdbtnStrengthWeight.setMinimumSize(new Dimension(100, 23));
		rdbtnStrengthWeight.setMaximumSize(new Dimension(100, 23));
		rdbtnStrengthWeight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnStrength.add(rdbtnStrengthWeight);

		defaultFieldStrengthGroup = new ButtonGroup();
		defaultFieldStrengthGroup.add(rdbtnStrengthEventName);
		defaultFieldStrengthGroup.add(rdbtnStrengthReps);
		defaultFieldStrengthGroup.add(rdbtnStrengthSets);
		defaultFieldStrengthGroup.add(rdbtnStrengthWeight);
		if (rdbtnStrengthEventName.isSelected()) {
			defaultStrengthField = 1;
		} else if (rdbtnStrengthTime.isSelected()) {
			defaultStrengthField = 2;
		} else if (rdbtnStrengthReps.isSelected()) {
			defaultStrengthField = 3;
		} else if (rdbtnStrengthSets.isSelected()) {
			defaultStrengthField = 4;
		} else if (rdbtnStrengthWeight.isSelected()) {
			defaultStrengthField = 5;
		}

		JMenu mnCompetitive = new JMenu("Competitive");
		mnDefaultField.add(mnCompetitive);

		JRadioButton rdbtnCompetitiveEventName = new JRadioButton("Event Name");
		rdbtnCompetitiveEventName.setSelected(true);
		rdbtnCompetitiveEventName.setToolTipText("Upon clearing, the default field will be name");
		rdbtnCompetitiveEventName.setPreferredSize(new Dimension(100, 23));
		rdbtnCompetitiveEventName.setMinimumSize(new Dimension(100, 23));
		rdbtnCompetitiveEventName.setMaximumSize(new Dimension(100, 23));
		rdbtnCompetitiveEventName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCompetitive.add(rdbtnCompetitiveEventName);

		JRadioButton rdbtnCompetitiveTime = new JRadioButton("Time");
		rdbtnCompetitiveTime.setToolTipText("Upon clearing, the default field will be weight");
		rdbtnCompetitiveTime.setPreferredSize(new Dimension(100, 23));
		rdbtnCompetitiveTime.setMinimumSize(new Dimension(100, 23));
		rdbtnCompetitiveTime.setMaximumSize(new Dimension(100, 23));
		rdbtnCompetitiveTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnCompetitive.add(rdbtnCompetitiveTime);

		defaultFieldCompetitiveGroup = new ButtonGroup();
		defaultFieldCompetitiveGroup.add(rdbtnCompetitiveEventName);
		defaultFieldCompetitiveGroup.add(rdbtnCompetitiveTime);
		if (rdbtnCompetitiveEventName.isSelected()) {
			defaultCompetitiveField = 1;
		} else if (rdbtnCompetitiveTime.isSelected()) {
			defaultCompetitiveField = 2;
		}

		JMenu mnDefaultKey = new JMenu("Default Action");
		mnDefaultKey.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnPreferences.add(mnDefaultKey);

		rdbtnActionAdd = new JRadioButton("Add");
		rdbtnActionAdd.setSelected(true);
		rdbtnActionAdd.setToolTipText("Pressing enter will add info to today's fitness");
		rdbtnActionAdd.setPreferredSize(new Dimension(80, 23));
		rdbtnActionAdd.setMinimumSize(new Dimension(80, 23));
		rdbtnActionAdd.setMaximumSize(new Dimension(80, 23));
		rdbtnActionAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultKey.add(rdbtnActionAdd);

		rdbtnActionRemove = new JRadioButton("Remove");
		rdbtnActionRemove.setToolTipText("Pressing enter will remove info from today's fitness");
		rdbtnActionRemove.setPreferredSize(new Dimension(80, 23));
		rdbtnActionRemove.setMinimumSize(new Dimension(80, 23));
		rdbtnActionRemove.setMaximumSize(new Dimension(80, 23));
		rdbtnActionRemove.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultKey.add(rdbtnActionRemove);

		rdbtnActionSave = new JRadioButton("Save");
		rdbtnActionSave.setToolTipText("Pressing enter will save all today's exercises to database");
		rdbtnActionSave.setPreferredSize(new Dimension(80, 23));
		rdbtnActionSave.setMinimumSize(new Dimension(80, 23));
		rdbtnActionSave.setMaximumSize(new Dimension(80, 23));
		rdbtnActionSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultKey.add(rdbtnActionSave);

		rdbtnActionLoad = new JRadioButton("Load");
		rdbtnActionLoad.setToolTipText("Pressing enter will load all today's exercises from database");
		rdbtnActionLoad.setPreferredSize(new Dimension(80, 23));
		rdbtnActionLoad.setMinimumSize(new Dimension(80, 23));
		rdbtnActionLoad.setMaximumSize(new Dimension(80, 23));
		rdbtnActionLoad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultKey.add(rdbtnActionLoad);

		rdbtnActionDelete = new JRadioButton("Delete");
		rdbtnActionDelete.setToolTipText("Pressing enter will delete all today's exercises from database");
		rdbtnActionDelete.setPreferredSize(new Dimension(80, 23));
		rdbtnActionDelete.setMinimumSize(new Dimension(80, 23));
		rdbtnActionDelete.setMaximumSize(new Dimension(80, 23));
		rdbtnActionDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnDefaultKey.add(rdbtnActionDelete);

		defaultActionGroup = new ButtonGroup();
		defaultActionGroup.add(rdbtnActionAdd);
		defaultActionGroup.add(rdbtnActionRemove);
		defaultActionGroup.add(rdbtnActionSave);
		defaultActionGroup.add(rdbtnActionLoad);
		defaultActionGroup.add(rdbtnActionDelete);
		
		if (rdbtnActionAdd.isSelected()) {
			defaultAction = 1;
		} else if (rdbtnActionRemove.isSelected()) {
			defaultAction = 2;
		} else if (rdbtnActionSave.isSelected()) {
			defaultAction = 3;
		} else if (rdbtnActionLoad.isSelected()) {
			defaultAction = 4;
		} else if (rdbtnActionDelete.isSelected()) {
			defaultAction = 5;
		}

		JMenu mnUnits = new JMenu("Units");
		mnUnits.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnPreferences.add(mnUnits);
		
		rdbtnImperial = new JRadioButton("Imperial");
		rdbtnImperial.setSelected(true);
		rdbtnImperial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnImperial.addActionListener(new optionsListener());
		rdbtnImperial.setMinimumSize(new Dimension(80, 23));
		rdbtnImperial.setMaximumSize(new Dimension(80, 23));
		mnUnits.add(rdbtnImperial);
		
		rdbtnMetric = new JRadioButton("Metric");
		rdbtnMetric.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnMetric.addActionListener(new optionsListener());
		rdbtnMetric.setMinimumSize(new Dimension(80, 23));
		rdbtnMetric.setMaximumSize(new Dimension(80, 23));
		mnUnits.add(rdbtnMetric);
		
		unitsGroup = new ButtonGroup();
		unitsGroup.add(rdbtnImperial);
		unitsGroup.add(rdbtnMetric);
		
		if (rdbtnImperial.isSelected()) {
			defaultUnit = 1;
		} else if (rdbtnMetric.isSelected()) {
			defaultUnit = 2;
		}
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(mnHelp);

		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.setPreferredSize(new Dimension(100, 23));
		btnInstructions.setMinimumSize(new Dimension(100, 23));
		btnInstructions.setMaximumSize(new Dimension(100, 23));
		btnInstructions.addActionListener(new BtnInstructionsActionListener());
		btnInstructions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mnHelp.add(btnInstructions);

		JPanel panelPersonal = new JPanel();
		panelPersonal.setBounds(10, 32, 624, 145);
		frmFitnessTracker.getContentPane().add(panelPersonal);
		panelPersonal.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(74, 13, 150, 20);
		panelPersonal.add(lblName);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(74, 46, 150, 20);
		panelPersonal.add(lblDate);

		JLabel lblRestingHR = new JLabel("Resting HR:");
		lblRestingHR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRestingHR.setBounds(74, 79, 150, 20);
		panelPersonal.add(lblRestingHR);

		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWeight.setBounds(74, 112, 150, 20);
		panelPersonal.add(lblWeight);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtName.setBounds(298, 13, 250, 20);
		panelPersonal.add(txtName);
		txtName.setColumns(10);
		txtName.addKeyListener(new infoListener());
		txtName.addActionListener(new BtnAdd1ActionListener());

		txtDate = new JTextField(creationTime.toString());
		txtDate.setText(LocalDate.now().toString());
		txtDate.setToolTipText("format: (YYYY-MM-DD)");
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDate.setColumns(10);
		txtDate.setBounds(298, 46, 250, 20);
		panelPersonal.add(txtDate);
		txtDate.addActionListener(new BtnAdd1ActionListener());

		txtRestingHR = new JTextField();
		txtRestingHR.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRestingHR.setColumns(10);
		txtRestingHR.setBounds(298, 79, 250, 20);
		panelPersonal.add(txtRestingHR);
		txtRestingHR.addKeyListener(new greaterThanZeroListener(txtRestingHR));
		txtRestingHR.addActionListener(new BtnAdd1ActionListener());

		txtWeight = new JTextField();
		txtWeight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtWeight.setColumns(10);
		txtWeight.setBounds(298, 112, 250, 20);
		panelPersonal.add(txtWeight);
		txtWeight.addKeyListener(new greaterThanZeroListener(txtWeight));
		txtWeight.addActionListener(new BtnAdd1ActionListener());

		JTabbedPane tabEvents = new JTabbedPane(JTabbedPane.TOP);
		tabEvents.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		tabEvents.setBounds(0, 190, 644, 230);
		frmFitnessTracker.getContentPane().add(tabEvents);

		JPanel panelCardio = new JPanel();
		tabEvents.addTab("Cardio", null, panelCardio, null);
		panelCardio.setLayout(null);

		JPanel cardioInfo = new JPanel();
		cardioInfo.setBounds(6, 0, 255, 202);
		panelCardio.add(cardioInfo);
		cardioInfo.setLayout(null);

		JLabel lblEventName1 = new JLabel("Event Name:");
		lblEventName1.setDisplayedMnemonic(KeyEvent.VK_C);
		lblEventName1.setBounds(8, 12, 90, 25);
		cardioInfo.add(lblEventName1);
		lblEventName1.setHorizontalAlignment(SwingConstants.LEFT);
		lblEventName1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(8, 49, 90, 25);
		cardioInfo.add(lblTime);
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblDistance = new JLabel("Distance:");
		lblDistance.setBounds(8, 86, 90, 25);
		cardioInfo.add(lblDistance);
		lblDistance.setHorizontalAlignment(SwingConstants.LEFT);
		lblDistance.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblHeartRate = new JLabel("Heart Rate:");
		lblHeartRate.setBounds(8, 123, 90, 25);
		cardioInfo.add(lblHeartRate);
		lblHeartRate.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeartRate.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtEventName1 = new JTextField();
		txtEventName1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEventName1.setBounds(106, 12, 140, 25);
		cardioInfo.add(txtEventName1);
		txtEventName1.setHorizontalAlignment(SwingConstants.CENTER);
		txtEventName1.setColumns(10);
		txtEventName1.addKeyListener(new infoListener());
		txtEventName1.addActionListener(new BtnAdd1ActionListener());

		txtExerciseTime1 = new JTextField();
		txtExerciseTime1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtExerciseTime1.setBounds(106, 49, 85, 25);
		cardioInfo.add(txtExerciseTime1);
		txtExerciseTime1.setHorizontalAlignment(SwingConstants.CENTER);
		txtExerciseTime1.setColumns(10);
		txtExerciseTime1.addKeyListener(new doubleListener(txtExerciseTime1));
		txtExerciseTime1.addActionListener(new BtnAdd1ActionListener());

		txtDistance = new JTextField();
		txtDistance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDistance.setBounds(106, 86, 85, 25);
		cardioInfo.add(txtDistance);
		txtDistance.setHorizontalAlignment(SwingConstants.CENTER);
		txtDistance.setColumns(10);
		txtDistance.addKeyListener(new doubleListener(txtDistance));
		txtDistance.addActionListener(new BtnAdd1ActionListener());

		txtHeartRate = new JTextField();
		txtHeartRate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtHeartRate.setHorizontalAlignment(SwingConstants.CENTER);
		txtHeartRate.setColumns(10);
		txtHeartRate.setBounds(106, 123, 140, 25);
		cardioInfo.add(txtHeartRate);
		txtHeartRate.addKeyListener(new greaterThanZeroListener(txtHeartRate));
		txtHeartRate.addActionListener(new BtnAdd1ActionListener());

		comboBoxDistance = new JComboBox<Object>();
		comboBoxDistance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxDistance.setBounds(191, 86, 55, 25);
		cardioInfo.add(comboBoxDistance);
		comboBoxDistance.setModel(new DefaultComboBoxModel<Object>(new String[] {"miles", "yards", "feet", "km", "m"}));
		comboBoxDistance.setSelectedIndex(0);
		comboBoxDistance.setToolTipText("Please select a type of measurement");

		comboBoxTime1 = new JComboBox<Object>();
		comboBoxTime1.setToolTipText("Please select a type of time");
		comboBoxTime1.setModel(new DefaultComboBoxModel(new String[] {"minutes", "hours"}));
		comboBoxTime1.setSelectedIndex(0);
		comboBoxTime1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxTime1.setBounds(191, 49, 55, 25);
		cardioInfo.add(comboBoxTime1);

		JPanel cardioEdit = new JPanel();
		cardioEdit.setLayout(null);
		cardioEdit.setBounds(267, 0, 105, 202);
		panelCardio.add(cardioEdit);

		btnAdd1 = new JButton("Add ->");
		btnAdd1.setToolTipText("Add data to exercise");
		btnAdd1.addActionListener(new BtnAdd1ActionListener());
		btnAdd1.setEnabled(false);
		btnAdd1.setBounds(2, 12, 100, 25);
		cardioEdit.add(btnAdd1);

		btnRemove1 = new JButton("<- Remove");
		btnRemove1.setToolTipText("Please select an exercise");
		btnRemove1.addActionListener(new BtnRemove1ActionListener());
		btnRemove1.setEnabled(false);
		btnRemove1.setBounds(2, 49, 100, 25);
		cardioEdit.add(btnRemove1);

		btnSave1 = new JButton("Save");
		btnSave1.addActionListener(new BtnSave1ActionListener());
		btnSave1.setToolTipText("Save exercises to database");
		btnSave1.setEnabled(false);
		btnSave1.setBounds(2, 86, 100, 25);
		cardioEdit.add(btnSave1);

		btnLoad1 = new JButton("Load");
		btnLoad1.addActionListener(new BtnLoadActionListener());
		btnLoad1.setToolTipText("Please enter only Name & Date to load first.");
		btnLoad1.setEnabled(false);
		btnLoad1.setBounds(2, 123, 100, 25);
		cardioEdit.add(btnLoad1);

		btnDelete1 = new JButton("Delete");
		btnDelete1.addActionListener(new BtnDeleteActionListener());
		btnDelete1.setToolTipText("Warning! Deleting will permanently delete the data. Do you wish to continue?");
		btnDelete1.setEnabled(false);
		btnDelete1.setBounds(2, 160, 100, 25);
		cardioEdit.add(btnDelete1);

		JPanel cardioToday = new JPanel();
		cardioToday.setLayout(null);
		cardioToday.setBounds(378, 0, 255, 202);
		panelCardio.add(cardioToday);

		JLabel lblFitnessToday1 = new JLabel("Fitness Today");
		lblFitnessToday1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFitnessToday1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFitnessToday1.setBounds(72, 12, 110, 20);
		cardioToday.add(lblFitnessToday1);
		
		JScrollPane cardioScrollPane = new JScrollPane();
		cardioScrollPane.setBounds(5, 44, 245, 145);
		cardioToday.add(cardioScrollPane);

		listFitnessToday1 = new JList<Object>();
		cardioScrollPane.setViewportView(listFitnessToday1);
		listFitnessToday1.setToolTipText("To edit an exercise, double click it.");
		listFitnessToday1.addMouseListener(new ListFitnessToday1MouseListener());
		listFitnessToday1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFitnessToday1.setModel(cardioModel);

		JPanel panelStrength = new JPanel();
		panelStrength.setLayout(null);
		tabEvents.addTab("Strength", null, panelStrength, null);

		JPanel strengthInfo = new JPanel();
		strengthInfo.setBounds(6, 0, 255, 202);
		panelStrength.add(strengthInfo);
		strengthInfo.setLayout(null);

		JLabel lblEventName2 = new JLabel("Event Name:");
		lblEventName2.setHorizontalAlignment(SwingConstants.LEFT);
		lblEventName2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEventName2.setDisplayedMnemonic(KeyEvent.VK_S);
		lblEventName2.setBounds(8, 12, 90, 25);
		strengthInfo.add(lblEventName2);
		
		lblTime2 = new JLabel("Time:");
		lblTime2.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime2.setFont(new Font("", Font.PLAIN, 14));
		lblTime2.setBounds(8, 49, 90, 25);
		strengthInfo.add(lblTime2);

		JLabel lblReps = new JLabel("Reps:");
		lblReps.setDisplayedMnemonic(KeyEvent.VK_S);
		lblReps.setBounds(8, 123, 90, 25);
		strengthInfo.add(lblReps);
		lblReps.setHorizontalAlignment(SwingConstants.LEFT);
		lblReps.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblSets = new JLabel("Sets:");
		lblSets.setBounds(8, 160, 90, 25);
		strengthInfo.add(lblSets);
		lblSets.setHorizontalAlignment(SwingConstants.LEFT);
		lblSets.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblWeightLifted = new JLabel("Weight Lifted:");
		lblWeightLifted.setBounds(8, 86, 90, 25);
		strengthInfo.add(lblWeightLifted);
		lblWeightLifted.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeightLifted.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtEventName2 = new JTextField();
		txtEventName2.setHorizontalAlignment(SwingConstants.CENTER);
		txtEventName2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEventName2.setColumns(10);
		txtEventName2.setBounds(106, 12, 140, 25);
		strengthInfo.add(txtEventName2);
		txtEventName2.addKeyListener(new infoListener());
		txtEventName2.addActionListener(new BtnAdd1ActionListener());
		
		txtExerciseTime2 = new JTextField();
		txtExerciseTime2.setHorizontalAlignment(SwingConstants.CENTER);
		txtExerciseTime2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtExerciseTime2.setColumns(10);
		txtExerciseTime2.setBounds(106, 49, 85, 25);
		strengthInfo.add(txtExerciseTime2);
		txtExerciseTime2.addKeyListener(new doubleListener(txtExerciseTime2));
		txtExerciseTime2.addActionListener(new BtnAdd2ActionListener());
		
		txtWeightLifted = new JTextField();
		txtWeightLifted.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtWeightLifted.setBounds(106, 86, 85, 25);
		strengthInfo.add(txtWeightLifted);
		txtWeightLifted.setHorizontalAlignment(SwingConstants.CENTER);
		txtWeightLifted.setColumns(10);
		txtWeightLifted.addKeyListener(new intListener());
		txtWeightLifted.addActionListener(new BtnAdd1ActionListener());

		txtReps = new JTextField();
		txtReps.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtReps.setBounds(106, 123, 140, 25);
		strengthInfo.add(txtReps);
		txtReps.setHorizontalAlignment(SwingConstants.CENTER);
		txtReps.setColumns(10);
		txtReps.addKeyListener(new intListener());
		txtReps.addActionListener(new BtnAdd1ActionListener());

		txtSets = new JTextField();
		txtSets.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSets.setBounds(106, 160, 140, 25);
		strengthInfo.add(txtSets);
		txtSets.setHorizontalAlignment(SwingConstants.CENTER);
		txtSets.setColumns(10);
		txtSets.addKeyListener(new intListener());
		txtSets.addActionListener(new BtnAdd1ActionListener());
		
		comboBoxTime2 = new JComboBox<Object>();
		comboBoxTime2.setToolTipText("Please select a type of time");
		comboBoxTime2.setModel(new DefaultComboBoxModel(new String[] {"minutes", "hours"}));
		comboBoxTime2.setSelectedIndex(0);
		comboBoxTime2.setFont(new Font("", Font.PLAIN, 12));
		comboBoxTime2.setBounds(191, 49, 55, 25);
		strengthInfo.add(comboBoxTime2);

		comboBoxWeight = new JComboBox<Object>();
		comboBoxWeight.setToolTipText("Please select a type of measurement");
		comboBoxWeight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxWeight.setBounds(191, 86, 55, 25);
		strengthInfo.add(comboBoxWeight);
		comboBoxWeight.setModel(new DefaultComboBoxModel(new String[] {"lbs", "ounces", "kg", "grams"}));
		comboBoxWeight.setSelectedIndex(0);
		comboBoxWeight.setToolTipText("Please select a type of measurement");

		JPanel strengthEdit = new JPanel();
		strengthEdit.setBounds(267, 0, 105, 202);
		panelStrength.add(strengthEdit);
		strengthEdit.setLayout(null);

		btnAdd2 = new JButton("Add ->");
		btnAdd2.setToolTipText("Add data to exercise");
		btnAdd2.addActionListener(new BtnAdd2ActionListener());
		btnAdd2.setEnabled(false);
		btnAdd2.setBounds(2, 12, 100, 25);
		strengthEdit.add(btnAdd2);

		btnRemove2 = new JButton("<- Remove");
		btnRemove2.setToolTipText("Please select an exercise");
		btnRemove2.addActionListener(new BtnRemove2ActionListener());
		btnRemove2.setEnabled(false);
		btnRemove2.setBounds(2, 49, 100, 25);
		strengthEdit.add(btnRemove2);

		btnSave2 = new JButton("Save");
		btnSave2.addActionListener(new BtnSave2ActionListener());
		btnSave2.setToolTipText("Save exercises to database");
		btnSave2.setEnabled(false);
		btnSave2.setBounds(2, 86, 100, 25);
		strengthEdit.add(btnSave2);

		btnLoad2 = new JButton("Load");
		btnLoad2.setToolTipText("Please enter only Name & Date to load first.");
		btnLoad2.setEnabled(false);
		btnLoad2.setBounds(2, 123, 100, 25);
		strengthEdit.add(btnLoad2);

		btnDelete2 = new JButton("Delete");
		btnDelete2.addActionListener(new BtnDeleteActionListener());
		btnDelete2.setToolTipText("Warning! Deleting will permanently delete the data. Do you wish to continue?");
		btnDelete2.setEnabled(false);
		btnDelete2.setBounds(2, 160, 100, 25);
		strengthEdit.add(btnDelete2);

		JPanel strengthToday = new JPanel();
		strengthToday.setBounds(378, 0, 255, 202);
		panelStrength.add(strengthToday);
		strengthToday.setLayout(null);

		JLabel lblFitnessToday2 = new JLabel("Fitness Today");
		lblFitnessToday2.setHorizontalAlignment(SwingConstants.CENTER);
		lblFitnessToday2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFitnessToday2.setBounds(72, 12, 110, 20);
		strengthToday.add(lblFitnessToday2);
		
		JScrollPane strengthScrollPane = new JScrollPane();
		strengthScrollPane.setBounds(5, 44, 245, 145);
		strengthToday.add(strengthScrollPane);
		
		listFitnessToday2 = new JList<Object>();
		strengthScrollPane.setViewportView(listFitnessToday2);
		listFitnessToday2.setToolTipText("To edit an exercise, double click it.");
		listFitnessToday2.addMouseListener(new ListFitnessToday2MouseListener());
		listFitnessToday2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFitnessToday2.setModel(strengthModel);

		JPanel panelCompetitive = new JPanel();
		panelCompetitive.setLayout(null);
		tabEvents.addTab("Competitive", null, panelCompetitive, null);

		JPanel competitiveInfo = new JPanel();
		competitiveInfo.setLayout(null);
		competitiveInfo.setBounds(6, 0, 255, 202);
		panelCompetitive.add(competitiveInfo);

		JLabel lblEventName3 = new JLabel("Event Name:");
		lblEventName3.setHorizontalAlignment(SwingConstants.LEFT);
		lblEventName3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEventName3.setBounds(8, 19, 90, 20);
		competitiveInfo.add(lblEventName3);
		
		lblTime3 = new JLabel("Time:");
		lblTime3.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime3.setFont(new Font("", Font.PLAIN, 14));
		lblTime3.setBounds(8, 49, 90, 25);
		competitiveInfo.add(lblTime3);
		
		JLabel lblVictorious = new JLabel("Did you win?");
		lblVictorious.setHorizontalAlignment(SwingConstants.LEFT);
		lblVictorious.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVictorious.setBounds(8, 86, 90, 20);
		competitiveInfo.add(lblVictorious);

		txtEventName3 = new JTextField();
		txtEventName3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEventName3.setHorizontalAlignment(SwingConstants.CENTER);
		txtEventName3.setColumns(10);
		txtEventName3.setBounds(106, 19, 140, 20);
		competitiveInfo.add(txtEventName3);
		txtEventName3.addKeyListener(new infoListener());
		txtEventName3.addActionListener(new BtnAdd3ActionListener());
		
		txtExerciseTime3 = new JTextField();
		txtExerciseTime3.setHorizontalAlignment(SwingConstants.CENTER);
		txtExerciseTime3.setFont(new Font("", Font.PLAIN, 12));
		txtExerciseTime3.setColumns(10);
		txtExerciseTime3.setBounds(106, 49, 85, 25);
		competitiveInfo.add(txtExerciseTime3);
		txtExerciseTime3.addKeyListener(new doubleListener(txtExerciseTime3));
		txtExerciseTime3.addActionListener(new BtnAdd3ActionListener());
		
		comboBoxTime3 = new JComboBox<Object>();
		comboBoxTime3.setToolTipText("Please select a type of time");
		comboBoxTime3.setModel(new DefaultComboBoxModel(new String[] {"minutes", "hours"}));
		comboBoxTime3.setSelectedIndex(0);
		comboBoxTime3.setFont(new Font("", Font.PLAIN, 12));
		comboBoxTime3.setBounds(191, 49, 55, 25);
		competitiveInfo.add(comboBoxTime3);

		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnYes.setBounds(104, 86, 70, 20);
		competitiveInfo.add(rdbtnYes);

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNo.setBounds(180, 86, 70, 20);
		competitiveInfo.add(rdbtnNo);

		victoriousGroup = new ButtonGroup();
		victoriousGroup.add(rdbtnYes);
		victoriousGroup.add(rdbtnNo);
		if (rdbtnYes.isSelected()) {
			victoriousInt = 1;
		} else {
			victoriousInt = 0;
		}

		JPanel competitiveEdit = new JPanel();
		competitiveEdit.setLayout(null);
		competitiveEdit.setBounds(267, 0, 105, 202);
		panelCompetitive.add(competitiveEdit);

		btnAdd3 = new JButton("Add ->");
		btnAdd3.setToolTipText("Add data to exercise");
		btnAdd3.addActionListener(new BtnAdd3ActionListener());
		btnAdd3.setEnabled(false);
		btnAdd3.setBounds(2, 12, 100, 25);
		competitiveEdit.add(btnAdd3);

		btnRemove3 = new JButton("<- Remove");
		btnRemove3.setToolTipText("Please select an exercise");
		btnRemove3.addActionListener(new BtnRemove3ActionListener());
		btnRemove3.setEnabled(false);
		btnRemove3.setBounds(2, 49, 100, 25);
		competitiveEdit.add(btnRemove3);

		btnSave3 = new JButton("Save");
		btnSave3.addActionListener(new BtnSave3ActionListener());
		btnSave3.setToolTipText("Save exercises to database");
		btnSave3.setEnabled(false);
		btnSave3.setBounds(2, 86, 100, 25);
		competitiveEdit.add(btnSave3);

		btnLoad3 = new JButton("Load");
		btnLoad3.setToolTipText("Please enter only Name & Date to load first.");
		btnLoad3.setEnabled(false);
		btnLoad3.setBounds(2, 123, 100, 25);
		competitiveEdit.add(btnLoad3);

		btnDelete3 = new JButton("Delete");
		btnDelete3.addActionListener(new BtnDeleteActionListener());
		btnDelete3.setToolTipText("Warning! Deleting will permanently delete the data. Do you wish to continue?");
		btnDelete3.setEnabled(false);
		btnDelete3.setBounds(2, 160, 100, 25);
		competitiveEdit.add(btnDelete3);

		JPanel copmetitiveToday = new JPanel();
		copmetitiveToday.setLayout(null);
		copmetitiveToday.setBounds(378, 0, 255, 202);
		panelCompetitive.add(copmetitiveToday);

		JLabel lblFitnessToday3 = new JLabel("Fitness Today");
		lblFitnessToday3.setHorizontalAlignment(SwingConstants.CENTER);
		lblFitnessToday3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFitnessToday3.setBounds(72, 12, 110, 20);
		copmetitiveToday.add(lblFitnessToday3);
		
		JScrollPane competitiveScrollPane = new JScrollPane();
		competitiveScrollPane.setBounds(5, 44, 245, 145);
		copmetitiveToday.add(competitiveScrollPane);
		
		listFitnessToday3 = new JList<Object>();
		competitiveScrollPane.setViewportView(listFitnessToday3);
		listFitnessToday3.setToolTipText("To edit an exercise, double click it.");
		listFitnessToday3.addMouseListener(new ListFitnessToday3MouseListener());
		listFitnessToday3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFitnessToday3.setModel(competitiveModel);

		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoPanel.setBounds(-3, 420, 650, 50);
		frmFitnessTracker.getContentPane().add(infoPanel);

		txtInfo = new JTextField();
		txtInfo.setEditable(false);
		txtInfo.setOpaque(false);
		txtInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txtInfo.setForeground(Color.RED);
		txtInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInfo.setColumns(10);
		txtInfo.setBounds(0, 0, 650, 50);
		infoPanel.add(txtInfo);
		infoPanel.setLayout(null);
	}

	// EVENT HANDLERS
	public void defaultButtons() {
		counters();
		syncGUI();
		// set default action for enter button

		// ADD
		if (rdbtnActionAdd.isSelected()) {
			if (btnAdd1.isEnabled()) {
				if (cardioModel.size() > 0 && cardioFieldCounter < 4) {
					frmFitnessTracker.getRootPane().setDefaultButton(btnSave1);
				} else {
					frmFitnessTracker.getRootPane().setDefaultButton(btnAdd1);
				}
				btnAdd1.setBackground(Color.CYAN);
			} else {
				btnAdd1.setBackground(null);
			}
			if (btnAdd2.isEnabled()) {
				if (strengthModel.size() > 0 && strengthFieldCounter < 3) {
					frmFitnessTracker.getRootPane().setDefaultButton(btnSave2);
				} else {
					frmFitnessTracker.getRootPane().setDefaultButton(btnAdd2);
				}
				btnAdd2.setBackground(Color.CYAN);
			} else {
				btnAdd2.setBackground(null);
			}
			if (btnAdd3.isEnabled()) {
				if (competitiveModel.size() > 0 && competitiveFieldCounter < 1) {
					frmFitnessTracker.getRootPane().setDefaultButton(btnSave3);
				} else {
					frmFitnessTracker.getRootPane().setDefaultButton(btnAdd3);
				}
				btnAdd3.setBackground(Color.CYAN);
			} else {
				btnAdd3.setBackground(null);
			}
		}

		// REMOVE
		if (btnRemove1.isEnabled()) {
			if (defaultAction == 2)
				frmFitnessTracker.getRootPane().setDefaultButton(btnRemove1);
			btnRemove1.setBackground(Color.RED);
		} else {
			btnRemove1.setBackground(null);
		}
		if (btnRemove2.isEnabled()) {
			if (defaultAction == 2)
				frmFitnessTracker.getRootPane().setDefaultButton(btnRemove2);
			btnRemove2.setBackground(Color.RED);
		} else {
			btnRemove2.setBackground(null);
		}
		if (btnRemove3.isEnabled()) {
			if (defaultAction == 2)
				frmFitnessTracker.getRootPane().setDefaultButton(btnRemove3);
			btnRemove3.setBackground(Color.RED);
		} else {
			btnRemove3.setBackground(null);
		}

		// SAVE
		if (btnSave1.isEnabled()) {
			if (defaultAction == 3)
				frmFitnessTracker.getRootPane().setDefaultButton(btnSave1);
			btnSave1.setBackground(Color.CYAN);
		} else {
			btnSave1.setBackground(null);
		}
		if (btnSave2.isEnabled()) {
			if (defaultAction == 3)
				frmFitnessTracker.getRootPane().setDefaultButton(btnSave2);
			btnSave2.setBackground(Color.CYAN);
		} else {
			btnSave2.setBackground(null);
		}
		if (btnSave3.isEnabled()) {
			if (defaultAction == 3)
				frmFitnessTracker.getRootPane().setDefaultButton(btnSave3);
			btnSave3.setBackground(Color.CYAN);
		} else {
			btnSave3.setBackground(null);
		}

		// LOAD
		if (btnLoad1.isEnabled()) {
			if (defaultAction == 4)
				frmFitnessTracker.getRootPane().setDefaultButton(btnLoad1);
			btnLoad1.setBackground(Color.CYAN);
		} else {
			btnLoad1.setBackground(null);
		}
		if (btnLoad2.isEnabled()) {
			if (defaultAction == 4)
				frmFitnessTracker.getRootPane().setDefaultButton(btnLoad2);
			btnLoad2.setBackground(Color.CYAN);
		} else {
			btnLoad2.setBackground(null);
		}
		if (btnLoad3.isEnabled()) {
			if (defaultAction == 4)
				frmFitnessTracker.getRootPane().setDefaultButton(btnLoad3);
			btnLoad3.setBackground(Color.CYAN);
		} else {
			btnLoad3.setBackground(null);
		}

		// DELETE
		if (btnDelete1.isEnabled()) {
			if (defaultAction == 5)
				frmFitnessTracker.getRootPane().setDefaultButton(btnDelete1);
			btnDelete1.setBackground(Color.RED);
		} else {
			btnDelete1.setBackground(null);
		}
		if (btnDelete2.isEnabled()) {
			if (defaultAction == 5)
				frmFitnessTracker.getRootPane().setDefaultButton(btnDelete2);
			btnDelete2.setBackground(Color.RED);
		} else {
			btnDelete2.setBackground(null);
		}
		if (btnDelete3.isEnabled()) {
			if (defaultAction == 5)
				frmFitnessTracker.getRootPane().setDefaultButton(btnDelete3);
			btnDelete3.setBackground(Color.RED);
		} else {
			btnDelete3.setBackground(null);
		}
		counters();
		syncGUI();
	}

	void counters() {
		// initiate counter to count number of values entered
		String addFailed = "Please enter all fields to save.";
		personalFieldCounter = 0;
		cardioFieldCounter = 0;
		strengthFieldCounter = 0;
		competitiveFieldCounter = 0;

		// PERSONAL
		// increment counter for name
		if (txtName.getText().length() > 0) {
			if (!txtName.getText().equals(".")) {
				personalFieldCounter++;
			} else {
				addFailed = "Insert name here";
			}
		}
		// increment counter for resting heart rate
		if (txtRestingHR.getText().length() > 0) {
			if (!txtRestingHR.getText().equals(".")
					&& Double.parseDouble(txtRestingHR.getText()) != 0)
				personalFieldCounter++;
			else if (txtRestingHR.getText().equals("."))
				addFailed = "Insert your resting heart rate here";
			else if (Double.parseDouble(txtRestingHR.getText()) == 0)
				addFailed = "Your resting heart rate must be greater than zero";
		}
		// increment counter for weight
		if (txtWeight.getText().length() > 0) {
			if (!txtWeight.getText().equals(".")
					&& Double.parseDouble(txtWeight.getText()) != 0)
				personalFieldCounter++;
			else if (txtWeight.getText().equals("."))
				addFailed = "Insert your weight here";
			else if (Double.parseDouble(txtWeight.getText()) == 0)
				addFailed = "Your weight must be greater than zero";
		}

		// CARDIO
		// increment counter for cardio event name
		if (txtEventName1.getText().length() > 0) {
			if (!txtEventName1.getText().equals(".")) {
				cardioFieldCounter++;
			} else {
				addFailed = "Insert cardio event name here";
			}
		}
		// increment counter for cardio time
		if (txtExerciseTime1.getText().length() > 0) {
			if (!txtExerciseTime1.getText().equals(".")
					&& Double.parseDouble(txtExerciseTime1.getText()) != 0)
				cardioFieldCounter++;
			else if (txtExerciseTime1.getText().equals("."))
				addFailed = "Insert total exercise time here";
			else if (Double.parseDouble(txtExerciseTime1.getText()) == 0)
				addFailed = "Your exercise time must be greater than zero";
		}
		// increment counter for cardio distance
		if (txtDistance.getText().length() > 0) {
			if (!txtDistance.getText().equals(".")
					&& Double.parseDouble(txtDistance.getText()) != 0)
				cardioFieldCounter++;
			else if (txtDistance.getText().equals("."))
				addFailed = "Insert total exercise distance here";
			else if (Double.parseDouble(txtDistance.getText()) == 0)
				addFailed = "Your exercise distance must be greater than zero";
		}
		// increment counter for cardio heart rate
		if (txtHeartRate.getText().length() > 0) {
			if (!txtHeartRate.getText().equals(".")
					&& Double.parseDouble(txtHeartRate.getText()) != 0)
				cardioFieldCounter++;
			else if (txtHeartRate.getText().equals("."))
				addFailed = "Insert your heart rate after cardio here";
			else if (Double.parseDouble(txtHeartRate.getText()) == 0)
				addFailed = "Your heart rate must be greater than zero";
		}

		// STRENGTH
		// increment counter for strength event name
		if (txtEventName2.getText().length() > 0) {
			if (!txtEventName2.getText().equals(".")) {
				strengthFieldCounter++;
			} else {
				addFailed = "Insert strength event name here";
			}
		}
		// increment counter for strength time
		if (txtExerciseTime2.getText().length() > 0) {
			if (!txtExerciseTime2.getText().equals(".")
					&& Double.parseDouble(txtExerciseTime2.getText()) != 0)
				strengthFieldCounter++;
			else if (txtExerciseTime2.getText().equals("."))
				addFailed = "Insert total exercise time here";
			else if (Double.parseDouble(txtExerciseTime2.getText()) == 0)
				addFailed = "Your exercise time must be greater than zero";
		}
		// increment counter for strength reps
		if (txtReps.getText().length() > 0) {
			if (!txtReps.getText().equals(".")
					&& Double.parseDouble(txtReps.getText()) != 0)
				strengthFieldCounter++;
			else if (txtReps.getText().equals("."))
				addFailed = "Insert number of reps here";
			else if (Double.parseDouble(txtReps.getText()) == 0)
				addFailed = "Number of reps must be greater than zero";
		}
		// increment counter for strength sets
		if (txtSets.getText().length() > 0) {
			if (!txtSets.getText().equals(".")
					&& Double.parseDouble(txtSets.getText()) != 0)
				strengthFieldCounter++;
			else if (txtSets.getText().equals("."))
				addFailed = "Insert number of sets here";
			else if (txtReps.getText().length() == 0 || Double.parseDouble(txtReps.getText()) == 0)
				addFailed = "Number of sets must be greater than zero";
		}
		// increment counter for weight lifted
		if (txtWeightLifted.getText().length() > 0) {
			if (!txtWeightLifted.getText().equals(".")
					&& Double.parseDouble(txtWeightLifted.getText()) != 0)
				strengthFieldCounter++;
			else if (txtWeightLifted.getText().equals("."))
				addFailed = "Insert weight lifted here";
			else if (Double.parseDouble(txtWeightLifted.getText()) == 0)
				addFailed = "Weight lifted must be greater than zero";
		}

		// COMPETITIVE
		// increment counter for competitive event name
		if (txtEventName3.getText().length() > 0) {
			if (!txtEventName3.getText().equals(".")) {
				competitiveFieldCounter++;
			} else {
				addFailed = "Insert competitive event name here";
			}
		}
		// increment counter for competitive time
		if (txtExerciseTime3.getText().length() > 0) {
			if (!txtExerciseTime3.getText().equals(".")
					&& Double.parseDouble(txtExerciseTime3.getText()) != 0)
				competitiveFieldCounter++;
			else if (txtExerciseTime3.getText().equals("."))
				addFailed = "Insert total exercise time here";
			else if (Double.parseDouble(txtExerciseTime3.getText()) == 0)
				addFailed = "Your exercise time must be greater than zero";
		}
		
		// tell user that two values are needed before adding to today's fitness
		if (btnAdd1.isEnabled() == false) {
			btnAdd1.setToolTipText(addFailed);
		} else {
			btnAdd1.setToolTipText("Add to today's fitness");
		}
		if (btnAdd2.isEnabled() == false) {
			btnAdd2.setToolTipText(addFailed);
		} else {
			btnAdd2.setToolTipText("Add to today's fitness");
		}
		if (btnAdd3.isEnabled() == false) {
			btnAdd3.setToolTipText(addFailed);
		} else {
			btnAdd3.setToolTipText("Add to today's fitness");
		}

		// help info
		if (btnLoad1.isEnabled() || btnLoad2.isEnabled() || btnLoad3.isEnabled()) {
			timers();
			txtInfo.setForeground(Color.BLACK);
			txtInfo.setText("Click load to load from database.");
		} else if (btnSave1.isEnabled() || btnSave2.isEnabled() || btnSave3.isEnabled()) {
			timers();
			txtInfo.setForeground(Color.BLACK);
			txtInfo.setText("Click save to save exercises to database.");
		} else {
			timers();
			txtInfo.setForeground(Color.RED);
			txtInfo.setText(addFailed);
		}
		
		// set default button to load if only name and date are entered
		if (personalFieldCounter == 2 && cardioFieldCounter == 0 && strengthFieldCounter == 0 && competitiveFieldCounter == 0) {
			frmFitnessTracker.getRootPane().setDefaultButton(btnLoad1);
		}
		
		// enable add button only if all values are entered
		if (personalFieldCounter == 3) {
			if (cardioFieldCounter == 4) {
				frmFitnessTracker.getRootPane().setDefaultButton(btnAdd1);
				btnAdd1.setEnabled(true);
			} else
				btnAdd1.setEnabled(false);

			if (strengthFieldCounter == 5) {
				frmFitnessTracker.getRootPane().setDefaultButton(btnAdd2);
				btnAdd2.setEnabled(true);
			} else
				btnAdd2.setEnabled(false);

			if (competitiveFieldCounter == 2) {
				frmFitnessTracker.getRootPane().setDefaultButton(btnAdd3);
				btnAdd3.setEnabled(true);
			} else
				btnAdd3.setEnabled(false);
		}

		// enable remove button only if an exercise is selected
		if (listFitnessToday1.getSelectedIndex() >= 0) {
			btnRemove1.setEnabled(true);
		} else {
			btnRemove1.setEnabled(false);
		}
		if (listFitnessToday2.getSelectedIndex() >= 0) {
			btnRemove2.setEnabled(true);
		} else {
			btnRemove2.setEnabled(false);
		}
		if (listFitnessToday3.getSelectedIndex() >= 0) {
			btnRemove3.setEnabled(true);
		} else {
			btnRemove3.setEnabled(false);
		}

		// enable save button only if fitness today has data
		if (cardioModel.size() > 0) {
			btnSave1.setEnabled(true);
		} else {
			btnSave1.setEnabled(false);
			btnSave1.setBackground(null);
		}
		if (strengthModel.size() > 0) {
			btnSave2.setEnabled(true);
		} else {
			btnSave2.setEnabled(false);
			btnSave2.setBackground(null);
		}
		if (competitiveModel.size() > 0) {
			btnSave3.setEnabled(true);
		} else {
			btnSave3.setEnabled(false);
			btnSave3.setBackground(null);
		}

		// enable load button only if name and date are entered
		if (txtName.getText().length() > 0 && personalFieldCounter == 1) {
			frmFitnessTracker.getRootPane().setDefaultButton(btnLoad1);
			btnLoad1.setEnabled(true);
			btnLoad2.setEnabled(true);
			btnLoad3.setEnabled(true);
		} else {
			btnLoad1.setEnabled(false);
			btnLoad2.setEnabled(false);
			btnLoad3.setEnabled(false);
		}

		// enable delete button only after data is loaded
		if (loadPassed == 1 && txtName.getText().length() > 0 && txtDate.getText().length() > 8) {
			if (cardioModel.size() > 0) {
				btnDelete1.setEnabled(true);
			} else {
				btnDelete1.setEnabled(false);
			}
			if (strengthModel.size() > 0) {
				btnDelete2.setEnabled(true);
			} else {
				btnDelete2.setEnabled(false);
			}
			if (competitiveModel.size() > 0) {
				btnDelete3.setEnabled(true);
			} else {
				btnDelete3.setEnabled(false);
			}
		}
	}

	void timers() {
		Timer timer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtInfo.setVisible(false);
			}
		});
		timer.setRepeats(false);
		timer.start();
		txtInfo.setVisible(true);
	}

	// TEXT LISTENERS
	private class GlobalKeyListener implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
				if (rdbtnDefaultClearAll.isSelected()) {
					clearPersonalFields();
					clearCardioFields();
					clearStrengthFields();
					clearCompetitiveFields();
				} else if (rdbtnDefaultClearPersonal.isSelected()) {
					clearPersonalFields();
				} else if (rdbtnDefaultClearCardio.isSelected()) {
					clearCardioFields();
				} else if (rdbtnDefaultClearStrength.isSelected()) {
					clearStrengthFields();
				} else if (rdbtnDefaultClearCompetitive.isSelected()) {
					clearCompetitiveFields();
				}
			}
			if (e.getKeyChar() == KeyEvent.VK_ALT + KeyEvent.VK_T) {
				// cycle through tabs
			}
			if (e.getKeyChar() == KeyEvent.VK_ALT + KeyEvent.VK_L) {
				// load on button or load continuously
			}
			if (e.getKeyChar() == KeyEvent.VK_SHIFT + KeyEvent.VK_ENTER) {
				// save data in models
				runSave();
			}
			if (e.getKeyChar() == KeyEvent.VK_ALT + KeyEvent.VK_ENTER) {
				// load data in models
				runLoad();
			}
			return false;
		}
	}

	private class infoListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			counters();
			defaultButtons();
		}
	}

	private class greaterThanZeroListener extends KeyAdapter {
		private JTextField txtField = null;

		private greaterThanZeroListener(JTextField pTxtField) {
			txtField = pTxtField;
		}

		public void keyTyped(KeyEvent e) {
			// checks to see if input is a valid key
			if (e.getKeyChar() < KeyEvent.VK_0
					|| e.getKeyChar() > KeyEvent.VK_9) {
				e.consume();
			}
			if (txtField.getText().length() <= 0
					&& e.getKeyChar() == KeyEvent.VK_0) {
				e.consume();
			}
		}

		public void keyReleased(KeyEvent e) {
			if (txtField.getText().length() > 0)
				if (txtField.getText().equals(".")
						|| Double.parseDouble(txtField.getText()) == 0)
					txtField.setForeground(Color.RED);
			counters();
			defaultButtons();
		}
	}

	private class intListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			// checks to see if input is a valid key
			if (e.getKeyChar() < KeyEvent.VK_0
					|| e.getKeyChar() > KeyEvent.VK_9) {
				e.consume();
			}
		}

		public void keyReleased(KeyEvent e) {
			counters();
			defaultButtons();
		}
	}

	private class doubleListener extends KeyAdapter {
		private JTextField txtField = null;

		private doubleListener(JTextField pTxtField) {
			txtField = pTxtField;
		}

		public void keyTyped(KeyEvent e) {
			// checks to see if input is a valid key

			txtField.setForeground(Color.BLACK);

			if (e.getKeyChar() < KeyEvent.VK_0
					|| e.getKeyChar() > KeyEvent.VK_9) {
				// limits input to one decimal
				if (e.getKeyChar() != KeyEvent.VK_PERIOD
						|| e.getKeyChar() == KeyEvent.VK_PERIOD
						&& txtField.getText().contains(".")) {
					// delete input from buffer if invalid key
					e.consume();
				}
			}
		}

		public void keyReleased(KeyEvent e) {
			if (txtField.getText().length() > 0)
				if (txtField.getText().equals(".")
						|| Double.parseDouble(txtField.getText()) == 0)
					txtField.setForeground(Color.RED);
			counters();
			defaultButtons();
		}

	}

	// RESETS
	void clearAllFields() {
		clearPersonalFields();
		clearCardioFields();
		clearStrengthFields();
		clearCompetitiveFields();
		// reset buttons
		defaultButtons();
	}

	void clearCardioFields() {
		// reset text boxes
		txtEventName1.setText("");
		txtExerciseTime1.setText("");
		txtDistance.setText("");
		txtHeartRate.setText("");
		// request focus for preferred field
		if (defaultCardioField == 1) {
			txtEventName1.requestFocus();
		} else if (defaultCardioField == 2) {
			txtExerciseTime1.requestFocus();
		} else if (defaultCardioField == 3) {
			txtDistance.requestFocus();
		} else if (defaultCardioField == 4) {
			txtHeartRate.requestFocus();
		}
		// reset buttons
		defaultButtons();
	}

	void clearStrengthFields() {
		// reset text boxes
		txtEventName2.setText("");
		txtExerciseTime2.setText("");
		txtReps.setText("");
		txtSets.setText("");
		txtWeightLifted.setText("");
		// request focus for preferred field
		if (defaultStrengthField == 1) {
			txtEventName2.requestFocus();
		} else if (defaultStrengthField == 2) {
			txtReps.requestFocus();
		} else if (defaultStrengthField == 3) {
			txtSets.requestFocus();
		} else if (defaultStrengthField == 4) {
			txtWeightLifted.requestFocus();
		}
		// reset buttons
		defaultButtons();
	}

	void clearCompetitiveFields() {
		// reset text boxes
		txtEventName3.setText("");
		txtExerciseTime3.setText("");
		// request focus for preferred field
		if (defaultCompetitiveField == 1) {
			txtEventName3.requestFocus();
		} else if (defaultCompetitiveField == 2) {
			txtExerciseTime3.requestFocus();
		}
		// reset buttons
		defaultButtons();
	}

	void clearPersonalFields() {
		// reset text boxes
		txtName.setText("");
		txtRestingHR.setText("");
		txtWeight.setText("");
		// request focus for preferred field
		if (defaultPersonalField == 1) {
			txtName.requestFocus();
		} else if (defaultPersonalField == 2) {
			txtRestingHR.requestFocus();
		} else if (defaultPersonalField == 3) {
			txtWeight.requestFocus();
		}
		// reset buttons
		defaultButtons();
	}
	
	void clearLists() {
		cardioModel.clear();
		strengthModel.clear();
		competitiveModel.clear();

		// reset buttons
		defaultButtons();
	}

	void resetComboBoxes() {
		comboBoxTime1.setSelectedItem("minutes");
		comboBoxTime2.setSelectedItem("minutes");
		comboBoxTime3.setSelectedItem("minutes");
		comboBoxDistance.setSelectedItem("miles");
		comboBoxWeight.setSelectedItem("lbs");
		rdbtnYes.isSelected();
	}

	// CONVERTERS
	public void cardioConvertTo() {
		if (comboBoxTime1.getSelectedItem() == "minutes")
			myCardio.setExerciseTime(Double.parseDouble(txtExerciseTime1.getText()));
		else if (comboBoxTime1.getSelectedItem() == "hours")
			myCardio.setExerciseTime((Double.parseDouble(txtExerciseTime1.getText()) * (60)));
		if (comboBoxDistance.getSelectedItem() == "miles")
			myCardio.setDistance(Double.parseDouble(txtDistance.getText()));
		else if (comboBoxDistance.getSelectedItem() == "km")
			myCardio.setDistance((Double.parseDouble(txtDistance.getText()) / (1.60934)));
		else if (comboBoxDistance.getSelectedItem() == "m")
			myCardio.setDistance((Double.parseDouble(txtDistance.getText()) / (1609.34)));
		else if (comboBoxDistance.getSelectedItem() == "yards")
			myCardio.setDistance((Double.parseDouble(txtDistance.getText()) / (1760)));
		else if (comboBoxDistance.getSelectedItem() == "feet")
			myCardio.setDistance((Double.parseDouble(txtDistance.getText()) / (5280)));
	}

	public void strengthConvertTo() {
		if (comboBoxTime2.getSelectedItem() == "minutes")
			myStrength.setExerciseTime(Double.parseDouble(txtExerciseTime2.getText()));
		else if (comboBoxTime2.getSelectedItem() == "hours")
			myStrength.setExerciseTime((Double.parseDouble(txtExerciseTime2.getText()) * (60)));
		if (comboBoxWeight.getSelectedItem() == "lbs")
			myStrength.setWeightLifted(Double.parseDouble(txtWeightLifted.getText()));
		else if (comboBoxWeight.getSelectedItem() == "kg")
			myStrength.setWeightLifted((Double.parseDouble(txtWeightLifted.getText()) / (0.453592)));
		else if (comboBoxWeight.getSelectedItem() == "ounces")
			myStrength.setWeightLifted((Double.parseDouble(txtWeightLifted.getText()) / (16)));
		else if (comboBoxWeight.getSelectedItem() == "grams")
			myStrength.setWeightLifted((Double.parseDouble(txtWeightLifted.getText()) / (453.592)));
	}
	
	public void competitiveConvertTo() {
		if (comboBoxTime3.getSelectedItem() == "minutes")
			myCompetitive.setExerciseTime(Double.parseDouble(txtExerciseTime3.getText()));
		else if (comboBoxTime3.getSelectedItem() == "hours")
			myCompetitive.setExerciseTime((Double.parseDouble(txtExerciseTime3.getText()) * (60)));
	}

	// ACTION LISTENERS
	private class optionsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (rdbtnImperial.isSelected()) {
				comboBoxDistance.setModel(new DefaultComboBoxModel<Object>(new String[] {"miles", "yards", "feet"}));
				comboBoxDistance.setSelectedIndex(0);
				comboBoxWeight.setModel(new DefaultComboBoxModel<Object>(new String[] {"lbs", "ounces"}));
				comboBoxWeight.setSelectedIndex(0);
			} else if (rdbtnMetric.isSelected()) {
				comboBoxDistance.setModel(new DefaultComboBoxModel<Object>(new String[] {"km", "m"}));
				comboBoxDistance.setSelectedIndex(0);
				comboBoxWeight.setModel(new DefaultComboBoxModel<Object>(new String[] {"kg", "grams"}));
				comboBoxWeight.setSelectedIndex(0);
			}
		}
	}
	
	private class BtnInstructionsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String output = "";
			output += "This form allows users to keep track of workouts by saving their information to a database.\n";
			output += "---------------------------------------------------------------";
			output += "---------------------------------------------------------------\n";
			output += "\n";
			output += "To add a workout, please enter all fields then click add.\n";
			output += "\n";
			output += "---------------------------------------------------------------";
			output += "---------------------------------------------------------------\n";
			output += "\n";
			output += "To save to the database, please first save to today's workouts.\n";
			output += "\n";
			output += "---------------------------------------------------------------";
			output += "---------------------------------------------------------------\n";
			output += "\n";
			output += "To load information from database, please only enter your name and the date of the exercise.\n";
			output += "\n";
			output += "---------------------------------------------------------------";
			output += "---------------------------------------------------------------\n";
			JOptionPane.showMessageDialog(null, output);
		}
	}

	private class BtnClearAllActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clearPersonalFields();
			clearCardioFields();
			clearStrengthFields();
			clearCompetitiveFields();
			clearLists();
		}
	}

	private class BtnPersonalActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clearPersonalFields();
			defaultButtons();
		}
	}

	private class BtnClearCardioActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clearCardioFields();
			defaultButtons();
		}
	}

	private class BtnClearStrengthActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clearStrengthFields();
			defaultButtons();
		}
	}

	private class BtnClearCompetitiveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			clearCompetitiveFields();
			defaultButtons();
		}
	}

	private class BtnClearListsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			clearLists();
			defaultButtons();
		}
	}

	// add
	private class BtnAdd1ActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			myCardio = new ExerciseCardio();
			defaultButtons();
			if (personalFieldCounter >= 3) {
				// SET ALL PROPERTIES FOR EXERCISE CARDIO ITSELF
				if (cardioFieldCounter >= 4) {
					cardioConvertTo();
					myCardio.setExerciseDate(LocalDate.parse(txtDate.getText()));
					myCardio.setPersonName(txtName.getText());
					myCardio.setExerciseName(txtEventName1.getText());
					myCardio.setMaxHeartRate(Integer.parseInt(txtHeartRate.getText()));
					myDay.addExerciseCardio(myCardio);
					// save elements in list
					for (ExerciseCardio cardio : myDay.getExerciseCardios()) {
						cardioModel.addElement(cardio.getExerciseName());
					}
					// clear cardio fields
					clearCardioFields();
				}
			}
			defaultButtons();
			resetComboBoxes();
		}
	}

	private class BtnAdd2ActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			myStrength = new ExerciseStrength();
			defaultButtons();
			if (personalFieldCounter >= 3) {
				// SET ALL PROPERTIES FOR EXERCISE STRENGTH ITSELF
				if (strengthFieldCounter >= 3) {
					strengthConvertTo();
					myStrength.setExerciseDate(LocalDate.parse(txtDate.getText()));
					myStrength.setPersonName(txtName.getText());
					myStrength.setExerciseName(txtEventName2.getText());
					myStrength.setReps(Integer.parseInt(txtReps.getText()));
					myStrength.setSets(Integer.parseInt(txtSets.getText()));
					myDay.addExerciseStrength(myStrength);
					// save elements in list
					for (ExerciseStrength strength : myDay.getExerciseStrengths()) {
						strengthModel.addElement(strength.getExerciseName());
					}
					// clear strength fields
					clearStrengthFields();
				}
			}
			defaultButtons();
			resetComboBoxes();
		}
	}

	private class BtnAdd3ActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			myCompetitive = new ExerciseCompetitive();
			defaultButtons();
			if (rdbtnYes.isSelected()) {
				victoriousInt = 1;
			} else {
				victoriousInt = 0;
			}
			if (personalFieldCounter >= 3) {
				// SET ALL PROPERTIES FOR EXERCISE COMPETITVE ITSELF
				if (competitiveFieldCounter >= 2) {
					competitiveConvertTo();
					myCompetitive.setExerciseDate(LocalDate.parse(txtDate.getText()));
					myCompetitive.setPersonName(txtName.getText());
					myCompetitive.setExerciseName(txtEventName3.getText());
					myCompetitive.setVictorious(victoriousInt);
					myDay.addExerciseCompetitive(myCompetitive);
					// save elements in list
					for (ExerciseCompetitive competitive : myDay.getExerciseCompetitives()) {
						competitiveModel.addElement(competitive.getExerciseName());
					}
					// clear Competitive fields
					clearCompetitiveFields();
				}
			}
			defaultButtons();
			resetComboBoxes();
		}
	}

	// remove
	private class BtnRemove1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			myCardio = new ExerciseCardio();
			defaultButtons();
			// REMOVE ALL PROPERTIES FOR EXERCISE CARDIO ITSELF
			if (listFitnessToday1.getSelectedIndex() > -1) {
				myDay.removeExerciseCardio(listFitnessToday1.getSelectedIndex());
				// clear cardio fields
				clearCardioFields();
			}
			defaultButtons();
			resetComboBoxes();
		}
	}

	private class BtnRemove2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			myStrength = new ExerciseStrength();
			defaultButtons();
			// REMOVE ALL PROPERTIES FOR EXERCISE STRENGTH ITSELF
			if (listFitnessToday2.getSelectedIndex() > -1) {
				myDay.removeExerciseStrength(listFitnessToday2
						.getSelectedIndex());
				// clear strength fields
				clearStrengthFields();
			}
			defaultButtons();
			resetComboBoxes();
		}
	}

	private class BtnRemove3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			myCompetitive = new ExerciseCompetitive();
			defaultButtons();
			// REMOVE ALL PROPERTIES FOR EXERCISE COMPETITVE ITSELF
			if (listFitnessToday3.getSelectedIndex() > -1) {
				myDay.removeExerciseCompetitive(listFitnessToday3
						.getSelectedIndex());
				// clear Competitive fields
				clearCompetitiveFields();
			}
			defaultButtons();
			resetComboBoxes();
		}
	}

	// save
	private class BtnSave1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			runSave();
			defaultButtons();
		}
	}

	private class BtnSave2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			runSave();
			defaultButtons();
		}
	}

	private class BtnSave3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			runSave();
			defaultButtons();
		}
	}

	// load
	private class BtnLoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// BERKSTRESSER: MAKE SURE IT IS USING THE DATE THE USER ENTERS TO LOAD
			try {
			myDay.load(LocalDate.parse(txtDate.getText().subSequence(0, txtDate.getText().length())), txtName.getText());
			syncDayInfo();

			runLoad();
			defaultButtons();
			} catch (Exception arg0) {
				timers();
				txtInfo.setForeground(Color.RED);
				txtInfo.setText("Sorry, the exercises for "
						+ LocalDate.parse(txtDate.getText().subSequence(0, txtDate.getText().length()))
						+ " could not be found. \nPlease try to load another date.");
			}
		}
	}
	
	// delete
	private class BtnDeleteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			runDelete();
			defaultButtons();
		}
	}

	// models
	private class ListFitnessToday1MouseListener extends MouseAdapter {
		@SuppressWarnings("rawtypes")
		@Override
		public void mouseClicked(MouseEvent mouse) {
			if (mouse.getClickCount() == 2) {
				if (cardioModel.size() > 0) {
					JList list = (JList) mouse.getSource();
					int index = list.locationToIndex(mouse.getPoint());
					ExerciseCardio exc = myDay.getExerciseCardio(index);
					txtEventName1.setText(exc.getExerciseName());
					txtExerciseTime1.setText("" + exc.getExerciseTime());
					txtDistance.setText("" + exc.getDistance());
					txtHeartRate.setText("" + exc.getMaxHeartRate());

					int cardioSelectedIndex = listFitnessToday1.getSelectedIndex();
					listFitnessToday1.setSelectedIndex(Math.min(cardioSelectedIndex, cardioModel.getSize()));
					myDay.removeExerciseCardio(listFitnessToday1.getSelectedIndex());

					defaultButtons();
					resetComboBoxes();
				}
			}
			defaultButtons();
			resetComboBoxes();
		}
	}

	private class ListFitnessToday2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent mouse) {
			if (mouse.getClickCount() == 2) {
				if (strengthModel.size() > 0) {
					// strengthConvertFrom();
					@SuppressWarnings("rawtypes")
					JList list = (JList) mouse.getSource();
					int index = list.locationToIndex(mouse.getPoint());
					ExerciseStrength exc = myDay.getExerciseStrength(index);
					txtEventName2.setText(exc.getExerciseName());
					txtExerciseTime2.setText("" + exc.getExerciseTime());
					txtReps.setText("" + exc.getReps());
					txtSets.setText("" + exc.getSets());
					txtWeightLifted.setText("" + exc.getWeightLifted());

					int strengthSelectedIndex = listFitnessToday2.getSelectedIndex();
					listFitnessToday2.setSelectedIndex(Math.min(strengthSelectedIndex, strengthModel.getSize()));
					myDay.removeExerciseStrength(listFitnessToday2.getSelectedIndex());

					defaultButtons();
					resetComboBoxes();
				}
			}
			defaultButtons();
			resetComboBoxes();
		}
	}

	private class ListFitnessToday3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent mouse) {
			if (mouse.getClickCount() == 2) {
				if (competitiveModel.size() > 0) {
					@SuppressWarnings("rawtypes")
					JList list = (JList) mouse.getSource();
					int index = list.locationToIndex(mouse.getPoint());
					ExerciseCompetitive exc = myDay.getExerciseCompetitive(index);
					txtEventName3.setText(exc.getExerciseName());
					txtExerciseTime3.setText("" + exc.getExerciseTime());
					if (exc.getVictorious() == 1) {
						rdbtnYes.setSelected(true);
					} else if (exc.getVictorious() == 0) {
						rdbtnNo.setSelected(true);
					}

					int competitiveSelectedIndex = listFitnessToday3.getSelectedIndex();
					listFitnessToday3.setSelectedIndex(Math.min(competitiveSelectedIndex, competitiveModel.getSize()));
					myDay.removeExerciseCompetitive(listFitnessToday3.getSelectedIndex());

					defaultButtons();
					resetComboBoxes();
				}
			}
			defaultButtons();
			resetComboBoxes();
		}
	}

	// DATABASE METHODS
	private void runSave() {
		// SET ALL PROPERTIES FOR PERSONAL INFO ITSELF
		myDay.setPersonName(txtName.getText());
		myDay.setExerciseDate(creationTime);
		myDay.setRestingHeartRate(Integer.parseInt(txtRestingHR.getText()));
		myDay.setWeight(Double.parseDouble(txtWeight.getText()));

		// display visual output to user
		String output = "";
		output += "-----------------------------------------------------------------\n";
		output += "Name: " + myDay.getPersonName() + "\n";
		output += "Date/Time: " + myDay.getExerciseDate() + "\n";
		output += "Resting Heart Rate: " + myDay.getRestingHeartRate() + "\n";
		output += "Weight: " + myDay.getWeight() + "\n";
		if (cardioModel.size() > 0) {
			totalTime = 0;
			for (ExerciseCardio cardio : myDay.getExerciseCardios()) {
				totalTime += (cardio.getExerciseTime());
			}
			output += "Total Time: " + totalTime + " hours\n";
			for (ExerciseCardio cardio : myDay.getExerciseCardios()) {
				output += "-----------------------------------------------------------------\n";
				output += "Cardio Exercises Information\n";
				output += "Exercise Name: " + cardio.getExerciseName() + "\n";
				if (comboBoxTime1.getSelectedItem() == "minutes")
					output += "Exercise Time: "	+ (cardio.getExerciseTime()) + " minutes\n";
				else if (comboBoxTime1.getSelectedItem() == "hours")
					output += "Exercise Time: " + cardio.getExerciseTime() / 60 + " hours\n";
				if (comboBoxDistance.getSelectedItem() == "miles")
					output += "Distance: " + cardio.getDistance() + " miles\n";
				else if (comboBoxDistance.getSelectedItem() == "km")
					output += "Distance: " + cardio.getDistance() + " kilometers\n";
				else if (comboBoxDistance.getSelectedItem() == "m")
					output += "Distance: " + cardio.getDistance() + " meters\n";
				else if (comboBoxDistance.getSelectedItem() == "yards")
					output += "Distance: " + cardio.getDistance() + " yards\n";
				else if (comboBoxDistance.getSelectedItem() == "feet")
					output += "Distance: " + cardio.getDistance() + " feet\n";
				output += "Max Heart Rate: " + cardio.getMaxHeartRate() + "\n";
			}
		}
		if (strengthModel.size() > 0) {
			totalTime = 0;
			for (ExerciseStrength strength : myDay.getExerciseStrengths()) {
				totalTime += (strength.getExerciseTime());
			}
			output += "Total Time: " + totalTime + " hours\n";
			for (ExerciseStrength strength : myDay.getExerciseStrengths()) {
				totalTime = 0;
				output += "-----------------------------------------------------------------\n";
				output += "Strength Exercises Information\n";
				output += "Exercise Name: " + strength.getExerciseName() + "\n";
				if (comboBoxTime2.getSelectedItem() == "minutes")
					output += "Exercise Time: "	+ (strength.getExerciseTime()) + " minutes\n";
				else if (comboBoxTime2.getSelectedItem() == "hours")
					output += "Exercise Time: " + strength.getExerciseTime() / 60 + " hours\n";
				output += "Sets: " + strength.getSets() + "\n";
				output += "Reps: " + strength.getReps() + "\n";
				if (comboBoxWeight.getSelectedItem() == "lbs")
					output += "Weight: " + strength.getWeightLifted() + " pounds\n";
				else if (comboBoxWeight.getSelectedItem() == "kg")
					output += "Weight: " + strength.getWeightLifted() + " kilograms\n";
				else if (comboBoxWeight.getSelectedItem() == "ounces")
					output += "Weight: " + strength.getWeightLifted() + " ounces\n";
				else if (comboBoxWeight.getSelectedItem() == "grams")
					output += "Weight: " + strength.getWeightLifted() + " grams\n";
			}
		}
		if (competitiveModel.size() > 0) {
			totalTime = 0;
			for (ExerciseCompetitive competitive : myDay.getExerciseCompetitives()) {
				totalTime += (competitive.getExerciseTime());
			}
			output += "Total Time: " + totalTime + " hours\n";
			for (ExerciseCompetitive competitive : myDay.getExerciseCompetitives()) {
				output += "-----------------------------------------------------------------\n";
				output += "Competitive Exercises Information\n";
				output += "Exercise Name: " + competitive.getExerciseName() + "\n";
				if (comboBoxTime3.getSelectedItem() == "minutes")
					output += "Exercise Time: "	+ (competitive.getExerciseTime()) + " minutes\n";
				else if (comboBoxTime3.getSelectedItem() == "hours")
					output += "Exercise Time: " + competitive.getExerciseTime() / 60 + " hours\n";
				if (competitive.getVictorious() == 0) {
					victorious = "No";
				} else if (competitive.getVictorious() == 1) {
					victorious = "Yes";
				}
				output += "Victorious: " + victorious + "\n";
			}
		}
		output += "-----------------------------------------------------------------\n";

		JOptionPane.showMessageDialog(null, output);

		// allow program to clear all fields if can't connect to database
		try {
			// save values to database
			myDay.save();
		} catch (Exception e) {
			txtInfo.setText("Sorry, your day's exercises data could not be saved to the database at this time.\nPlease try again later.");
		}

		// disallow delete box after delete
		loadPassed = 0;

		// clear all fields to enter new data
		clearCardioFields();
		clearStrengthFields();
		clearCompetitiveFields();
		clearLists();
	}

	private void runLoad() {
		// allow program to clear all fields if can't connect to database
		try {
			// load values given person's name and date
			myDay.load(LocalDate.parse(txtDate.getText()), txtName.getText());
		} catch (Exception e) {
			txtInfo.setText("Sorry, your day's exercises data could not be loaded from database at this time.\nPlease try again later.");
			
			clearCardioFields();
			clearStrengthFields();
			clearCompetitiveFields();
		}
		// allow delete box after load
		loadPassed = 1;
		
		// sync gui
		syncGUI();
	}

	private void runDelete() {
		JOptionPane.showMessageDialog(null, "Delete functionality would occur if the procedure existed in database.");
//		ExerciseDay myDay = new ExerciseDay();
//
//		// allow program to clear all fields if can't connect to database
//		try {
//			// set parameters
//			myDay.setPersonName(txtName.getText());
//			myDay.setExerciseDate(Exercise.convertToDate(LocalDate.now()));
//
//			// delete all fields in database
//			myDay.delete();
//
//			// disallow delete box after delete
//			loadPassed = 0;
//
//			// tell user data has been deleted
//			txtInfo.setText("Toy ID data has been deleted from database.");
//
//			// clear all fields to enter new data
//			clearCardioFields();
//			clearStrengthFields();
//			clearCompetitiveFields();
//		} catch (Exception e) {
//			txtInfo.setText("Sorry, your day's exercises data could not be deleted from database at this time.\nPlease try again later.");
//		}
	}

	// SYNC
	@SuppressWarnings("unchecked")
	public void syncGUI() {
		// refresh cardio list
		int cardioSelectedIndex = listFitnessToday1.getSelectedIndex();
		cardioModel.clear();
		for (int n = 0; n < myDay.getExerciseCardios().size(); n++) {
			cardioModel.add(n, myDay.getExerciseCardio(n).getExerciseName());
		}
		listFitnessToday1.setSelectedIndex(Math.min(cardioSelectedIndex, cardioModel.getSize()));

		// refresh strength list
		int strengthSelectedIndex = listFitnessToday2.getSelectedIndex();
		strengthModel.clear();
		for (int n = 0; n < myDay.getExerciseStrengths().size(); n++) {
			strengthModel.add(n, myDay.getExerciseStrength(n).getExerciseName());
		}
		listFitnessToday2.setSelectedIndex(Math.min(strengthSelectedIndex, cardioModel.getSize()));

		// refresh competitive list
		int competitiveSelectedIndex = listFitnessToday3.getSelectedIndex();
		competitiveModel.clear();
		for (int n = 0; n < myDay.getExerciseCompetitives().size(); n++) {
			competitiveModel.add(n, myDay.getExerciseCompetitive(n).getExerciseName());
		}
		listFitnessToday3.setSelectedIndex(Math.min(competitiveSelectedIndex, cardioModel.getSize()));
	}
	
	private void syncDayInfo() {
		txtName.setText(myDay.getPersonName());
		txtDate.setText(myDay.getExerciseDate().toString().substring(0,10));
		txtRestingHR.setText(String.valueOf(myDay.getRestingHeartRate()));
		txtWeight.setText(String.valueOf(myDay.getWeight()));
	}
}