package GUI;

import static java.lang.Math.abs;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Queen extends boardPieces {
	final String BLACKQUEEN = "BlackQueen.png";
	final String WHITEQUEEN = "WhiteQueen.png";
	final int BLACK = 0;
	final int WHITE = 1;

	public Queen(int row, int col, String iconName, int color) {
		super(row, col, iconName, color);
	}

	private boolean isPathClear(int endRow, int endCol, boardPieces[][] boardOfPieces) {
		boolean movedVertically = (endCol == col);
		boolean movedHorizontally = (endRow == row);

		//If queen is moved upwards
		if(movedVertically && (endRow < row)) {
			int currentRow = row - 1;
			int currentCol = col;
			//Checks if there is a piece between the start square and the target square
			while(currentRow != endRow) {
				if(boardOfPieces[currentRow][currentCol].iconName.compareTo("Empty") != 0){
					return false;
				}
				--currentRow;
			}
			return true;
		}

		//If the queen is moved downwards
		if(movedVertically && (endRow > row)) {
			int currentRow = row + 1;
			int currentCol = col;
			//Checks if there is a piece between the start square and the target square
			while(currentRow != endRow) {
				if(boardOfPieces[currentRow][currentCol].iconName.compareTo("Empty") != 0){
					return false;
				}
				++currentRow;
			}
			return true;
		}

		//If the rook is moved left
		if(movedHorizontally && (endCol < col)) {
			int currentRow = row;
			int currentCol = col - 1;
			//Checks if there is a piece between the start square and the target square
			while(currentCol != endCol) {
				if(boardOfPieces[currentRow][currentCol].iconName.compareTo("Empty") != 0){
					return false;
				}
				--currentCol;
			}
			return true;
		}

		//If the rook is moved right
		if(movedHorizontally && (endCol > col)) {
			int currentRow = row;
			int currentCol = col + 1;
			//Checks if there is a piece between the start square and the target square
			while(currentCol != endCol) {
				if(boardOfPieces[currentRow][currentCol].iconName.compareTo("Empty") != 0){
					return false;
				}
				++currentCol;
			}	
			return true;
		}

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
		//Moves queen on boardOfImages
		if(color == WHITE) {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(WHITEQUEEN)));
			boardOfImages[endRow][endCol].validate();
		} else {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(BLACKQUEEN)));
			boardOfImages[endRow][endCol].validate();
		}

		//Moves queen on boardOfPieces
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

			boolean movedDiagonally = abs(endRow - row) == abs(endCol - col);
			boolean movedVertically = (endCol == col);
			boolean movedHorizontally = (endRow == row);

			if((movedDiagonally || movedVertically || movedHorizontally) && isPathClear(endRow, endCol, boardOfPieces)) {
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
