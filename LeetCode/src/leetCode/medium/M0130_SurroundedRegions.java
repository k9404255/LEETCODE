package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0130_SurroundedRegions {
	public static void printBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// solution 1
	public static void solve1(char[][] board) {
		int m = board[0].length;
		int n = board.length;

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				if (board[i][j] == 'O') {
					List<int[]> list = new ArrayList<>();
					boolean isBoarder = helper1(board, m, n, i, j, list);
					if (!isBoarder) {
						for (int[] nums : list) {
							board[nums[0]][nums[1]] = 'X';
						}
					}
				}
			}
		}

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				if (board[i][j] == 'Z') {
					board[i][j] = 'O';
				}
			}
		}

		printBoard(board);
	}

	public static int[] row = new int[] { 1, -1, 0, 0 };
	public static int[] col = new int[] { 0, 0, 1, -1 };

	public static boolean helper1(char[][] board, int m, int n, int i, int j, List<int[]> list) {
		if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
			return true;
		}

		board[i][j] = 'Z';
		list.add(new int[] { i, j });

		boolean isBoarder = false;
		for (int k = 0; k < 4; k++) {
			int x = i + row[k];
			int y = j + col[k];
			if ((x >= 0 && x < n) && (y >= 0 && y < m) && board[x][y] == 'O') {
				if (helper1(board, m, n, x, y, list)) {
					isBoarder = true;
				}
			}
		}

		return isBoarder;
	}

	static int m = 0;
	static int n = 0;

	// solution 2
	// https://leetcode.com/problems/surrounded-regions/
	public static void solve2(char[][] board) {
		m = board[0].length;
		n = board.length;

		// Mark 'O' cells on the borders and initiate DFS from them
		for (int i = 0; i < n; i++) {
			if (board[i][0] == 'O') {
				dfs(board, i, 0);
			}
			if (board[i][m - 1] == 'O') {
				dfs(board, i, m - 1);
			}
		}
		for (int j = 0; j < m; j++) {
			if (board[0][j] == 'O') {
				dfs(board, 0, j);
			}
			if (board[n - 1][j] == 'O') {
				dfs(board, n - 1, j);
			}
		}
		
		printBoard(board);

		// Convert remaining 'O' cells to 'X', and revert marked cells back to 'O'
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				} else if (board[i][j] == '1') {
					board[i][j] = 'O';
				}
			}
		}

		printBoard(board);
	}

	private static void dfs(char[][] board, int i, int j) {
		// Out of bounds check
		if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] != 'O') {
			return;
		}

		// Mark current 'O' cell as visited
		board[i][j] = '1';

		// DFS in all four directions
		dfs(board, i, j - 1); // left
		dfs(board, i - 1, j); // up
		dfs(board, i, j + 1); // right
		dfs(board, i + 1, j); // down
	}

	public static void main(String[] args) {
		solve1(new char[][] { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'X' } });
		solve1(new char[][] { { 'X' } });
		solve1(new char[][] { { 'O', 'O', 'O' }, { 'O', 'O', 'O' }, { 'O', 'O', 'O' } });

		solve2(new char[][] { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'X' } });
		solve2(new char[][] { { 'X' } });
		solve2(new char[][] { { 'O', 'O', 'O' }, { 'O', 'O', 'O' }, { 'O', 'O', 'O' } });
	}
}
