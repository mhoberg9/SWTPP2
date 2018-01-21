package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.LinkedList;

public class Cannon{

	Figure head;
	Figure center;
	Figure tail;
	
	LinkedList<Figure> figureholder;
	
	



	public Cannon(Figure head, Figure center, Figure tail, LinkedList<Figure> figureholder) {
		super();
		this.head = head;
		this.center = center;
		this.tail = tail;
		this.figureholder = figureholder;
	}





	public boolean canFire(String fireAt) {
		return figureholder.stream().anyMatch(a->a.postion.equals(fireAt))&& figureholder.removeFirst().isEmpty();
	}
	
}
