package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class Listener implements MouseListener {
	JPanel square;
	JPanel[][] boardOfImages;
	boardPieces[][] boardOfPieces;
	final int BLACK = 0;
	final int WHITE = 1;
	int turnColor = WHITE;

	public Listener(JPanel square, JPanel[][] boardOfImages, boardPieces[][] boardOfPieces, int turnToMove) {
		this.square = square;
		this.boardOfImages = boardOfImages;
		this.boardOfPieces = boardOfPieces;
	}

	private void resetBoardColors() {
		//Returns the background colors to normal
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 != 0) {
					boardOfImages[i][j].setBackground(Color.gray);
				} else {
					boardOfImages[i][j].setBackground(Color.white);
				}
			}
		}
	}

	// Checks to see if the player's mouse click is intended to move a piece
	private boolean isPlayerMovingAPiece() {
		// Checks if there is a yellow square on the board
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if(boardOfImages[row][col].getBackground().equals(Color.yellow)) {
					// Checks if the square has a piece on it
					if(boardOfPieces[row][col].iconName.compareTo("Empty") != 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private int getYellowRow(){
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if(boardOfImages[row][col].getBackground().equals(Color.yellow)) {
					return row;
				}
			}
		}
		//Code should never get to this point
		return -1;
	}

	private int getYellowCol(){
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if(boardOfImages[row][col].getBackground().equals(Color.yellow)) {
					return col;
				}
			}
		}
		//Code should never get to this point
		return -1;
	}

	private int getBlueRow(){
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if(boardOfImages[row][col].getBackground().equals(Color.blue)) {
					return row;
				}
			}
		}
		//Code should never get to this point
		return -1;
	}

	private int getBlueCol(){
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if(boardOfImages[row][col].getBackground().equals(Color.blue)) {
					return col;
				}
			}
		}
		//Code should never get to this point
		return -1;
	}


	public void mousePressed(MouseEvent e) {
		if(square.getBackground().equals(Color.yellow)) { //If the player clicks a yellow square
			resetBoardColors();

			//Leaves the king square red if it is in check
			if(boardOfPieces[GameGUI.whiteKingRow][GameGUI.whiteKingCol].isInCheck(boardOfPieces, new ArrayList<boardPieces>())) {
				boardOfImages[GameGUI.whiteKingRow][GameGUI.whiteKingCol].setBackground(Color.red);
			} else if(boardOfPieces[GameGUI.blackKingRow][GameGUI.blackKingCol].isInCheck(boardOfPieces, new ArrayList<boardPieces>())) {
				boardOfImages[GameGUI.blackKingRow][GameGUI.blackKingCol].setBackground(Color.red);
			}
		} else {
			// If the player is trying to move a piece
			if(isPlayerMovingAPiece()) {
				square.setBackground(Color.blue); //Makes the target square blue
				int currentRow = getYellowRow();
				int currentCol = getYellowCol();
				int targetRow = getBlueRow();
				int targetCol = getBlueCol();
				Boolean validMove = boardOfPieces[currentRow][currentCol].isValidMove(targetRow, targetCol, boardOfPieces);

				if(validMove) { //Checks move validity
					boardOfPieces[currentRow][currentCol].move(targetRow, targetCol, boardOfImages, boardOfPieces);
					// Alternates which side gets to move
					if (GameGUI.turnToMove == BLACK) {
						GameGUI.turnToMove = WHITE;
					} else {
						GameGUI.turnToMove = BLACK;
					}
					//Resets the color of the former square if move() is successful
					if ((currentRow + currentCol) % 2 != 0) {
						boardOfImages[currentRow][currentCol].setBackground(Color.gray);
					} else {
						boardOfImages[currentRow][currentCol].setBackground(Color.white);
					}


					ArrayList<boardPieces> attackingPieces = new ArrayList<boardPieces>();

					//Checks if black king is in check
					if(boardOfPieces[GameGUI.blackKingRow][GameGUI.blackKingCol].isInCheck(boardOfPieces, attackingPieces)) {
						boardOfImages[GameGUI.blackKingRow][GameGUI.blackKingCol].setBackground(Color.red);
						//Checks if black king is in check mate
						if(boardOfPieces[GameGUI.blackKingRow][GameGUI.blackKingCol].isInCheckMate(boardOfPieces, attackingPieces)) {
							GameGUI.turnToMove = -1; //stops any player from moving because the game is over
							JOptionPane.showMessageDialog(null, "Checkmate!");
						}
					} else {
						//Reset square color (just in case it was red)
						if ((GameGUI.blackKingRow + GameGUI.blackKingCol) % 2 != 0) {
							boardOfImages[GameGUI.blackKingRow][GameGUI.blackKingCol].setBackground(Color.gray);
						} else {
							boardOfImages[GameGUI.blackKingRow][GameGUI.blackKingCol].setBackground(Color.white);
						}
					}
					attackingPieces.clear();

					//Checks if white king is in check
					if(boardOfPieces[GameGUI.whiteKingRow][GameGUI.whiteKingCol].isInCheck(boardOfPieces, attackingPieces)) {
						boardOfImages[GameGUI.whiteKingRow][GameGUI.whiteKingCol].setBackground(Color.red);
						//Checks if white king is in check mate
						if(boardOfPieces[GameGUI.whiteKingRow][GameGUI.whiteKingCol].isInCheckMate(boardOfPieces, attackingPieces)) {
							GameGUI.turnToMove = -1; //stops any player from moving because the game is over
							JOptionPane.showMessageDialog(null, "Checkmate!");
						}
					} else {
						//Reset square color (just in case it was red)
						if ((GameGUI.whiteKingRow + GameGUI.whiteKingCol) % 2 != 0) {
							boardOfImages[GameGUI.whiteKingRow][GameGUI.whiteKingCol].setBackground(Color.gray);
						} else {
							boardOfImages[GameGUI.whiteKingRow][GameGUI.whiteKingCol].setBackground(Color.white);
						}
					}

				} else {
					//Resets the color of the target square if move() is unsuccessful
					if ((targetRow + targetCol) % 2 != 0) {
						boardOfImages[targetRow][targetCol].setBackground(Color.gray);
					} else {
						boardOfImages[targetRow][targetCol].setBackground(Color.white);
					}   

				}
			} else {
				resetBoardColors();
				
				//Leaves the king square red if it is in check
				if(boardOfPieces[GameGUI.whiteKingRow][GameGUI.whiteKingCol].isInCheck(boardOfPieces, new ArrayList<boardPieces>())) {
					boardOfImages[GameGUI.whiteKingRow][GameGUI.whiteKingCol].setBackground(Color.red);
				} else if(boardOfPieces[GameGUI.blackKingRow][GameGUI.blackKingCol].isInCheck(boardOfPieces, new ArrayList<boardPieces>())) {
					boardOfImages[GameGUI.blackKingRow][GameGUI.blackKingCol].setBackground(Color.red);
				}
				
				square.setBackground(Color.yellow);
			}
		}

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
}
