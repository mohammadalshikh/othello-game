import java.util.HashMap;

public class Board {
	char[] square = new char[64];
	HashMap<String, Integer> position = new HashMap<>();
	Player current;
	Player p1;
	Player p2;

	// new game
	public Board(int startingPosition) {
		String alphabets = "abcdefgh";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				String key = alphabets.charAt(j) + String.valueOf(i + 1);
				int value = i * 8 + j;
				position.put(key, value);
			}
		}
		if (startingPosition == 1) {
			drawBoard(1);
		} else if (startingPosition == 2) {
			drawBoard(2);
		}
	}

	// loading game
	public Board(String squares) {
		String alphabets = "abcdefgh";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				String key = alphabets.charAt(j) + String.valueOf(i + 1);
				int value = i * 8 + j;
				position.put(key, value);
			}
		}
		for (int i = 0; i < squares.length(); i++) {
			square[i] = squares.charAt(i);
		}
	}

	private void drawBoard(int startingPosition) {
		if (startingPosition == 1) {
			for (int i = 0; i < 64; i++) {
				this.square[i] = '□';
			}
			this.square[0] = '*';
			this.square[7] = '*';
			this.square[56] = '*';
			this.square[63] = '*';
			this.square[18] = 'W';
			this.square[19] = 'W';
			this.square[26] = 'W';
			this.square[27] = 'W';
			this.square[36] = 'W';
			this.square[37] = 'W';
			this.square[44] = 'W';
			this.square[45] = 'W';
			this.square[20] = 'B';
			this.square[21] = 'B';
			this.square[28] = 'B';
			this.square[29] = 'B';
			this.square[34] = 'B';
			this.square[35] = 'B';
			this.square[42] = 'B';
			this.square[43] = 'B';
		} else if (startingPosition == 2) {
			for (int i = 0; i < 64; i++) {
				this.square[i] = '□';
			}
			this.square[0] = '*';
			this.square[7] = '*';
			this.square[56] = '*';
			this.square[63] = '*';
			this.square[27] = 'W';
			this.square[36] = 'W';
			this.square[28] = 'B';
			this.square[35] = 'B';
		}
	}

	public void print() {
		int W = 0;
		int B = 0;
		for (int i = 0; i < square.length; i++) {
			if (square[i] == 'W') {
				W++;
			} else if (square[i] == 'B') {
				B++;
			}
		}
		System.out.println();
		System.out.println("			     Black | " + B);
		System.out.println("			     White | " + W);
		System.out.println();
		System.out.print("  			  ");
		for (int i = 0; i < 8; i++) {
			System.out.print((char) ('A' + i) + " ");
		}
		System.out.println();
		for (int i = 0; i < 8; i++) {
			System.out.print("			" + (i + 1) + " ");
			for (int j = 0; j < 8; j++) {
				System.out.print(square[i * 8 + j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
