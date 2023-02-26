import java.util.Random;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random ra = new Random();
		int userLoc, pcLoc;
		boolean winner;
		System.out.println("1|2|3\n�X+�X+�X\n4|5|6\n�X+�X+�X\n7|8|9");
		int order;
		String again;
		order = ra.nextInt(2);
		while (true) {
			char[][] board = { { ' ', '|', ' ', '|', ' ' }, { '�X', '+', '�X', '+', '�X' }, { ' ', '|', ' ', '|', ' ' },
					{ '�X', '+', '�X', '+', '�X' }, { ' ', '|', ' ', '|', ' ' } };
			int[] record = new int[9];
			if (order == 0) {
				System.out.println("�A��");
				while (true) {
					System.out.println("=============");
					userLoc = userInput(record);
					recordMark(board, record, "user", userLoc);
					boardMark(board, record, "user", userLoc);
					printBoard(board);
					winner = getWinner(record);
					if (!winner) {
						System.out.print("\n");
						break;
					}
					System.out.println("�q����J:");
					pcLoc = pcInput(record);
					recordMark(board, record, "pc", pcLoc);
					boardMark(board, record, "pc", pcLoc);
					printBoard(board);
					winner = getWinner(record);
					if (!winner) {
						System.out.print("\n");
						break;
					}
				}
			} else {
				System.out.println("�ڥ�");
				while (true) {
					System.out.println("=============");
					System.out.println("�q����J:");
					pcLoc = pcInput(record);
					recordMark(board, record, "pc", pcLoc);
					boardMark(board, record, "pc", pcLoc);
					printBoard(board);
					winner = getWinner(record);
					if (!winner) {
						System.out.print("\n");
						break;
					}
					userLoc = userInput(record);
					recordMark(board, record, "user", userLoc);
					boardMark(board, record, "user", userLoc);
					printBoard(board);
					winner = getWinner(record);
					if (!winner) {
						System.out.print("\n");
						break;
					}
				}
			}
			System.out.println("��C���s�}�l�A�����N�䵲��...");
			again = sc.next();
			if (again.equalsIgnoreCase("C")) {
				System.out.print("\n");
				continue;
			} else {
				break;
			}
		}
		sc.close();
	}

	public static int userInput(int[] record) {
		Scanner sc = new Scanner(System.in);
		System.out.print("��J��m(1~9):");
		int location = sc.nextInt();
		while (true) {
			if (location > 9 || location < 1) {
				System.out.println("(��m����1~9)");
				System.out.println("=============");
				System.out.print("���s��J:");
				location = sc.nextInt();
				continue;
			} else if (record[location - 1] != 0) {
				System.out.println("(�L�k���ƿ��)");
				System.out.println("=============");
				System.out.print("���s��J:");
				location = sc.nextInt();
				continue;
			} else {
				return location;
			}
		}
	}

	public static int pcInput(int[] record) {
		Random ra = new Random();
		int location;
		while (true) {
			location = ra.nextInt(9) + 1;
			if (location > 9 || location < 1) {
				continue;
			} else if (record[location - 1] != 0) {
				continue;
			} else {
				return location;
			}
		}
	}

	public static boolean getWinner(int[] record) {
		if (record[0] == record[1] && record[0] == record[2] && record[0] != 0) {
			if (record[0] == 1)
				System.out.println("\n" + "�AĹ�F");
			else
				System.out.println("\n" + "�A��F");
			return false;
		} else if (record[3] == record[4] && record[3] == record[5] && record[3] != 0) {
			if (record[3] == 1)
				System.out.println("\n" + "�AĹ�F");
			else
				System.out.println("\n" + "�A��F");
			return false;
		} else if (record[6] == record[7] && record[6] == record[8] && record[6] != 0) {
			if (record[6] == 1)
				System.out.println("\n" + "�AĹ�F");
			else
				System.out.println("\n" + "�A��F");
			return false;
		} else if (record[0] == record[3] && record[0] == record[6] && record[0] != 0) {
			if (record[0] == 1)
				System.out.println("\n" + "�AĹ�F");
			else
				System.out.println("\n" + "�A��F");
			return false;
		} else if (record[1] == record[4] && record[1] == record[7] && record[1] != 0) {
			if (record[1] == 1)
				System.out.println("\n" + "�AĹ�F");
			else
				System.out.println("\n" + "�A��F");
			return false;
		} else if (record[2] == record[5] && record[2] == record[8] && record[2] != 0) {
			if (record[2] == 1)
				System.out.println("\n" + "�AĹ�F");
			else
				System.out.println("\n" + "�A��F");
			return false;
		} else if (record[0] == record[4] && record[0] == record[8] && record[0] != 0) {
			if (record[0] == 1)
				System.out.println("\n" + "�AĹ�F");
			else
				System.out.println("\n" + "�A��F");
			return false;
		} else if (record[2] == record[4] && record[2] == record[6] && record[2] != 0) {
			if (record[2] == 1)
				System.out.println("\n" + "�AĹ�F");
			else
				System.out.println("\n" + "�A��F");
			return false;
		}
		for (int r : record) {
			if (r == 0)
				return true;
		}
		System.out.println("\n" + "����");
		return false;
	}

	public static int[] recordMark(char[][] board, int[] record, String player, int location) {
		int recordMark = 1;
		if (player.equals("user")) {
			recordMark = 1;
		} else if (player.equals("pc")) {
			recordMark = 2;
		}
		switch (location) {
			case 1:
				record[0] = recordMark;
				break;
			case 2:
				record[1] = recordMark;
				break;
			case 3:
				record[2] = recordMark;
				break;
			case 4:
				record[3] = recordMark;
				break;
			case 5:
				record[4] = recordMark;
				break;
			case 6:
				record[5] = recordMark;
				break;
			case 7:
				record[6] = recordMark;
				break;
			case 8:
				record[7] = recordMark;
				break;
			case 9:
				record[8] = recordMark;
				break;
		}
		return record;
	}

	public static char[][] boardMark(char[][] board, int[] record, String player, int location) {
		char boardMark = 'O';
		if (player.equals("user")) {
			boardMark = 'O';
		} else if (player.equals("pc")) {
			boardMark = 'X';
		}
		switch (location) {
			case 1:
				board[0][0] = boardMark;
				break;
			case 2:
				board[0][2] = boardMark;
				break;
			case 3:
				board[0][4] = boardMark;
				break;
			case 4:
				board[2][0] = boardMark;
				break;
			case 5:
				board[2][2] = boardMark;
				break;
			case 6:
				board[2][4] = boardMark;
				break;
			case 7:
				board[4][0] = boardMark;
				break;
			case 8:
				board[4][2] = boardMark;
				break;
			case 9:
				board[4][4] = boardMark;
				break;
		}
		return board;
	}

	public static void printBoard(char[][] board) {
		for (char[] a : board) {
			for (char b : a) {
				System.out.print(b);
			}
			System.out.print("\n");
		}
	}

}
