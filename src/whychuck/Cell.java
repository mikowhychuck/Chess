package whychuck;

public class Cell {
	private int x;
	private int y;
	private Figure figure;
	
	public boolean isEmpty() {
		if(figure == null) {
			return false;
		}else {
			return true;
		}
	}
	public Figure getFigure() {
		return figure;
	}
	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	private int getX() {
		return x;
	}
	private void setX(int x) {
		this.x = x;
	}
	private int getY() {
		return y;
	}
	private void setY(int y) {
		this.y = y;
	}
}
