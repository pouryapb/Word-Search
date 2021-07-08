package pouryapb.wordSearch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Iterator;

class HideWord extends RunProgram{
	
	private Iterator<String> words = inputs.iterator();
	private RandGenerator table = new RandGenerator();
	// [which word][first letter coordinates, [0] for x, [1] for y]
	private int[][] coordinates = new int[5][2];
	private String[] way = new String[5];
	private int i = 0;
	
	// prints table
	protected void printTable() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(table.table[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// places words in table
	protected void placeWords() {
		while (words.hasNext()) {
			
			coordinates[i][0] = rand.nextInt(10);
			coordinates[i][1] = rand.nextInt(10);
			
			while (!chechCondition(i, "NONE")) {
				coordinates[i][0] = rand.nextInt(10);
				coordinates[i][1] = rand.nextInt(10);
			}
			
			String where = whereTo();
			
			while (!chechCondition(i, where)) {
				where = whereTo();
			}
			
			way[i] = where;
			
			String word = words.next();
			
			switch (where) {
			case "N":
				for (int i = 0; i < lengths.get(this.i); i++) {
					table.table[coordinates[this.i][0]][coordinates[this.i][1] - i] = String.valueOf(word.charAt(i));
				}
				break;
			case "NW":
				for (int i = 0; i < lengths.get(this.i); i++) {
					table.table[coordinates[this.i][0] - i][coordinates[this.i][1] - i] = String.valueOf(word.charAt(i));
				}
				break;
			case "W":
				for (int i = 0; i < lengths.get(this.i); i++) {
					table.table[coordinates[this.i][0] - i][coordinates[this.i][1]] = String.valueOf(word.charAt(i));
				}
				break;
			case "SW":
				for (int i = 0; i < lengths.get(this.i); i++) {
					table.table[coordinates[this.i][0] - i][coordinates[this.i][1] + i] = String.valueOf(word.charAt(i));
				}
				break;
			case "S":
				for (int i = 0; i < lengths.get(this.i); i++) {
					table.table[coordinates[this.i][0]][coordinates[this.i][1] + i] = String.valueOf(word.charAt(i));
				}
				break;
			case "SE":
				for (int i = 0; i < lengths.get(this.i); i++) {
					table.table[coordinates[this.i][0] + i][coordinates[this.i][1] + i] = String.valueOf(word.charAt(i));
				}
				break;
			case "E":
				for (int i = 0; i < lengths.get(this.i); i++) {
					table.table[coordinates[this.i][0] + i][coordinates[this.i][1]] = String.valueOf(word.charAt(i));
				}
				break;
			case "NE":
				for (int i = 0; i < lengths.get(this.i); i++) {
					table.table[coordinates[this.i][0] + i][coordinates[this.i][1] - i] = String.valueOf(word.charAt(i));
				}
				break;
			}
			i++;
		}
	}
	
	// randomly picks a direction
	private String whereTo() {
		String[] options = {"N", "NW", "W", "SW", "S", "SE", "E", "NE"};
		return options[rand.nextInt(8)];
	}
	
	// checks if a word have conditions to fit in table?!
	private boolean chechCondition(int p, String where) {
		boolean flag = false;
		
		switch (where) {
		case "NONE":
			if (table.table[coordinates[p][0]][coordinates[p][1]].equals("0")) {
				flag = true;
			}
			else {
				flag = false;
			}
			break;
		case "N":
			try {
				for (int i = coordinates[p][1]; i > coordinates[p][1] - lengths.get(p); i--) {
					if (table.table[coordinates[p][0]][i].equals("0")) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
			}
			catch (ArrayIndexOutOfBoundsException e) {
				flag = false;
			}
			break;
		case "NW":
			try {
				for (int i = 0; i < lengths.get(p); i++) {
					if (table.table[coordinates[p][0] - i][coordinates[p][1] - i].equals("0")) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				flag = false;
			}
			break;
		case "W":
			try {
				for (int i = 0; i < lengths.get(p); i++) {
					if (table.table[coordinates[p][0] - i][coordinates[p][1]].equals("0")) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				flag = false;
			}
			break;
		case "SW":
			try {
				for (int i = 0; i < lengths.get(p); i++) {
					if (table.table[coordinates[p][0] - i][coordinates[p][1] + i].equals("0")) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				flag = false;
			}
			break;
		case "S":
			try {
				for (int i = 0; i < lengths.get(p); i++) {
					if (table.table[coordinates[p][0]][coordinates[p][1] + i].equals("0")) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				flag = false;
			}
			break;
		case "SE":
			try {
				for (int i = 0; i < lengths.get(p); i++) {
					if (table.table[coordinates[p][0] + i][coordinates[p][1] + i].equals("0")) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				flag = false;
			}
			break;
		case "E":
			try {
				for (int i = 0; i < lengths.get(p); i++) {
					if (table.table[coordinates[p][0] + i][coordinates[p][1]].equals("0")) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				flag = false;
			}
			break;
		case "NE":
			try {
				for (int i = 0; i < lengths.get(p); i++) {
					if (table.table[coordinates[p][0] + i][coordinates[p][1] - i].equals("0")) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				flag = false;
			}
			break;
		}
		return flag;
	}
	
	// makes other parts of table randomly filled
	public void generate() {
		table.generate();
	}
	
	// saving table in a file
	public void save() {
		
		File record = new File(".\\record.txt");
		File keys = new File (".\\keys.txt");
		File coordinates = new File(".\\coords.txt");
		
		String temp;
		try {
			
			FileWriter fwRecord = new FileWriter(record, true);
			Formatter fRecord = new Formatter(fwRecord);
			
			// saving tables
			for (int i = 0; i < 10; i++) {
				temp = table.table[i][0];
				for (int j = 1; j < 10; j++) {
					temp += " " + table.table[i][j];
				}
				fRecord.format(temp + "\r\n");
				if (i == 9) {
					fRecord.format("\r\n");
				}
			}
			
			FileWriter fwKeys = new FileWriter(keys, true);
			Formatter fKeys = new Formatter(fwKeys);
			
			// saving keys
			for (int i = 0; i < inputs.size(); i++) {
				fKeys.format(inputs.get(i));
				if (i != inputs.size() - 1)
					fKeys.format(" ");
			}
			fKeys.format("\r\n");
			
			FileWriter fwCoordinates = new FileWriter(coordinates, true);
			Formatter fCoordinates = new Formatter(fwCoordinates);
			
			// saving coordinates and ways
			for (int i = 0; i < this.i; i++) {
				fCoordinates.format(this.coordinates[i][0] + " " + this.coordinates[i][1] + " " + way[i] + " ");
			}
			fCoordinates.format("\r\n");
			
			fRecord.close();
			fKeys.close();
			fCoordinates.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
