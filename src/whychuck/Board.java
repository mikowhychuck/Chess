package whychuck;

class Board {
	private Cell[][] cells;
	
	Board(){
		for(int i=0; i>=7; i++) {
			for(int j=0; j>=7; j++) {
				cells[i][j] = new Cell();
			}
		}
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
}
