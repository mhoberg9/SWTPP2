package de.tuberlin.sese.swtpp.gameserver.model.cannon;

import java.util.LinkedList;

public class FigureHandler extends LinkedList<Figure> {

	public String getTopLeft(String pos) {
		return getString(-1, 1, pos);

	}

	public String getRight(String pos) {
		return getString(1, 0, pos);

	}

	public String getLeft(String pos) {
		return getString(-1, 0, pos);

	}

	public String getTopRight(String pos) {
		return getString(1, 1, pos);

	}

	public String getTop(String pos) {
		return getString(0, 1, pos);

	}

	public String getBot(String pos) {
		return getString(0, -1, pos);

	}

	public String getBotLeft(String pos) {
		return getString(-1, -1, pos);
	}

	public String getBotRight(String pos) {
		return getString(1, -1, pos);

	}

	public String getString(int a, int b, String position) {
		return "" + (char) a + position.charAt(0) + "" + (char) b + position.charAt(2);

	}

	public String findWay(String from, String to) {
		String way = from;
		int fromCol = (int) from.charAt(0);
		int fromRow = (int) from.charAt(1);
		int toCol = (int) to.charAt(0);
		int toRow = (int) to.charAt(1);

		while (fromCol!=toCol&&fromRow!=toRow) {
			if (fromCol < toCol)
				fromCol++;
			if (fromCol > toCol)
				fromCol--;

			if (fromRow > toRow)
				toRow--;
			if (fromRow < toRow)
				toRow++;
way+=""+(char)fromCol+"" +fromRow+";";
			
		}
		return way;

	}

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
				} else {
					normal(f, i);
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
