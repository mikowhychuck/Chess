package net.hypki.wmi.oop.chess;

public class Tournament {

	private ChessInterface white;

	private ChessInterface black;

	public Tournament() {

	}

	public Tournament(ChessInterface white, ChessInterface black) {
		setWhite(white);
		setBlack(black);
	}

	public ChessInterface getWhite() {
		return white;
	}

	public void setWhite(ChessInterface white) {
		this.white = white;
	}

	public ChessInterface getBlack() {
		return black;
	}

	public void setBlack(ChessInterface black) {
		this.black = black;
	}

	public void run() {
		String nextMove = null;
		while (true) {
			nextMove = getWhite().nextMove(null);

			if (nextMove == null)
				break;

			System.out.println("Next move: " + nextMove);
			white.printBoard();
			System.out.println();
			
			nextMove = getBlack().nextMove(nextMove);
				
			if (nextMove == null)
				break;
			
			System.out.println("Next move: " + nextMove);
			black.printBoard();
			System.out.println();
		}
	}

	private String nextMove(ChessInterface chessInterface, String nextMove) {
		try {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			});
			
			return chessInterface.nextMove(nextMove);
		} catch (Throwable t) {
			System.err.println(t);
			return null;
		}
	}
}
