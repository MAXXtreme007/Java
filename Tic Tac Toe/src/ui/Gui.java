package ui;

import game.Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Gui {
	public static JFrame mainFrame;
	public static JLabel[] board = new JLabel[9];
	public Gui() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Tic-Tac-Toe");
		mainFrame.getContentPane().setBackground(Color.BLACK);
		mainFrame.setBounds(0, 0, 405, 425);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		Container cp = mainFrame.getContentPane();
		mainFrame.getContentPane().setLayout(new GridLayout(3, 3, 2, 2));
		for (int i = 0; i < 9; i++) {
			JPanel tile = new JPanel();
			tile.setBackground(Color.white);
			tile.setVisible(true);
			final JLabel label = new JLabel();
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);
			label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 123));
			label.setVisible(true);
			board[i] = label;
			tile.add(label);
			cp.add(tile);
			cp.setComponentZOrder(tile, 0);
			tile.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
					Game.playOn(label, Game.playerToken);
				}
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stu
				}
			});
		}
	}
}
