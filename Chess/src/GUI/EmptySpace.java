package GUI;

import javax.swing.JPanel;
import java.util.ArrayList;

public class EmptySpace extends boardPieces {
	
	public EmptySpace(int row, int col, String iconName, int color) {
		super(row, col, iconName, color, null);
	}

	//Does nothing
	public void move(int endRow, int endCol, JPanel[][]boardOfImages, boardPieces[][] boardOfPieces) {
		
	}
	//Does nothing
	public Boolean isValidMove(int endRow, int endCol, boardPieces[][] boardOfPieces) {
		return true;
	}
	//Does nothing
	public boolean isInCheck(boardPieces[][] boardOfPieces, ArrayList<boardPieces> attackingPieces) {
		return false;
	}
	//Does nothing
	public boolean isInCheckMate(boardPieces[][] boardOfPieces, ArrayList<boardPieces> attackingPieces) {
		return false;
	}
}
