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

	public boolean inDanger(String moveBeginning, boolean requestingPlayer) {

		char column = moveBeginning.charAt(0);
		int row = (int) moveBeginning.charAt(1);
		Figure f = new Figure(requestingPlayer, column, row);

		if (requestingPlayer = false) {
			return storage.stream().anyMatch(a -> top.test(a, f) || topRight.test(a, f) || topLeft.test(a, f)
					|| left.test(a, f) || right.test(a, f) && a.white==true);
		} else {
			return storage.stream().anyMatch(a -> left.test(a, f) || bottomLeft.test(a, f) || bottom.test(a, f)
					|| bottomRight.test(a, f) || right.test(a, f) && a.white==false);
		}
	}

	// check if figure is cannon
	public boolean isCannonValid(String move, boolean requestingPlayer) {
		String[]moveturn=move.split("-");
		String from =moveturn[0];
		char fromCol = from.charAt(0);
		char fromRow= from.charAt(1);
		String to = moveturn[1];
		
		char toCol = to.charAt(0);
		char toRow= to.charAt(1);
		Figure f=null;
		if(isWhite(fromCol,fromRow,requestingPlayer)) {
		f=	storage.stream().filter(a-> a.column=fromColumn && a.getRow()==fromRow).findFirst().get();
		}
		//filter kanonen heraus nur Versuch
		storage.stream().filter(a->a.left!=null &&a.right!=null&&a.isWhite()==requestingPlayer);
	
		/**
		 * Wichtig 8 F�lle: 
		 * Diagonal, Schuss nach oben / unten u. links / rechts
		 * 
		 * Horizontal 
		 * Vertikal
		 * keine unterscheidung zwischen black und white
		 * 
		 * 
		 * 
		 * TODO @Malte 
		 * BiPr�dikate f�r die die einzelnen Kanonenf�lle schreiben:
		 * Nach folgendem Schema: 2 Figuren als Input
		 * 
		 * Zum besseren Verst�ndnis 'f' ist die figur, die angesprochen wird um den Schuss zu machen
		 * 
		 * Am besten du machst direkt die Filter Befehle
		 * 
		 * in Z. 81 ein gutes Bsp
		 */
		
		if (fromRow==toRow && (toCol-fromCol <=4)) {
			return (storage.stream().filter(a->a==f.left||a==f.left.left && a.isWhite()==requestingPlayer ).count()==2)&&f.getLeft().getLeft().isEmpty();
		}
		
		//
		if (fromRow-toRow>=4 &&fromCol==toCol) {
			storage.stream().filter(a-> top.test(a, f)||top.test(a, new Figure(requestedPlayer,fromCol,(int)fromRow+1))).count()==2;
		}
//		if(fromRow-toRow<=4 &&fromCol==toCol) {
//			return (f.bot.bot=f.isWhite() &&f.bot.isWhite()&& f.bot.isWhite() &&f.bot.bot.bot.isWhite());
//		
//		}
		//in einer Reihe black Richtig?
		if(fromRow==toRow && (toCol-fromCol>=4)) {
		return	storage.stream().filter(a->)
		
		}
		//Diagonal
		if(Math.abs(fromRow-toRow)>=4 && Math.abs(fromCol-toCol)>=4){}
		
		

	}

	public boolean normalMoveBlack(String move, boolean requestingPlayer) {

		String[] moveturn = move.split("-");
		String from = moveturn[0];
		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		String to = moveturn[1];

		char toCol = to.charAt(0);
		char toRow = to.charAt(1);
		if ((((Math.abs(fromCol - toCol)) <= 1) && ((toCol - fromCol) <= 1))) {
			return storage.stream().anyMatch(a -> a.column == toCol && a.row == toRow && a.white == requestingPlayer);
		}
	}

	// schlagen
	public boolean leftOrRight(String move, boolean requestingPlayer) { // ist auf linker oder rechten Seite ein Gegner
		String[] moveturn = move.split("-");
		String from = moveturn[0];
		char fromCol = from.charAt(0);
		char fromRow = from.charAt(1);
		String to = moveturn[1];
		char toCol = to.charAt(0);
		char toRow = to.charAt(1);

		if (fromRow == toRow && toCol == fromCol - 1 || fromCol + 1 == toCol) {
			return storage.stream().anyMatch(a -> a.column == toCol && a.row == toRow && a.white == !requestingPlayer);
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

	private boolean isWhite(char column, int row, boolean requestedPlayer) {
		return storage.stream().anyMatch(a -> a.column == column && a.row == row && a.white == requestedPlayer);
	}

}
