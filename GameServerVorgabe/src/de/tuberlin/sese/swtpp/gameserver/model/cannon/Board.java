package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

		List<String> tempList2 = posFields.subList(0, 8);
		List<Field> tempList = fieldList.stream().filter(a -> tempList2.contains(a.getPostion()))
				.collect(Collectors.toList());
		return tempList.stream().anyMatch(a -> !a.isPlayer(requestingPlayer));
	}

	/**
	 * 
	 * @param posFields
	 *            Liste aus die durch Algorithmus ausgewï¿½hlt wurden
	 * @return
	 */
	public List<String> retreatFields(List<String> posFields) {

		List<String> tempList = posFields.subList(5, 11);
		List<Field> tempList2 = fieldList.stream().filter(a -> tempList.contains(a.getPostion()))
				.collect(Collectors.toList());

		List<String> posRetreat = new ArrayList<String>();

		if (tempList2.get(0).isEmpty() && tempList.get(3).isEmpty()) {
			posRetreat.add(tempList2.get(3).getPostion());
		} else if (tempList2.get(1).isEmpty() && tempList.get(4).isEmpty()) {
			posRetreat.add(tempList2.get(4).getPostion());
		} else if (tempList2.get(2).isEmpty() && tempList.get(5).isEmpty()) {
			posRetreat.add(tempList2.get(5).getPostion());
		} else {
			posRetreat.add("NO RETREAT");
		}
		return posRetreat;

	}

	public boolean checkRetreat(String to, List<String> possibleRetreatFields, String requestingPlayer) {

		return possibleRetreatFields.contains(to) && inDanger(possibleRetreatFields, requestingPlayer);
	}

	// ï¿½berprï¿½fung sieht immer wie folgt aus eigener Stein auf fromMove Gegner
	// etc
	// auf toMove

	/**
	 * Bei normal Move checken ob findway teilliste von den ersten 3 von MARK ist
	 * ï¿½berprï¿½fung auch direkt durch move ohne findway, also to Move is teil von
	 * subliste(0,4) danach checken ob feld nicht durch eigenen spieler besetzt ist
	 * /**
	 * 
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
		} else if (moves.size() > 4 && moves.size() < 7
				&& enemyAtPosition(moves.get(moves.size() - 1), requestingPlayer)) {
			destroy(moves.get(moves.size() - 1));
			return true;
		}
		return false;

	}

	/**
	 * 
	 * subliste(0,4) danach checken ob feld nicht durch eigenen spieler besetzt ist
	 * isPlayer hat 3-Seitige Logik-> wenn wir Gegnerischen spieler und nicht frei
	 * haben wollen mï¿½ssen wir nicht player und isEmpty nehmen.
	 */

	public boolean normalMoveCheck(String to, List<String> surroundingFields, String requestingPlayer) {
		return (surroundingFields.subList(0, 3).contains(to) && fieldList.stream()
				.filter(f -> !f.isPlayer(requestingPlayer)).map(f -> f.getPostion()).anyMatch(f -> f.equals(to)));

	}

	/**
	 * TODO
	 */
	// Ist der eine Teil
	public boolean normalMove(String requestingPlayer, List<String> surroundingFields) {
		return fieldList.stream().anyMatch(
				a -> !a.isPlayer(requestingPlayer) && surroundingFields.subList(0, 3).contains(a.getPostion()));

	}

	/**
	 * 
	 * @param to
	 *            Destination of move
	 * @param requestingPlayer
	 *            player whose turn it is
	 * @param surroundingFields
	 *            neighbor fields found by algorithm
	 * @return true if field from has neighbors and could move there
	 */
	public boolean hasNeighbor(String to, String requestingPlayer, List<String> surroundingFields) {
		List<String> neighbor = surroundingFields.subList(3, 5);
		return (neighbor.contains(to) && fieldList.stream()
				.anyMatch(f -> f.getPostion().equals(to) && !f.isPlayer(requestingPlayer) && !f.isEmpty()));

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
	 * @TODO !!!!!!!!!!!!!!!!!!!!!!! finde ein Element, was vor sich frei hat, zum
	 *       Spieler gehört und nicht eine Burg ist
	 */

	public boolean canStillPlay(String requestingPlayer) {

		return fieldList.stream().anyMatch(a -> {
			List<String> posFields = FieldHandler.mark(a.getPostion(), requestingPlayer);
			if (inDanger(posFields, requestingPlayer) && !retreatFields(posFields).contains("NO RETREAT")
					|| normalMove(requestingPlayer, posFields))
				return true;
			return false;
		});
	}

	public boolean performMove(String move, String requestingPlayer) {
		String from = move.split("-")[0];
		String to = move.split("-")[1];
		List<String> possibleFields = FieldHandler.mark(to, requestingPlayer);
		boolean fromIsOk = fieldList.stream()
				.anyMatch(f -> f.isPlayer(requestingPlayer) && f.getPostion().equals(from));
		boolean toIsOk = fieldList.stream().anyMatch(f -> !f.isPlayer(requestingPlayer) && f.getPostion().equals(to));
		if (fromIsOk && toIsOk) {
			if (cannonAction(from, to, requestingPlayer)) {
				return true;
			} else if (checkRetreat(to, possibleFields, requestingPlayer)
					|| normalMoveCheck(to, possibleFields, requestingPlayer)
					|| hasNeighbor(to, requestingPlayer, possibleFields)) {
				move(from, to);
				return true;}
		}
	return canStillPlay(requestingPlayer);

	}

	public List<Field> fieldsFromPositions(List<String> positions) {
		return fieldList.stream().filter(f -> positions.contains(f.getPostion())).collect(Collectors.toList());
	}

	public void move(String from, String to) {
		String first = fieldList.stream().filter(f -> f.getPostion().equals(from)).findFirst().get().getColor();
		fieldList.stream().filter(f -> f.getPostion().equals(to)).findFirst().get().setColor(first);
		fieldList.stream().filter(f -> f.getPostion().equals(from)).findFirst().get().destroy();

	}

	public boolean enemyAtPosition(String position, String requestingPlayer) {
		return fieldList.stream()
				.anyMatch(f -> f.getPostion().equals(position) && !f.isPlayer(requestingPlayer) && !f.isEmpty());
	}

}
