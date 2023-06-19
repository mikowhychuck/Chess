package whychuck;
import java.util.List;

import net.hypki.wmi.oop.chess.Tournament;

class Main {

	public static void main(String[] args) {
		ChessGame white = new ChessGame();
		ChessGame black = new ChessGame();
		String lastMove = null;
		int i = 0;
		while(true) {
			System.out.println(i);
			lastMove = white.nextMove(lastMove);
			if(lastMove == null) {
				System.out.println("Czarni wygrali");
				black.printBoard();
				break;
			}
			System.out.println("ruch białych: " + lastMove);
			white.printBoard();
			System.out.println();
			lastMove = black.nextMove(lastMove);
			if(lastMove == null) {
				System.out.println("Biali wygrali");
				white.printBoard();
				break;
			}	
			System.out.println("ruch czarnych: " + lastMove);
			black.printBoard();
			System.out.println();
			i++;
		}
	}
}
