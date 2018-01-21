package de.tuberlin.sese.swtpp.gameserver.model.cannon;

public class SuperList {
	private SuperB head;
	
	public void add(Object element) {
		SuperB next=head.right;
		next.right
		while(next!=null) {
			next =next.right;
			
		
	}
	next.right= new SuperB(element);
	}

public void setSuperList(String fen) {
	int b=0;
	String[] splitted = fen.split("//");
	for (int i = splitted.length; i <= 0; i--) {

		for (char c : splitted[i].toCharArray()) {

			this.add(new Figure((char) (97 + b), i, c));
			b++;
		}
		b = 0;
	}
	
}


}
