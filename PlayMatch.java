import java.util.Scanner;

public class PlayMatch {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String input;
		int guessCounter = 0;

		while(true) {
			//will only work for size 4 or less due to size of symbols char[]
			///System.out.print("\nEnter an even number for grid size: ");
			//int size = s.nextInt();
			System.out.print("\nBuilding your game...");
			Match m = new Match(4);

			System.out.println("\nFind the pairs!\n");
		
			while(true) {
				m.printGame();
				System.out.print("Enter coordinates for your guess in [x y] format: ");
				m.setX(s.nextInt() - 1);
				m.setY(s.nextInt() - 1);
					if(m.getX() == -1 || m.getY() == -1) {
						System.out.println("Invalid input.");
						m.reset();
						continue;
					}
					else if(m.getGrid()[m.getY()][m.getX()] != '?') {
						System.out.println("You already got this one!");
						//m.setGrid(m.getY(), m.getX());
						m.reset();
						continue;
					}
					else 
						m.printGame();
				System.out.print("Enter another guess in [x y] format: ");
				m.setX2(s.nextInt() - 1);
				m.setY2(s.nextInt() - 1);
					if(m.getX2() == -1 || m.getY2() == -1) {
						System.out.println("Invalid input.");
						m.reset();
						continue;
					}
					else if(m.getGrid()[m.getY2()][m.getX2()] != '?') {
						System.out.println("You already got this one!");
						m.setGrid(m.getX(), m.getY());
						m.reset();
						continue;
					}
					else
						m.printGame();

				m.guess(m.getX(), m.getY(), m.getX2(), m.getY2());
				guessCounter++;
					if(m.isWinner()) {
						System.out.print("You won! Total guesses: " + guessCounter + "\nPlay Again? [Y or N]: ");
						input = s.next();
						if(input.toUpperCase().equals("Y"))
							break;
						else if(input.toUpperCase().equals("N"))
							System.exit(0);
						else {
							System.out.println("Invalid input. Goodbye.");
							System.exit(1);
						}
					}
			}
		}
	}
}