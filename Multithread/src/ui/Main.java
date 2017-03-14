package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import bp.Database;

public class Main {

	private JFrame frame;
	JTextPane txtSqlToExecute = new JTextPane();
	JLabel lblDatabaseTester = new JLabel("Database Tester");
	Database myDb = new Database();
	Thread myDbThread = new Thread(myDb);

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
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(lblDatabaseTester, BorderLayout.NORTH);

		JButton btnExecuteSqu = new JButton("Execute Sql");
		btnExecuteSqu.addActionListener(new BtnExecuteSquActionListener());
		frame.getContentPane().add(btnExecuteSqu, BorderLayout.SOUTH);

		txtSqlToExecute.setText("Select * \nFrom ExerciseDay");
		frame.getContentPane().add(txtSqlToExecute, BorderLayout.CENTER);
	}

	private class BtnExecuteSquActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myDbThread = new Thread(myDb);
			myDb.setSqlToExecute(txtSqlToExecute.getText());
			myDbThread.start();
			Thread hounder = new Thread(new Hound());
			hounder.start();
		}
	}

	class Hound implements Runnable {
		@Override
		public void run() {
			try {
				myDbThread.join();
				String outcome = myDb.getExecuteResult();
				JOptionPane.showMessageDialog(null, outcome);
			} catch (InterruptedException e) {
			}
		}
	}
}
