import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws InterruptedException {
		int option = 0;
		String name;
		int startingPosition;
		String gameFile;

		Scanner in = new Scanner(System.in);

		System.out.println();
		System.out.println("		          Othello The Game");
		System.out.println("		          ================\n");
		System.out.println("1. Quit");
		System.out.println("2. Start a New Game");
		System.out.println("3. Load a Game");
		System.out.println("");
		System.out.print("Option: ");

		while (true) {
			if (in.hasNextInt()) {
				option = in.nextInt();
				if (option == 1 || option == 2 || option == 3) {
					if (option == 1) {
						System.out.println("		  Thank you for playing Othello The Game\n");
						System.exit(0);
					} else if (option == 2) {
						System.out.println("\n		       Starting a new game...");

						System.out.print("\nEnter <Player 1> (Black) name: ");
						name = in.nextLine();

						System.out.print("");
						name = in.nextLine();
						Player p1 = new Player(name, 'B');

						System.out.print("Enter <Player 2> (White) name: ");
						name = in.nextLine();
						Player p2 = new Player(name, 'W');

						Player current = p1;

						System.out.println("\n		  Creating players, please wait...");
						Thread.sleep(2000);

						System.out.println("\n		    Choose a starting position:\n");
						System.out.println("1. Four-by-Four Starting Position");
						System.out.println("2. Standard Starting Positions");
						System.out.print("\nOption: ");

						while (true) {
							if (in.hasNextInt()) {
								startingPosition = in.nextInt();
								if (startingPosition == 1 || startingPosition == 2) {
									System.out.println("\n		   Drawing board, please wait...");
									Thread.sleep(2000);

									Game game = new Game(p1, p2, current, startingPosition);
									game.start();
									break;
								} else {
									System.out.println(
											"\n	Invalid option. Please choose a starting position 1 or 2\n");
									System.out.println("1. Four-by-Four Starting Position");
									System.out.println("2. Standard Starting Positions");
									System.out.print("\nOption: ");
								}
							} else {
								System.out.println("\n		Invalid input. Please enter an integer\n");
								System.out.println("1. Four-by-Four Starting Position");
								System.out.println("2. Standard Starting Positions");
								System.out.print("\nOption: ");
							}
							in.nextLine();
						}
						break;
					} else if (option == 3) {
						System.out.println("		      Loading an existing game...");
						System.out.print("\nEnter the file name of the game: ");
						gameFile = in.next();
						if (gameFile.contains(".txt") == false) {
							gameFile = gameFile + ".txt";
						}
						try {
							FileReader file = new FileReader(gameFile);
							BufferedReader reader = new BufferedReader(file);
							Player p1 = new Player(reader.readLine(), 'B');
							Player p2 = new Player(reader.readLine(), 'W');
							Player current = p1;
							String compare = reader.readLine();
							String squares;
							if (compare.equalsIgnoreCase(p2.name)) {
								current = p2;
							}
							squares = reader.readLine();
							Game game = new Game(p1, p2, current, squares);
							System.out.println("\n		   Creating players, please wait...");
							Thread.sleep(2000);

							System.out.println("\n		          Drawing board...");
							Thread.sleep(2000);
							game.start();
						} catch (FileNotFoundException e) {
							System.out.print("\n	  File was not found. Please restart the game and try again\n\n");
						} catch (IOException e) {
							System.out.print("\n		File exists. However couldn't read its content\n\n");
						}
						break;
					}
				} else {
					System.out.println("\n	    Invalid option. Please choose option 1, 2 or 3\n");
					System.out.println("1. Quit");
					System.out.println("2. Start a New Game");
					System.out.println("3. Load a Game");
					System.out.println("");
					System.out.print("Option: ");
				}
			} else {
				System.out.println("\n		Invalid input. Please enter an integer\n");
				System.out.println("1. Quit");
				System.out.println("2. Start a New Game");
				System.out.println("3. Load a Game");
				System.out.println("");
				System.out.print("Option: ");
			}
			in.nextLine();
		}
		in.close();
	}
}
