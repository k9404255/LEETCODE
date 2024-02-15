package leetCode.medium;

public class M0064_MinimumPathSum {
	// solution 1: recursion (Time Limit Exceeded)
	public static int minPathSum1(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		return helper1(grid, m - 1, n - 1);
	}

	public static int helper1(int[][] grid, int m, int n) {
		if (m == 0 && n == 0) {
			return grid[0][0];
		} else if (m == 0) {
			return helper1(grid, m, n - 1) + grid[m][n];
		} else if (n == 0) {
			return helper1(grid, m - 1, n) + grid[m][n];
		}

		return Math.min(helper1(grid, m - 1, n), helper1(grid, m, n - 1)) + grid[m][n];
	}

	// solution 2: memorization
	public static int minPathSum2(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		return helper2(grid, m - 1, n - 1, new int[m][n]);
	}

	public static int helper2(int[][] grid, int m, int n, int[][] dp) {
		if (dp[m][n] > 0) {
			return dp[m][n];
		}

		int min = 0;
		if (m == 0 && n == 0) {
			min = grid[0][0];
		} else if (m == 0) {
			min = helper2(grid, m, n - 1, dp) + grid[m][n];
		} else if (n == 0) {
			min = helper2(grid, m - 1, n, dp) + grid[m][n];
		} else {
			min = Math.min(helper2(grid, m - 1, n, dp), helper2(grid, m, n - 1, dp)) + grid[m][n];
		}

		dp[m][n] = min;

		return min;
	}

	// solution 3: dp
	public static int minPathSum3(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int curVal = grid[i][j];

				if (i == 0 && j == 0) {
					dp[i][j] = curVal;
				} else if (i == 0) {
					dp[i][j] = dp[i][j - 1] + curVal;
				} else if (j == 0) {
					dp[i][j] = dp[i - 1][j] + curVal;
				} else {
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + curVal;
				}
			}
		}

		return dp[m - 1][n - 1];
	}

	// solution 4: dp (in-place)
	public static int minPathSum4(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		for (int i = 1; i < m; i++) {
			grid[i][0] += grid[i - 1][0];
		}

		for (int j = 1; j < n; j++) {
			grid[0][j] += grid[0][j - 1];
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
			}
		}

		return grid[m - 1][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(minPathSum1(new int[][] { { 1, 1 }, { 2, 1 } }));
		System.out.println(minPathSum1(new int[][] { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } }));
		System.out.println(minPathSum1(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } }));

		System.out.println(minPathSum2(new int[][] { { 1, 1 }, { 2, 1 } }));
		System.out.println(minPathSum2(new int[][] { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } }));
		System.out.println(minPathSum2(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } }));

		System.out.println(minPathSum3(new int[][] { { 1, 1 }, { 2, 1 } }));
		System.out.println(minPathSum3(new int[][] { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } }));
		System.out.println(minPathSum3(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } }));

		System.out.println(minPathSum4(new int[][] { { 1, 1 }, { 2, 1 } }));
		System.out.println(minPathSum4(new int[][] { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } }));
		System.out.println(minPathSum4(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } }));
	}
}