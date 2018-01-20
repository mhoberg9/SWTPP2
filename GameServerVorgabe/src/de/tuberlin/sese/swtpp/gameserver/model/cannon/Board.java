package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.LinkedList;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Board  {
	
	public static LinkedList<Figure> storage = null;

	// topLeft
	public static BiPredicate<Figure, Figure> topLeft = (a, b) -> a.column == b.column && a.row == b.row + 1;
	// top
	public static BiPredicate<Figure, Figure> top = (a, b) -> a.column == b.column && a.row == b.row + 1;
	// topRight
	public static BiPredicate<Figure, Figure> topRight = (a, b) -> a.column == b.column + 1 && a.row == b.row + 1;
	// left
	public static BiPredicate<Figure, Figure> left = (a, b) -> a.column == b.column - 1 && a.row == b.row;
	// right
	public static BiPredicate<Figure, Figure> right = (a, b) -> a.column == b.column + 1 && a.row == b.row;
	// bottomLeft
	public static BiPredicate<Figure, Figure> bottomLeft = (a, b) -> a.column == b.column - 1 && a.row == b.row - 1;
	// bottom
	public static BiPredicate<Figure, Figure> bottom = (a, b) -> a.column == b.column && a.row == b.row - 1;
	// bottomRight
	public static BiPredicate<Figure, Figure> bottomRight = (a, b) -> a.column == b.column + 1 && a.row == b.row - 1;

	public boolean inDanger(String moveBeginning, boolean requestingPlayer) {

		char column = moveBeginning.charAt(0);
		int row = (int) moveBeginning.charAt(1);

		Figure f = new Figure(requestingPlayer, column, row);

		return storage.stream().anyMatch(a -> top.test(a, f) || topRight.test(a, f) || topLeft.test(a, f)
				|| left.test(a, f) || right.test(a, f) && !requestingPlayer);
	}

	public boolean isCannon(String unit) {

	}

	public boolean normalMove(String move) {

		Figure f;
		// vorne frei
		storage.stream().anyMatch(a -> a.column == f.column && a.row == f.row);
	}

	public boolean leftOrRight(String unit) { // ist auf linker oder rechten Seite ein Gegner
	}

	public static String getBoard() {
		String s = "";
		for (int i = 0; i < storage.size(); i++) {
			s += storage.remove(i).toString();
			if (i % 10 == 0)
				s += "//";
		}
		return s;
	}

}
