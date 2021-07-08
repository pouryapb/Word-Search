package pouryapb.wordSearch;

import java.util.LinkedList;
import java.util.Scanner;

public class RunProgram extends RandGenerator{
	
	protected static LinkedList<String> inputs = new LinkedList<String>();
	protected static LinkedList<Integer> lengths = new LinkedList<Integer>(); 

	public static void main(String[] args) {
		
		System.out.println("1. Hide word!\n2. Find word!");
		Scanner sc = new Scanner(System.in);
		
		// choosing an option
		int a = sc.nextInt();
		while (a < 1 || a > 2) {
			System.out.println("Wrong Entery! try again");
			a = sc.nextInt();
		}
		
		// doing an option :|
		switch (a) {
		// hiding words
		case 1:
			System.out.println("How many words do you want to hide?");
			int n = sc.nextInt();
			while (n < 1 || n > 5) {
				System.out.println("Wrong Entery! (0 < n < 6)");
				n = sc.nextInt();
			}
			System.out.println("Enter words:");
			String temp;
			for (int i = 0; i < n; i++) {
				temp = sc.next();
				while (temp.length() < 1 || temp.length() > 5) {
					System.out.println("Too long Entery! (1 < n < 6)");
					temp = sc.next();
				}
				inputs.add(temp);
			}
			for (int i = 0; i < inputs.size(); i++) {
				lengths.add(inputs.get(i).length());
			}
			HideWord obj = new HideWord();
			obj.placeWords();
			obj.generate();
			obj.save();
			break;
		// finding words
		case 2:
			WordSearch obj2 = new WordSearch();
			obj2.play();
			break;
		}
		sc.close();
	}
}
