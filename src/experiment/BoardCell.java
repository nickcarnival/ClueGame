package experiment;

public class BoardCell {
	private int row;
	private int column;

	public BoardCell(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public boolean equals(Object obj) {
		return (((BoardCell) obj).getRow()==this.row&&((BoardCell) obj).getColumn()==this.column);
	}

	

}
