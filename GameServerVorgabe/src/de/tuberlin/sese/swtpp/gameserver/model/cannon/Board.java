package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.LinkedList;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Board {

	public static FigureHandler storage = null;

	// Test
	public static TriPredicate<Figure, Figure, Figure> diagonal = (a, b, c) -> a.equals(2);
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

	public boolean inDanger(String moveBeginning, String requestingPlayer) {

		char column = moveBeginning.charAt(0);
		int row = (int) moveBeginning.charAt(1);
		Figure f = new Figure(requestingPlayer, column, row);

		if (requestingPlayer.equalsIgnoreCase("b")) {
			return storage.stream().anyMatch(a -> top.test(a, f) || topRight.test(a, f) || topLeft.test(a, f)
					|| left.test(a, f) || right.test(a, f) && a.isPlayer(requestingPlayer) == false);
		} else {
			return storage.stream().anyMatch(a -> left.test(a, f) || bottomLeft.test(a, f) || bottom.test(a, f)
					|| bottomRight.test(a, f) || right.test(a, f) && a.isPlayer(requestingPlayer) == false);
		}
	}

	// check if figure is cannon
	public Cannon isCannonValid(String from,String to, String requestingPlayer) {
	
		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		char toCol = to.charAt(0);
		char toRow = to.charAt(1);

<<<<<<< HEAD
		Figure f = null;
//		if (f.isPlayer(requestingPlayer)) {
//			f = storage.stream().filter(a -> a.column = fromCol && a.row == fromRow).findFirst().get();

=======
		Figure f = null;	
		f = storage.stream().filter(a -> a.getPosition()==
			 fromRow).findFirst().get();
//Warning!
		if (f.isPlayer(requestingPlayer)) {
		
>>>>>>> NW


		// Diagonal - untenlinks to obenrechts
		if (toRow > fromRow && toCol > fromCol && (toRow - fromRow) >= 4 && (toRow - fromRow) <= 5
				&& ((toCol - fromCol) >= 4) && ((toCol - fromCol) <= 5)) {
			if ((f.topRight.color == f.color && f.topRight.topRight.color == f.color) ) {
				LinkedList<Figure>aim = new LinkedList <> ();
				aim.add(f.topRight.topRight.topRight);
				aim.add(f.topRight.topRight.topRight.topRight);
				aim.add(f.topRight.topRight.topRight.topRight.topRight);
				return new Cannon (f, f.topRight, f.topRight.topRight, aim);
		}

		// Diagonal - untenrechts to obenrechts
		if (toRow > fromRow && fromCol > toCol && (toRow - fromRow) >= 4 && (toRow - fromRow) <= 5
				&& (fromCol - toCol) >= 4 && (fromCol - toCol) <= 5) {
			if(f.topLeft.color == f.color && f.topLeft.topLeft.color == f.color) {
				LinkedList<Figure>aim = new LinkedList <> ();
				aim.add(f.topLeft.topLeft.topLeft);
				aim.add(f.topLeft.topLeft.topLeft.topLeft);
				aim.add(f.topLeft.topLeft.topLeft.topLeft.topLeft);
				return new Cannon (f, f.topLeft, f.topLeft.topLeft, aim);
			}
		}

		}
		// NachOben
		if (toRow > fromRow && ((toRow - fromRow) >= 4) && ((toRow - fromRow) <= 5) && fromCol == toCol) {
			if(f.top.color==f.color && f.top.top.color == f.color) {
				LinkedList<Figure>aim = new LinkedList <> ();
				aim.add(f.top.top.top);
				aim.add(f.top.top.top.top);
				aim.add(f.top.top.top.top.top);
				return new Cannon (f,f.top,f.top.top,aim);
			}

		}
		// NachUnten
		if (fromRow > toRow && ((fromRow - toRow) >= 4) && ((fromRow - toRow) <= 5) && fromCol == toCol) {
			if(f.bot.color == f.color && f.bot.bot.color == f.color) {
				LinkedList<Figure> aim = new LinkedList <> ();
				aim.add(f.bot.bot.bot);
				aim.add(f.bot.bot.bot.bot);
				aim.add(f.bot.bot.bot.bot.bot);
				return new Cannon (f, f.bot, f.bot.bot, aim);
			}
		}

		// NachRechts
		if (toCol > fromCol && ((toCol - fromCol) >= 4) && ((toCol - fromCol) <= 5) && fromRow == toRow) {
			if(f.right.color == f.color && f.right.right.color== f.color) {
				LinkedList<Figure> aim = new LinkedList <> ();
				aim.add(f.right.right.right);
				aim.add(f.right.right.right.right);
				aim.add(f.right.right.right.right.right);
				return new Cannon (f, f.right, f.right.right, aim);
			}
		}
		// NachLinks
		if (fromCol > toCol && ((fromCol - toCol) >= 4) && ((fromCol - toCol) <= 5) && fromRow == toRow) {
			if(f.left.color == f.color && f.left.left.color == f.color) {
				LinkedList <Figure> aim = new LinkedList <> ();
				aim.add(f.left.left.left);
				aim.add(f.left.left.left.left);
				aim.add(f.left.left.left.left.left);
				return new Cannon (f,f.left,f.left.left, aim);
			}
		}
		// Diagonal - obenlinks to untenrechts
		if (fromRow > toRow && toCol > fromCol && (fromRow - toRow) >= 4 && (fromRow - toRow) <= 5
				&& (toCol - fromCol) <= 4 && (toCol - fromCol) <= 5) {
			if(f.botLeft.color == f.color && f.botLeft.botLeft.color == f.color) {
				LinkedList<Figure> aim = new LinkedList <> ();
				aim.add(f.botLeft.botLeft.botLeft);
				aim.add(f.botLeft.botLeft.botLeft.botLeft);
				aim.add(f.botLeft.botLeft.botLeft.botLeft.botLeft);
				return new Cannon (f, f.botRight, f.botRight, aim);
			}
		}

		// Diagonal - obenrechts to untenlinks
		if (fromRow > toRow && fromCol > toCol && (fromRow - toRow) >= 4 && (fromRow - toRow) <= 5
				&& (fromCol - toCol) <= 4 && (fromCol - toCol) >= 5) {
			if(f.botLeft.color == f.color && f.botLeft.botLeft.color == f.color) {
				LinkedList<Figure> aim = new LinkedList <> ();
				aim.add(f.botLeft.botLeft.botLeft);
				aim.add(f.botLeft.botLeft.botLeft.botLeft);
				aim.add(f.botLeft.botLeft.botLeft.botLeft.botLeft);
				return new Cannon (f, f.botLeft, f.botLeft.botLeft, aim);
			}
		}

<<<<<<< HEAD
=======
	
	}
	

	public boolean isShotValid(String from,String to, boolean requestingPlayer, Figure f) {

		
		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		char toCol = to.charAt(0);
		char toRow = to.charAt(1);
	

		// Diagonal - ObenRechts

		// Diagonal - ObenLinks
=======
		if ((bottom.test(a, f) && top.test(a, f)) && requestingPlayer) {
			return new Cannon(f, f.top, f.bot);
		}

		if ((bottomLeft.test(a, f) && topRight.test(a, f)) && requestingPlayer) {
			return new Cannon(f, f.topRight, f.getBotLeft());
		}
		if ((bottomRight.test(a, f) && topLeft.test(a, f)) && requestingPlayer) {
			return new Cannon(f, f.botRight, f.topLeft);

		}
		// filter kanonen heraus nur Versuch
		// storage.stream().filter(a -> a.left != null && a.right != null && a.isWhite()
		// == requestingPlayer);
	}


	}

>>>>>>> NW
	public boolean normalMoveBlack(String from, String to, String requestingPlayer) {

		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		char toCol = to.charAt(0);
		char toRow = to.charAt(1);

		if ((((Math.abs(fromCol - toCol)) <= 1) && ((toCol - fromCol) <= 1))) {
			return storage.stream().anyMatch(a -> a.column == toCol && a.row == toRow && a.isPlayer(requestingPlayer));
		}
		return false;
	}

	// schlagen
	public boolean leftOrRight(String from, String to, String requestingPlayer) { // ist auf linker oder rechten Seite
																					// ein Gegner
		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		char toCol = to.charAt(0);
		char toRow = to.charAt(1);

		if (fromRow == toRow && toCol == fromCol - 1 || fromCol + 1 == toCol) {
			return storage.stream()
					.anyMatch(a -> a.column == toCol && a.row == toRow && a.isPlayer(requestingPlayer) == false);
		}
		return false;
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
