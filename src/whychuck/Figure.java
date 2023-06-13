package whychuck;

public abstract class Figure {

	private boolean wasMoved;
	private boolean isMine;
	private boolean isWhite;

    public Figure(boolean isMine, boolean isWhite) {
        setMine(isMine);
        setColor(isWhite);
    }

    public boolean isMine() {
        return isMine;
    }
    private void setMine(boolean isMine) {
		this.isMine = isMine;
	}
	public boolean isWhite() {
		return isWhite;
	}

	public void setColor(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	public void setMoved(){
		wasMoved = true;
	}
	public boolean wasMoved() {
		return wasMoved;
	}
    public abstract boolean move(Board board, int currentIndex, int targetIndex);
    public abstract String getSymbol();
    public abstract boolean isMoveValid(Board board, int currentIndex, int targetIndex);

}
