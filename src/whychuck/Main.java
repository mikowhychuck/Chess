package whychuck;

class Main {

	public static void main(String[] args) {

		Board board = new Board();
		board.cells[8].getFigure().move(board, 8, 24);
		board.cells[0].getFigure().move(board, 0, 16);
		board.print();
	}

}
