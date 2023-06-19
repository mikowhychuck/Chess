package whychuck;

public class Queen extends Figure {
	public Queen(boolean isMine, boolean isWhite) {
		super(isMine, isWhite);
	}
	public String getSymbol() {
		if(isWhite())
			return "\u2655";
		else
			return "\u265B";
	}
	public boolean isMoveValid(Board board, int currentIndex, int targetIndex) {
		int currentRow = currentIndex / 8;
        int currentCol = currentIndex % 8;
        int targetRow = targetIndex / 8;
        int targetCol = targetIndex % 8;

        // Sprawdź czy ruch jest na tej samej linii poziomej, pionowej lub na przekątnej
        if (currentRow == targetRow || currentCol == targetCol || Math.abs(currentRow - targetRow) == Math.abs(currentCol - targetCol)) {
            // Sprawdź czy nie ma przeszkód na drodze
            int rowDiff = Integer.compare(targetRow, currentRow);
            int colDiff = Integer.compare(targetCol, currentCol);

            int row = currentRow + rowDiff;
            int col = currentCol + colDiff;
            while (row != targetRow || col != targetCol) {
                Cell cell = board.getCell(row * 8 + col);
                if (cell.getFigure() != null) {
                    // Istnieje przeszkoda na drodze
                    return false;
                }
                row += rowDiff;
                col += colDiff;
            }

            Figure targetFigure = board.getCell(targetIndex).getFigure();
            if (targetFigure != null && targetFigure.isMine() == isMine()) {
                return false;
            }
//            if (targetFigure != null && targetFigure instanceof King) {
//            	return false;
//            }
            return true;
        }
        return false;
	}
	
    public boolean move(Board board, int currentIndex, int targetIndex) {
        if(isMoveValid(board, currentIndex, targetIndex)) {
        	board.setFigure(currentIndex, null);
            board.setFigure(targetIndex, this);
            return true;
        }else
        	return false;
    }

}
