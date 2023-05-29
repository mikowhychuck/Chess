package net.hypki.wmi.oop.chess;

/**
 * Interface for chess programs for the classes 'Programowanie obiektowe' for Java language
 *
 */
public interface ChessInterface {
	/**
	 * 
	 * @param opponentMove - it is a simple from-to format (e.g. e2-e4)
	 * @return
	 */
	public String nextMove(String opponentMove);
	
	public void printBoard();
}
