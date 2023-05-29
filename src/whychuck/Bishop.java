package whychuck;

public class Bishop extends Figure {
	public Bishop(boolean isMine) {
		super(isMine);
	}
	public String getSymbol() {
		return "♗";
	}
	public boolean move(Board board, int currentIndex, int targetIndex) {
        int currentRow = currentIndex / 8;
        int currentCol = currentIndex % 8;
        int targetRow = targetIndex / 8;
        int targetCol = targetIndex % 8;

        int rowDiff = Math.abs(targetRow - currentRow);
        int colDiff = Math.abs(targetCol - currentCol);

        // Sprawdź, czy ruch jest po przekątnej
        if (rowDiff == colDiff) {
            // Sprawdź, czy nie ma przeszkód na drodze
            int rowDir = Integer.compare(targetRow, currentRow);
            int colDir = Integer.compare(targetCol, currentCol);

            int row = currentRow + rowDir;
            int col = currentCol + colDir;
            while (row != targetRow || col != targetCol) {
                Cell cell = board.getCell(row * 8 + col);
                if (cell.getFigure() != null) {
                    // Istnieje przeszkoda na drodze
                    return false;
                }
                row += rowDir;
                col += colDir;
            }

            Figure targetFigure = board.getCell(targetIndex).getFigure();
            if (targetFigure != null && targetFigure.isMine() == isMine()) {
                // Nie można zbijać własnych figur
                return false;
            }
            board.setFigure(currentIndex, null);
            board.setFigure(targetIndex, this);
            return true;
        }

        return false; // Ruch nie jest możliwy dla Gońca
    }
}
