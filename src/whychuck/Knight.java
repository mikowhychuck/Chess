package whychuck;

public class Knight extends Figure {
	public Knight(boolean isMine) {
		super(isMine);
	}
	public String getSymbol() {
		return "♘";
	}
	public boolean move(Board board, int currentIndex, int targetIndex) {
        int currentRow = currentIndex / 8;
        int currentCol = currentIndex % 8;
        int targetRow = targetIndex / 8;
        int targetCol = targetIndex % 8;

        int rowDiff = Math.abs(targetRow - currentRow);
        int colDiff = Math.abs(targetCol - currentCol);

        // Sprawdź, czy ruch jest w kształcie literki "L"
        if ((rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1)) {
            Figure targetFigure = board.getCell(targetIndex).getFigure();
            if (targetFigure != null && targetFigure.isMine() == isMine()) {
                // Nie można zbijać własnych figur
                return false;
            }
            board.setFigure(currentIndex, null);
            board.setFigure(targetIndex, this);
            return true;
        }

        return false; // Ruch nie jest możliwy dla Skoczka
    }

}
