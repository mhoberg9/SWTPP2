package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.LinkedList;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Board {

	public static FigureHandler storage = null;

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
					|| bottomRight.test(a, f) || right.test(a, f) && a.isPlayer(requestingPlayer)==false);
		}
	}

	// check if figure is cannon
	public boolean isCannonValid(String move, boolean requestingPlayer) {
		String[] moveturn = move.split("-");
		String from = moveturn[0];
		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		String to = moveturn[1];
		char toCol = to.charAt(0);
		char toRow = to.charAt(1);

		Figure f = null;

		// Diagonal - untenlinks to obenrechts
		if (toRow > fromRow && toCol > fromCol && (toRow - fromRow) >= 4 && (toRow - fromRow) <= 5
				&& ((toCol - fromCol) >= 4) && ((toCol - fromCol) <= 5)) {

		}

		// Diagonal - untenrechts to obenrechts
		if (toRow > fromRow && fromCol > toCol && (toRow - fromRow) >= 4 && (toRow - fromRow) <= 5
				&& (fromCol - toCol) >= 4 && (fromCol - toCol) <= 5) {

		}
		// NachOben
		if (toRow > fromRow && ((toRow - fromRow) >= 4) && ((toRow - fromRow) <= 5) && fromCol == toCol) {

		}
		// NachUnten
		if (fromRow > toRow && ((fromRow - toRow) >= 4) && ((fromRow - toRow) <= 5) && fromCol == toCol) {

		}

		// NachRechts
		if (toCol > fromCol && ((toCol - fromCol) >= 4) && ((toCol - fromCol) <= 5) && fromRow == toRow) {

		}
		// NachLinks
		if (fromCol > toCol && ((fromCol - toCol) >= 4) && ((fromCol - toCol) <= 5) && fromRow == toRow) {

		}
		// Diagonal - obenlinks to untenrechts
		if (fromRow > toRow && toCol > fromCol && (fromRow - toRow) >= 4 && (fromRow - toRow) <= 5
				&& (toCol - fromCol) <= 4 && (toCol - fromCol) <= 5) {

		}

		// Diagonal - obenrechts to untenlinks
		if (fromRow > toRow && fromCol > toCol && (fromRow - toRow) >= 4 && (fromRow - toRow) <= 5
				&& (fromCol - toCol) <= 4 && (fromCol - toCol) >= 5) {

		}

		// if (isWhite(fromCol, fromRow, requestingPlayer)) {
		//// f = storage.stream().filter(a -> a.column = fromCol&& a.getRow() ==
		// fromRow).findFirst().get();
		//
		// }

		// if((bottom.test(a, f) && top.test(a, f)) && requestingPlayer) {
		// return new Cannon (f, f.top,f.bot);
		// }
		//
		// if((bottomLeft.test(a, f) && topRight.test(a, f)) && requestingPlayer) {
		// return new Cannon(f, f.topRight , f.getBotLeft());
		// }
		// if((bottomRight.test(a, f) && topLeft.test(a, f)) && requestingPlayer) {
		// return new Cannon (f, f.botRight , f.topLeft);
		//
	}
	// filter kanonen heraus nur Versuch
	// storage.stream().filter(a -> a.left != null && a.right != null && a.isWhite()
	// == requestingPlayer);

	public boolean isShotValid(String move, boolean requestingPlayer, Figure f) {

		String[] moveturn = move.split("-");
		String from = moveturn[0];
		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		String to = moveturn[1];

		char toCol = to.charAt(0);
		char toRow = to.charAt(1);
		// Figure f=null;
		//
		// if(isWhite(fromCol,fromRow,requestingPlayer)) {
		// f= storage.stream().filter(a-> a.column=fromColumn &&
		// a.getRow()==fromRow).findFirst().get();
		// }

		// Diagonal - ObenRechts

		// Diagonal - ObenLinks

		// Oben
		if ((fromRow - toRow <= 4) && fromCol == toCol) {
			return (storage.stream().filter(a -> (a == f.bot || a == f.bot.bot) && a.isWhite() == requestingPlayer)
					.count() == 2) && f.getBot().getBot().isEmpty();
		}
		// Left
		if (fromRow == toRow && (fromCol - toCol <= 4)) {
			return (storage.stream()
					.filter(a -> (a == f.right || a == f.right.right) && a.isWhite() == requestingPlayer).count() == 2)
					&& f.getRight().getRight().isEmpty();
		}

		// Right
		if (fromRow == toRow && (toCol - fromCol <= 4)) {
			return (storage.stream().filter(a -> (a == f.left || a == f.left.left) && a.isWhite() == requestingPlayer)
					.count() == 2) && f.getLeft().getLeft().isEmpty();
		}

		// Unten
		if ((toRow - fromRow <= 4) && fromCol == toCol) {
			return (storage.stream().filter(a -> (a == f.top || a == f.top.top) && a.isWhite() == requestingPlayer)
					.count() == 2) && f.getTop().getTop().isEmpty();
		}

		// Diagonal - UntenRechts

		// Diagonal - UntenLinks

		//
		// if (fromRow-toRow>=4 &&fromCol==toCol) {
		// storage.stream().filter(a-> top.test(a, f)||top.test(a, new
		// Figure(requestedPlayer,fromCol,(int)fromRow+1))).count()==2;
		// }
		// if(fromRow-toRow<=4 &&fromCol==toCol) {
		// return (f.bot.bot=f.isWhite() &&f.bot.isWhite()&& f.bot.isWhite()
		// &&f.bot.bot.bot.isWhite());
		//
		// }
		// in einer Reihe black Richtig?
		// if(fromRow==toRow && (toCol-fromCol>=4)) {
		// return storage.stream().filter(a->)
		//
		// }
		// Diagonal
		// if(Math.abs(fromRow-toRow)>=4 && Math.abs(fromCol-toCol)>=4){}
	}

	public boolean normalMoveBlack(String move, String requestingPlayer) {

		String[] moveturn = move.split("-");
		String from = moveturn[0];
		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		String to = moveturn[1];

		char toCol = to.charAt(0);
		char toRow = to.charAt(1);
		if ((((Math.abs(fromCol - toCol)) <= 1) && ((toCol - fromCol) <= 1))) {
			return storage.stream().anyMatch(a -> a.column == toCol && a.row == toRow && a.isPlayer(requestingPlayer));
		}
	}

	// schlagen
	public boolean leftOrRight(String move, String requestingPlayer) { // ist auf linker oder rechten Seite ein Gegner
		String[] moveturn = move.split("-");
		String from = moveturn[0];
		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		String to = moveturn[1];
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
