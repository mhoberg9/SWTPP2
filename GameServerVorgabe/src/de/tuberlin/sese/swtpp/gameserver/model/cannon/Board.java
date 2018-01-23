package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Board {

	public static FieldHandler fieldList = null;


	 
	/**
	 * @TODO
	 * @param posFields
	 *            InputList that will be checked for possibilities of retreat
	 * @param requestingPlayer
	 * @return
	 */

	public boolean inDanger(List<String> posFields, String requestingPlayer) {
		List<String> tempList2 = posFields.subList(0, 9);
		List<Field> tempList = fieldList.stream().filter(a -> tempList2.contains(a.getPostion()))
				.collect(Collectors.toList());
		return tempList.stream().anyMatch(a -> !a.isPlayer(requestingPlayer));
	}

	/**
	 * 
	 * @param posFields
	 *            Liste aus die durch Algorithmus ausgewählt wurden
	 * @return
	 */
	public List<String> retreatFields(List<String> posFields) {

		List<String> tempList = posFields.subList(5, 10);

		List<Field> tempList2 = fieldList.stream().filter(a -> tempList.contains(a.getPostion()))
				.collect(Collectors.toList());

		List<String> posRetreat = null;

		if (tempList2.get(0).isEmpty() && tempList.get(3).isEmpty()) {
			posRetreat.add(tempList2.get(3).getPostion());
		}
		if (tempList2.get(1).isEmpty() && tempList.get(4).isEmpty()) {
			posRetreat.add(tempList2.get(4).getPostion());
		}
		if (tempList2.get(2).isEmpty() && tempList.get(5).isEmpty()) {
			posRetreat.add(tempList2.get(5).getPostion());
		}
		return posRetreat;

	}
public boolean checkRetreat(String to, List<String> possibleRetreatFields){
	return possibleRetreatFields.contains(to);
}



	/**
	 * @TODO check type safety for findway!!!
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

	/**
	
	 * subliste(0,4) danach checken ob feld nicht durch eigenen spieler besetzt ist
	 * isPlayer hat 3-Seitige Logik-> wenn wir Gegnerischen spieler und nicht frei haben wollen müssen wir nicht player und isEmpty nehmen.
	 */

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

	public void move(String from, String to) {
		String first = fieldList.stream().filter(f -> f.getPostion().equals(from)).findFirst().get().getColor();
		fieldList.stream().filter(f -> f.getPostion().equals(to)).findFirst().get().setColor(first);
		fieldList.stream().filter(f -> f.getPostion().equals(from)).findFirst().get().destroy();

	}

	public List<Field> fieldsFromPositions(List<String> positions) {
		return fieldList.stream().filter(f -> positions.contains(f.getPostion())).collect(Collectors.toList());
	}

}
