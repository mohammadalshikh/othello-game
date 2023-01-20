import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
	private Player first;
	private Player second;
	private Player current;
	private static String move;
	private static int option;
	private Board board;

	// new game
	public Game(Player p1, Player p2, Player current, int startingPosition) {
		this.first = p1;
		this.second = p2;
		this.current = current;
		this.board = new Board(startingPosition);
	}

	// loading game
	public Game(Player p1, Player p2, Player current, String squares) {
		this.first = p1;
		this.second = p2;
		this.current = current;
		this.board = new Board(squares);
	}

	static Scanner in = new Scanner(System.in);

	public void start() {
		board.print();
		options();
	}

	private void options() {
		System.out.println("		      It's <" + current.name + "> (" + current.color + ") turn\n");
		System.out.println("1. Make a move		2. Save		3. Forfeit		4. Save game and quit");
		System.out.print("\nOption: ");
		while (true) {
			if (in.hasNextInt()) {
				option = in.nextInt();
				if (option >= 1 && option <= 4) {
					if (option == 1) {
						play();
					} else if (option == 2) {
						save();
					} else if (option == 3) {
						forfeit();
					} else if (option == 4) {
						saveGame();
					}
					break;
				} else {
					board.print();
					System.out.println("	  Invalid option. Please choose option 1, 2, 3 or 4\n");
					System.out.println("		      It's <" + current.name + "> (" + current.color + ") turn\n");
					System.out.println("1. Make a move		2. Save		3. Forfeit		4. Save game and quit");
					System.out.print("\nOption: ");
				}
			} else {
				board.print();
				System.out.println("		Invalid input. Please enter an integer\n");
				System.out.println("		      It's <" + current.name + "> (" + current.color + ") turn\n");
				System.out.println("1. Make a move		2. Save		3. Forfeit		4. Save game and quit");
				System.out.print("\nOption: ");
				in.next();
			}
		}
	}

	private void play() {
		char opposite = 'W';
		if (current == first)
			opposite = second.color;
		else if (current == second)
			opposite = first.color;

		System.out.print("\nMake a move by entering column letter (A-H) "
				+ "followed by row number (1-8): ");
		move = in.next();
		move = move.toLowerCase();

		while (board.position.containsKey(move.toLowerCase()) == false) {
			board.print();
			System.out.println("	Position does not exist. Please enter a valid position\n");
			System.out.print("\nMake a move by entering column letter (A-H) "
					+ "followed by row number (1-8): ");
			move = in.next();
			move = move.toLowerCase();
		}
		int index = board.position.get(move);
		int remaining;
		if (board.square[index] == '□') {
			boolean captured = false;
			// case 1: capture to left
			if (board.square[index - 1] == opposite && index % 8 > 1) {
				remaining = index % 8 + 1;
				for (int i = 2; i < remaining; i++) {
					if (board.square[index - i] == current.color) {
						captured = true;
						for (int j = index - 1; j > index - i; j--) {
							board.square[j] = current.color;
						}
						break;
					} else if (board.square[index - i] == opposite) {
						continue;
					} else {
						break;
					}
				}
			}
			// case 2: capture to right
			if (board.square[index + 1] == opposite && index % 8 < 6) {
				remaining = 8 - (index % 8);
				for (int i = 2; i < remaining; i++) {
					if (board.square[index + i] == current.color) {
						captured = true;
						for (int j = index + 1; j < index + i; j++) {
							board.square[j] = current.color;
						}
						break;
					} else if (board.square[index + i] == opposite) {
						continue;
					} else {
						break;
					}
				}
			}
			// case 3: capture to top
			if (index - 8 > 7) {
				if (board.square[index - 8] == opposite) {
					remaining = index - (index % 8 - 1);
					for (int i = 16; i < remaining; i = i + 8) {
						if (board.square[index - i] == current.color) {
							captured = true;
							for (int j = index - 8; j > index - i; j = j - 8) {
								board.square[j] = current.color;
							}
							break;
						} else if (board.square[index - i] == opposite) {
							continue;
						} else {
							break;
						}
					}
				}
			}
			// case 4: capture to bottom
			if (index + 8 < 56) {
				if (board.square[index + 8] == opposite) {
					remaining = index % 8 + (63 - index);
					for (int i = 16; i < remaining; i = i + 8) {
						if (board.square[index + i] == current.color) {
							captured = true;
							for (int j = index + 8; j < index + i; j = j + 8) {
								board.square[j] = current.color;
							}
							break;
						} else if (board.square[index + i] == opposite) {
							continue;
						} else {
							break;
						}
					}
				}
			}
			// case 5: capture to top-left
			if (index - 9 > 8 && index % 8 > 1) {
				if (board.square[index - 9] == opposite) {
					for (int i = 18; i < 64; i = i + 9) {
						if (index - i >= 0) {
							if (board.square[index - i] == current.color) {
								captured = true;
								for (int j = index - 9; j > index - i; j = j - 9) {
									board.square[j] = current.color;
								}
								break;
							} else if (board.square[index - i] == opposite) {
								continue;
							} else {
								break;
							}
						} else {
							break;
						}
					}
				}
			}
			// case 6: capture to top-right
			if (index - 7 > 6 && index % 8 < 6) {
				if (board.square[index - 7] == opposite) {
					for (int i = 14; i < 50; i = i + 7) {
						if (index - i >= 0) {
							if (board.square[index - i] == current.color) {
								captured = true;
								for (int j = index - 7; j > index - i; j = j - 7) {
									board.square[j] = current.color;
								}
								break;
							} else if (board.square[index - i] == opposite) {
								continue;
							} else {
								break;
							}
						} else {
							break;
						}
					}
				}
			}
			// case 7: capture to bottom-left
			if (index + 7 < 55 && index % 8 > 1) {
				if (board.square[index + 7] == opposite) {
					for (int i = 14; i < 50; i = i + 7) {
						if (index + i <= 63) {
							if (board.square[index + i] == current.color) {
								captured = true;
								for (int j = index + 7; j < index + i; j = j + 7) {
									board.square[j] = current.color;
								}
								break;
							} else if (board.square[index + i] == opposite) {
								continue;
							} else {
								break;
							}
						} else {
							break;
						}
					}
				}
			}
			// case 8: capture to bottom-right:
			if (index + 9 < 55 && index % 8 < 6) {
				if (board.square[index + 9] == opposite) {
					for (int i = 18; i < 64; i = i + 9) {
						if (index + i <= 63) {
							if (board.square[index + i] == current.color) {
								captured = true;
								for (int j = index + 9; j < index + i; j = j + 9) {
									board.square[j] = current.color;
								}
								break;
							} else if (board.square[index + i] == opposite) {
								continue;
							} else {
								break;
							}
						} else {
							break;
						}
					}
				}
			}
			if (captured) {
				board.square[index] = current.color;
				board.print();
				System.out.println("		      <" + current.name + "> played at " + move + "\n");
				takeTurn();
				options();
			} else {
				board.print();
				System.out.println(" 	  You won't capture any pieces playing at that position."
						+ "\n 		Please make another move or save\n");
				options();
			}
		} else {
			board.print();
			System.out.println("	You can't play at that position. Please make another move or save\n");
			options();
		}
	}

	private void save() {
		board.print();
		System.out.println("		      <" + current.name + "> (" + current.color + ") chose to save.\n");
		takeTurn();
		options();
	}

	private void forfeit() {
		if (current == first) {
			System.out.println(
					"\n		<" + first.name + "> " + "(Black) has forfeited. <" + second.name + "> "
							+ "(White) has won!\n");
		} else {
			System.out.println("\n		<" + second.name + "> " + "(White) has forfeited. <" + first.name + "> "
					+ "(Black) has won!\n");
		}
	}

	private void saveGame() {
		System.out.print("\nEnter a file name to save the game: ");
		String gameFile = in.next();
		while (gameFile.contains(".") || gameFile.contains(":") || gameFile.contains("/") || gameFile.contains("?")
				|| gameFile.contains("\"") || gameFile.contains("<") || gameFile.contains(">") || gameFile.contains("|")
				|| gameFile.contains("/")) {
			System.out.println(
					"\nInvalid file name, please enter a valid one that does not contain: /, :, *, ., ?, \", <, >, or |");
			System.out.print("\nEnter a file name to save the game: ");
			gameFile = in.next();
		}
		gameFile = gameFile + ".txt";
		try {
			FileWriter writer = new FileWriter(gameFile);
			writer.append(first.name + "\n");
			writer.append(second.name + "\n");
			writer.append(current.name + "\n");
			for (int i = 0; i < board.square.length; i++) {
				writer.append(board.square[i]);
			}
			writer.close();
			System.out.println(
					"\n		  Game saved in: \"" + gameFile + "\". Thank you for playing Othello The Game\n");
			System.exit(0);
		} catch (IOException e) {
		}
	}

	public void takeTurn() {
		if (this.current == this.first) {
			current = second;
		} else if (current == second) {
			current = first;
		}
	}
}
