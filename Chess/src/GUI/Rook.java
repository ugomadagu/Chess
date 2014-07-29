package GUI;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Rook extends boardPieces {
	final String BLACKROOK = "BlackRook.png";
	final String WHITEROOK = "WhiteRook.png";
	final int BLACK = 0;
	final int WHITE = 1;

	public Rook(int row, int col, String iconName, int color) {
		super(row, col, iconName, color);
	}

	//Checks to see if other pieces are in the way
	private boolean isPathClear(int endRow, int endCol, boardPieces[][] boardOfPieces) {
		boolean movedVertically = (endCol == col);
		boolean movedHorizontally = (endRow == row);

		//If rook is moved upwards
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

		//If the rook is moved downwards
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

		//Program should never get to this point
		return false;
	}

	public void move(int endRow, int endCol, JPanel[][]boardOfImages, boardPieces[][] boardOfPieces) {
		isFirstMove = false;

		//Moves rook on boardOfImages
		if(color == WHITE) {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(WHITEROOK)));
			boardOfImages[endRow][endCol].validate();
		} else {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(BLACKROOK)));
			boardOfImages[endRow][endCol].validate();
		}

		//Moves rook on boardOfPieces
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
			boolean movedVertically = (endCol == col);
			boolean movedHorizontally = (endRow == row);
			if((movedVertically || movedHorizontally) && isPathClear(endRow, endCol, boardOfPieces)) {
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
