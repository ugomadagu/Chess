package GUI;

import javax.swing.JPanel;

public abstract class boardPieces {
	int col;
	int row;
	int color;
	String iconName; 
	boolean isFirstMove;
	
	
	public boardPieces(int row, int col, String iconName, int color) {
		this.row = row;
		this.col = col;
		this.iconName = iconName;
		this.color = color;
		isFirstMove = true;
	}
	public abstract void move(int endRow, int endCol, JPanel[][]boardOfImages, boardPieces[][] boardOfPieces);
	public abstract Boolean isValidMove(int endRow, int endCol, boardPieces[][] boardOfPieces);
	public abstract boolean isInCheck(boardPieces[][] boardOfPieces);
	public abstract boolean isInCheckMate(boardPieces[][] boardOfPieces);
	
}
