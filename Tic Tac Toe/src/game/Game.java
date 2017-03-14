package game;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ui.Gui;
import ui.Main;

public class Game {
	public enum Token {
		X, O, EMPTY;
	}
	public static Token turn = Token.X;
	public static Token playerToken = Token.X;
	public static AI ai = new AI(playerToken == Token.X ? Token.O : Token.X);
	public static boolean gameOver = false;
	
	public static void playOn(JLabel tile, Token team) {
		setTile(tile, team);
		turn = turn == Token.X ? Token.O : Token.X;
		doEndGame(hasWinner() | getEmptyTiles() == 0);
		ai.makeMove();
		doEndGame(hasWinner() | getEmptyTiles() == 0);
	}
	
	public static void doEndGame(boolean isGameOver) {
		if (isGameOver) {
			int answer = JOptionPane.showOptionDialog(Gui.mainFrame, "Would you like to play again?", "Game Over!", 0, 3, null, new String[]{"Yes", "No"}, null);
			if (answer == 0) {
				turn = Token.X; // Set turn to X.
				for (JLabel tile : Gui.board) {
					tile.setText("");
				}
				answer = JOptionPane.showOptionDialog(Gui.mainFrame, "Choose a token!", "Choose Your Team", 0, 3, null, new String[]{"X", "O", "Quit"}, null);
				if (answer == 0) playerToken = Token.X; 
				if (answer == 1) playerToken = Token.O;
				if (answer == 2) System.exit(0);
				ai.setTeam(playerToken == Token.X ? Token.O : Token.X);
				if (ai.myTeam == Token.X) ai.makeMove();
			} else {
				System.exit(0);
			}
		}
	}
	
	public static void setTile(JLabel tile, Token type) {
		tile.setText(type.toString());
	}
	
	public static int getEmptyTiles() {
		int open = 0;
		for (JLabel label : Gui.board) {
			if (label.getText().equals("")) open++;
		}
		return open;
	}
	
	public static int getRandomTile() {
		if (getEmptyTiles() == 0) return -1;
		int tileNum = (int) (Math.random() * Gui.board.length - 1);
		JLabel tile = Gui.board[tileNum];
		if (!tile.getText().equals("")) return getRandomTile();
		return tileNum;
	}
	
	public static ArrayList<JLabel> getOpenTiles() {
		ArrayList<JLabel> open = new ArrayList<JLabel>();
		for (JLabel tile : Gui.board) {
			if (tile.getText().equals("")) open.add(tile);
		}
		return open;
	}
	
	public static boolean hasWinner() {
		for (int tile = 0; tile < Gui.board.length; tile++) {
			if (Gui.board[tile].getText().equals("")) continue;
			for (int adjacent = -4; adjacent < 4; adjacent++) { // Find all tiles around the current tile.
				int neighbor = tile + adjacent; // Convert from tile to board space.
				if (neighbor < 0 || neighbor > 8 || adjacent == 0) continue; // If out of map bounds or is the current tile then ignore node.
				if ((neighbor % 3 == 0 && tile % 3 == 2)
						|| (neighbor % 3 == 2 && tile % 3 == 0)) continue; // If tiles are not close enough ignore them.
				if (!Gui.board[neighbor].getText().equals(Gui.board[tile].getText())) continue; // Ignore if unoccupied or occupied by other team.
				int extrapolatedNeighbor = neighbor + adjacent; // Get the third-in-a-row by extrapolating using our "adjacent" number as direction.
				if (extrapolatedNeighbor < 0 || extrapolatedNeighbor > 8) continue; // Make sure it's within bounds.
				if (!Gui.board[extrapolatedNeighbor].getText().equals(Gui.board[neighbor].getText())) continue; // Do team check again.
				if ((neighbor % 3 == 0 && extrapolatedNeighbor % 3 == 2)
						|| (neighbor % 3 == 2 && extrapolatedNeighbor % 3 == 0)) continue; // Do ignore distance check again.
				return true;
			}
		}
		return false;
	}
}
