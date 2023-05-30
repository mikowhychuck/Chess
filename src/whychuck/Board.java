package whychuck;

class Board {
	private Cell[] cells;
	
	public Board() {
        setCells(new Cell[64]);
        initializeCells();
        initializeDefaultFigures();
    }
	
	private void initializeCells() {
        for (int i = 0; i < 64; i++) {
            getCells()[i] = new Cell(i);
        }
    }
	
	public Cell getCell(int index) {
        return getCells()[index];
    }

    public void setFigure(int index, Figure figure) {
        getCells()[index].setFigure(figure);
    }
	
    private void initializeDefaultFigures() {
        // Ustaw domyślne figury dla początkowego ustawienia
        // moje figury
        getCells()[0].setFigure(new Rook(true, true)); 
        getCells()[1].setFigure(new Knight(true, true)); 
        getCells()[2].setFigure(new Bishop(true, true));
        getCells()[3].setFigure(new Queen(true, true));
        getCells()[4].setFigure(new King(true, true));
        getCells()[5].setFigure(new Bishop(true, true));
        getCells()[6].setFigure(new Knight(true, true));
        getCells()[7].setFigure(new Rook(true, true));
        for (int i = 8; i < 16; i++) {
            getCells()[i].setFigure(new Pawn(true, true));
        }

        // figury opponenta
        getCells()[56].setFigure(new Rook(false, false)); 
        getCells()[57].setFigure(new Knight(false, false)); 
        getCells()[58].setFigure(new Bishop(false, false));  
        getCells()[59].setFigure(new Queen(false, false));
        getCells()[60].setFigure(new King(false, false));
        getCells()[61].setFigure(new Bishop(false, false));
        getCells()[62].setFigure(new Knight(false, false));
        getCells()[63].setFigure(new Rook(false, false));
        for (int i = 48; i < 56; i++) {
            getCells()[i].setFigure(new Pawn(false, false));
        }
    }
    
    public void print() {
    	for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                Cell cell = getCells()[row * 8 + col];
                Figure figure = cell.getFigure();
                if (figure == null) {
                    System.out.print("□ ");
                } else {
                    // Wyświetl odpowiedni symbol figury
                    System.out.print(figure.getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

}

