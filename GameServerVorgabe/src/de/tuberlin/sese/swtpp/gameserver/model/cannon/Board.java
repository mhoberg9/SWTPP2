package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Board {

	public static FieldHandler fieldList = null;

	
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
	 * @param posFields
	 *            InputList that will be checked for possibilities of retreat
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

	// ï¿½berprï¿½fung sieht immer wie folgt aus eigener Stein auf fromMove Gegner
	// etc
	// auf toMove
	
	
	/**
	 * Bei normal Move checken ob findway teilleiste von den ersten 3 von MARK ist überprüfung auch direkt
	 * durch move ohne findway, also to Move is teil von subliste(0,4)
	 * danach checken ob feld nicht durch eigenen spieler besetzt ist
	 * 
	 */
	
	/**
	 * Useless since CannonAction
	 */
//	public boolean isCannonAndCanFire(String fromMove, String toMove, String requestingPlayer) {
//		List l = fieldList.findWay(fromMove, toMove);
//		List<String> l2 = fieldList.stream().filter(a -> a.isPlayer(requestingPlayer)).map(Field::getPostion)
//				.collect(Collectors.toList());
//
//		boolean free = fieldList.stream().anyMatch((a -> a.getPostion().equals(l.get(3)) && a.isEmpty()));
//		// l.sublist to check whether it is a cannon
//		return l2.containsAll(l.subList(0, 2)) && l.contains(toMove) && free;

//	}
/**
 * @TODO
 * check type safety for findway!!!
 * 
 * 
 * @param fromMove
 * @param toMove
 * @param requestingPlayer
 * @return
 */
	public List<String> isCannon(String fromMove, String toMove, String requestingPlayer) {
		List<String> moves = fieldList.findWay(fromMove, toMove);
		List<String> possibleMoves = fieldList.stream().filter(a -> a.isPlayer(requestingPlayer)).map(Field::getPostion)
				.collect(Collectors.toList());

		if (possibleMoves.containsAll(moves.subList(0, 3))
				&& fieldList.stream().anyMatch(f -> f.getPostion().equals(moves.get(3)) && f.isEmpty()))
			return moves;
		else
			return Arrays.asList("FAILED!");

	}

	/**
	 * checks whether field is part of a canon if head is free and it's a move turn
	 * it swaps fields if head is free and it's a fire turn it destroys the field it
	 * fires at
	 * 
	 * 
	 * @param fromMove
	 * @param toMove
	 * @param requestingPlayer
	 */
	public boolean cannonAction(String fromMove, String toMove, String requestingPlayer) {
		List<String> moves = isCannon(fromMove, toMove, requestingPlayer);
		if (moves.size() == 4) {
			swap(moves.get(0), moves.get(1));
			return true;
		} else if (moves.size() > 4 && moves.size() < 7) {
			destroy(moves.get(moves.size() - 1));
			return true;
		}
		return false;

	}

	
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
	 * 		finde ein Element, was vor sich frei hat, zum Spieler gehï¿½rt und
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

	public List<Field> fieldsFromPositions(List<String> positions) {
		return fieldList.stream().filter(f -> positions.contains(f.getPostion())).collect(Collectors.toList());
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
