package whychuck;

class Movement {
	public int indexFrom;
	public int indexTo;
	
	public Movement(int indexFrom, int indexTo){
		this.indexFrom = indexFrom;
		this.indexTo = indexTo;
	}
	
	public String ToString() {
		return indexFrom + "-" + indexTo;
	}
}
