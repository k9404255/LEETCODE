package leetCode.medium;

public class M0062_UniquePaths {
	// solution 1: recursion (Time Limit Exceeded)
	public static int uniquePaths1(int m, int n) {
		int rightSteps = m - 1;
		int downSteps = n - 1;
		int ans[] = new int[] { 0 };

		helper1(rightSteps + downSteps, rightSteps, downSteps, 0, 0, ans);

		return ans[0];
	}

	public static void helper1(int totalSteps, int rightSteps, int downSteps, int curR, int curD, int[] ans) {
		if (curR + curD == totalSteps) {
			ans[0]++;
			return;
		}

		if (curR < rightSteps) {
			curR++;
			helper1(totalSteps, rightSteps, downSteps, curR, curD, ans);
			curR--;
		}

		if (curD < downSteps) {
			curD++;
			helper1(totalSteps, rightSteps, downSteps, curR, curD, ans);
			curD--;
		}
	}

	// solution 2: recursion (Time Limit Exceeded)
	public static int uniquePaths2(int m, int n) {
		return helper2(m, n);
	}

	public static int helper2(int m, int n) {
		if (n == 1 || m == 1) {
			return 1;
		}

		return helper2(m - 1, n) + helper2(m, n - 1);
	}

	// solution 3: memorization
	public static int uniquePaths3(int m, int n) {
		return helper3(m, n, new int[m][n]);
	}

	public static int helper3(int m, int n, int[][] dp) {
		if (n == 1 || m == 1) {
			return 1;
		}

		if (dp[m - 1][n - 1] != 0) {
			return dp[m - 1][n - 1];
		}

		dp[m - 1][n - 1] = helper3(m - 1, n, dp) + helper3(m, n - 1, dp);

		return dp[m - 1][n - 1];
	}

	// solution 4: dp
	public static int uniquePaths4(int m, int n) {
		int[][] dp = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}

		return dp[m - 1][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(uniquePaths1(5, 5));
		System.out.println(uniquePaths2(5, 5));
		System.out.println(uniquePaths3(51, 9));
		System.out.println(uniquePaths4(51, 9));
	}
}