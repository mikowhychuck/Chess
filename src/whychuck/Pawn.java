package whychuck;

public class Pawn extends Figure {
	private int direction;
	
    public Pawn(boolean isMine, boolean isWhite) {
		super(isMine, isWhite);
		if (isWhite() == true) {
			direction = 1;
		}else {
			direction = -1;
		}
	}
    public String getSymbol() {
		if(isWhite())
			return "\u2659";
		else
			return "\u265F";
	}
    public boolean isMoveValid(Board board, int currentIndex, int targetIndex) {
    	
    }
	public boolean move(Board board, int currentIndex, int targetIndex) {
    	Cell currentCell = board.getCell(currentIndex);
        Cell targetCell = board.getCell(targetIndex);

        if (targetIndex == currentIndex + 8*direction) {
            targetCell.setFigure(this);
            currentCell.setFigure(null);
            return true;
            
        } else if (targetIndex == currentIndex + 16*direction && currentIndex < 16) {
            targetCell.setFigure(this);
            currentCell.setFigure(null);
            return true;
            
        }   
        return false;
    }
}
