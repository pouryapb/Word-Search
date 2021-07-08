package pouryapb.wordSearch;

import java.util.Random;

public class RandGenerator {
	
	protected String[][] table = new String[10][10];
	protected String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	protected Random rand = new Random();
	
	
	
	public RandGenerator() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				table[i][j] = "0";
			}
		}
	}

	protected void printTable() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void generate() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (table[i][j].equals("0"))
					table[i][j] = letters[rand.nextInt(26)];
			}
		}
	}
}
