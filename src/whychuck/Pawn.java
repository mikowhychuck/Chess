package whychuck;

public class Pawn extends Figure {
	
    public Pawn(boolean isWhite) {
		super(isWhite);
	}
    public String getSymbol() {
		return "♙";
	}

	public boolean move(Board board, int currentIndex, int targetIndex) {
    	Cell currentCell = board.getCell(currentIndex);
        Cell targetCell = board.getCell(targetIndex);

        if (targetIndex == currentIndex + 8) {
            targetCell.setFigure(this);
            currentCell.setFigure(null);
            return true;
            
        } else if (targetIndex == currentIndex + 16 && currentIndex < 16) {
            targetCell.setFigure(this);
            currentCell.setFigure(null);
            return true;
            
        }   
        return false;
    }
}
