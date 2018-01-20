package de.tuberlin.sese.swtpp.gameserver.model;

import java.util.LinkedList;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Field extends LinkedList<Figure> {


	public boolean surrounded(String moveBeginning, boolean requestingPlayer) {
		char column = moveBeginning.charAt(0);

		int row = (int) moveBeginning.charAt(1);
		Figure f= new Figure(requestingPlayer, column, row);

		
		
	return this.stream().anyMatch(a->(topLeft.test(a,b)));

	
	}

	public boolean isCannon(String unit) {

	}

	public boolean normalMove(String move) {

		Figure f;
		// vorne frei
		this.stream().anyMatch(a -> a.column == f.column && a.row == f.row);
	}

	public boolean leftOrRight(String unit) { // ist auf linker oder rechten Seite ein Gegner
	}
	
	public String toFenString() {
		String s="";
		for(int i=0;i<this.size();i++) {
		s+=	this.remove(i).toString();
		if(i%10==0) s+="//";
		}
		return s;
	}

}
