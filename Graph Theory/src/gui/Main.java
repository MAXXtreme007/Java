package gui;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.QuadCurve2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import util.Bezier;

import javax.swing.KeyStroke;

import java.awt.event.InputEvent;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.JPopupMenu;
import java.awt.Insets;
import java.awt.Frame;
import java.awt.Window.Type;

public class Main {
	
	private int VERTEX_SCALE = 8;
	
	private File saveDestination = new File(System.getProperty("user.home"), "Graphs");
	private File saveFile = null;
	private ArrayList<Graph> actionHistory = new ArrayList<>();
	private int atHistory = 0;
	
	private Graph graph = new Graph();
	private Graph mostCurrent = graph;
	private ArrayList<Vertex> selectedVertices = new ArrayList<>();
	private ArrayList<Vertex> mouseOverVertices = new ArrayList<>();
	private ArrayList<Edge> selectedEdges = new ArrayList<>();
	private ArrayList<Edge> mouseOverEdges = new ArrayList<>();
	private ArrayList<Object[]> initalGripOffsets = new ArrayList<>();
	private Vertex grabbing = null;
	private Edge grabbedEdge = null;
	private Point initialGripPoint, currentGripPoint;
	private Rectangle selectionDragger = new Rectangle();
	private ArrayList<Vertex> shortestPath = new ArrayList<>();
	private ArrayList<Edge> displayCycle = new ArrayList<>();
	private ArrayList<Vertex> displayCycleVerts = new ArrayList<>();
	//
	
	private JFrame frmGraphDesigner;
	private JPanel panel, optionPane, drawPane, refreshPane;
	private JButton btnClear, btnShowComponents, btnPrintEulerCycle;
	
	//
	
	private DefaultListModel output = new DefaultListModel();
	private JList Output;
	
	//
	
	private JTable table;
	private boolean dragging = false, redrawing = false, showingCycle = false;
	private JMenu menu;
	private JCheckBoxMenuItem cBoxVertexLabels, cBoxEdgeLabels, cBoxShortestPath, cBoxGrid, chckbxmntmSnapToGrid;
	private JLabel lblNewLabel;
	private JLabel lblVertexSettings;
	private JLabel lblPathSettings;
	private JMenuBar menuBar_1;
	private JMenu mnFile;
	private JButton btnGenerateGraph;
	private JSpinner spinnerBtn2;
	private JSpinner spinnerBtn1;
	private JSeparator separator;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;
	private JMenuItem mntmLoad;
	private JButton btnShowEulerCycle;
	private JSlider sliderCycleDelay;
	private JMenu mnDemos;
	private JMenuItem mntmEulerDemo;
	private JLabel lblEdgeSettings;
	private JCheckBoxMenuItem cBoxDragBreak;
	private JMenu mnEdit;
	private JMenuItem mntmUndoLast;
	private JMenuItem mntmRedoLast;
	private JSeparator separator_1;
	private JMenuItem mntmCopy;
	private JMenuItem mntmPaste;
	private JSeparator separator_2;
	private JTabbedPane tabbedPane_1;
	private JScrollPane adMatrix;
	private JButton btnPrintHamiltonian;
	private JButton btnShowHamiltonianCycle;
	private JProgressBar progressBar;
	private JButton btnBipartiteSets;
	private JPanel outputBtnsPnl;
	private JPanel panel_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    try {
            // Set cross-platform Java L&F (also called "Metal")
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	        try {
				UIManager.setLookAndFeel(
				        UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				System.exit(0);
			}
	    }
	    catch (Exception e) {
	    	System.exit(0);
	    }
	  
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmGraphDesigner.setVisible(true);
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
		saveDestination.mkdirs();
		//addUndo();
		
		frmGraphDesigner = new JFrame();
		frmGraphDesigner.setPreferredSize(new Dimension(700, 700));
		frmGraphDesigner.setMinimumSize(new Dimension(400, 400));
		frmGraphDesigner.setTitle("Graph Designer");
		frmGraphDesigner.setBounds(100, 100, 789, 642);
		frmGraphDesigner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGraphDesigner.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(150, 22));
		scrollPane.setPreferredSize(new Dimension(150, 150));
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmGraphDesigner.getContentPane().add(scrollPane, BorderLayout.SOUTH);
		
		Output = new JList();
		Output.setFont(new Font("Dialog", Font.BOLD, 10));
		Output.setModel(output);
		Output.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Output.setValueIsAdjusting(true);
		Output.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Output.setAutoscrolls(false);
		scrollPane.setViewportView(Output);
		
		
		JLabel lblOutput = new JLabel("Console");
		lblOutput.setVerticalAlignment(SwingConstants.TOP);
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBorder(null);
		scrollPane.setColumnHeaderView(lblOutput);
		
		optionPane = new JPanel();
		optionPane.setPreferredSize(new Dimension(150, 10));
		optionPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmGraphDesigner.getContentPane().add(optionPane, BorderLayout.EAST);
		optionPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		outputBtnsPnl = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) outputBtnsPnl.getLayout();
		flowLayout_1.setVgap(0);
		outputBtnsPnl.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Output", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		outputBtnsPnl.setPreferredSize(new Dimension(135, 102));
		optionPane.add(outputBtnsPnl);
		
		btnShowComponents = new JButton("Component Sets");
		btnShowComponents.setContentAreaFilled(false);
		outputBtnsPnl.add(btnShowComponents);
		btnShowComponents.setPreferredSize(new Dimension(125, 20));
		btnShowComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<ArrayList<Vertex>> components = graph.getComponentSets();
				print(components.size() > 0 ? ("Component sets: " + (graph.getComponentSets()).toString()) : "Graph contains no vertices.", components.size() > 0 ? "blue" : "red");
			}
		});
		btnShowComponents.setToolTipText("<html>Shows all component sets in the graph.</html>");
		btnShowComponents.setFont(new Font("Dialog", Font.BOLD, 12));
		btnShowComponents.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		btnBipartiteSets = new JButton("Bipartite Sets");
		btnBipartiteSets.setContentAreaFilled(false);
		outputBtnsPnl.add(btnBipartiteSets);
		btnBipartiteSets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<ArrayList<Vertex>> bipartiteSets = graph.getBipartiteSets();
				print(bipartiteSets.size() > 0 ? ("Bipartite sets: " + bipartiteSets.toString()) : "Graph is not bipartite.", bipartiteSets.size() > 0 ? "blue" : "red");
			}
		});
		btnBipartiteSets.setToolTipText("<html>Draws a graph based on the values below.</html>");
		btnBipartiteSets.setPreferredSize(new Dimension(125, 20));
		btnBipartiteSets.setFont(new Font("Dialog", Font.BOLD, 12));
		btnBipartiteSets.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		btnPrintEulerCycle = new JButton("Euler Cycle");
		btnPrintEulerCycle.setContentAreaFilled(false);
		outputBtnsPnl.add(btnPrintEulerCycle);
		btnPrintEulerCycle.setPreferredSize(new Dimension(125, 20));
		btnPrintEulerCycle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Vertex> eulerCycle = graph.getEulerCycle();
				if (eulerCycle.size() > 0) {
					print("Euler Cycle - " + graph.getEulerCycle().toString(), "blue");
				} else print("No Euler Cycle exists.", "red");
			}
		});
		btnPrintEulerCycle.setToolTipText("<html>Prints out a set of the vertices from start to finish of an Euler cycle.</html>");
		btnPrintEulerCycle.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPrintEulerCycle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		btnPrintHamiltonian = new JButton("Hamiltonian Cycle");
		btnPrintHamiltonian.setContentAreaFilled(false);
		outputBtnsPnl.add(btnPrintHamiltonian);
		btnPrintHamiltonian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread td = new Thread() {
					public void run() {
						ArrayList<Vertex> hamiltonianCycle = graph.getHamiltonianCycle();
						if (hamiltonianCycle.size() > 0) {
							print("Hamiltonian Cycle: " + hamiltonianCycle.toString(), "blue");
						} else print("No Hamiltonian Cycle exists.", "red");
						try {
							join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
				td.start();
			}
		});
		btnPrintHamiltonian.setToolTipText("<html>Prints out a set of the vertices from start to finish of a Hamiltonian cycle.</html>");
		btnPrintHamiltonian.setPreferredSize(new Dimension(125, 20));
		btnPrintHamiltonian.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPrintHamiltonian.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(0);
		panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Cycle demonstration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setPreferredSize(new Dimension(135, 100));
		optionPane.add(panel_1);
		
		btnShowEulerCycle = new JButton("Euler Cycle");
		btnShowEulerCycle.setContentAreaFilled(false);
		panel_1.add(btnShowEulerCycle);
		btnShowEulerCycle.addActionListener(new showCycle("Euler"));	
		btnShowEulerCycle.setToolTipText("<html>Shows an Euler cycle step-by-step.</html>");
		btnShowEulerCycle.setPreferredSize(new Dimension(125, 20));
		btnShowEulerCycle.setFont(new Font("Dialog", Font.BOLD, 12));
		btnShowEulerCycle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		btnShowHamiltonianCycle = new JButton("Hamiltonian Cycle");
		btnShowHamiltonianCycle.setContentAreaFilled(false);
		panel_1.add(btnShowHamiltonianCycle);
		btnShowHamiltonianCycle.addActionListener(new showCycle("Hamiltonian"));
		btnShowHamiltonianCycle.setToolTipText("<html>\r\nShows a Hamiltonian cycle step-by-step. <br>\r\n<font color=\"red\">Note:</font> there exists n! different sequences of vertices.<br>\r\nWhere n is the number of vertices. This problem is NP-Complete.<br>\r\nMaximum time to solve: O(1.657^n)\r\n\r\n</html>");
		btnShowHamiltonianCycle.setPreferredSize(new Dimension(125, 20));
		btnShowHamiltonianCycle.setFont(new Font("Dialog", Font.BOLD, 11));
		btnShowHamiltonianCycle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		sliderCycleDelay = new JSlider();
		panel_1.add(sliderCycleDelay);
		sliderCycleDelay.setMinorTickSpacing(1);
		sliderCycleDelay.setFont(new Font("Dialog", Font.BOLD, 6));
		sliderCycleDelay.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "<html>Step Delay</html>", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		sliderCycleDelay.setValue(500);
		sliderCycleDelay.setMinimum(10);
		sliderCycleDelay.setMaximum(1500);
		sliderCycleDelay.setMajorTickSpacing(10);
		sliderCycleDelay.setPreferredSize(new Dimension(125, 40));
		
		btnClear = new JButton("Clear Graph");
		btnClear.setContentAreaFilled(false);
		btnClear.setPreferredSize(new Dimension(135, 20));
		btnClear.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//addUndo();
				graph = new Graph();
				frmGraphDesigner.repaint();
				updateData();
				//mostCurrent = graph.clone();
				output.clear();
			}
		});
		
		btnGenerateGraph = new JButton("Draw Complete Graph");
		btnGenerateGraph.setContentAreaFilled(false);
		btnGenerateGraph.addMouseListener(new drawGraph());
		btnGenerateGraph.setToolTipText("<html>Draws a graph based on the values below.</html>");
		btnGenerateGraph.setPreferredSize(new Dimension(135, 20));
		btnGenerateGraph.setFont(new Font("Dialog", Font.BOLD, 12));
		btnGenerateGraph.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		optionPane.add(btnGenerateGraph);
		
		spinnerBtn1 = new JSpinner();
		spinnerBtn1.setPreferredSize(new Dimension(50, 20));
		spinnerBtn1.setModel(new SpinnerNumberModel(5, 1, null, 1));
		optionPane.add(spinnerBtn1);
		
		separator = new JSeparator();
		separator.setPreferredSize(new Dimension(10, 2));
		optionPane.add(separator);
		
		spinnerBtn2 = new JSpinner();
		spinnerBtn2.setPreferredSize(new Dimension(50, 20));
		spinnerBtn2.setModel(new SpinnerNumberModel(5, 1, null, 1));
		optionPane.add(spinnerBtn2);
		btnClear.setToolTipText("<html><b>Warning:</b> This action removes <b><font color=red>ALL</font></b> edges and vertices from the screen.</html>");
		btnClear.setFont(new Font("Dialog", Font.BOLD, 12));
		optionPane.add(btnClear);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmGraphDesigner.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		drawPane = new GridPanel();
		tabbedPane.addTab("Builder", null, drawPane, null);
		drawPane.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		drawPane.addMouseListener(new handleDrawing());
		drawPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		drawPane.setBackground(Color.WHITE);
		drawPane.setLayout(null);
		
		panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Editor", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane_1, BorderLayout.CENTER);
		
		adMatrix = new JScrollPane();
		adMatrix.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tabbedPane_1.addTab("Adjacency Matrix", null, adMatrix, null);
		
		table = new adjacencyMatrixDisplay();
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table.setCellSelectionEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		adMatrix.add(table);
		adMatrix.setViewportView(table);
		
		menuBar_1 = new JMenuBar();
		frmGraphDesigner.setJMenuBar(menuBar_1);
		
		mnFile = new JMenu("File");
		menuBar_1.add(mnFile);		
		
		mntmSave = new JMenuItem("Save");
		mntmSave.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	// Quick save option first.
		    	if (saveFile != null) {
		    		String save = "";
		    		for (Vertex v : graph.getVertices()) {
			    		String vertices = "";
			    		for (Vertex v2 : v.getAdjacentVertices()) {
			    			for (Edge e : v2.getEdges()) {
			    				if (e.isIncidentTo(v2) && e.isIncidentTo(v) && e.getVertex1() == v2) {
			    					vertices+=v2.getNumber()+",";
			    				}
			    			}
			    		}
			    		if (v.getAdjacentVertices().size() > 0 && vertices.length() > 1) vertices = "," + vertices.substring(0, vertices.length()-1);
			    		save += "" + v.getX() + "," + v.getY() + vertices + ",-1;";
			    	}
			    	try {
						FileWriter writer = new FileWriter(saveFile);
						writer.write(save);
						writer.close();
						JOptionPane.showMessageDialog(frmGraphDesigner, "Saved successfully!");
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	} else saveFileChooser();
		    }
		});
		mntmSave.setPreferredSize(new Dimension(150, 21));
		mnFile.add(mntmSave);
		
		mntmSaveAs = new JMenuItem("Save As..");
		mntmSaveAs.setBorder(UIManager.getBorder("MenuItem.border"));
		mntmSaveAs.setBorderPainted(true);
		mntmSaveAs.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mntmSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmSaveAs.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	saveFileChooser();
		    }
		});
		mntmSaveAs.setPreferredSize(new Dimension(200, 21));
		mnFile.add(mntmSaveAs);
		
		mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	UIManager.put("FileChooser.openButtonText", "Load");
		    	UIManager.put("FileChooser.fileNameLabelText", "File Name:");
		    	UIManager.put("FileChooser.readOnly", true); 
		    	JFileChooser chooser = new JFileChooser(saveDestination);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt", "txt");
			    chooser.setFileFilter(filter);
			    chooser.setMultiSelectionEnabled(false);
			    chooser.remove(0);
			    chooser.remove(0);
			    //System.out.println(chooser.getComponentCount());
			    chooser.setDialogTitle("Load Graph");
			    chooser.setAcceptAllFileFilterUsed(false);
			    int returnVal = chooser.showOpenDialog(frmGraphDesigner);
			    if (returnVal == JFileChooser.APPROVE_OPTION) {
			    	File loaded = new File(saveDestination, chooser.getSelectedFile().getName());
			    	try {
			    		Scanner fileScanner = new Scanner(loaded);
			    		if (fileScanner.hasNextLine()) {
			    			loadData(fileScanner.nextLine());
			    		} else {
			    			print("Failed to load: Save file is empty.", "red");
			    			loaded.delete();
			    		}
			    		fileScanner.close();
					} catch (FileNotFoundException e) {
						print("File does not exist", "red");
					}
			    }	
		    }
		});
		
		separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(100, 2));
		mnFile.add(separator_1);
		mntmLoad.setPreferredSize(new Dimension(150, 21));
		mnFile.add(mntmLoad);
		
		mnDemos = new JMenu("Demos..");
		mnFile.add(mnDemos);
		
		mntmEulerDemo = new JMenuItem("Euler Cycle Demo");
		mntmEulerDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputStream is = getClass().getResourceAsStream("EulerDemo.txt");
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				try {
					loadData(br.readLine());
					br.close();
					isr.close();
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnDemos.add(mntmEulerDemo);
		
		mnEdit = new JMenu("Edit");
		menuBar_1.add(mnEdit);
		
		mntmUndoLast = new JMenuItem("Undo");
		mntmUndoLast.setPreferredSize(new Dimension(120, 21));
		mntmUndoLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graph = actionHistory.get(Math.max(--atHistory, 0));
				updateData();
				//graph.clone();
				drawPane.repaint();
			}
		});
		mntmUndoLast.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnEdit.add(mntmUndoLast);
		
		mntmRedoLast = new JMenuItem("Redo");
		mntmRedoLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println((atHistory) + " -- " + (actionHistory.size()-1));
				if (atHistory == actionHistory.size()-1) { 
					graph = mostCurrent;
					atHistory++;
				} else graph = actionHistory.get(Math.min((++atHistory), actionHistory.size()-1));
				drawPane.repaint();
			}
		});
		mntmRedoLast.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		mnEdit.add(mntmRedoLast);
		
		separator_2 = new JSeparator();
		mnEdit.add(separator_2);
		
		mntmCopy = new JMenuItem("Copy");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCopy);
		
		mntmPaste = new JMenuItem("Paste");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mnEdit.add(mntmPaste);
		
		menu = new JMenu("Settings");
		menuBar_1.add(menu);
		menu.setHorizontalTextPosition(SwingConstants.CENTER);
		menu.setDelay(400);
		
		lblNewLabel = new JLabel("Display");
		menu.add(lblNewLabel);
		lblNewLabel.setBorder(null);
		lblNewLabel.setPreferredSize(new Dimension(0, 10));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 9));
		cBoxGrid = new JCheckBoxMenuItem("Show Grid");
		cBoxGrid.setToolTipText("Toggles background grid being rendered.");
		cBoxGrid.addActionListener(new updateSettings());
		menu.add(cBoxGrid);
		cBoxGrid.setSelected(true);
		
		lblPathSettings = new JLabel("Path Settings");
		menu.add(lblPathSettings);
		lblPathSettings.setBorder(null);
		lblPathSettings.setPreferredSize(new Dimension(0, 10));
		lblPathSettings.setFont(new Font("Dialog", Font.BOLD, 9));
		
		cBoxShortestPath = new JCheckBoxMenuItem("Show Shortest Path");
		cBoxShortestPath.setToolTipText("<html>\r\nDisplays the shortest path from a vertex to a vertex.\r\n<br>If no vertices are selected displays the shortest path from the first to last vertices\r\n<br> If one vertex is selected displays shortest path from the selected vertex to the last vertex\r\n<br> If two or more vertices are selected displays the path from the first selected vertex to the last.\r\n</html>");
		cBoxShortestPath.addActionListener(new updateSettings());
		menu.add(cBoxShortestPath);
		
		lblVertexSettings = new JLabel("Vertex Settings");
		menu.add(lblVertexSettings);
		lblVertexSettings.setBorder(null);
		lblVertexSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		lblVertexSettings.setPreferredSize(new Dimension(0, 10));
		lblVertexSettings.setFont(new Font("Dialog", Font.BOLD, 9));
		
		cBoxVertexLabels = new JCheckBoxMenuItem("Show Vertex Labels");
		cBoxVertexLabels.setToolTipText("Toggle vertex labels being displayed.\r\n");
		cBoxVertexLabels.addActionListener(new updateSettings());
		menu.add(cBoxVertexLabels);
		cBoxVertexLabels.setSelected(true);
		
		chckbxmntmSnapToGrid = new JCheckBoxMenuItem("Snap to Grid");
		menu.add(chckbxmntmSnapToGrid);
		chckbxmntmSnapToGrid.setSelected(true);
		chckbxmntmSnapToGrid.setToolTipText("Causes vertices to snap to a grid when being dragged.");
		
		lblEdgeSettings = new JLabel("Edge Settings");
		lblEdgeSettings.setPreferredSize(new Dimension(0, 10));
		lblEdgeSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEdgeSettings.setFont(new Font("Dialog", Font.BOLD, 9));
		lblEdgeSettings.setBorder(null);
		menu.add(lblEdgeSettings);
		
		cBoxEdgeLabels = new JCheckBoxMenuItem("Show Edge Labels");
		cBoxEdgeLabels.addActionListener(new updateSettings());
		cBoxEdgeLabels.setToolTipText("<html><font color=red>Warning:</font> Enabling Edge Labels may cause significant tax to your CPU when there is a large number of edges.</html>");
		menu.add(cBoxEdgeLabels);
		
		cBoxDragBreak = new JCheckBoxMenuItem("Drag Break");
		cBoxDragBreak.setSelected(true);
		cBoxDragBreak.setToolTipText("<html>Makes the edge break (remove) if dragged past a certain point.</html>");
		menu.add(cBoxDragBreak);
		
		drawPane.setDoubleBuffered(true);
		drawPane.addMouseMotionListener(new handleDragging());
		frmGraphDesigner.pack();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new deleteSelected());
		
	}
	
//	private void addUndo() {
//		actionHistory.removeAll(actionHistory.subList(Math.max(Math.min(atHistory, actionHistory.size()), 0), Math.max(actionHistory.size(), 0)));
//		actionHistory.add(graph.clone());
//		atHistory = actionHistory.size();
//	}
	
	private void loadData(String loadedData) {
		String load = loadedData;
		//addUndo();
		//System.out.println(load);
		int lastChunk = 0;
		ArrayList<ArrayList<Integer>> dataSets = new ArrayList<>();
		for (int i = 0; i < load.length(); i++) {
			ArrayList<Integer> data = new ArrayList<Integer>();
			dataSets.add(data);
			String chunk = load.substring(lastChunk, load.indexOf(";", lastChunk)+1);
			int posInChunk = 0;
			for (int j = 0; j < chunk.length(); j++) {
				//System.out.println(chunk.indexOf(",", posInChunk));
				data.add((int) toNumber(chunk.substring(posInChunk, chunk.indexOf(",", posInChunk))));
				//System.out.println((chunk.substring(posInChunk, chunk.length()).contains(",")));
				posInChunk = chunk.indexOf(",", posInChunk)+1;
				if (chunk.indexOf(",", posInChunk) == -1) break; 
				//System.out.println(posInChunk);
			}
			lastChunk = lastChunk+chunk.length();
			if (!load.substring(lastChunk, load.length()).contains(";")) break; 
		}
		for (int i = 0; i < dataSets.size(); i++) {
			ArrayList<Integer> data = dataSets.get(i);
			//System.out.println(data.size());
			graph.addVertex(data.get(0), data.get(1));
		}
		for (int i = 0; i < dataSets.size(); i++) {
			ArrayList<Integer> data = dataSets.get(i);
			for (int j = 2; j < data.size(); j++) {
				//System.out.println(data.get(j));
				graph.addEdge(graph.getVertex(i+1), graph.getVertex((data.get(j)+1)));
			}
		}
		updateData();
		updatePath();
		drawPane.repaint();
		//mostCurrent = graph.clone();
	}
	
	private void saveFileChooser() {
		UIManager.put("FileChooser.openButtonText", "Save");
		UIManager.put("FileChooser.fileNameLabelText", "Save As:");
		UIManager.put("FileChooser.readOnly", true); 
    	JFileChooser chooser = new JFileChooser(saveDestination);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt", "txt");
	    chooser.setFileFilter(filter);
	    chooser.setMultiSelectionEnabled(false);
	    chooser.remove(0);
	    chooser.remove(0);
	    chooser.setDialogTitle("Save Graph");
	    chooser.setAcceptAllFileFilterUsed(false);
	    int returnVal = chooser.showOpenDialog(frmGraphDesigner);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	String fileName = chooser.getSelectedFile().getName();
	    	if (!fileName.contains(".txt")) {
	    		//System.out.println(fileName);
	    		fileName += ".txt";
	    		//System.out.println(fileName);
	    	}
	    	File newSave = new File(saveDestination, fileName);
	    	saveDestination.mkdirs();
	    	String save = "";
	    	for (Vertex v : graph.getVertices()) {
	    		String vertices = "";
	    		for (Vertex v2 : v.getAdjacentVertices()) {
	    			for (Edge e : v2.getEdges()) {
	    				if (e.isIncidentTo(v2) && e.isIncidentTo(v) && e.getVertex1() == v2) {
	    					vertices+=v2.getNumber()+",";
	    				}
	    			}
	    		}
	    		if (v.getAdjacentVertices().size() > 0 && vertices.length() > 1) vertices = "," + vertices.substring(0, vertices.length()-1);
	    		save += "" + v.getX() + "," + v.getY() + vertices + ",-1;";
	    	}
	    	try {
				FileWriter writer = new FileWriter(newSave);
				writer.write(save);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	print("Saved graph to: " + chooser.getSelectedFile().getName(), "blue");
	    	//JOptionPane.showMessageDialog(frmGraphDesigner, "Saved successfully!");
	    	saveFile = newSave;
	    }	
	}
	
	private Point correctVertexLabel(Vertex v) {
		if (v != null) {
			double avgAng = 0;
			for (Vertex v2 : v.getAdjacentVertices()) {
				double angle = Math.atan2(v.getY() - v2.getY(), v.getX() - v2.getX());
				if (angle >= Math.PI/2 && angle <= Math.PI && v.getAdjacentVertices().size() > 1) angle = -angle;
				avgAng += angle;
			}
			avgAng = (v.getAdjacentVertices().size() > 0) ? avgAng / v.getAdjacentVertices().size() : 0;
			//System.out.println(v + ": " + avgAng);
			double xOffset = 15*(Math.cos(avgAng));
			double yOffset = 15*(Math.sin(avgAng));
			return new Point((int) (v.getX()+xOffset), (int) (v.getY()+yOffset));
		}
		return null;
	}
	
	private void updateData() {
		int[][] adjacency = graph.getAdjacencyMatrix();
		String[] headers = new String[graph.getVertices().size()+1];
		headers[0] = "";
		for (int i = 0; i < graph.getVertices().size(); i++) {
			headers[i+1] = ("v" + (i+1));
		}
		Object[][] matrix = new Object[graph.getVertices().size()][graph.getVertices().size()+1];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j >= 1) {
					matrix[i][j] = adjacency[Math.max(i, 0)][j-1];
				} else if (j == 0) {
					matrix[i][j] = ("v" + (i+1));
				}
			}
		}
		table.setModel(new DefaultTableModel(matrix, headers) {
			boolean[] columnEditables = new boolean[] {
					false
				};
				public boolean isCellEditable(int row, int column) {
					if (columnEditables.length-1 >= (column)) return columnEditables[column];
					return true;
				}
		});
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(25+(Math.max((headers[i].length()-2)*5, 0)));
			if (i == 0) table.getColumnModel().getColumn(i).setPreferredWidth(25+(Math.max((headers[headers.length-1].length()-2)*5, 0)));
		}
		selectedEdges = new ArrayList<>();
		selectedVertices = new ArrayList<>();
		mouseOverEdges = new ArrayList<>();
		mouseOverVertices = new ArrayList<>();
	}
	
	private void updatePath() {
		if (graph.getVertices().size() > 1) { 
			Thread td = new Thread() {
				public void run() {
					shortestPath = new ArrayList<>();
					Vertex v1 = graph.getVertices().get(0);
					Vertex v2 = graph.getVertices().get(graph.getVertices().size()-1);
					if (selectedVertices.size() == 1) {
				 		v1 = selectedVertices.get(0);
					} else if (selectedVertices.size() > 1) {
						v1 = selectedVertices.get(0);
						v2 = selectedVertices.get(selectedVertices.size()-1);
					}
					shortestPath = graph.getShortestPath(v1, v2);
					//System.out.println(shortestPath);
					drawPane.repaint();
					try {
						join();
					} catch (InterruptedException e) {
						interrupt();
					}
				}
			};
			td.start();
		}
	}
	
	private void print(String str) {
		print(str, "black");
	}
	private void print(String str, String color) {
		int maxText = (int) ((Output.getWidth()/5.4));
		boolean tooLong = false;
		String oldString = str;
		int outOfBoundsPos = 0;
		if (str.length() >= maxText+5) {
			tooLong = true;
			outOfBoundsPos = str.substring(0, maxText+5).lastIndexOf(",") + 2;
			//System.out.println(outOfBoundsPos);
			str = str.substring(0, outOfBoundsPos);
		}
		output.addElement("<html><font color=" + color + ">" + str + "</font></html>");
		Output.ensureIndexIsVisible(output.size()-1);
		if (output.size() > 300) {
			output.remove(0);
		}
		if (tooLong) print(oldString.substring(outOfBoundsPos, oldString.length()), color);
	}
	
	private double toNumber(String s) {
		double x = 0;
		try {
			x = Double.parseDouble(s);
		} catch (Exception e) {
			x = 0;
		}
		return x;
	}
	
	public boolean findInArrayList(ArrayList<?> list, Object obj) {
		if (obj != null) {
			for (Object index : list) {
				if (index == obj) return true;
			}
		}
		return false;
	}
	
	public void handleVertexDrag(Vertex v, Point e) {
		if (!chckbxmntmSnapToGrid.isSelected()) {
			v.setPosition(Math.max(Math.min((int) (e.getX())-VERTEX_SCALE/2, drawPane.getWidth()-VERTEX_SCALE), 0), 
					Math.max(Math.min((int) (e.getY())-VERTEX_SCALE/2, (drawPane.getHeight()-20)-VERTEX_SCALE), 0));
		} else {
			v.setPosition(Math.max(Math.min((((int) Math.floor((e.getX()) - ((e.getX())%10))-VERTEX_SCALE/2)+2), drawPane.getWidth()-VERTEX_SCALE), 0), 
					Math.max(Math.min((((int) Math.floor((e.getY()) - ((e.getY())%10))-VERTEX_SCALE/2)+2), (drawPane.getHeight()-20)-VERTEX_SCALE), 0));
		}
	}
	
	private Edge findEdgeOnPoint(Point p) {
		for (Edge ed : graph.getEdges()) {
			Point v1 = new Point(ed.getVertex1().getX()+VERTEX_SCALE/2, ed.getVertex1().getY()+VERTEX_SCALE/2);
			Point v2 = new Point(ed.getVertex2().getX()+VERTEX_SCALE/2, ed.getVertex2().getY()+VERTEX_SCALE/2);
			Point v3 = new Point((int) ed.getCurve().getX()+1, (int) ed.getCurve().getY()+1);
			if (Bezier.isPointOnCurve(p, v1, v3, v2)) {
				return ed;
			}
		}
		return null;
	}
	
	private class adjacencyMatrixDisplay extends JTable {
		private int lastRow, lastColumn;
		public void setValueAt(Object to, int row, int column) {
			super.setValueAt((Object) ((int) toNumber((String) to)), row, column);
		}
		public Component prepareEditor(TableCellEditor editor, int row, int column) {
			Component c = super.prepareEditor(editor, row, column);
			lastRow = row;
			lastColumn = column;
			return c;
		}
		public void removeEditor() {
			super.removeEditor();
			Vertex v1 = graph.getVertices().get(lastRow);
			Vertex v2 = graph.getVertices().get(lastColumn-1);
			int value = Integer.parseInt("" + this.getModel().getValueAt(lastRow, lastColumn));
			if (value <= 0 && v1.isAdjacentTo(v2)) {
				Edge e = v1.getIncidentEdge(v2);
				graph.removeEdge(e);
			} 
			if (value > 0) {
				Edge e = graph.addEdge(v1, v2);
				Main.this.print("Drew edge incident to " + v1 + " and " + v2);
			}
			updateData();
		}
	}
	
	private class updateSettings implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			drawPane.repaint();
		}
	}
	
	public class showCycle implements ActionListener {
		private String type;
		public showCycle(String pType) {
			type = pType;
		}
		public void actionPerformed(ActionEvent arg0) {
			if (!showingCycle) {
				Thread td = new Thread() {
					public void run() {
						ArrayList<Vertex> cycle = type.equals("Euler") ? graph.getEulerCycle() : graph.getHamiltonianCycle();
						try {
							showingCycle = true;
							displayCycleVerts.add(cycle.get(0));
							for (int i = 1; i < cycle.size(); i++) {
								Vertex v = cycle.get(i);
								displayCycleVerts.add(v);
								for (Edge e : v.getEdges()) {
									if (e.isIncidentTo(v) && e.isIncidentTo(cycle.get(i-1)) && !findInArrayList(displayCycle, e)) {
										displayCycle.add(e);
										drawPane.repaint();
										break;
									}
								}
								sleep(sliderCycleDelay.getValue());
							}
							sleep(2000);
							displayCycle = new ArrayList<>();
							displayCycleVerts = new ArrayList<>();
							showingCycle = false;
							drawPane.repaint();
							join();
						} catch (Exception e) {
						}
					}
				};
				td.start();
			} else {
				graph.cancelHamiltonSearch(); 
				showingCycle = false;
			}
		}
	}
	
	private class handleDrawing extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Case for selecting vertices.
			boolean didSelection = false;
			for (Vertex v : graph.getVertices()) {
				if (e.getPoint().distance(v.getX() + VERTEX_SCALE / 2, v.getY() + VERTEX_SCALE / 2) <= VERTEX_SCALE/2) {
					if (selectedVertices.size() == 0 || e.isShiftDown()) {
						selectedVertices.add(v);
						//System.out.println("Selected " + v);
						drawPane.repaint();
						didSelection = true;
					} else if (selectedVertices.size() == 1 && !e.isShiftDown()) {
						//addUndo();
						graph.addEdge(selectedVertices.get(0), v);
						//System.out.println("Drew edge.");
						selectedVertices = new ArrayList<>();
						updateData();
						drawPane.repaint();
						didSelection = true;
						//mostCurrent = graph.clone();
					}
				}
			}
			if (!didSelection) {
				Edge ed = findEdgeOnPoint(e.getPoint());
				if (ed != null) {
					selectedEdges.add(ed);
					didSelection = true;
				}
			}
			// Case for drawing vertices.
			if ((selectedVertices.size() <= 0 && selectedEdges.size() <= 0) && !didSelection && !e.isShiftDown()) {
				//addUndo();
				Vertex v = null;
				if (chckbxmntmSnapToGrid.isSelected()) {
					v = graph.addVertex(((int) Math.floor(e.getX() - (e.getX()%10))-VERTEX_SCALE/2)+2, 
							((int) Math.floor(e.getY() - (e.getY()%10))-VERTEX_SCALE/2)+2);
				} else {
					v = graph.addVertex(e.getX()-VERTEX_SCALE/2, e.getY()-VERTEX_SCALE/2);
				}
				updateData();
				drawPane.repaint();
				print("Created new vertex at: (" + e.getX() + ", " + e.getY() + ")");
			}
			// Case for deselecting all vertices.
			if (!didSelection && !e.isShiftDown()) {
				selectedVertices = new ArrayList<>();
				selectedEdges = new ArrayList<>();
				drawPane.repaint();
			}
			updatePath();
		}
		@Override
		public void mousePressed(MouseEvent e) {
			initialGripPoint = e.getPoint();
			grabbing = null;
			initalGripOffsets = new ArrayList<>();
			for (Vertex v : graph.getVertices()) {
				if (e.getPoint().distance(v.getX()+VERTEX_SCALE/2, v.getY()+VERTEX_SCALE/2) <= VERTEX_SCALE/2) {
					grabbing = v;
					drawPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
					break;
				}
			}
			if (grabbing == null) {
				grabbedEdge = findEdgeOnPoint(e.getPoint());
			} else {
				for (Vertex v : selectedVertices) {
					Point offset = new Point((int) (v.getX() - grabbing.getX()), (int) (v.getY() - grabbing.getY()));
					initalGripOffsets.add(new Object[]{v, offset});
				}
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			updatePath();
			initialGripPoint = null;
			drawPane.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			selectionDragger.setBounds(0,0,0,0);
			dragging = false;
			grabbedEdge = null;
			grabbing = null;
			drawPane.repaint();
		}
	}

	public class handleDragging implements MouseMotionListener {
		int x = -100, y = -100;
		@Override
		public void mouseDragged(MouseEvent e) {
			currentGripPoint = e.getPoint();
			if (grabbing == null && grabbedEdge == null) {
				drawPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				int xOffset = e.getX();
				int yOffset = e.getY();
				if (!dragging) {
					x = xOffset;
					y = yOffset;
					dragging = true;
				}
				selectionDragger.setBounds(Math.min(x, xOffset), Math.min(y, yOffset), Math.abs(x-xOffset), Math.abs(y-yOffset));
				
				if (!e.isShiftDown()) {
					selectedVertices = new ArrayList<>();
				}
				for (Vertex v : graph.getVertices()) {
					if (selectionDragger.contains(v.getX(), v.getY())) {
						if (!findInArrayList(selectedVertices, v)) {
							selectedVertices.add(v);
						}
					}
				}
				//updatePath();
				drawPane.repaint();
			} else if (grabbing != null) {
				drawPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				for (Vertex v : selectedVertices) {
					if (v != grabbing) {
						Point offset = new Point(0,0);
						for (Object info[] : initalGripOffsets) {
							if (info[0] == v) {
								offset = (Point) info[1];
								break;
							}
						}
						handleVertexDrag(v, new Point((int) (e.getX() + offset.getX()), (int) (e.getY() + offset.getY())));
					}
				}
				if (!chckbxmntmSnapToGrid.isSelected()) {
					grabbing.setPosition(Math.max(Math.min((e.getX())-VERTEX_SCALE/2, drawPane.getWidth()-VERTEX_SCALE), 0), 
							Math.max(Math.min((e.getY())-VERTEX_SCALE/2, (drawPane.getHeight()-20)-VERTEX_SCALE), 0));
				} else {
					grabbing.setPosition(Math.max(Math.min((((int) Math.floor((e.getX()) - ((e.getX())%10))-VERTEX_SCALE/2)+2), drawPane.getWidth()-VERTEX_SCALE), 0), 
							Math.max(Math.min((((int) Math.floor((e.getY()) - ((e.getY())%10))-VERTEX_SCALE/2)+2), (drawPane.getHeight()-20)-VERTEX_SCALE), 0));
				}
				updatePath();
				drawPane.repaint();
			} else if (grabbedEdge != null) {
				drawPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				grabbedEdge.setCurve(e.getPoint());
				if (initialGripPoint.distance(e.getPoint()) > Math.min(grabbedEdge.getLength(), 60) && cBoxDragBreak.isSelected()) {
					//addUndo();
					print("Removed " + grabbedEdge, "blue");
					graph.removeEdge(grabbedEdge);
					grabbedEdge = null;
					//mostCurrent = graph.clone();
				}
				drawPane.repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mouseOverVertices = new ArrayList<>();
			mouseOverEdges = new ArrayList<>();
			drawPane.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			boolean hasMouseOver = false;
			for (Vertex v : graph.getVertices()) {
				if (e.getPoint().distance(v.getX()+VERTEX_SCALE/2, v.getY()+VERTEX_SCALE/2) <= VERTEX_SCALE/2) {
					if (!findInArrayList(mouseOverVertices, v)) {
						mouseOverVertices.add(v);
						drawPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						hasMouseOver = true;
						break;
					}
				}
			}
			if (!hasMouseOver) {
				Edge ed = findEdgeOnPoint(e.getPoint());
				if (ed != null) {
					mouseOverEdges.add(ed);
					drawPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}
			drawPane.repaint();
		}
		
	}
	
	private class drawGraph extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Thread td = new Thread() {
				public void run() {
					//addUndo();
					int xOffset = (drawPane.getWidth()/2)-VERTEX_SCALE/2;
					int yOffset = (drawPane.getHeight()/2)-VERTEX_SCALE/2;
					if ((int)spinnerBtn1.getValue() != (int)spinnerBtn2.getValue()) {
						ArrayList<Vertex> verts = new ArrayList<>();
						for (int i = 0; i < (int) spinnerBtn1.getValue(); i++) {
							// Place left.
							int value = (int) spinnerBtn1.getValue();
							Vertex v = graph.addVertex(xOffset-40, yOffset-(40*value)+(40*i)+20*(value+1%2));
							verts.add(v);
						}
						for (int i = 0; i < (int) spinnerBtn2.getValue(); i++) {
							// Place right.
							int value = (int) spinnerBtn2.getValue();
							Vertex v = graph.addVertex(xOffset+40, yOffset-(40*value)+(40*i)+20*(value+1%2));
							for (Vertex v2 : verts) {
								graph.addEdge(v, v2);
							}
						}
					} else {
						ArrayList<Vertex> verts = new ArrayList<>();
						for (int i = 0; i < (int) spinnerBtn1.getValue(); i++) {
							int value = (int) spinnerBtn1.getValue();
							int radius = (int) Math.min((15 * Math.ceil(value / 2)), Math.min(drawPane.getWidth()/2, drawPane.getHeight()/2));
							double theta = i * (Math.PI * 2) / value;
							Vertex v = graph.addVertex((int) (xOffset+(radius * Math.cos(theta))), (int) (yOffset+(radius * Math.sin(theta))));
							for (Vertex v2 : verts) {
								graph.addEdge(v, v2);
							}
							verts.add(v);
						}
					}
					try {
						updateData();
						updatePath();
						drawPane.repaint();
						//mostCurrent = graph.clone();
						join();
					} catch (InterruptedException e) {

					}
				}
			};
			td.start();
		}
	}
	
	public class GridPanel extends JPanel {
		public void paint(Graphics graphics) {
			mntmUndoLast.setEnabled(atHistory > 1);
			mntmRedoLast.setEnabled(atHistory != actionHistory.size());
			if (!redrawing) {
				redrawing = true;
				Graphics2D g = (Graphics2D) graphics;
				g.setColor(Color.white);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				int x = this.getWidth();
				int y = this.getHeight();
				if (cBoxGrid.isSelected()) {
					g.setColor(new Color(220, 220, 220));
					int gridSize = 10;
					for (int i = 0; i <= (this.getHeight()-20) / gridSize; i++) {
						g.drawLine(0, 2 + (i * gridSize), this.getWidth(), 2 + (i * gridSize)); // x -> x
					}
					for (int i = 0; i <= (this.getWidth()) / gridSize; i++) {
						g.drawLine(2 + (i * gridSize), 0, 2 + (i * gridSize), this.getHeight()-20); // y -> y
					}
				}
				// Draw edges and their labels.
				for (Edge e : graph.getEdges()) {
					if (e != null) {
						Vertex ver1 = (graph.getVertices().indexOf(e.getVertex1()) < graph.getVertices().indexOf(e.getVertex2())) ? e.getVertex1() : e.getVertex2();
						Vertex ver2 = (graph.getVertices().indexOf(e.getVertex1()) < graph.getVertices().indexOf(e.getVertex2())) ? e.getVertex2() : e.getVertex1();
						g.setStroke(new BasicStroke(VERTEX_SCALE / 4));
						g.setColor(Color.red);
						Point v1 = new Point((ver1.getX()+VERTEX_SCALE/2), ver1.getY()+VERTEX_SCALE/2);
						Point v2 = new Point(ver2.getX()+VERTEX_SCALE/2, ver2.getY()+VERTEX_SCALE/2);
						if (cBoxShortestPath.isSelected() && (findInArrayList(shortestPath, e.getVertex1()) && findInArrayList(shortestPath, e.getVertex2()))) {
							g.setColor(Color.green);
						}
						if (showingCycle && displayCycle.size() > 0 && findInArrayList(displayCycle, e)) {
							g.setColor(Color.blue);
						} else if (showingCycle && displayCycle.size() > 0 && !findInArrayList(displayCycle, e)) g.setColor(new Color(220, 220, 220));
						if (findInArrayList(mouseOverEdges, e) || findInArrayList(selectedEdges, e)) g.setColor(Color.green);
						if (grabbedEdge != e) {
							if (!e.isLoop()) {
								if (e.getParallelEdges().size() == 0) e.setCurve(new Point(((int) (v1.getX()+v2.getX())/2), ((int) (v1.getY()+v2.getY())/2)));
								int counter = e.getParallelEdges().indexOf(e)+((e.getParallelEdges().size()+1)%2), dir = 1 - (e.getParallelEdges().indexOf(e)%2)*2;
								double angle = Math.atan2(v2.getY()-v1.getY(), v2.getX()-v1.getX());
								int radiusX = (int) ((15 * ((counter+1)/2)) * Math.cos(angle+Math.atan2(25, 0))) * dir;
								int radiusY = (int) ((15 * ((counter+1)/2)) * Math.sin(angle+Math.atan2(25, 0))) * dir;
								e.setCurve(new Point((int) ((v1.getX()+v2.getX())/2+(radiusX)), 
										(int) ((v1.getY()+v2.getY())/2+radiusY)));
								g.draw(new QuadCurve2D.Double(v1.getX(), v1.getY(), e.getCurve().getX(), e.getCurve().getY(), v2.getX(), v2.getY()));
							} else {
								Point pos = correctVertexLabel(ver1);
								v2 = new Point((int) (pos.getX()), (int) (pos.getY()));
								double angle = Math.atan2(v2.getY()-v1.getY(), v2.getX()-v1.getX());
								v2 = new Point((int) (pos.getX() + (30*Math.cos(angle+Math.atan2(20, 0)))), (int) (pos.getY() + (30*Math.sin(angle+Math.atan2(20, 0)))));
								angle = Math.atan2(v2.getY()-v1.getY(), v2.getX()-v1.getX());
								int dir = 1;
								for (int i = 0; i < 2; i++) {
									int radiusX = (int) ((30) * Math.cos(angle+Math.atan2(20, 0))) * dir;
									int radiusY = (int) ((30) * Math.sin(angle+Math.atan2(20, 0))) * dir;
									e.setCurve(new Point((int) ((v1.getX()+v2.getX())/2+(radiusX)), 
											(int) ((v1.getY()+v2.getY())/2+radiusY)));
									dir = -dir;
									g.draw(new QuadCurve2D.Double(v1.getX(), v1.getY(), e.getCurve().getX(), e.getCurve().getY(), v2.getX(), v2.getY()));
								}
							}
						} else if (grabbedEdge == e && currentGripPoint != null) {
							g.drawLine((int) v1.getX(), (int) v1.getY(), (int) currentGripPoint.getX(), (int) currentGripPoint.getY());
							g.drawLine((int) currentGripPoint.getX(), (int) currentGripPoint.getY(), (int) v2.getX(), (int) v2.getY());
						}
						
						if (cBoxEdgeLabels.isSelected() && graph.getEdges().size() < 1000) {
							int angle = (int) Math.atan2(ver1.getY()-e.getVertex2().getY(), ver1.getX()-e.getVertex2().getX());
							Point pos = e.getCurve();
							Font f = new Font("Dialog", Font.BOLD, 12);
							String label = (e.toString().substring(e.toString().lastIndexOf("e")));
							g.setFont(f);
							int lblWidth = g.getFontMetrics().stringWidth(label);
							int lblHeight = g.getFontMetrics().getHeight();
							g.setColor(new Color(162, 162, 162));
							g.drawString(label, (int) (pos.getX() + lblWidth), (int) ((pos.getY() + lblHeight)+Math.sin(angle)));
						}
					}
				}
				Font f = new Font("Dialog", Font.BOLD, 12);
				// Draw vertices and their labels.
				for (Vertex v : graph.getVertices()) {
					g.setColor(Color.black);
					if (showingCycle && displayCycleVerts.size() > 0 && findInArrayList(displayCycleVerts, v)) g.setColor(Color.cyan);
					if (findInArrayList(selectedVertices, v) || findInArrayList(mouseOverVertices, v)) g.setColor(Color.green);
					g.setStroke(new BasicStroke(2));
					g.fillArc(v.getX(), v.getY(), VERTEX_SCALE, VERTEX_SCALE, 0, 360);
					if (cBoxVertexLabels.isSelected()) {
						Point pos = correctVertexLabel(v);
						g.setFont(f);
						String label = ("v" + v.toString().substring(v.toString().lastIndexOf("x") + 1));
						int lblWidth = g.getFontMetrics().stringWidth(label);
						int lblHeight = g.getFontMetrics().getHeight();
						g.setColor(new Color(162, 162, 162));
						g.drawString(label, (int) (pos.getX() - (lblWidth/2)+7)-2, (int) (pos.getY() + lblHeight/2)-2+VERTEX_SCALE/2);
					}
				}
				// Draw data labels
				g.setColor(Color.white);
				g.setStroke(new BasicStroke(2));
				g.fillRect(-1, this.getHeight()-20, this.getWidth()+2, this.getHeight());
				g.setColor(Color.darkGray);
				g.drawRect(-1, this.getHeight()-20, this.getWidth()+2, this.getHeight());
				g.setFont(new Font("Dialog", Font.BOLD, 12));
				g.setColor(Color.black);
				String text = "Vertices - " + graph.getVertices().size() + ";";
				int width = g.getFontMetrics().stringWidth(text)+5;
				g.drawString(text, 5, (y-5));
				text = "Edges - " + graph.getEdges().size() + ";";
				g.drawString(text, (width+10), (y-5));
				width += g.getFontMetrics().stringWidth(text)+10;
				text = "Components - " + graph.getComponentSets().size() + ";";
				g.drawString(text, width+10, (y-5));
				width += g.getFontMetrics().stringWidth(text)+10;
				text = "Euler Cycle - " + graph.hasEulerCycle() + ";";
				g.drawString(text, width+10, (y-5));
				width += g.getFontMetrics().stringWidth(text)+10;
				text = "Bipartite - " + (graph.getBipartiteSets().size() > 0) + ";";
				g.drawString(text, width+10, (y-5));
				// Draw selection box.
				g.setStroke(new BasicStroke(1));
				g.setColor(Color.black);
				g.drawRect((int) selectionDragger.getX(), (int) selectionDragger.getY(), (int) selectionDragger.getWidth(), (int) selectionDragger.getHeight());
				redrawing = false;
			}
		}
	}
	public class deleteSelected implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
//			if (e.getKeyChar() == 'w' || e.getKeyChar() == 's' || e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
//				paneScroll = new Point(0, 0);
//				if (e.getKeyChar() == 'w') {
//					paneScroll.setLocation(0, -scrollSpeed);
//				} else if (e.getKeyChar() == 's') {
//					paneScroll.setLocation(0, scrollSpeed);
//				} 
//				if (e.getKeyChar() == 'a') {
//					paneScroll.setLocation(-scrollSpeed, 0);
//				} else if (e.getKeyChar() == 'd') {
//					paneScroll.setLocation(scrollSpeed, 0);
//				}
//				for (Vertex v : graph.getVertices()) {
//					v.setPosition((int) (v.getX() + paneScroll.getX()), (int) (v.getY() + paneScroll.getY())); 
//				}
//				drawPane.repaint();
//			}
			if (e.getKeyChar() == KeyEvent.VK_DELETE) {
				//System.out.println("Deleted");
				//if (selectedVertices.size() > 0 || selectedEdges.size() > 0) addUndo();
				if (selectedVertices.size() > 1) {
					print("Removed vertices: " + selectedVertices, "blue");
				} else if (selectedVertices.size() == 1) print("Removed vertex: " + selectedVertices.get(0), "blue");
				if (selectedEdges.size() > 1) {
					print("Removed edges: " + selectedEdges, "blue");
				} else if (selectedEdges.size() == 1) print("Removed edge: " + selectedEdges.get(0), "blue");
				for (Vertex v1 : selectedVertices) {
					graph.removeVertex(v1);
				}
				for (Edge ed : selectedEdges) {
					graph.removeEdge(ed);
				}
				//mostCurrent = graph.clone();
				updatePath();
				updateData();
				drawPane.repaint();
			}
			return false;
		}
	}
}
