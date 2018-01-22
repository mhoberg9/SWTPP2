package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FigureHandler extends LinkedList<Figure> {

	public String getTopLeft(String pos) {
		return getString(-1, 1, pos);

	}

	public String getRight(String pos) {
		return getString(1, 0, pos);

	}

	public String getLeft(String pos) {
		return getString(-1, 0, pos);

	}

	public String getTopRight(String pos) {
		return getString(1, 1, pos);

	}

	public String getTop(String pos) {
		return getString(0, 1, pos);

	}

	public String getBot(String pos) {
		return getString(0, -1, pos);

	}

	public String getBotLeft(String pos) {
		return getString(-1, -1, pos);
	}

	public String getBotRight(String pos) {
		return getString(1, -1, pos);

	}

	public String getString(int a, int b, String position) {
		return "" + (char) a + position.charAt(0) + "" + (char) b + position.charAt(2);

	}

	public List findWay(String move) {
		
	}
	public List findWay(String from, String to) {

		int counter = 0;
		String way = from;
		ArrayList ls = new ArrayList();
		int fromCol = (int) from.charAt(0);
		int fromRow = (int) from.charAt(1);
		int toCol = (int) to.charAt(0);
		int toRow = (int) to.charAt(1);

		while (fromCol != toCol && fromRow != toRow && counter != 5) {
			way = "" + (char) fromCol + "" + fromRow;
			ls.add(way);
			if (fromCol < toCol)
				fromCol++;
			if (fromCol > toCol)
				fromCol--;
			if (fromRow > toRow)
				toRow--;
			if (fromRow < toRow)
				toRow++;
			counter++;
		}
		return ls;

	}

	public List mark(String from, String to) {
		
		String field = from;
		ArrayList ls = new ArrayList();
		int fromCol = (int) from.charAt(0);
		int fromRow = (int) from.charAt(1);
		int toCol = (int) to.charAt(0);
		int toRow = (int) to.charAt(1);
		int temp = toCol;

		while (toRow != fromRow) {

			while (toCol != fromCol) {
				field = "" + (char) fromCol + "" + fromRow;
				ls.add(field);
				if (toCol < fromCol) fromCol--;
				else fromCol++;
		}
			toCol= temp;
			if (fromRow < toRow) fromRow++;
			else fromRow--;

	}
		return ls;
	}
}
