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

        Cell targetCell = board.getCell(targetIndex);
    	if (targetIndex == currentIndex + 8*direction && targetCell.getFigure() == null) {
            return true;
    	}else if(targetIndex == currentIndex + 16*direction && !wasMoved() && targetCell.getFigure() == null) {	
    		return true;
    	}else if(targetIndex == currentIndex + 7*direction &&  targetIndex == currentIndex + 9*direction) {
    		if(targetCell.getFigure().isMine() == false) {
    			return true;
    		}
//    		if (targetCell.getFigure() != null && targetCell.getFigure() instanceof King) {
//            	return false;
//            }
    	}
    	return false;
    }
    	
	public boolean move(Board board, int currentIndex, int targetIndex) {
        if(isMoveValid(board, currentIndex, targetIndex)) {
        	board.setFigure(currentIndex, null);
            board.setFigure(targetIndex, this);
            this.setMoved();
            return true;
        }else
        	return false;
    }
}
