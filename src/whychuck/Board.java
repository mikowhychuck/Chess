package whychuck;

class Board {
	private Cell[] cells;
	
	public Board() {
        cells = new Cell[64];
        initializeCells();
    }
	
	private void initializeCells() {
        for (int i = 0; i < 64; i++) {
            cells[i] = new Cell(i);
        }
    }
	
	public Cell getCell(int index) {
        return cells[index];
    }

    public void setFigure(int index, Figure figure) {
        cells[index].setFigure(figure);
    }
	
    private void initializeDefaultFigures() {
        // Ustaw domyślne figury dla początkowego ustawienia
        // moje figury
        cells[0].setFigure(new Rook(true)); 
        cells[1].setFigure(new Knight(true)); 
        cells[2].setFigure(new Bishop(true));
        cells[3].setFigure(new Queen(true));
        cells[4].setFigure(new King(true));
        cells[5].setFigure(new Bishop(true));
        cells[6].setFigure(new Knight(true));
        cells[7].setFigure(new Rook(true));
        for (int i = 8; i < 16; i++) {
            cells[i].setFigure(new Pawn(true));
        }

        // figury opponenta
        cells[56].setFigure(new Rook(false)); 
        cells[57].setFigure(new Knight(false)); 
        cells[58].setFigure(new Bishop(false));  
        cells[59].setFigure(new Queen(true));
        cells[60].setFigure(new King(true));
        cells[61].setFigure(new Bishop(true));
        cells[62].setFigure(new Knight(true));
        cells[63].setFigure(new Rook(true));
        for (int i = 48; i < 56; i++) {
            cells[i].setFigure(new Pawn(false));
        }
    }
}

