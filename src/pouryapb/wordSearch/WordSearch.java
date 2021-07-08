package pouryapb.wordSearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class WordSearch extends RunProgram{

	// for counting how many tables i have?
	private int tablesCount = 0;
	private String[][] table = new String[10][10];
	private LinkedList<String> answers = new LinkedList<String>();
	private ArrayList<Integer> coords = new ArrayList<Integer>();
	private ArrayList<String> dir = new ArrayList<String>(); 

	public WordSearch() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("keys.txt"));
			while (reader.readLine() != null) {
				tablesCount++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// finding a table
		Random rand = new Random();
		// chosen table :D
		int x = rand.nextInt(tablesCount) + 1;

		//filling table
		try {
			BufferedReader reader = new BufferedReader(new FileReader("record.txt"));
			for (int i = 0; i < (x - 1) * 11; i++) {
				reader.readLine();
			}
			String temp;
			for (int i = 0; i < 10; i++) {
				temp = reader.readLine();
				int j = 0;
				Scanner sc = new Scanner(temp);
				while (sc.hasNext()) {
					table[i][j] = sc.next();
					j++;
				}
				sc.close();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// filling answers
		try {
			BufferedReader reader = new BufferedReader(new FileReader("keys.txt"));
			for (int i = 0; i < x - 1; i++) {
				reader.readLine();
			}
			Scanner sc = new Scanner(reader.readLine());
			while (sc.hasNext()) {
				answers.add(sc.next().toUpperCase());
			}
			reader.close();
			sc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		//filling directions and coordinates
		try {
			BufferedReader reader = new BufferedReader(new FileReader("coords.txt"));
			for (int i = 0; i < x - 1; i++) {
				reader.readLine();
			}
			Scanner sc = new Scanner(reader.readLine());
			while (sc.hasNext()) {
				if (sc.hasNextInt()) {
					coords.add(Integer.valueOf(sc.next()));
				}
				else {
					dir.add(sc.next());
				}
			}
			reader.close();
			sc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// printer!
	private void print() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}

	protected void play() {
		int count = answers.size();
		while (count > 0) {
			print();
			System.out.println("Find a word:");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String in = sc.nextLine();
			boolean flag = false;
			
			for (int i = 0; i < answers.size(); i++) {
				flag = false;
				if (in.toUpperCase().equals(answers.get(i))) {
					switch (dir.get(i)) {
					case "N":
						for (int j = 0; j < answers.get(i).length(); j++) {
							table[coords.get(i + i)][coords.get(i + i + 1) - j] = String.valueOf(answers.get(i).charAt(j));
						}
						answers.remove(i);
						dir.remove(i);
						coords.remove(i + i + 1);
						coords.remove(i + i);
						break;
					case "NW":
						for (int j = 0; j < answers.get(i).length(); j++) {
							table[coords.get(i + i) - j][coords.get(i + i + 1) - j] = String.valueOf(answers.get(i).charAt(j));
						}
						answers.remove(i);
						dir.remove(i);
						coords.remove(i + i + 1);
						coords.remove(i + i);
						break;
					case "W":
						for (int j = 0; j < answers.get(i).length(); j++) {
							table[coords.get(i + i) - j][coords.get(i + i + 1)] = String.valueOf(answers.get(i).charAt(j));
						}
						answers.remove(i);
						dir.remove(i);
						coords.remove(i + i + 1);
						coords.remove(i + i);
						break;
					case "SW":
						for (int j = 0; j < answers.get(i).length(); j++) {
							table[coords.get(i + i) - j][coords.get(i + i + 1) + j] = String.valueOf(answers.get(i).charAt(j));
						}
						answers.remove(i);
						dir.remove(i);
						coords.remove(i + i + 1);
						coords.remove(i + i);
						break;
					case "S":
						for (int j = 0; j < answers.get(i).length(); j++) {
							table[coords.get(i + i)][coords.get(i + i + 1) + j] = String.valueOf(answers.get(i).charAt(j));
						}
						answers.remove(i);
						dir.remove(i);
						coords.remove(i + i + 1);
						coords.remove(i + i);
						break;
					case "SE":
						for (int j = 0; j < answers.get(i).length(); j++) {
							table[coords.get(i + i) + j][coords.get(i + i + 1) + j] = String.valueOf(answers.get(i).charAt(j));
						}
						answers.remove(i);
						dir.remove(i);
						coords.remove(i + i + 1);
						coords.remove(i + i);
						break;
					case "E":
						for (int j = 0; j < answers.get(i).length(); j++) {
							table[coords.get(i + i) + j][coords.get(i + i + 1)] = String.valueOf(answers.get(i).charAt(j));
						}
						answers.remove(i);
						dir.remove(i);
						coords.remove(i + i  + 1);
						coords.remove(i + i);
						break;
					case "NE":
						for (int j = 0; j < answers.get(i).length(); j++) {
							table[coords.get(i + i) + j][coords.get(i + i + 1) - j] = String.valueOf(answers.get(i).charAt(j));
						}
						answers.remove(i);
						dir.remove(i);
						coords.remove(i + i + 1);
						coords.remove(i + i);
						break;
					}
					count--;
					System.out.println("Found!");
					flag = true;
				}
			}
			if (!flag) {
				System.out.println("Doesn't Exist!");
			}
		}
		print();
		System.out.println("You Found Them All!");
	}
}
