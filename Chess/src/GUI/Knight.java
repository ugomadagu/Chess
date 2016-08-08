package GUI;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Knight extends boardPieces{
	final String BLACKKNIGHT = "./Chess/BlackKnight.png";
	final String WHITEKNIGHT = "./Chess/WhiteKnight.png";
	final int BLACK = 0;
	final int WHITE = 1;

	public Knight(int row, int col, String iconName, int color, boardPieces king) {
		super(row, col, iconName, color, king);
	}

	public void move(int endRow, int endCol, JPanel[][]boardOfImages, boardPieces[][] boardOfPieces) {
		//Moves knight on boardOfImages
		isFirstMove = false;
		if(color == WHITE) {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(WHITEKNIGHT)));
			boardOfImages[endRow][endCol].validate();
		} else {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(BLACKKNIGHT)));
			boardOfImages[endRow][endCol].validate();
		}

		//Moves knight on boardOfPieces
		boardOfPieces[endRow][endCol] = boardOfPieces[row][col];
		boardOfPieces[row][col] = new EmptySpace(row, col, "Empty", -1 );
		row = endRow;
		col = endCol;
	}

	public Boolean isValidMove(int endRow, int endCol, boardPieces[][] boardOfPieces) {
		if((color == WHITE && GameGUI.turnToMove == WHITE) || (color == BLACK && GameGUI.turnToMove == BLACK)) {
			//Checks if move will put king in check
			if(isMovingIntoCheck(boardOfPieces, endRow, endCol)) {
				return false;
			}

			//Checks if the target square is blocked by a piece of like color
			if(boardOfPieces[endRow][endCol].color == color) {
				return false;
			}

			if(     (endRow == (row - 1) && endCol == (col - 2)) ||
					(endRow == (row - 2) && endCol == (col - 1)) ||
					(endRow == (row - 2) && endCol == (col + 1)) ||
					(endRow == (row - 1) && endCol == (col + 2)) ||
					(endRow == (row + 1) && endCol == (col - 2)) ||
					(endRow == (row + 2) && endCol == (col - 1)) ||
					(endRow == (row + 2) && endCol == (col + 1)) ||
					(endRow == (row + 1) && endCol == (col + 2))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean isInCheck(boardPieces[][] boardOfPieces, ArrayList<boardPieces> attackingPieces) {
		return false;
	}
	public boolean isInCheckMate(boardPieces[][] boardOfPieces, ArrayList<boardPieces> attackingPieces) {
		return false;
	}
}
