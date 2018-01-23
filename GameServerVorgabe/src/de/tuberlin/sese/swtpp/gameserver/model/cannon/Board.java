package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Board {

	public static FieldHandler fieldList = null;

	// Test
	public static TriPredicate<Field, Field, Field> diagonal = (a, b, c) -> a.equals(2);
	// topLeft

	public static BiPredicate<Field, Field> topLeft = (a, b) -> a.column == b.column && a.row == b.row + 1;
	// top
	public static BiPredicate<Field, Field> top = (a, b) -> a.column == b.column && a.row == b.row + 1;
	// topRight
	public static BiPredicate<Field, Field> topRight = (a, b) -> a.column == b.column + 1 && a.row == b.row + 1;
	// left
	public static BiPredicate<Field, Field> left = (a, b) -> a.column == b.column - 1 && a.row == b.row;
	// right
	public static BiPredicate<Field, Field> right = (a, b) -> a.column == b.column + 1 && a.row == b.row;
	// bottomLeft
	public static BiPredicate<Field, Field> bottomLeft = (a, b) -> a.column == b.column - 1 && a.row == b.row - 1;
	// bottom
	public static BiPredicate<Field, Field> bottom = (a, b) -> a.column == b.column && a.row == b.row - 1;
	// bottomRight
	public static BiPredicate<Field, Field> bottomRight = (a, b) -> a.column == b.column + 1 && a.row == b.row - 1;

	/**
	 * checks whether unit of Field xy is in danger
	 */
	public boolean inDanger(List<String> posFields, String requestingPlayer) {
		List<String> tempList2 = posFields.subList(0, 4);
		List<Field> tempList = fieldList.stream().filter(a -> tempList2.contains(a.getPostion()))
				.collect(Collectors.toList());
		return tempList.stream().anyMatch(a -> !a.isPlayer(requestingPlayer));
	}

	
	/*
	 * @TODO
	 * 
	 */
	/**
 * @TODO 
 * @param posFields InputList that will be checked for possibilities of retreat
 * @param requestingPlayer
 * @return 
 */
	public List<String> retreat(List<String> posFields, String requestingPlayer) {

		List<String> tempList = posFields.subList(5, 10);
		List<Field> tempList2 = fieldList.stream().filter(a -> tempList.contains(a.getPostion()))
				.collect(Collectors.toList());

		return null;

		// char column = moveBeginning.charAt(0);
		// int row = (int) moveBeginning.charAt(1);
		// Field f = new Field(requestingPlayer, column, row);
		//
		// if (requestingPlayer.equalsIgnoreCase("b")) {
		// return fieldList.stream().anyMatch(a -> top.test(a, f) || topRight.test(a, f)
		// || topLeft.test(a, f)
		// || left.test(a, f) || right.test(a, f) && a.isPlayer(requestingPlayer) ==
		// false);
		// } else {
		// return fieldList.stream().anyMatch(a -> left.test(a, f) || bottomLeft.test(a,
		// f) || bottom.test(a, f)
		// || bottomRight.test(a, f) || right.test(a, f) && a.isPlayer(requestingPlayer)
		// == false);
	}

	// �berpr�fung sieht immer wie folgt aus eigener Stein auf fromMove Gegner
	// etc
	// auf toMove
	public boolean isCannonAndCanFire(String fromMove, String toMove, String requestingPlayer) {
		List l = fieldList.findWay(fromMove, toMove);
		List<String> l2 = fieldList.stream().filter(a -> a.isPlayer(requestingPlayer)).map(Field::getPostion)
				.collect(Collectors.toList());
		boolean free = fieldList.stream().anyMatch((a -> a.getPostion().equals(l.get(3)) && a.isEmpty()));
		// l.sublist to check whether it is a cannon
		return l2.containsAll(l.subList(0, 2)) && l.contains(toMove) && free;

	}

	// @TODO Verschiebung mittels swap
	// kann auch eine Liste von Figuren wiedergeben, die Verschoben werden m�ssen
	public boolean isCannonAndCanMove(String fromMove, String toMove, String requestingPlayer) {
		List moves = fieldList.findWay(fromMove, toMove);
		List<String> possibleMoves = fieldList.stream().filter(a -> a.isPlayer(requestingPlayer)).map(Field::getPostion)
				.collect(Collectors.toList());
		if (moves.size() == 4) {
			if (possibleMoves.contains(moves.subList(0, 3))
					&& fieldList.stream().anyMatch(a -> a.getPostion().equals(moves.get(4)) && a.isEmpty()))
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
			return fieldList.stream()
					.anyMatch(a -> a.column == toCol && a.row == toRow && a.isPlayer(requestingPlayer));
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
			return fieldList.stream()
					.anyMatch(a -> a.column == toCol && a.row == toRow && a.isPlayer(requestingPlayer) == false);
		}
		return false;
	}

	public static String getBoard() {
		String s = "";
		for (int i = 0; i < fieldList.size(); i++) {
			s += fieldList.remove(i).toString();
			if (i % 10 == 0)
				s += "//";
		}
		return s;
	}

	public static void swap(String fromM, String toM) {
		Field first = fieldList.stream().filter(a -> a.getPostion().equals(fromM)).findFirst().get();
		Field last = fieldList.stream().filter(a -> a.getPostion().equals(toM)).findFirst().get();

		String tempColor = first.getColor();
		first.setColor(last.getColor());
		last.setColor(tempColor);
	}

	public static void destroy(String destination) {
		fieldList.stream().filter(a -> a.getPostion().equals(destination)).findFirst().get().destroy();
	}

	/**
	 * 
	 * @TODO
	 *
	 * 		finde ein Element, was vor sich frei hat, zum Spieler geh�rt und
	 *       nicht eine Burg ist
	 */

	public List<Field> playableFigure(String requestingPlayer) {

		List<Field> lplay = null;
		List<Field> l = fieldList.stream().filter(a -> a.isSolider()).collect(Collectors.toList());
		List<Field> lplayw = null;
		List<Field> lplayb = null;

		for (Field help : l) {
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

	}
public List<Field> fieldsFromPositions(List<String> positions){
	return fieldList.stream().filter(f->positions.contains(f.getPostion())).collect(Collectors.toList());
}
	/**
	 * doesn't work !!!
	 */

	public boolean canStillPlay(List<Field> lplay, String requestingPlayer) {
		if (lplay.contains(requestingPlayer)) {
			return true;
		} else {
			return false;
		}

	}

}
