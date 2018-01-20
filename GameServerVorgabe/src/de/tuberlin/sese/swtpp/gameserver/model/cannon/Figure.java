package de.tuberlin.sese.swtpp.gameserver.model.cannon;

public class Figure {
private	boolean empty = false;
	boolean white = true;
	boolean castle = false;
	char column;
	int row;

	public Figure(boolean white, char column, int row) {

		this.white = white;
		this.column = column;
		this.row = row;
	}

	public Figure(char column, int row, char c) {
		if (c == 'b' || c == 'B')
			this.white = false;
		if (c == 'B')
			this.castle = true;
		if (c == 'W')
			this.castle = true;
		if (c == '1') {
			this.empty = true;

		}
		this.column = column;
		this.row = row;

	}

	@Override
	public String toString() {
		if (white == true && castle == false)
			return "w";
		if (white == true && castle == true)
			return "W";
		if (white == false && castle == false)
			return "b";
		if (empty == true)
			return "1";
		return "B";

	}

	// Useless if in constructor
	// public void partFigure(char c) {
	// if (c == 'b' ||c== 'B')
	// this.white = false;
	// if (c == 'B')
	// this.castle = true;
	// if (c == 'W')
	// this.castle = true;
	//
	// }

}
