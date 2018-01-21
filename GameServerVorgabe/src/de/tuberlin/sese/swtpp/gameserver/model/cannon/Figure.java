package de.tuberlin.sese.swtpp.gameserver.model.cannon;

public class Figure {
	private boolean empty = false;
	boolean white = true;
	boolean castle = false;
	char column;
	int row;
	Figure top;
	Figure topLeft;
	Figure topRight;
	Figure left;
	Figure right;
	Figure bot;
	Figure botRight;
	Figure botLeft;

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

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}

	public boolean isCastle() {
		return castle;
	}

	public void setCastle(boolean castle) {
		this.castle = castle;
	}

	public char getColumn() {
		return column;
	}

	public void setColumn(char column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Figure getTop() {
		return top;
	}

	public void setTop(Figure top) {
		this.top = top;
	}

	public Figure getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Figure topLeft) {
		this.topLeft = topLeft;
	}

	public Figure getTopRight() {
		return topRight;
	}

	public void setTopRight(Figure topRight) {
		this.topRight = topRight;
	}

	public Figure getLeft() {
		return left;
	}

	public void setLeft(Figure left) {
		this.left = left;
	}

	public Figure getRight() {
		return right;
	}

	public void setRight(Figure right) {
		this.right = right;
	}

	public Figure getBot() {
		return bot;
	}

	public void setBot(Figure bot) {
		this.bot = bot;
	}

	public Figure getBotRight() {
		return botRight;
	}

	public void setBotRight(Figure botRight) {
		this.botRight = botRight;
	}

	public Figure getBotLeft() {
		return botLeft;
	}

	public void setBotLeft(Figure botLeft) {
		this.botLeft = botLeft;
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
