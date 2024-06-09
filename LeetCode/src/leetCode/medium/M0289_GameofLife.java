package leetCode.medium;

public class M0289_GameofLife {
	public static void printBoard(int[][] board) {
		System.out.println("-------");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int[] col = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static int[] row = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

	// solution 1: brute-force
	public static void gameOfLife1(int[][] board) {
		int m = board[0].length;
		int n = board.length;

		int numOfLive = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				numOfLive = 0;
				for (int k = 0; k < 8; k++) {
					int y = i + col[k];
					int x = j + row[k];
					if (y >= 0 && y < n && x >= 0 && x < m) {
						if (board[y][x] == 1 || board[y][x] == 2) {
							numOfLive++;
						}
					}
				}

				if (board[i][j] == 1 && numOfLive < 2) {
					board[i][j] = 2; // 1->0的情況，mark成2
				} else if (board[i][j] == 1 && numOfLive > 3) {
					board[i][j] = 2;
				} else if (board[i][j] == 0 && numOfLive == 3) {
					board[i][j] = 3;// 0->1的情況，mark成3
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 2) {
					board[i][j] = 0;
				} else if (board[i][j] == 3) {
					board[i][j] = 1;
				}
			}
		}
	}

	// solution 2
	public static void gameOfLife2(int[][] board) {
		if (board == null || board.length == 0) {
			return;
		}

		int m = board.length;
		int n = board[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int lives = liveNeighbors(board, m, n, i, j);

				// In the beginning, every 2nd bit is 0;
				// So we only need to care about when will the 2nd bit become 1.
				if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
					board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
				}
				if (board[i][j] == 0 && lives == 3) {
					board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] >>= 1; // Get the 2nd state.
			}
		}
	}

	public static int liveNeighbors(int[][] board, int m, int n, int i, int j) {
		int lives = 0;
		for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
			for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
				lives += board[x][y] & 1;
			}
		}
		lives -= board[i][j] & 1;
		return lives;
	}

	public static void main(String[] args) {
		int[][] board;
		board = new int[][] { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
		gameOfLife1(board);
		printBoard(board);

		board = new int[][] { { 1, 1 }, { 1, 0 } };
		gameOfLife1(board);
		printBoard(board);
	}
}
