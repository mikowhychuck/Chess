package whychuck;

class King extends Figure {
	public King(boolean isMine, boolean isWhite) {
		super(isMine, isWhite);
	}
	
	public String getSymbol() {
		if(isWhite())
			return "\u2654";
		else 
			return "\u265A";
		
	}
	
    public boolean move(Board board, int currentIndex, int targetIndex) {
        int currentRow = currentIndex / 8;
        int currentCol = currentIndex % 8;
        int targetRow = targetIndex / 8;
        int targetCol = targetIndex % 8;

        int rowDiff = Math.abs(targetRow - currentRow);
        int colDiff = Math.abs(targetCol - currentCol);

        // Sprawdź, czy ruch jest o jedno pole w poziomie, pionie lub na przekątnej
        if ((rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 0) || (rowDiff == 1 && colDiff == 1)) {
            Figure targetFigure = board.getCell(targetIndex).getFigure();
            if (targetFigure != null && targetFigure.isMine() == isMine()) {
                // Nie można zbijać własnych figur
                return false;
            }
            board.setFigure(currentIndex, null);
            board.setFigure(targetIndex, this);
            return true;
        }

        return false; // Ruch nie jest możliwy dla Króla
    }
}
