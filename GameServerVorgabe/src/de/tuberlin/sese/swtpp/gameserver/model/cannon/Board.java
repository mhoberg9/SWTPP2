package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Board {

	public static FieldHandler fieldList = null;

<<<<<<< HEAD
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

	/*
	 * @TODO
	 * 
	 */
=======

	 
>>>>>>> b7bfba16bcb42f4c8ea4505193914f1f1a215db7
	/**
	 * @TODO
	 * @param posFields
	 *            InputList that will be checked for possibilities of retreat
	 * @param requestingPlayer
	 * @return
	 */

	public boolean inDanger(List<String> posFields, String requestingPlayer) {
<<<<<<< HEAD
		List<String> tempList2 = posFields.subList(0, 7);
=======
		List<String> tempList2 = posFields.subList(0, 9);
>>>>>>> b7bfba16bcb42f4c8ea4505193914f1f1a215db7
		List<Field> tempList = fieldList.stream().filter(a -> tempList2.contains(a.getPostion()))
				.collect(Collectors.toList());
		return tempList.stream().anyMatch(a -> !a.isPlayer(requestingPlayer));
	}

<<<<<<< HEAD
	public List <String> retreat(List<String> posFields) {
=======
	/**
	 * 
	 * @param posFields
	 *            Liste aus die durch Algorithmus ausgewählt wurden
	 * @return
	 */
	public List<String> retreatFields(List<String> posFields) {

		List<String> tempList = posFields.subList(5, 10);
>>>>>>> b7bfba16bcb42f4c8ea4505193914f1f1a215db7

		List<String> tempList = posFields.subList(5, 11);
		List<Field> tempList2 = fieldList.stream().filter(a -> tempList.contains(a.getPostion()))
				.collect(Collectors.toList());

		List<String> posRetreat = null;

		if (tempList2.get(0).isEmpty() && tempList.get(3).isEmpty()) {
			posRetreat.add(tempList2.get(3).getPostion());
		}
		else if (tempList2.get(1).isEmpty() && tempList.get(4).isEmpty()) {
			posRetreat.add(tempList2.get(4).getPostion());
		}
		else if (tempList2.get(2).isEmpty() && tempList.get(5).isEmpty()) {
			posRetreat.add(tempList2.get(5).getPostion());
		} else {
			posRetreat.add("NO RETREAT");
		}
		return posRetreat;
<<<<<<< HEAD
=======

>>>>>>> b7bfba16bcb42f4c8ea4505193914f1f1a215db7
	}
public boolean checkRetreat(String to, List<String> possibleRetreatFields){
	return possibleRetreatFields.contains(to);
}



<<<<<<< HEAD
	// ï¿½berprï¿½fung sieht immer wie folgt aus eigener Stein auf fromMove Gegner
	// etc
	// auf toMove

	/**
	 * Bei normal Move checken ob findway teilleiste von den ersten 3 von MARK ist
	 * ï¿½berprï¿½fung auch direkt durch move ohne findway, also to Move is teil von
	 * subliste(0,4) danach checken ob feld nicht durch eigenen spieler besetzt ist
=======
	/**
	 * @TODO check type safety for findway!!!
>>>>>>> b7bfba16bcb42f4c8ea4505193914f1f1a215db7
	 * 
	 * 
	 * @param fromMove
	 * @param toMove
	 * @param requestingPlayer
	 * @return
	 */
<<<<<<< HEAD

	/**
	 * Useless since CannonAction
	 */
	// public boolean isCannonAndCanFire(String fromMove, String toMove, String
	// requestingPlayer) {
	// List l = fieldList.findWay(fromMove, toMove);
	// List<String> l2 = fieldList.stream().filter(a ->
	// a.isPlayer(requestingPlayer)).map(Field::getPostion)
	// .collect(Collectors.toList());
	//
	// boolean free = fieldList.stream().anyMatch((a ->
	// a.getPostion().equals(l.get(3)) && a.isEmpty()));
	// // l.sublist to check whether it is a cannon
	// return l2.containsAll(l.subList(0, 2)) && l.contains(toMove) && free;

	// }
	/**
	 * @TODO check type safety for findway!!!
	 * 
	 * 
	 * @param fromMove
	 * @param toMove
	 * @param requestingPlayer
	 * @return
	 */
=======
>>>>>>> b7bfba16bcb42f4c8ea4505193914f1f1a215db7
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
	 * @TODO checken ob Gegner auf Feld ist!
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

<<<<<<< HEAD
	public boolean normalMoveBlack(String from, String to, String requestingPlayer) {
=======
	/**
	
	 * subliste(0,4) danach checken ob feld nicht durch eigenen spieler besetzt ist
	 * isPlayer hat 3-Seitige Logik-> wenn wir Gegnerischen spieler und nicht frei haben wollen müssen wir nicht player und isEmpty nehmen.
	 */
>>>>>>> b7bfba16bcb42f4c8ea4505193914f1f1a215db7

	public boolean normalMove(String to, String requestingPlayer, List<String> surroundingFields) {
		return (surroundingFields.subList(0, 3).contains(to) && fieldList.stream()
				.filter(f -> !f.isPlayer(requestingPlayer)).map(f -> f.getPostion()).anyMatch(f -> f.equals(to)));

	}

/**
 * 	
 * @param to Destination of move
 * @param requestingPlayer player whose turn it is
 * @param surroundingFields neighbor fields found by algorithm
 * @return true if field from has neighbors and could move there
 */
	public boolean hasNeighbor(String to, String requestingPlayer, List<String> surroundingFields) { 
		List<String> neighbor = surroundingFields.subList(3, 5);
		return (neighbor.contains(to) && fieldList.stream().anyMatch(f -> f.getPostion().equals(to)&& !f.isPlayer(requestingPlayer)&&!f.isEmpty()));

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

<<<<<<< HEAD
	/**
	 * 
	 * @TODO
	 *
	 * 		finde ein Element, was vor sich frei hat, zum Spieler gehï¿½rt und
	 *       nicht eine Burg ist
	 */

	public boolean canStillPlay(List <String> posFields, String requestingPlayer) {
		return posFields.contains((inDanger(posFields, requestingPlayer)) && !retreat(posFields).contains("NO RETREAT") || normalMove() || canHit()));
	}




	public List<Field> fieldsFromPositions(List<String> positions) {
		return fieldList.stream().filter(f -> positions.contains(f.getPostion())).collect(Collectors.toList());
	}


=======
	public void move(String from, String to) {
		String first = fieldList.stream().filter(f -> f.getPostion().equals(from)).findFirst().get().getColor();
		fieldList.stream().filter(f -> f.getPostion().equals(to)).findFirst().get().setColor(first);
		fieldList.stream().filter(f -> f.getPostion().equals(from)).findFirst().get().destroy();

	}

	public List<Field> fieldsFromPositions(List<String> positions) {
		return fieldList.stream().filter(f -> positions.contains(f.getPostion())).collect(Collectors.toList());
	}

>>>>>>> b7bfba16bcb42f4c8ea4505193914f1f1a215db7
}
