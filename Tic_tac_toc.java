import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Tic_tac_toc {

	static Scanner sc = new Scanner(System.in);
	static Random ra = new Random();
	static int[] aiRecord = new int[9];

	public static void main(String[] args) {
		System.out.println("1|2|3\n—+—+—\n4|5|6\n—+—+—\n7|8|9");
		String again = "V";
		String[] players = new String[2];
		int order;
		int[] record = new int[9];
		int[] score = new int[2];
		while (again.equalsIgnoreCase("V")) {
			Arrays.fill(score, 0);
			enterName(players);
			while (true) {
				char[][] board = { { ' ', '|', ' ', '|', ' ' }, { '—', '+', '—', '+', '—' },
						{ ' ', '|', ' ', '|', ' ' }, { '—', '+', '—', '+', '—' }, { ' ', '|', ' ', '|', ' ' } };
				Arrays.fill(record, 0);
				order = ra.nextInt(2);
				// order = 0;
				if (order == 0)
					play(record, score, board, players, players[0], players[1]);
				else
					play(record, score, board, players, players[1], players[0]);
				printScore(score);
				System.out.println("輸入\sC再來一局\sV重新選擇\s任意鍵結束...");
				again = sc.next();
				// again = "c";
				if (again.equalsIgnoreCase("C")) {
					System.out.print("\n");
					continue;
				} else {
					printWinner(score, players);
					System.out.print("\n");
					break;
				}
			}
		}
	}

	public static void enterName(String[] players) {
		System.out.println("輸入玩家名稱(PC為電腦、AI為仿真人)");
		System.out.print("player1:");
		players[0] = sc.next();
		System.out.print("player2:");
		players[1] = sc.next();
		while (true) {
			if (players[1].equals(players[0])) {
				System.err.println("(名稱重複)");
				System.out.print("=============\n重新輸入:");
				players[1] = sc.next();
				continue;
			} else
				break;
		}
	}

	public static void play(int[] record, int[] score, char[][] board, String[] players, String player1,
			String player2) {
		System.out.println(player1 + "先");
		while (true) {
			System.out.println("=============");
			input(record, board, player1, 1);
			if (checkWinner(record) == 1) {
				addPoint(score, players, player1);
				System.out.println("\n" + player1 + "贏了\n");
				break;
			} else if (checkWinner(record) == -1) {
				System.out.println("\n平手");
				break;
			}
			input(record, board, player2, 2);
			if (checkWinner(record) == 2) {
				addPoint(score, players, player2);
				System.out.println("\n" + player2 + "贏了\n");
				break;
			} else if (checkWinner(record) == -1) {
				System.out.println("\n平手");
				break;
			}
		}
	}

	public static void input(int[] record, char[][] board, String player, int playerNumber) {
		int location;
		if (player.equalsIgnoreCase("pc")) {
			System.out.println(player + "輸入:");
			location = pcInput(record);
		} else if (player.equalsIgnoreCase("ai")) {
			System.out.println(player + "輸入:");
			location = aiInput(record, playerNumber);
		} else
			location = userInput(record, player);
		mark(record, board, playerNumber, location);
		printBoard(board);
	}

	public static int aiInput(int[] record, int playerNumber) {
		int aiMark = (playerNumber == 1) ? 1 : 2;
		int location = -10;
		int bestScore = Integer.MIN_VALUE;
		aiRecord = record;
		for (int i = 0; i < aiRecord.length; i++) {
			if (aiRecord[i] == 0) {
				aiRecord[i] = aiMark;
				int score = minimax(aiRecord, playerNumber, false, 9);
				aiRecord[i] = 0;
				if (score > bestScore) {
					bestScore = score;
					location = i + 1;
				}
			}
		}
		return location;
	}

	public static int minimax(int[] aiRecord, int playerNumber, boolean max, int depth) {
		int aiMark = (playerNumber == 1) ? 1 : 2;
		int userMark = (playerNumber == 1) ? 2 : 1;
		if (checkWinner(aiRecord) != 0) {
			if (checkWinner(aiRecord) == aiMark)
				return 10 * depth;
			if (checkWinner(aiRecord) == userMark)
				return -10 * depth;
			if (checkWinner(aiRecord) == -1)
				return 0;
		}
		if (max) {
			int bestScore = Integer.MIN_VALUE;
			for (int i = 0; i < aiRecord.length; i++) {
				if (aiRecord[i] == 0) {
					aiRecord[i] = aiMark;
					int score = minimax(aiRecord, playerNumber, false, depth - 1);
					aiRecord[i] = 0;
					bestScore = Math.max(score, bestScore);
				}
			}
			return bestScore;
		} else {
			int bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < aiRecord.length; i++) {
				if (aiRecord[i] == 0) {
					aiRecord[i] = userMark;
					int score = minimax(aiRecord, playerNumber, true, depth - 1);
					aiRecord[i] = 0;
					bestScore = Math.min(score, bestScore);
				}
			}
			return bestScore;
		}
	}

	public static int userInput(int[] record, String player) {
		System.out.print(player + "輸入(1~9):");
		int location = sc.nextInt();
		while (true) {
			if (location > 9 || location < 1) {
				System.err.println("(位置須為1~9)");
				System.out.print("=============\n重新輸入:");
				location = sc.nextInt();
				continue;
			} else if (record[location - 1] != 0) {
				System.err.println("(無法重複選擇)");
				System.out.print("=============\n重新輸入:");
				location = sc.nextInt();
				continue;
			} else
				return location;
		}
	}

	public static int pcInput(int[] record) {
		int location;
		while (true) {
			location = ra.nextInt(9) + 1;
			if (record[location - 1] != 0)
				continue;
			else
				return location;
		}
	}

	public static int checkWinner(int[] record) {
		for (int i = 0; i <= 6; i += 3) {
			if ((record[i] == record[i + 1] && record[i] == record[i + 2] && record[i] != 0))
				return (record[i] == 1) ? 1 : 2;
		}
		for (int j = 0; j <= 2; j += 1) {
			if ((record[j] == record[j + 3] && record[j] == record[j + 6] && record[j] != 0))
				return (record[j] == 1) ? 1 : 2;
		}
		if ((record[0] == record[4] && record[0] == record[8] && record[0] != 0)
				|| (record[2] == record[4] && record[2] == record[6] && record[2] != 0)) {
			return (record[4] == 1) ? 1 : 2;
		}
		for (int r : record) {
			if (r == 0)
				return 0;
		}
		return -1;
	}

	public static void mark(int[] record, char[][] board, int playerNumber, int location) {
		char boardMark = 'O';
		int recordMark = 1;
		if (playerNumber == 1) {
			boardMark = 'O';
			recordMark = 1;
		} else {
			boardMark = 'X';
			recordMark = 2;
		}
		switch (location) {
		case 1:
			board[0][0] = boardMark;
			record[0] = recordMark;
			break;
		case 2:
			board[0][2] = boardMark;
			record[1] = recordMark;
			break;
		case 3:
			board[0][4] = boardMark;
			record[2] = recordMark;
			break;
		case 4:
			board[2][0] = boardMark;
			record[3] = recordMark;
			break;
		case 5:
			board[2][2] = boardMark;
			record[4] = recordMark;
			break;
		case 6:
			board[2][4] = boardMark;
			record[5] = recordMark;
			break;
		case 7:
			board[4][0] = boardMark;
			record[6] = recordMark;
			break;
		case 8:
			board[4][2] = boardMark;
			record[7] = recordMark;
			break;
		case 9:
			board[4][4] = boardMark;
			record[8] = recordMark;
			break;
		}
	}

	public static void addPoint(int[] score, String[] players, String player) {
		if (player.equals(players[0]))
			score[0] += 1;
		else
			score[1] += 1;
	}

	public static void printWinner(int[] score, String[] players) {
		if (score[0] > score[1])
			System.out.println("\n最終成績:" + players[0] + "勝");
		else if (score[0] < score[1])
			System.out.println("\n最終成績:" + players[1] + "勝");
		else {
			System.out.println("\n最終成績:平手");
		}
	}

	public static void printScore(int[] score) {
		System.out.println("比數\s" + score[0] + ":" + score[1] + "\n");
	}

	public static void printBoard(char[][] board) {
		for (char[] array : board) {
			for (char c : array)
				System.out.print(c);
			System.out.print("\n");
		}
	}

}
