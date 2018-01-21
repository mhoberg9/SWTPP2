package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.LinkedList;

public class FigureHandler extends LinkedList<Figure> {

	public void update() {
		if (this.size() == 100) {
			for (int i = 0; i < 100; i++) {
				Figure f = this.get(i);
				if (i < 9 && i > 0) {
					top(f, i);
				}
				if (i == 0) {
					topLeft(f, i);
				}
				if (i == 9) {
					topRight(f, i);
				}
				if (i % 10 == 0 && i != 0 && i != 90) {
					left(f, i);
				}
				if (i % 10 == 9 && i != 9 && i != 99) {
					right(f, i);
				}
				if (i == 99) {
					botRight(f, i);
				}
				if (i == 90) {
					botLeft(f, i);
				}
				if (i > 90 && i < 99) {
					botLane(f, i);
				}
				else {
					normal(f,i);
				}

			}
		}
	}

	public void normal(Figure f, int i) {
		f.top = this.get(i - 10);
		f.topLeft = this.get(i - 11);
		f.topRight = this.get(i - 9);
		f.left = this.get(i - 1);
		f.right = this.get(i + 1);
		f.botLeft = this.get(i + 9);
		f.botRight = (this.get(i + 11));
		f.bot = this.get(i + 10);
	}

	public void top(Figure f, int i) {
		if (i < 9 && i > 0) {
			f.top = null;
			f.left = this.get(i - 1);
			f.right = this.get(i + 1);
			f.topLeft = this.get(i - 11);
			f.topRight = this.get(i - 9);

			f.botLeft = this.get(i + 9);
			f.botRight = (this.get(i + 11));
			f.bot = this.get(i + 10);
		}
	}

	public void topLeft(Figure f, int i) {
		if (i == 0) {
			f.top = null;
			f.right = this.get(i + 1);
			f.left = null;
			f.topLeft = null;
			f.topRight = null;

			f.botLeft = this.get(i + 9);
			f.botRight = (this.get(i + 11));
			f.bot = this.get(i + 10);
		}

	}

	public void topRight(Figure f, int i) {
		if (i == 9) {
			f.top = null;
			f.right = null;
			f.left = this.get(i - 1);
			f.topLeft = this.get(i - 11);
			f.topRight = this.get(i - 9);
			f.right = this.get(i + 1);
			f.botLeft = this.get(i + 9);
			f.botRight = (this.get(i + 11));
			f.bot = this.get(i + 10);
		}
	}

	public void left(Figure f, int i) {
		if (i % 10 == 0 && i != 0 && i != 90) {
			f.top = this.get(i - 10);
			f.right = this.get(i + 1);
			f.left = null;
			f.topLeft = null;
			f.topRight = this.get(i - 9);
			f.botLeft = null;
			f.botRight = (this.get(i + 11));
			f.bot = this.get(i + 10);
		}
	}

	public void right(Figure f, int i) {
		if (i % 10 == 9 && i != 9 && i != 99) {
			f.top = this.get(i - 10);
			f.topLeft = this.get(i - 11);
			f.topRight = null;
			f.left = this.get(i - 1);
			f.right = null;
			f.botLeft = this.get(i + 9);
			f.botRight = null;
			f.bot = this.get(i + 10);

		}
	}

	public void botRight(Figure f, int i) {
		if (i == 99) {
			f.top = this.get(i - 10);
			f.topLeft = this.get(i - 11);
			f.topRight = null;
			f.left = this.get(i - 1);
			f.right = null;
			f.botLeft = null;
			f.botRight = null;
			f.bot = null;

		}
	}

	public void botLeft(Figure f, int i) {
		if (i == 90) {
			f.top = this.get(i - 10);
			f.right = this.get(i + 1);
			f.left = null;
			f.topLeft = null;
			f.topRight = this.get(i - 9);

			f.botLeft = null;
			f.botRight = null;
			f.bot = null;
		}
	}

	public void botLane(Figure f, int i) {
		if (i > 90 && i < 99) {
			f.top = this.get(i - 10);
			f.topLeft = this.get(i - 11);
			f.topRight = this.get(i - 9);
			f.left = this.get(i - 1);
			f.right = this.get(i + 1);
			f.botLeft = null;
			f.botRight = null;
			f.bot = null;

		}
	}

}
