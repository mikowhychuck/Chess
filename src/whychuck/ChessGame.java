package whychuck;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.hypki.wmi.oop.chess.ChessInterface;


public class ChessGame implements ChessInterface {
    public Board board;
    
    public ChessGame() {
    }

    @Override
    public String nextMove(String opponentMove) {
    	if(board == null) { //pierwszy ruch
    		if(opponentMove == null) {
    			board = new Board(true); //jestem biały
    		}else
    			board = new Board(false); //jestem czarny
    	}
    	
    	if(opponentMove != null) { //wprowadzanie ruchu przeciwnika na plansze
    		Movement opponentMovement = formatClassicNotationToMyNotation(opponentMove);
    		int indexFrom = opponentMovement.indexFrom;
    		int indexTo = opponentMovement.indexTo;
    		board.move(indexFrom, indexTo);
    	}
        
    	if(board.amIChecked()) {
    		System.out.println("Jest szach");
    		Movement movement = board.killAttackingFigure();
    		if(movement != null) {
    			board.getCell(movement.indexFrom).getFigure().move(board, movement.indexFrom, movement.indexTo);
    			System.out.println("Udało się zbić atakującą figurę");
    			return formatMoveToClassicNotation(movement.indexFrom, movement.indexTo);
    		}else {
    			System.out.println("Nie udało się zbić atakującej figury");
//    			movement = board.moveKingToAvoidMate();
//        		if(movement != null) {
//        			board.getMyKing().move(board, movement.indexFrom, movement.indexTo);
//        			System.out.println("Król unika ataku ruszając się");
//        			return formatMoveToClassicNotation(movement.indexFrom, movement.indexTo);
//        		}else {
//        			System.out.println("Nie udało się uciec przed atakiem");
//        			return null;
//        		}
    			return null;
    		}
    		
    	}
    	
    	List<Cell> myCells = board.getMyCells();
        Cell selectedCell = getRandomCell(myCells);
        int selectedCellCoordinate = selectedCell.getCoordinate();
        int targetIndex = generateRandomTarget();

        Figure figure = selectedCell.getFigure();
        boolean moveSuccessful = figure.move(board, selectedCellCoordinate, targetIndex);
        if(board.amIChecked() && moveSuccessful) {
        	figure.move(board, targetIndex, selectedCellCoordinate);
        	return nextMove(opponentMove);
        }
        
        if (moveSuccessful) {
        	return formatMoveToClassicNotation(selectedCellCoordinate, targetIndex); 
        } else {
            return nextMove(opponentMove);
        }
    }

    @Override
    public void printBoard() {
        board.print();
    }

    

    private Cell getRandomCell(List<Cell> cells) {
        Random random = new Random();
        int index = random.nextInt(cells.size());
        return cells.get(index);
    }

    private int generateRandomTarget() {
        Random random = new Random();
        return random.nextInt(64);
    }
    
    public String formatMoveToClassicNotation(int fromIndex, int toIndex) {
        String fromPosition = convertIndexToPosition(fromIndex);
        String toPosition = convertIndexToPosition(toIndex);
        return fromPosition + "-" + toPosition;
    }
    
    public Movement formatClassicNotationToMyNotation(String classicNotation) {
    	String notation[] = classicNotation.split("-");
    	int from = convertPositionToIndex(notation[0]);
    	int to = convertPositionToIndex(notation[1]);
    	Movement movement = new Movement(from, to);
    	return movement;
    }
    
    //konwersja indeksu pola do klasycznej notacji i odwrotnie
    public String convertIndexToPosition(int index) {
        int row = index / 8;
        int col = index % 8;
        char file = (char) ('a' + col);
        int rank = row+1;
        return String.valueOf(file) + rank;
    }
    
    public int convertPositionToIndex(String position) {
    	int column = position.charAt(0) - 'a';
    	int row = Character.getNumericValue(position.charAt(1));
        return (row-1)*8 + column;
    	
    }
}


