package leetCode.medium;

public class M0079_WordSearch {
	// solution 1: backtracking
	public static boolean exist1(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				boolean result = helper1(board, word, m, n, i, j, new boolean[m][n], new StringBuilder());
				if (result) {
					return true;
				}
			}
		}

		return false;
	}

	private static final int[] delRow = { 1, -1, 0, 0 };
	private static final int[] delCol = { 0, 0, -1, 1 };

	public static boolean helper1(char[][] board, String word, int m, int n, int i, int j, boolean[][] visit,
			StringBuilder sb) {
		String str = sb.toString();
		if (str.equals(word)) {
			return true;
		}

		// boundary check
		if (i < 0 || i >= m || j < 0 || j >= n || !str.equals(word.substring(0, str.length())) || visit[i][j] == true) {
			return false;
		}

		for (int k = 0; k < 4; k++) {
			int y = i + delRow[k];
			int x = j + delCol[k];

			sb.append(board[i][j]);
			visit[i][j] = true;
			if (helper1(board, word, m, n, y, x, visit, sb)) {
				return true;
			}
			sb.deleteCharAt(sb.length() - 1);
			visit[i][j] = false;
		}

		return false;
	}

	// solution 2
	public static boolean exist2(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0)) {
					if (helper2(board, word.toCharArray(), m, n, i, j, new boolean[m][n], 0)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean helper2(char[][] board, char[] word, int m, int n, int i, int j, boolean[][] visit,
			int curIdx) {
		if (curIdx == word.length) {
			return true;
		}

		if (i < 0 || i >= m || j < 0 || j >= n || word[curIdx] != board[i][j] || visit[i][j]) {
			return false;
		}

		visit[i][j] = true;
		for (int k = 0; k < 4; k++) {
			int y = i + delRow[k];
			int x = j + delCol[k];

			if (helper2(board, word, m, n, y, x, visit, curIdx + 1)) {
				return true;
			}
		}
		visit[i][j] = false;

		return false;
	}

	// solution 3
	public static boolean exist3(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0)) {
					if (helper3(board, word.toCharArray(), m, n, i, j, 0)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean helper3(char[][] board, char[] word, int m, int n, int i, int j, int curIdx) {
		if (curIdx == word.length) {
			return true;
		}

		if (i < 0 || i >= m || j < 0 || j >= n || word[curIdx] != board[i][j]) {
			return false;
		}

		char c = board[i][j];
		board[i][j] = '*';
//		for (int k = 0; k < 4; k++) {
//			int y = i + delRow[k];
//			int x = j + delCol[k];
//
//			if (helper3(board, word, m, n, y, x, curIdx + 1)) {
//				return true;
//			}
//		}

		// 用這種方式比用上面for迴圈速度要快
		if (helper3(board, word, m, n, i + 1, j, curIdx + 1) || helper3(board, word, m, n, i, j + 1, curIdx + 1)
				|| helper3(board, word, m, n, i - 1, j, curIdx + 1) || helper3(board, word, m, n, i, j - 1, curIdx + 1))
			return true;
		board[i][j] = c;

		return false;
	}

	public static void main(String[] args) {
		System.out.println(exist2(
				new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "ABCCED"));
		System.out.println(
				exist2(new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "SEE"));
		System.out.println(exist2(
				new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "ABCB"));
		System.out.println(exist2(new char[][] { { 'C', 'A', 'A' }, { 'A', 'A', 'A' }, { 'B', 'C', 'D' } }, "AAB"));
		System.out.println(exist2(new char[][] { { 'a', 'b' }, { 'c', 'd' } }, "cdba"));
	}
}
