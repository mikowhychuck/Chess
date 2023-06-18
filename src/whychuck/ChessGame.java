package whychuck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.hypki.wmi.oop.chess.ChessInterface;


public class ChessGame implements ChessInterface {
    private Board board;
    
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
        
    	if(amIChecked()) {
    		System.out.println("Poddaję się");
    		return null;
    	}
    	
    	List<Cell> myCells = getMyCells();
        Cell selectedCell = getRandomCell(myCells);
        int selectedCellCoordinate = selectedCell.getCoordinate();
        int targetIndex = generateRandomTarget();

        Figure figure = selectedCell.getFigure();
        boolean moveSuccessful = figure.move(board, selectedCellCoordinate, targetIndex);
        
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

    public List<Cell> getMyCells() {
        List<Cell> myCells = new ArrayList<>();

        for (Cell cell : board.getCells()) {
            Figure figure = cell.getFigure();
            if (figure != null && figure.isMine()) {
                myCells.add(cell);
            }
        }
        return myCells;
    }
    
    public List<Cell> getOpponentCells() {
        List<Cell> OpponentCells = new ArrayList<>();

        for (Cell cell : this.board.getCells()) {
            Figure figure = cell.getFigure();
            if (figure != null && !figure.isMine()) {
                OpponentCells.add(cell);
            }
        }
        return OpponentCells;
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
    //Gettery koordynatów królów:
    public int getMyKingCoordinates() {
        for (Cell cell : this.board.getCells()) {
            Figure figure = cell.getFigure();
            if (figure instanceof King && figure.isMine()) {
            	return cell.getCoordinate();
            }
        }
        return -1;
    }
    
    public int getOpponentKingCoordinates() {
        for (Cell cell : this.board.getCells()) {
            Figure figure = cell.getFigure();
            if (figure instanceof King && !figure.isMine()) {
            	return cell.getCoordinate();
            }
        }
        return -1;
    }
    //Sprawdzanie szacha:
    public boolean amIChecked() {
    	List<Cell> Cells = getOpponentCells();
    	for (Cell cell : Cells) {
    		if(cell.getFigure().isMoveValid(board, cell.getCoordinate(), getMyKingCoordinates()))
    			return true;
    	}
    	return false;
    }
    public boolean isOpponentChecked() {
    	List<Cell> Cells = getMyCells();
    	for (Cell cell : Cells) {
    		if(cell.getFigure().isMoveValid(board, cell.getCoordinate(), getOpponentKingCoordinates()))
    			return true;
    	}
    	return false;
    }
    public boolean isMate() {
    	int myKingCoordinate = getMyKingCoordinates();
    	for(Cell cell: board.getCells()) {
    		if(board.getCell(myKingCoordinate).getFigure().isMoveValid(board, myKingCoordinate, cell.getCoordinate())) {
    			board.getCell(myKingCoordinate).getFigure().move(board, myKingCoordinate, cell.getCoordinate());
    			if(amIChecked()) {
    				cell.getFigure().move(board, cell.getCoordinate(), myKingCoordinate);
    				break;
    			}else
    				return false;
    		}
    	}
    	return true;
    }
}


