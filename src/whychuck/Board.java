package whychuck;

import java.util.ArrayList;
import java.util.List;

class Board {
	private Cell[] cells;
	
	public Board(boolean amIwhite) {
        setCells(new Cell[64]);
        initializeCells();
        initializeDefaultFigures(amIwhite);
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
	
    private void initializeDefaultFigures(boolean amIwhite) {
        // Ustaw domyślne figury dla początkowego ustawienia
        // biale figury
        getCells()[0].setFigure(new Rook(amIwhite, true)); 
        getCells()[1].setFigure(new Knight(amIwhite, true)); 
        getCells()[2].setFigure(new Bishop(amIwhite, true));
        getCells()[3].setFigure(new Queen(amIwhite, true));
        getCells()[4].setFigure(new King(amIwhite, true));
        getCells()[5].setFigure(new Bishop(amIwhite, true));
        getCells()[6].setFigure(new Knight(amIwhite, true));
        getCells()[7].setFigure(new Rook(amIwhite, true));
        for (int i = 8; i < 16; i++) {
            getCells()[i].setFigure(new Pawn(amIwhite, true));
        }

        // czarne figury
        getCells()[56].setFigure(new Rook(!amIwhite, false)); 
        getCells()[57].setFigure(new Knight(!amIwhite, false)); 
        getCells()[58].setFigure(new Bishop(!amIwhite, false));  
        getCells()[59].setFigure(new Queen(!amIwhite, false));
        getCells()[60].setFigure(new King(!amIwhite, false));
        getCells()[61].setFigure(new Bishop(!amIwhite, false));
        getCells()[62].setFigure(new Knight(!amIwhite, false));
        getCells()[63].setFigure(new Rook(!amIwhite, false));
        for (int i = 48; i < 56; i++) {
            getCells()[i].setFigure(new Pawn(!amIwhite, false));
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
	
	
	/**
	 * Jesli ruch nie jest mój, to nie sprawdzam czy jest prawidłowy
	 * @param currentIndex - co to jest
	 * 
	 * @param targetIndex
	 */
	public void move(int currentIndex, int targetIndex) {
		if(this.getCell(currentIndex).getFigure() != null) {
			Figure figure = this.getCell(currentIndex).getFigure();
		    setFigure(currentIndex, null);
		    setFigure(targetIndex, figure);
		}
	}
	
    //Sprawdzanie szacha:
    public boolean amIChecked() {
    	List<Cell> cells = getOpponentCells();
    	for (Cell cell : cells) {
    		if(cell.getFigure().isMoveValid(this, cell.getCoordinate(), getMyKingCoordinates()))
    			return true;
    	}
    	return false;
    }
    public boolean isOpponentChecked() {
    	List<Cell> Cells = getMyCells();
    	for (Cell cell : Cells) {
    		if(cell.getFigure().isMoveValid(this, cell.getCoordinate(), getOpponentKingCoordinates()))
    			return true;
    	}
    	return false;
    }
    
    //Gettery koordynatów królów:
    public int getMyKingCoordinates() {
        for (Cell cell : getCells()) {
            Figure figure = cell.getFigure();
            if (figure instanceof King && figure.isMine()) {
            	return cell.getCoordinate();
            }
        }
        return -1;
    }
    
    public Figure getMyKing() {
    	return getCell(getMyKingCoordinates()).getFigure();
    }
    
    public int getOpponentKingCoordinates() {
        for (Cell cell : getCells()) {
            Figure figure = cell.getFigure();
            if (figure instanceof King && !figure.isMine()) {
            	return cell.getCoordinate();
            }
        }
        return -1;
    }
    
    public List<Cell> getMyCells() {
        List<Cell> myCells = new ArrayList<>();

        for (Cell cell : getCells()) {
            Figure figure = cell.getFigure();
            if (figure != null && figure.isMine()) {
                myCells.add(cell);
            }
        }
        return myCells;
    }
    
    public List<Cell> getOpponentCells() {
        List<Cell> OpponentCells = new ArrayList<>();

        for (Cell cell : getCells()) {
            Figure figure = cell.getFigure();
            if (figure != null && !figure.isMine()) {
                OpponentCells.add(cell);
            }
        }
        return OpponentCells;
    }
    
    public Movement killAttackingFigure() {
    	Board tempBoard = this;
    	Movement movement = null;
    	int i = 0;
    	for(Cell OpponentCell : getOpponentCells()) {
    		if(OpponentCell.getFigure().isMoveValid(tempBoard, OpponentCell.getCoordinate(), getMyKingCoordinates())) {
    			for(Cell myCell : getMyCells()) {
    				if(myCell.getFigure().isMoveValid(tempBoard, myCell.getCoordinate(), OpponentCell.getCoordinate()) 
    						&& !(OpponentCell.getFigure() instanceof King)) {
    					if(tempBoard.amIChecked())
    						movement = new Movement(myCell.getCoordinate(), OpponentCell.getCoordinate());
    				}
    			}
    		}
    	}
    	if(i > 1 || movement == null)
    		return null;
    	else
    		return movement;
    }
    public Movement moveKingToAvoidMate() {
    	Board tempBoard = this;
    	Movement movement = null;
    	int kingCoordinates = this.getMyKingCoordinates();
    	for(Cell targetCell : getCells()) {
    		if(getMyKing().isMoveValid(this, tempBoard.getMyKingCoordinates(), targetCell.getCoordinate())){
    			getMyKing().move(this, tempBoard.getMyKingCoordinates(), targetCell.getCoordinate());
    			if(!this.amIChecked()) {
    				movement = new Movement(kingCoordinates, targetCell.getCoordinate());
    				return movement;
    			}else {
    				getMyKing().move(this, tempBoard.getMyKingCoordinates(), kingCoordinates);
    			}
    		}
    	}
    	return movement;
    }
}

