package whychuck;

public class Cell {
	private int coordinate;
	private Figure figure;
	
	public Cell(int x) {
		setCoordinate(x);
	}
	
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
	int getCoordinate() {
		return coordinate;
	}
	private void setCoordinate(int coordinate) {
		this.coordinate = coordinate;
	}
	
}
