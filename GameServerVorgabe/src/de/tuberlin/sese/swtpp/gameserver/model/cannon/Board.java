package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

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

	public boolean inDanger(List<String> posFields, String requestingPlayer) {
		List<String> tempList2 = posFields.subList(0, 4);
		List<Figure> tempList = storage.stream().filter(a -> tempList2.contains(a.getPostion()))
				.collect(Collectors.toList());
		return tempList.stream().anyMatch(a -> !a.isPlayer(requestingPlayer));
	}

	public List<String> retreat(List<String> posFields, String requestingPlayer, String move) {

		List<String> tempList = posFields.subList(5, 10);

		List<Figure> tempList2 = storage.stream().filter(a -> tempList.contains(a.getPostion()))
				.collect(Collectors.toList());
		//List<Figure> emptyList = tempList2.stream().filter(a -> a.isEmpty()).collect(Collectors.toList());
		
		List<String>posRetreat = null;
		
		if (tempList2.get(0).isEmpty() && tempList.get(3).isEmpty()) {
			posRetreat.add(tempList2.get(0).getPostion());
			posRetreat.add(tempList2.get(3).getPostion());
		}
		if (tempList2.get(1).isEmpty() && tempList.get(4).isEmpty()) {
			posRetreat.add(tempList2.get(1).getPostion());
			posRetreat.add(tempList2.get(4).getPostion());
		}
		if (tempList2.get(2).isEmpty() && tempList.get(5).isEmpty()) {
			posRetreat.add(tempList2.get(2).getPostion());
			posRetreat.add(tempList2.get(5).getPostion());
		}
		return posRetreat;
		
		// tempList2.stream().filter(a -> tempList.get(0).isEmpty()).).
		// tempList2.stream().filter(a ->
		// a.getPostion(tempList2.get(0).isEmpty())).collect(Collectors.toList());
		// tempList2.stream().filter(a -> (a.getPostion().equals(tempList.get(0))&&
		// a.getPostion().equals(tempList.get(3)) && a.isEmpty()) ||
		// a.getPostion().equals(tempList))).collect(Collectors.toList());

		// return emptyList.stream().filter(a ->
		// emptyList.contains((a.getPostion().equals(tempList[0]) &&
		// a.getPostion().equals(tempList[3])))).map(a ->
		// a.getPostion().collect(Collectors.toList()));

		// char column = moveBeginning.charAt(0);
		// int row = (int) moveBeginning.charAt(1);
		// Figure f = new Figure(requestingPlayer, column, row);
		//
		// if (requestingPlayer.equalsIgnoreCase("b")) {
		// return storage.stream().anyMatch(a -> top.test(a, f) || topRight.test(a, f)
		// || topLeft.test(a, f)
		// || left.test(a, f) || right.test(a, f) && a.isPlayer(requestingPlayer) ==
		// false);
		// } else {
		// return storage.stream().anyMatch(a -> left.test(a, f) || bottomLeft.test(a,
		// f) || bottom.test(a, f)
		// || bottomRight.test(a, f) || right.test(a, f) && a.isPlayer(requestingPlayer)
		// == false);
	}

	// �berpr�fung sieht immer wie folgt aus eigener Stein auf fromMove Gegner etc
	// auf toMove
	public boolean isCannonAndCanFire(String fromMove, String toMove, String requestingPlayer) {
		List l = storage.findWay(fromMove, toMove);
		List<String> l2 = storage.stream().filter(a -> a.isPlayer(requestingPlayer)).map(Figure::getPostion)
				.collect(Collectors.toList());
		boolean free = storage.stream().anyMatch((a -> a.getPostion().equals(l.get(3)) && a.isEmpty()));
		// l.sublist to check whether it is a cannon
		return l2.containsAll(l.subList(0, 2)) && l.contains(toMove) && free;

	}

	// @TODO Verschiebung mittels swap
	// kann auch eine Liste von Figuren wiedergeben, die Verschoben werden m�ssen
	public boolean isCannonAndCanMove(String fromMove, String toMove, String requestingPlayer) {
		List moves = storage.findWay(fromMove, toMove);
		List<String> possibleMoves = storage.stream().filter(a -> a.isPlayer(requestingPlayer)).map(Figure::getPostion)
				.collect(Collectors.toList());
		if (moves.size() == 4) {
			if (possibleMoves.contains(moves.subList(0, 3))
					&& storage.stream().anyMatch(a -> a.getPostion().equals(moves.get(4)) && a.isEmpty()))
				;
			// Es handelt sich um eine Verschiebung nun muss nurnoch Verschoben werden
		}
		return false;
	}

	// @Todo Methode swap muss implementiert werden und inhalt der Figurenfelder
	// Swappen

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

	public static void swap(String fromM, String toM) {
		Figure first = storage.stream().filter(a -> a.getPostion().equals(fromM)).findFirst().get();
		Figure last = storage.stream().filter(a -> a.getPostion().equals(toM)).findFirst().get();

		String tempColor = first.getColor();
		first.setColor(last.getColor());
		last.setColor(tempColor);
	}

	public static void destroy(String destination) {
		storage.stream().filter(a -> a.getPostion().equals(destination)).findFirst().get().setColor("1");
	}

	/**
	 * 
	 * @TODO
	 *
	 * 		finde ein Element, was vor sich frei hat, zum Spieler geh�rt und nicht
	 *       eine Burg ist
	 */

	public List<Figure> playableFigure(String requestingPlayer) {

		List<Figure> lplay = null;
		List<Figure> l = storage.stream().filter(a -> a.isSolider()).collect(Collectors.toList());
		List<Figure> lplayw = null;
		List<Figure> lplayb = null;

		for (Figure help : l) {
			if (help.isPlayer(requestingPlayer)) {
				lplayw = (l.stream()
						.filter(a -> ((inDanger(help.getPostion(), help.getColor())
								&& (!top.test(a, help) || !topLeft.test(a, help) || !topRight.test(a, help)
										|| !right.test(a, help) || !left.test(a, help) || !bottom.test(a, help)
										|| !bottomLeft.test(a, help) || !bottomRight.test(a, help))))
								|| ((!inDanger(help.getPostion(), help.getColor())
										&& (!right.test(a, help) || !left.test(a, help) || !bottom.test(a, help)
												|| !bottomLeft.test(a, help) || !bottomRight.test(a, help)))))
						.collect(Collectors.toList()));
			} else {
				lplayb = (l.stream()
						.filter(a -> ((inDanger(help.getPostion(), help.getColor())
								&& (!top.test(a, help)
										|| !topLeft.test(a, help) || !topRight.test(a, help) || !right.test(a, help)
										|| !left.test(a, help) || !bottom.test(a, help) || !bottomLeft.test(a, help)
										|| !bottomRight.test(a, help)))
								|| ((!inDanger(help.getPostion(), help.getColor())
										&& (!top.test(a, help) || !topLeft.test(a, help) || !topRight.test(a, help)
												|| !right.test(a, help) || !left.test(a, help))))))
						.collect(Collectors.toList()));
			}
			lplay.addAll(lplayw);
			lplay.addAll(lplayb);
		}
		return lplay;

		// for (Figure h : l) {
		// if (h.color.equals("w")) {
		// if (inDanger(h.postion, h.color)) {
		// if (l.stream()
		// .filter(a -> (!top.test(a, h) || !topLeft.test(a, h) || !topRight.test(a, h)
		// || !right.test(a, h) || !left.test(a, h) || !bottom.test(a, h)
		// || !bottomLeft.test(a, h) || !bottomRight.test(a, h))) != null) {
		// lplay.add(h);
		// }
		// } else {
		// if (l.stream().filter(a -> !right.test(a, h) || !left.test(a, h) ||
		// !bottom.test(a, h)
		// || !bottomLeft.test(a, h) || !bottomRight.test(a, h)) != null) {
		// lplay.add(h);
		// }
		// }
		// } else {
		// if (inDanger(h.postion, h.color)) {
		// if (l.stream()
		// .filter(a -> (!top.test(a, h) || !topLeft.test(a, h) || !topRight.test(a, h)
		// || !right.test(a, h) || !left.test(a, h) || !bottom.test(a, h)
		// || !bottomLeft.test(a, h) || !bottomRight.test(a, h))) != null) {
		// lplay.add(h);
		// }
		// } else {
		// if (l.stream().filter(a -> (!top.test(a, h) || !topLeft.test(a, h) ||
		// !topRight.test(a, h)
		// || !right.test(a, h) || !left.test(a, h))) != null) {
		// lplay.add(h);
		// }
		// }
		//
		// }
		// }
		// return lplay;
		//
		// // 1. Suche Elemente mit color != 1 & !=isCastle() add to List
		// // 2. Nehme mit schleife jedes Element und check predicate
		// // 3. sofern min. 1 freies feld und/oder inDanger= true adde to list
		// // return new List mit benutzbaren Elementen
	}

	public boolean canStillPlay(List<Figure> lplay, String requestingPlayer) {
		if (lplay.contains(requestingPlayer)) {
			return true;
		} else {
			return false;
		}

	}

}
