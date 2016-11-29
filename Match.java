public class Match {
	
	private int size; //grid length
	private char[][] grid;
	private char[][] grid2;
	private int x = -1;
	private int y = -1;
	private int x2 = -1;
	private int y2 = -1;
	private char[] symbols = {'\u269C', '\u2663', '\u266F', '\u266A', '\u2601', '\u2660', '\u2605', '\u2666',
							  '\u2600', '\u263A', '\u2744', '\u262F', '\u2665', '\u2638', '\u2699', '\u266B'};

	Match(int size) {
		setSize(size);
		initAnswerGrid(getSize());
		initGameGrid(getSize());
		shuffleGrid();
	}

	private void initAnswerGrid(int size) {
		int counter = 0;
		grid = new char[size][size];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				grid[i][j] = symbols[counter];
				counter++;
					if(i == (grid.length / 2 - 1) && j == (grid.length - 1))
						counter = 0;
			}
		}
	}

	public void initGameGrid(int size) {
		grid2 = new char[size][size];
		for(int i = 0; i < grid2.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				grid2[i][j] = '?';
			}
		}

	}

	private void setSize(int size) {
		if(size > 0 && (size % 2 == 0))
			this.size = size;
		else {
			System.out.println("Invalid input. Setting default size 4");
			this.size = 4;
		}
	}

	public void setX(int x) {
		if(x < 0 || x > (getSize() - 1))
			this.x = -1;
		else
			this.x = x;
	}

	public void setX2(int x2) {
		if(x2 < 0 || x2 > (getSize() - 1))
			this.x2 = -1;
		else
			this.x2 = x2;
	}

	public void setY(int y) {
		if(y < 0 || y > (getSize() - 1))
			this.y = -1;
		else
			this.y = y; 
	}

	public void setY2(int y2) {
		if(y2 < 0 || y2 > (getSize() - 1))
			this.y2 = -1;
		else
			this.y2 = y2;
	}

	public void setGrid(int x, int y) {
		grid2[y][x] = '?';
	}

	private void shuffleGrid() {
		char[][] temp = new char[grid.length][grid.length];

		for(int i = 0; i < grid.length; i++) {
			int x = randomElement(grid.length);
			int y = randomElement(grid.length);
			for(int j = 0; j < grid[0].length; j++) {
				temp[i][j] = grid[i][j];
				grid[i][j] = grid[x][y];
				grid[x][y] = temp[i][j];
			}
		}
	}

	public void printGame() {
		System.out.print("  x ");
		for(int i = 1; i <= grid.length; i++) {
			System.out.print(i + "   ");
		}
		System.out.println("\ny");
		for(int i = 0; i < grid2.length; i++) {
			for(int j = 0; j < grid2[0].length; j++) {
				if(i == getY() && j == getX())
					grid2[i][j] = grid[getY()][getX()];
				if(i == getY2() && j == getX2())
					grid2[i][j] = grid[getY2()][getX2()];				
			}
		}
		for(int i = 0; i < grid2.length; i++) {
			System.out.print((i + 1) + "   ");
			for(int j = 0; j < grid2[0].length; j++) {
				System.out.print(grid2[i][j] + "   ");
			}
			System.out.println("\n");
		}
		System.out.println();
	}

	public int getX() {
		return x;
	}

	public int getX2() {
		return x2;
	}

	public int getY() {
		return y;
	}

	public int getY2() {
		return y2;
	}

	public int getSize() {
		return size;
	}

	public char[][] getGrid() {
		return grid2;
	}

	public int randomElement(int i) {
		return (int)(Math.random() * i);
	}

	public void guess(int x, int y, int x2, int y2) {
		if(grid[y][x] == grid[y2][x2]) {
			grid2[y2][x2] = grid[y2][x2];
			System.out.println("Got a match!");
		}
		else {
			System.out.println("Try again!\n");
			setGrid(x, y);
			setGrid(x2, y2);
			reset();
		}
			
	}

	public void reset() {
		this.x = -1;
		this.y = -1;
		this.x2 = -1;
		this.y2 = -1;
	}

	public boolean isWinner() {
		for(int i = 0; i < grid2.length; i++) {
			for(int j = 0; j < grid2[0].length; j++) {
				if(grid2[i][j] == '?')
					return false;
			}
		}
		return true;
	}

}