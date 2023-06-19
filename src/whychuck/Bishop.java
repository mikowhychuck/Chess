package whychuck;

public class Bishop extends Figure {
	public Bishop(boolean isMine, boolean isWhite) {
		super(isMine, isWhite);
	}
	public String getSymbol() {
		if(isWhite())
			return "\u2657";
		else
			return "\u265D";
	}
	public boolean isMoveValid(Board board, int currentIndex, int targetIndex) {
		int currentRow = currentIndex / 8;
        int currentCol = currentIndex % 8;
        int targetRow = targetIndex / 8;
        int targetCol = targetIndex % 8;

        int rowDiff = Math.abs(targetRow - currentRow);
        int colDiff = Math.abs(targetCol - currentCol);

        if (rowDiff == colDiff) {

            int rowDir = Integer.compare(targetRow, currentRow);
            int colDir = Integer.compare(targetCol, currentCol);

            int row = currentRow + rowDir;
            int col = currentCol + colDir;
            while (row != targetRow || col != targetCol) {
                Cell cell = board.getCell(row * 8 + col);
                if (cell.getFigure() != null) {
                    return false;
                }
                row += rowDir;
                col += colDir;
            }
            Figure targetFigure = board.getCell(targetIndex).getFigure();
            if (targetFigure != null && targetFigure.isMine() == isMine()) {
                return false;
            }
//            if (targetFigure != null && targetFigure instanceof King) {
//            	return false;
//            }
            return true;
        }else
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
