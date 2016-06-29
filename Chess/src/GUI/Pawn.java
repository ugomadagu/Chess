package GUI;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.boardPieces;

public class Pawn extends boardPieces {
	final String WHITEPAWN = "./Chess/WhitePawn.png";
	final String BLACKPAWN = "./Chess/BlackPawn.png";
	final int BLACK = 0;
	final int WHITE = 1;
	
	public Pawn(int row, int col, String iconName, int color, boardPieces king) {
		super(row, col, iconName, color, king);
	}

	public void move(int endRow, int endCol, JPanel[][]boardOfImages, boardPieces[][] boardOfPieces) {
		isFirstMove = false;
		
		//Moves pawn on boardOfImages
		if(color == WHITE) {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(WHITEPAWN)));
			boardOfImages[endRow][endCol].validate();
		} else {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(BLACKPAWN)));
			boardOfImages[endRow][endCol].validate();
		}
		
		//Moves pawn on boardOfPieces
		boardOfPieces[endRow][endCol] = boardOfPieces[row][col];
		boardOfPieces[row][col] = new EmptySpace(row, col, "Empty", -1 );
		row = endRow;
		col = endCol;
		
	}

	public Boolean isValidMove(int endRow, int endCol, boardPieces[][] boardOfPieces) {
		if(color == WHITE && GameGUI.turnToMove == WHITE) {
			//Checks if move will put king in check
			if(isMovingIntoCheck(boardOfPieces, endRow, endCol)) {
				return false;
			}

			//Checks the validity of double jump movement for white pawns
			if(col == endCol && endRow == (row - 2) && isFirstMove && boardOfPieces[endRow][endCol].iconName.compareTo("Empty") == 0 &&
					boardOfPieces[endRow + 1][endCol].iconName.compareTo("Empty") == 0) {
				return true;
			}
			
			//Checks the validity of vertical movement for white pawns
			if(col == endCol && endRow == (row - 1) && boardOfPieces[endRow][endCol].iconName.compareTo("Empty") == 0) {
				return true;
			}
			
			//Checks the validity of diagonal (capture) movement for white pawns
			if((endCol == (col - 1) || endCol == (col + 1)) && endRow == (row - 1)) {
				if(boardOfPieces[endRow][endCol].iconName.compareTo("Empty") != 0 && boardOfPieces[endRow][endCol].color == BLACK) {
					return true;
				}
			}
			
			return false;
		} else {
			if (GameGUI.turnToMove == WHITE) {
				return false;
			}
			//Checks if move will put king in check
			if(isMovingIntoCheck(boardOfPieces, endRow, endCol)) {
				return false;
			}

			//Checks the validity of double jump movement for black pawns
			if(col == endCol && endRow == (row + 2) && isFirstMove && boardOfPieces[endRow][endCol].iconName.compareTo("Empty") == 0 &&
					boardOfPieces[endRow - 1][endCol].iconName.compareTo("Empty") == 0) {
				return true;
			}
			
			//Checks the validity of vertical movement for black pawns
			if(col == endCol && endRow == (row + 1) && boardOfPieces[endRow][endCol].iconName.compareTo("Empty") == 0) {
				return true;
			}
			
			//Checks the validity of diagonal (capture) movement for black pawns
			if((endCol == (col - 1) || endCol == (col + 1)) && endRow == (row + 1)) {
				if(boardOfPieces[endRow][endCol].iconName.compareTo("Empty") != 0 && boardOfPieces[endRow][endCol].color == WHITE) {
					return true;
				}
			}
			
			return false;
		}
		
	}
	
	public boolean isInCheck(boardPieces[][] boardOfPieces) {
		return false;
	}
	public boolean isInCheckMate(boardPieces[][] boardOfPieces) {
		return false;
	}

}
