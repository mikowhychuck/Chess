package whychuck;

class Main {

	public static void main(String[] args) {

		Board board = new Board();
		board.getCells()[8].getFigure().move(board, 8, 24);
		board.print();
		ChessGame przyklad = new ChessGame();
	}

}
