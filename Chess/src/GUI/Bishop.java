package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static java.lang.Math.abs;

public class Bishop extends boardPieces {
	final String BLACKBISHOP = "BlackBishop.png";
	final String WHITEBISHOP = "WhiteBishop.png";
	final int BLACK = 0;
	final int WHITE = 1;

	public Bishop(int row, int col, String iconName, int color) {
		super(row, col, iconName, color);
	}

	private boolean isPathClear(int endRow, int endCol, boardPieces[][] boardOfPieces) {
		//If bishop is moved diagonally upwards-left
		if((endRow < row) && (endCol < col)) {
			int currentRow = row - 1;
			int currentCol = col - 1; 
			while(currentRow != endRow && currentCol != endCol) {
				if(boardOfPieces[currentRow][currentCol].iconName.compareTo("Empty") != 0){
					return false;
				}
				--currentRow;
				--currentCol;
			}
			return true;
		}

		//If bishop is moved diagonally upwards-right
		if((endRow < row) && (endCol > col)) {
			int currentRow = row - 1;
			int currentCol = col + 1; 
			while(currentRow != endRow && currentCol != endCol) {
				if(boardOfPieces[currentRow][currentCol].iconName.compareTo("Empty") != 0){
					return false;
				}
				--currentRow;
				++currentCol;
			}
			return true;
		}

		//If bishop is moved diagonally downward-left
		if((endRow > row) && (endCol < col)) {
			int currentRow = row + 1;
			int currentCol = col - 1; 
			while(currentRow != endRow && currentCol != endCol) {
				if(boardOfPieces[currentRow][currentCol].iconName.compareTo("Empty") != 0){
					return false;
				}
				++currentRow;
				--currentCol;
			}
			return true;
		}

		//If bishop is moved diagonally downward-right
		if((endRow > row) && (endCol > col)) {
			int currentRow = row + 1;
			int currentCol = col + 1; 
			while(currentRow != endRow && currentCol != endCol) {
				if(boardOfPieces[currentRow][currentCol].iconName.compareTo("Empty") != 0){
					return false;
				}
				++currentRow;
				++currentCol;
			}
			return true;
		}

		//Program should never reach this point
		return false;



	}

	public void move(int endRow, int endCol, JPanel[][]boardOfImages, boardPieces[][] boardOfPieces) {
		//Moves bishop on boardOfImages
		if(color == WHITE) {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(WHITEBISHOP)));
			boardOfImages[endRow][endCol].validate();
		} else {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(BLACKBISHOP)));
			boardOfImages[endRow][endCol].validate();
		}

		//Moves bishop on boardOfPieces
		boardOfPieces[endRow][endCol] = boardOfPieces[row][col];
		boardOfPieces[row][col] = new EmptySpace(row, col, "Empty", -1 );
		row = endRow;
		col = endCol;
	}

	public Boolean isValidMove(int endRow, int endCol, boardPieces[][] boardOfPieces) {
		
		if((color == WHITE && GameGUI.turnToMove == WHITE) || (color == BLACK && GameGUI.turnToMove == BLACK)) {
			//Checks if the target square is blocked by a piece of like color
			if(boardOfPieces[endRow][endCol].color == color) {
				return false;
			}

			//Checks for move validity
			boolean movedDiagonally = abs(endRow - row) == abs(endCol - col);

			if(movedDiagonally && isPathClear(endRow, endCol, boardOfPieces)) {
				return true;
			} else {
				return false;
			}
		} else {
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
