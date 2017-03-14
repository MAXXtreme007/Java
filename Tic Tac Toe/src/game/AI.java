package game;

import java.util.ArrayList;

import javax.swing.JLabel;

import game.Game.Token;
import ui.Gui;

public class AI {
	public static Token myTeam = Token.O;
	public static Token oppTeam = Token.X;
	int moves = 0; // Keep track of AI's moves.
	public AI(Token team) {
		setTeam(team);
	}
	public void setTeam(Token team) {
		myTeam = team;
		oppTeam = Game.playerToken;
	}
	public void makeMove() {
		if (Game.turn.equals(myTeam)) {
			JLabel tile = getBestMove();
			Game.setTile(tile, myTeam);
			Game.turn = Game.turn == Token.X ? Token.O : Token.X;
		}
	}
	
	private JLabel getBestMove() {
//		int best = -1;
//		for (int tile = 0; tile < Gui.board.length; tile++) {
//			if (!Gui.board[tile].getText().equals("")) continue; // Ignore the tile if it's not empty
//			// Do win check.
//			Game.setTile(Gui.board[tile], myTeam);
//			if (Game.hasWinner()) {
//				Gui.board[tile].setText("");
//				return tile; // End search, found a winning move.
//			} else {
//				Gui.board[tile].setText("");
//			}
//			// Check if other player can win. If so, block them.
//			Game.setTile(Gui.board[tile], Game.playerToken);
//			if (Game.hasWinner()) {
//				Gui.board[tile].setText("");
//				best = tile; // Set best move to the one that blocks the opponent.
//			} else {
//				Gui.board[tile].setText("");
//			}
//		}
		Object[] data = minimax(Game.getEmptyTiles(), myTeam, Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.println(data[0]);
		JLabel bestMove = (JLabel) data[1];
		return bestMove;
	}
	
	private ArrayList<JLabel> generateMoves() {
		ArrayList<JLabel> nextMoves = new ArrayList<JLabel>(); // allocate List

		// If gameover, i.e., no next move
		if (Game.hasWinner()) {
			return nextMoves; // return empty list
		}
		for (JLabel tile : Gui.board) {
			if (tile.getText().equals("")) {
				nextMoves.add(tile);
			}
		}
		return nextMoves;
	}
	
//	private int evaluateScore() {
//		int totalScore = 0;
//		for (int tile = 0; tile < Gui.board.length; tile++) {
//			if (tile == 4) continue;
//			int score = 1;
//			if (Gui.board[tile].getText().equals("")) continue;
//			if (Gui.board[tile].getText().equals(oppTeam.toString())) score = -score;
//			Token mT = (score == -1) ? oppTeam : myTeam; // Scoring for team
//			Token oT = (score == -1) ? myTeam : oppTeam; // Scoring against team
//			for (int adjacent = -4; adjacent < 4; adjacent++) { // Find all tiles around the current tile.
//				int neighbor = tile + adjacent; // Convert from tile to board space.
//				if (neighbor < 0 || neighbor > 8 || adjacent == 0) continue; // If out of map bounds or is the current tile then ignore node.
//				if ((neighbor % 3 == 0 && tile % 3 == 2) || (neighbor % 3 == 2 && tile % 3 == 0)) continue; // If tiles are not close enough ignore them.
//				if (Gui.board[neighbor].getText().equals(oT.toString())) {
//					totalScore += score;
//					continue;
//				}
//				if (Gui.board[neighbor].getText().equals(mT.toString())) score *= 10; // Multiply score by 10.
//				int extrapolatedNeighbor = neighbor + adjacent; // Get the third-in-a-row by extrapolating using our "adjacent" number as direction.
//				if (extrapolatedNeighbor < 0 || extrapolatedNeighbor > 8) {
//					totalScore += score;
//					continue;
//				}
//				if ((neighbor % 3 == 0 && extrapolatedNeighbor % 3 == 2) || (neighbor % 3 == 2 && extrapolatedNeighbor % 3 == 0)) continue; // Do ignore distance check again.
//				if (!Gui.board[extrapolatedNeighbor].getText().equals(mT.toString())) {
//					totalScore += score;
//					continue; // Do team check again.
//				}
//				score *= 10; // Multiply score by 10 again.
//				totalScore += score;
//			}
//		}
//		return totalScore;
//	}
	
	private int evaluate() {
		int score = 0;
		// Evaluate score for each of the 8 lines (3 rows, 3 columns, 2
		// diagonals)
		score += evaluateLine(0, 0, 0, 1, 0, 2); // row 0
		score += evaluateLine(1, 0, 1, 1, 1, 2); // row 1
		score += evaluateLine(2, 0, 2, 1, 2, 2); // row 2
		score += evaluateLine(0, 0, 1, 0, 2, 0); // col 0
		score += evaluateLine(0, 1, 1, 1, 2, 1); // col 1
		score += evaluateLine(0, 2, 1, 2, 2, 2); // col 2
		score += evaluateLine(0, 0, 1, 1, 2, 2); // diagonal
		score += evaluateLine(0, 2, 1, 1, 2, 0); // alternate diagonal
		return score;
	}

	private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
		int score = 0;
		
		Token[][] cells = new Token[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				JLabel tile = Gui.board[8 - (y + (3 * x))];
				if (tile.getText().equals("")) {
					cells[x][y] = Token.EMPTY;
				} else {
					cells[x][y] = tile.getText().equals("X") ? Token.X : Token.O;
				}
			}
		}
		
		if (cells[row1][col1] == myTeam) {
			score = 1;
		} else if (cells[row1][col1] == oppTeam) {
			score = -1;
		}

		// Second cell
		if (cells[row2][col2] == myTeam) {
			if (score == 1) { // cell1 is mySeed
				score = 10;
			} else if (score == -1) { // cell1 is oppSeed
				return 0;
			} else { // cell1 is empty
				score = 1;
			}
		} else if (cells[row2][col2] == oppTeam) {
			if (score == -1) { // cell1 is oppSeed
				score = -10;
			} else if (score == 1) { // cell1 is mySeed
				return 0;
			} else { // cell1 is empty
				score = -1;
			}
		}

		// Third cell
		if (cells[row3][col3] == myTeam) {
			if (score > 0) { // cell1 and/or cell2 is mySeed
				score *= 10;
			} else if (score < 0) { // cell1 and/or cell2 is oppSeed
				return 0;
			} else { // cell1 and cell2 are empty
				score = 1;
			}
		} else if (cells[row3][col3] == oppTeam) {
			if (score < 0) { // cell1 and/or cell2 is oppSeed
				score *= 10;
			} else if (score > 1) { // cell1 and/or cell2 is mySeed
				return 0;
			} else { // cell1 and cell2 are empty
				score = -1;
			}
		}
		return score;
	}
	
	private Object[] minimax(int depth, Token player, int alpha, int beta) {
		ArrayList<JLabel> nextMoves = generateMoves();

		if (9 - nextMoves.size() == 0) {
			return new Object[] {0, Gui.board[0]};
		} else if (9 - nextMoves.size() == 2) {
			return new Object[] {0, Gui.board[8]};
		}
		
		int score = 0;
		JLabel bestTile = null;

		if (nextMoves.isEmpty() || depth == 0) {
			score = evaluate();
			return new Object[] {score, bestTile};
		} else {
			for (JLabel tile : nextMoves) {
				tile.setText(player.toString());
				if (player == myTeam) {
					score = (int) minimax(depth - 1, oppTeam, alpha, beta)[0];
					if (score > alpha) {
						alpha = score;
						bestTile = tile;
					}
				} else {
					score = (int) minimax(depth - 1, myTeam, alpha, beta)[0];
					if (score < beta) {
						beta = score;
						bestTile = tile;
					}
				}
				tile.setText("");
				if (alpha >= beta)
					break;
			}
			return new Object[] {(player == myTeam) ? alpha : beta, bestTile};
		}
	}
}
