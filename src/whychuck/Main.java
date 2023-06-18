package whychuck;
import java.util.List;

import net.hypki.wmi.oop.chess.Tournament;

class Main {

	public static void main(String[] args) {
		ChessGame white = new ChessGame();
		ChessGame black = new ChessGame();
		String lastMove = null;
		while(true) {
			lastMove = white.nextMove(lastMove);
			if(lastMove == null) {
				System.out.println("Czarni wygrali");
				break;
			}
			System.out.println("ruch białych: " + lastMove);
			lastMove = black.nextMove(lastMove);
			if(lastMove == null) {
				System.out.println("Biali wygrali");
				break;
			}	
			System.out.println("ruch czarnych: " + lastMove);
		}
	}
}
