package GUI;

import javax.swing.JPanel;

public abstract class boardPieces {
	int col;
	int row;
	int color;
	String iconName; 
	boolean isFirstMove;
	boardPieces kingPiece;
	
	
	public boardPieces(int row, int col, String iconName, int color, boardPieces kingPiece) {
		this.row = row;
		this.col = col;
		this.iconName = iconName;
		this.color = color;
		isFirstMove = true;

		if(kingPiece == null) {
			this.kingPiece = this;
		} else {
			this.kingPiece = kingPiece;
		}

	}
	public abstract void move(int endRow, int endCol, JPanel[][]boardOfImages, boardPieces[][] boardOfPieces);
	public abstract Boolean isValidMove(int endRow, int endCol, boardPieces[][] boardOfPieces);
	public abstract boolean isInCheck(boardPieces[][] boardOfPieces);
	public abstract boolean isInCheckMate(boardPieces[][] boardOfPieces);

	boolean isMovingIntoCheck(boardPieces[][] boardOfPieces, int endRow, int endCol) {
		int originalRow = row;
		int originalCol = col;
		boardPieces piecesOnEndSquare = boardOfPieces[endRow][endCol];
		boardOfPieces[endRow][endCol] = boardOfPieces[row][col];
		boardOfPieces[row][col] = new EmptySpace(row, col, "Empty", -1 );
		row = endRow;
		col = endCol;
		if(kingPiece.isInCheck(boardOfPieces)) {
			row = originalRow;
			col = originalCol;
			boardOfPieces[row][col] = boardOfPieces[endRow][endCol];
			boardOfPieces[endRow][endCol] = piecesOnEndSquare;
			return true;
		} else {
			row = originalRow;
			col = originalCol;
			boardOfPieces[row][col] = boardOfPieces[endRow][endCol];
			boardOfPieces[endRow][endCol] = piecesOnEndSquare;
			return false;
		}
	}
	
}
