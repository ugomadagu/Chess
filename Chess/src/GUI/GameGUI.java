package GUI;

import javax.swing.*;
import java.awt.*;
import GUI.boardPieces;
import GUI.EmptySpace;

public class GameGUI {
	JFrame frame;
	JPanel[][] boardOfImages = new JPanel[8][8];
	boardPieces[][] boardOfPieces = new boardPieces[8][8];
	final static int BLACK = 0;
	final static int WHITE = 1;
	public static int turnToMove = WHITE;
	public static int whiteKingRow = 7;
	public static int whiteKingCol = 4;
	public static int blackKingRow = 0;
	public static int blackKingCol = 4;

	//String representations for the corresponding file names for all the pieces
	final String WHITEPAWN = "WhitePawn.png";
	final String BLACKPAWN = "BlackPawn.png";
	final String BLACKROOK = "BlackRook.png";
	final String WHITEROOK = "WhiteRook.png";
	final String BLACKKNIGHT = "BlackKnight.png";
	final String WHITEKNIGHT = "WhiteKnight.png";
	final String BLACKBISHOP = "BlackBishop.png";
	final String WHITEBISHOP = "WhiteBishop.png";
	final String BLACKQUEEN = "BlackQueen.png";
	final String WHITEQUEEN = "WhiteQueen.png";
	final String BLACKKING = "BlackKing.png";
	final String WHITEKING = "WhiteKing.png";


	public GameGUI() {
		frame = new JFrame("Simplified Chess");
		frame.setSize(600, 600);
		frame.setLayout(new GridLayout(8, 8));

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				boardOfImages[i][j] = new JPanel();

				if ((i + j) % 2 != 0) {
					boardOfImages[i][j].setBackground(Color.gray);
				} else {
					boardOfImages[i][j].setBackground(Color.white);
				}   
				frame.add(boardOfImages[i][j]);
			}
		}
		setUpBoard();
	}

	public void setUpBoard() {
		final int BLACK = 0;
		final int WHITE = 1;

		// Sets up the boardOfImages array
		boardOfImages[0][0].add(new JLabel(new ImageIcon(BLACKROOK)));
		boardOfImages[0][1].add(new JLabel(new ImageIcon(BLACKKNIGHT)));
		boardOfImages[0][2].add(new JLabel(new ImageIcon(BLACKBISHOP)));
		boardOfImages[0][3].add(new JLabel(new ImageIcon(BLACKQUEEN)));
		boardOfImages[0][4].add(new JLabel(new ImageIcon(BLACKKING)));
		boardOfImages[0][5].add(new JLabel(new ImageIcon(BLACKBISHOP)));
		boardOfImages[0][6].add(new JLabel(new ImageIcon(BLACKKNIGHT)));
		boardOfImages[0][7].add(new JLabel(new ImageIcon(BLACKROOK)));
		boardOfImages[7][0].add(new JLabel(new ImageIcon(WHITEROOK)));
		boardOfImages[7][1].add(new JLabel(new ImageIcon(WHITEKNIGHT)));
		boardOfImages[7][2].add(new JLabel(new ImageIcon(WHITEBISHOP)));
		boardOfImages[7][3].add(new JLabel(new ImageIcon(WHITEQUEEN)));
		boardOfImages[7][4].add(new JLabel(new ImageIcon(WHITEKING)));
		boardOfImages[7][5].add(new JLabel(new ImageIcon(WHITEBISHOP)));
		boardOfImages[7][6].add(new JLabel(new ImageIcon(WHITEKNIGHT)));
		boardOfImages[7][7].add(new JLabel(new ImageIcon(WHITEROOK)));

		for (int i = 0; i < 8; i++) {
			boardOfImages[1][i].add(new JLabel(new ImageIcon(BLACKPAWN)));
			boardOfImages[6][i].add(new JLabel(new ImageIcon(WHITEPAWN)));
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Adds listeners to each square on the board
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				boardOfImages[row][col].addMouseListener(new Listener(boardOfImages[row][col], boardOfImages, boardOfPieces, turnToMove));
			}
		}

		//Sets up the pawns in boardOfPieces array
		for (int i = 0; i < 8; i++) {
			boardOfPieces[1][i] = new Pawn(1, i, "Pawn", BLACK);
			boardOfPieces[6][i] = new Pawn(6, i, "Pawn", WHITE);
		}

		//Sets up the rooks in boardOfPieces array
		boardOfPieces[0][0] = new Rook(0, 0, "Rook", BLACK);
		boardOfPieces[0][7] = new Rook(0, 7, "Rook", BLACK);
		boardOfPieces[7][0] = new Rook(7, 0, "Rook", WHITE);
		boardOfPieces[7][7] = new Rook(7, 7, "Rook", WHITE);

		//Sets up the knights in boardOfPieces array
		boardOfPieces[0][1] = new Knight(0, 1, "Knight", BLACK);
		boardOfPieces[0][6] = new Knight(0, 6, "Knight", BLACK);
		boardOfPieces[7][1] = new Knight(7, 1, "Knight", WHITE);
		boardOfPieces[7][6] = new Knight(7, 6, "Knight", WHITE);

		//Sets up the bishops in boardOfPieces array
		boardOfPieces[0][2] = new Bishop(0, 2, "Bishop", BLACK);
		boardOfPieces[0][5] = new Bishop(0, 5, "Bishop", BLACK);
		boardOfPieces[7][2] = new Bishop(7, 2, "Bishop", WHITE);
		boardOfPieces[7][5] = new Bishop(7, 5, "Bishop", WHITE);

		//Sets up the queen on boardOfPieces array
		boardOfPieces[0][3] = new Queen(0, 3, "Queen", BLACK);
		boardOfPieces[7][3] = new Queen(7, 3, "Queen", WHITE);

		//Sets up the king on the boardOfPieces array
		boardOfPieces[0][4] = new King(0, 4, "King", BLACK);
		boardOfPieces[7][4] = new King(7, 4, "King", WHITE);

		//Sets up empty squares in boardOfPieces array
		for (int row = 2; row < 6; row++) {
			for (int col = 0; col < 8; col++) {
				boardOfPieces[row][col] = new EmptySpace(row, col, "Empty", -1 );
			}
		}


	}



	public static void main(String[] args) {
		new GameGUI();
	}



}
