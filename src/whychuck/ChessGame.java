package whychuck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.hypki.wmi.oop.chess.ChessInterface;


public class ChessGame implements ChessInterface {
    private Board board;
    
    public ChessGame() {
    	board = new Board();
    }

    @Override
    public String nextMove(String opponentMove) {
        // Losowo wybierz jedno z twoich pól
        List<Cell> myCells = getMyCells();
        Cell selectedCell = getRandomCell(myCells);
        int selectedCellCoordinate = selectedCell.getCoordinate();
        // Wygeneruj losowe pole docelowe
        int targetIndex = generateRandomTarget();

        // Wykonaj ruch figury znajdującej się na wybranym polu
        Figure figure = selectedCell.getFigure();
        boolean moveSuccessful = figure.move(board, selectedCellCoordinate, targetIndex);

        if (moveSuccessful) {
            return formatMove(selectedCell.getCoordinate(), targetIndex);
        } else {

            return nextMove(opponentMove);
        }
    }

    @Override
    public void printBoard() {
        board.print();
    }

    private List<Cell> getMyCells() {
        List<Cell> myCells = new ArrayList<>();
        // Przeiteruj przez wszystkie komórki planszy i dodaj pola, które mają twoje figury
        for (Cell cell : board.getCells()) {
            Figure figure = cell.getFigure();
            if (figure != null && figure.isMine()) {
                myCells.add(cell);
            }
        }
        return myCells;
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

    private String formatMove(int fromIndex, int toIndex) {
        String fromPosition = convertIndexToPosition(fromIndex);
        String toPosition = convertIndexToPosition(toIndex);
        return fromPosition + "-" + toPosition;
    }

    private String convertIndexToPosition(int index) {
        int row = index / 8;
        int col = index % 8;
        char file = (char) ('a' + col);
        int rank = 8 - row;
        return String.valueOf(file) + rank;
    }
}


