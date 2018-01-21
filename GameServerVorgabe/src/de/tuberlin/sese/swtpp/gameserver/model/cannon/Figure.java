package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.LinkedList;

public class Figure {
	// private boolean empty = false;
	// boolean white = true;
	String color;
	boolean castle = false;
	String postion;
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

	public Figure(String color, char column, int row) {

		this.color = color;
		this.column = column;
		this.row = row;
		this.postion = "" + column + "" + row;
	}

	public Figure(char column, int row, char c) {
		if (c == 'b' || c == 'B')
			this.color = "b";
		if (c == 'B')
			this.castle = true;
		if (c == 'w' || c == 'W')
			this.color = "w";
		if (c == 'W')
			this.castle = true;
		if (c == '1') {
			this.color = null;

		}
		this.column = column;
		this.row = row;
		this.postion = "" + column + "" + row;

	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}

	@Override
	public String toString() {
		if (color.equals("w") && castle == false)
			return "w";
		if (color.equals("w") && castle == true)
			return "W";
		if (color.equals("b") == false && castle == false)
			return "b";
		if (color.equals("b") && castle == true) {
			return "B";
		}
		return "1";

	}

	public boolean isEmpty() {
		return this.color == null;
	}

	public boolean isPlayer(String player) {
		return color.equalsIgnoreCase(player);
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

	public char getCol() {
		return this.postion.charAt(0);
	}

	public int getRo() {
		return this.postion.charAt(1);

	}

}
