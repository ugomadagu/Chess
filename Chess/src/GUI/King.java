package GUI;

import static java.lang.Math.abs;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class King extends boardPieces {
	final String BLACKKING = "./Chess/BlackKing.png";
	final String WHITEKING = "./Chess/WhiteKing.png";
	private boolean isFirstMove = true;
	final int BLACK = 0;
	final int WHITE = 1;

	public King(int row, int col, String iconName, int color, boardPieces king) {
		super(row, col, iconName, color, king);
	}

	private boolean canCastle(int endRow, int endCol, boardPieces[][] boardOfPieces) {
		//If castling the black king to the left
		if(color == BLACK && endRow == 0 && endCol == 2 && boardOfPieces[0][0].isFirstMove) {
			//Checks if the path between the king and rook is clear
			for(int i = col; i > 0; i--) {
				if(boardOfPieces[0][i].iconName != "Empty") {
					return false;
				}
			}
			return true;
		}

		//If castling the black king to the right
		if(color == BLACK && endRow == 0 && endCol == 6 && boardOfPieces[0][7].isFirstMove) {
			//Checks if the path between the king and rook is clear
			for(int i = col; i < 7; i++) {
				if(boardOfPieces[0][i].iconName != "Empty") {
					return false;
				}
			}
			return true;
		}

		//If castling the white king to the left
		if(color == WHITE && endRow == 7 && endCol == 2 && boardOfPieces[7][0].isFirstMove) {
			//Checks if the path between the king and rook is clear
			for(int i = col; i > 0; i--) {
				if(boardOfPieces[0][i].iconName != "Empty") {
					return false;
				}
			}
			return true;
		}

		//If castling the white king to the right
		if(color == WHITE && endRow == 7 && endCol == 6 && boardOfPieces[7][7].isFirstMove) {
			//Checks if the path between the king and rook is clear
			for(int i = col; i < 7; i++) {
				if(boardOfPieces[7][i].iconName != "Empty") {
					return false;
				}
			}
			return true;
		}

		// King is not castling
		return false;
	}

	public void move(int endRow, int endCol, JPanel[][] boardOfImages, boardPieces[][] boardOfPieces) {
		//Moves king on boardOfImages
		isFirstMove = false;
		if(color == WHITE) {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(WHITEKING)));
			boardOfImages[endRow][endCol].validate();
		} else {
			boardOfImages[row][col].removeAll();
			boardOfImages[endRow][endCol].removeAll();
			boardOfImages[endRow][endCol].add(new JLabel(new ImageIcon(BLACKKING)));
			boardOfImages[endRow][endCol].validate();
		}

		//Moves king on boardOfPieces
		boardOfPieces[endRow][endCol] = boardOfPieces[row][col];
		boardOfPieces[row][col] = new EmptySpace(row, col, "Empty", -1 );
		row = endRow;
		col = endCol;
		if(color == BLACK) {
			GameGUI.blackKingRow = row;
			GameGUI.blackKingCol = col;
		} else {
			GameGUI.whiteKingRow = row;
			GameGUI.whiteKingCol = col;
		}

	}

	public Boolean isValidMove(int endRow, int endCol, boardPieces[][] boardOfPieces) {
		if(endRow < 0 || endCol < 0) {
			return false;
		}

		if((color == WHITE && GameGUI.turnToMove == WHITE) || (color == BLACK && GameGUI.turnToMove == BLACK)) {
			boolean movedDiagonallyByOneSquare = abs(endRow - row) == 1 &&
					abs(endCol - col) == 1;

			//Checks if the target square is blocked by a piece of like color
			if(boardOfPieces[endRow][endCol].color == color) {
				return false;
			}

			if(isMovingIntoCheck(boardOfPieces, endRow, endCol)) {
				return false;
			}

			//Checks the validity of castling a king
			if(isFirstMove && canCastle(endRow, endCol, boardOfPieces)) {
				return true;
			}

			//Checks the validity of upward movement
			if(col == endCol && endRow == (row - 1)) {
				return true;
			}

			//Checks the validity of downward movement
			if(col == endCol && endRow == (row + 1)) {
				return true;
			}

			//Checks the validity of left movement
			if(endCol == (col - 1) && row == endRow) {
				return true;
			}

			//Checks the validity of right movement
			if(endCol == (col + 1) && row == endRow) {
				return true;
			}

			//Checks the validity of diagonal movement
			if(movedDiagonallyByOneSquare) {
				return true;
			}

			//If the king is not moving up, down, left, or right by one square or castling
			return false;

		} else {
			return false;
		}
	}

	public boolean isInCheck(boardPieces[][] boardOfPieces) {

		// Checks upwards for threats
		for(int currRow = row - 1; currRow >= 0; currRow--) {
			if(boardOfPieces[currRow][col].iconName.compareTo("Empty") != 0) { //If the current square is not empty
				if (boardOfPieces[currRow][col].color != color && 
						(boardOfPieces[currRow][col].iconName.compareTo("Rook") == 0 || boardOfPieces[currRow][col].iconName.compareTo("Queen") == 0)) {
					return true;
				} else {
					break;
				}
			}
		}

		// Checks downward for threats
		for(int currRow = row + 1; currRow < 8; currRow++) {
			if(boardOfPieces[currRow][col].iconName.compareTo("Empty") != 0) { //If the current square is not empty
				if(boardOfPieces[currRow][col].color != color && 
						(boardOfPieces[currRow][col].iconName.compareTo("Rook") == 0 || boardOfPieces[currRow][col].iconName.compareTo("Queen") == 0)) {
					return true;
				} else {
					break;
				}
			}
		}

		//Checks right side for threats
		for(int currCol = col + 1; currCol < 8; currCol++) {
			if(boardOfPieces[row][currCol].iconName.compareTo("Empty") != 0) { //If the current square is not empty
				if(boardOfPieces[row][currCol].color != color && 
						(boardOfPieces[row][currCol].iconName.compareTo("Rook") == 0 || boardOfPieces[row][currCol].iconName.compareTo("Queen") == 0)) {
					return true;
				} else {
					break;
				}
			}
		}

		//Checks left side for threats
		for(int currCol = col - 1; currCol >= 0; currCol--) {
			if(boardOfPieces[row][currCol].iconName.compareTo("Empty") != 0) { //If the current square is not empty
				if(boardOfPieces[row][currCol].color != color && 
						(boardOfPieces[row][currCol].iconName.compareTo("Rook") == 0 || boardOfPieces[row][currCol].iconName.compareTo("Queen") == 0)) {
					return true;
				} else {
					break;
				}
			}
		}

		//Checks upward-left diagonal for threats
		if(boardOfPieces[row][col].color == WHITE  && (row - 1 >= 0) && (col - 1 >= 0) &&  //If there is a pawn threat
				(boardOfPieces[row - 1][col - 1].iconName.compareTo("Pawn") == 0) 
				&& (boardOfPieces[row - 1][col - 1].color == BLACK))  {   
			return true;
		} else {
			//Checks for threats from other pieces
			for(int currRow = row - 1, currCol = col - 1; currRow >= 0 && currCol >= 0; currRow--, currCol--) {
				if(boardOfPieces[currRow][currCol].iconName.compareTo("Empty") != 0) { //If the current square is not empty
					if(boardOfPieces[currRow][currCol].color != color && 
							(boardOfPieces[currRow][currCol].iconName.compareTo("Bishop") == 0 || boardOfPieces[currRow][currCol].iconName.compareTo("Queen") == 0)) {
						return true;
					} else {
						break;
					}
				}
			}
		}

		//Checks upward-right diagonal for threats
		if(boardOfPieces[row][col].color == WHITE  && (row - 1 >= 0) && (col + 1 < 8) &&  //If there is a pawn threat
				(boardOfPieces[row - 1][col + 1].iconName.compareTo("Pawn") == 0) 
				&& (boardOfPieces[row - 1][col + 1].color == BLACK))  {   
			return true;
		} else {
			//Checks for threats from other pieces
			for(int currRow = row - 1, currCol = col + 1; currRow >= 0 && currCol < 8; currRow--, currCol++) {
				if(boardOfPieces[currRow][currCol].iconName.compareTo("Empty") != 0) { //If the current square is not empty
					if(boardOfPieces[currRow][currCol].color != color && 
							(boardOfPieces[currRow][currCol].iconName.compareTo("Bishop") == 0 || boardOfPieces[currRow][currCol].iconName.compareTo("Queen") == 0)) {
						return true;
					} else {
						break;
					}
				}
			}
		}

		//Checks downward-left diagonal for threats
		if(boardOfPieces[row][col].color == BLACK  && (row + 1 < 8) && (col - 1 >= 0) &&  //If there is a pawn threat
				(boardOfPieces[row + 1][col - 1].iconName.compareTo("Pawn") == 0) 
				&& (boardOfPieces[row + 1][col - 1].color == WHITE))  {   
			return true;
		} else {
			//Checks for threats from other pieces
			for(int currRow = row + 1, currCol = col - 1; currRow < 8 && currCol >= 0; currRow++, currCol--) {
				if(boardOfPieces[currRow][currCol].iconName.compareTo("Empty") != 0) { //If the current square is not empty
					if(boardOfPieces[currRow][currCol].color != color && 
							(boardOfPieces[currRow][currCol].iconName.compareTo("Bishop") == 0 || boardOfPieces[currRow][currCol].iconName.compareTo("Queen") == 0)) {
						return true;
					} else {
						break;
					}
				}
			}
		}

		//Checks downward-right diagonal for threats
		if(boardOfPieces[row][col].color == BLACK  && (row + 1 < 8) && (col + 1 < 8) &&  //If there is a pawn threat
				(boardOfPieces[row + 1][col + 1].iconName.compareTo("Pawn") == 0) 
				&& (boardOfPieces[row + 1][col + 1].color == WHITE))  {   
			return true;
		} else {
			//Checks for threats from other pieces
			for(int currRow = row + 1, currCol = col + 1; currRow < 8 && currCol < 8; currRow++, currCol++) {
				if(boardOfPieces[currRow][currCol].iconName.compareTo("Empty") != 0) { //If the current square is not empty
					if(boardOfPieces[currRow][currCol].color != color && 
							(boardOfPieces[currRow][currCol].iconName.compareTo("Bishop") == 0 || boardOfPieces[currRow][currCol].iconName.compareTo("Queen") == 0)) {
						return true;
					} else {
						break;
					}
				}
			}
		}

		//Checks if the king is being threatened by a knight
		if( 	(row - 2 >= 0 && col - 1 >= 0 && boardOfPieces[row - 2][col - 1].iconName.compareTo("Knight") == 0 && boardOfPieces[row - 2][col - 1].color != color) ||
				(row - 2 >= 0 && col + 1 < 8 && boardOfPieces[row - 2][col + 1].iconName.compareTo("Knight") == 0 && boardOfPieces[row - 2][col + 1].color != color)  ||
				(row - 1 >= 0 && col + 2 < 8 && boardOfPieces[row - 1][col + 2].iconName.compareTo("Knight") == 0 && boardOfPieces[row - 1][col + 2].color != color)  ||
				(row + 1 < 8 && col + 2 < 8 && boardOfPieces[row + 1][col + 2].iconName.compareTo("Knight") == 0 && boardOfPieces[row + 1][col + 2].color != color)   ||
				(row + 2 < 8 && col + 1 < 8 && boardOfPieces[row + 2][col + 1].iconName.compareTo("Knight") == 0 && boardOfPieces[row + 2][col + 1].color != color)   ||
				(row + 2 < 8 && col - 1 >= 0 && boardOfPieces[row + 2][col - 1].iconName.compareTo("Knight") == 0 && boardOfPieces[row + 2][col - 1].color != color)  ||
				(row + 1 < 8 && col - 2 >= 0 && boardOfPieces[row + 1][col - 2].iconName.compareTo("Knight") == 0 && boardOfPieces[row + 1][col - 2].color != color)  ||
				(row - 1 >= 0 && col - 2 >= 0  && boardOfPieces[row - 1][col - 2].iconName.compareTo("Knight") == 0 && boardOfPieces[row - 1][col - 2].color != color)
				) {	
			return true;
		}


		//No threats were detected in any direction
		return false;
	}

	//TODO finish this method
	public boolean isInCheckMate(boardPieces[][] boardOfPieces) {
		//Can checkmate be avoided by moving the King?
		if(isValidMove(row - 1, col - 1, boardOfPieces) && !isMovingIntoCheck(boardOfPieces,row - 1, col - 1)) {
			return false;
		}
		if(isValidMove(row - 1, col, boardOfPieces) && !isMovingIntoCheck(boardOfPieces,row - 1, col)) {
			return false;
		}
		if(isValidMove(row - 1, col + 1, boardOfPieces) && !isMovingIntoCheck(boardOfPieces,row - 1, col + 1)) {
			return false;
		}
		if(isValidMove(row, col + 1, boardOfPieces) && !isMovingIntoCheck(boardOfPieces,row, col + 1)) {
			return false;
		}
		if(isValidMove(row + 1, col + 1, boardOfPieces) && !isMovingIntoCheck(boardOfPieces,row + 1, col + 1)) {
			return false;
		}
		if(isValidMove(row + 1, col, boardOfPieces) && !isMovingIntoCheck(boardOfPieces,row + 1, col)) {
			return false;
		}
		if(isValidMove(row + 1, col - 1, boardOfPieces) && !isMovingIntoCheck(boardOfPieces,row + 1, col - 1)) {
			return false;
		}
		if(isValidMove(row, col - 1, boardOfPieces) && !isMovingIntoCheck(boardOfPieces,row, col - 1)) {
			return false;
		}




		return true;
	}

}
