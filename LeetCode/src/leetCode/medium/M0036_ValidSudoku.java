package leetCode.medium;

import java.util.HashSet;
import java.util.Set;

public class M0036_ValidSudoku {
	// solution 1: brute-force with hash set
	public static boolean isValidSudoku1(char[][] board) {
		Set<Character> set = new HashSet<>();

		// check rows
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					continue;
				} else if (set.contains(board[i][j])) {
					return false;
				}
				set.add(board[i][j]);
			}
			set.clear();
		}

		// check columns
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[j][i] == '.') {
					continue;
				} else if (set.contains(board[j][i])) {
					return false;
				}
				set.add(board[j][i]);
			}
			set.clear();
		}

		// check 3*3 boards
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 3; n++) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (board[m * 3 + i][n * 3 + j] == '.') {
							continue;
						} else if (set.contains(board[m * 3 + i][n * 3 + j])) {
							return false;
						}
						set.add(board[m * 3 + i][n * 3 + j]);
					}
				}
				set.clear();
			}
		}

		return true;
	}

	// solution 2
	public static boolean isValidSudoku2(char[][] board) {
		Set<String> seen = new HashSet<>();
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				char number = board[i][j];
				if (number != '.')
					if (!seen.add(number + " in row " + i) || !seen.add(number + " in column " + j)
							|| !seen.add(number + " in block " + i / 3 + "-" + j / 3))
						return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(isValidSudoku1(new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } }));
		System.out.println(isValidSudoku1(new char[][] { { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } }));
	}
}
