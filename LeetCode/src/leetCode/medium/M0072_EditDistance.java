package leetCode.medium;

import java.util.Arrays;

public class M0072_EditDistance {
	// solution 1: dp
	// https://leetcode.com/problems/edit-distance/solutions/3230662/clean-codes-full-explanation-dynamic-programming-c-java-python3/
	public static int minDistance1(String word1, String word2) {
		int m = word1.length();// first word length
		int n = word2.length();/// second word length

		// dp[i][j] := min # of operations to convert word1[0..i) to word2[0..j)
		int[][] dp = new int[m + 1][n + 1];

		// i deletions
		for (int i = 1; i <= m; ++i) {
			dp[i][0] = i;
		}

		// j insertions
		for (int j = 1; j <= n; ++j) {
			dp[0][j] = j;
		}

		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];// no operation
				} else {
					// min(replace, delete, insert)
					dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
				}
			}
		}

		return dp[m][n];
	}

	// solution 2: memorization
	// https://leetcode.com/problems/edit-distance/solutions/4854676/memoization-solution/
	public static int minDistance2(String word1, String word2) {
		if (word1.equals("") && word2.equals("")) {
			return 0;
		}

		int n = word1.length();
		int m = word2.length();
		int[][] dp = new int[n][m];
		for (int[] d : dp) {
			Arrays.fill(d, -1);
		}
		return helper2(n - 1, m - 1, word1, word2, dp);
	}

	public static int helper2(int i, int j, String s1, String s2, int[][] dp) {
		if (i < 0) {
			return j + 1;
		}

		if (j < 0) {
			return i + 1;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		if (s1.charAt(i) == s2.charAt(j)) {
			return dp[i][j] = 0 + helper2(i - 1, j - 1, s1, s2, dp);
		} else {
			int insert = Integer.MAX_VALUE;
			int delete = Integer.MAX_VALUE;
			int replace = Integer.MAX_VALUE;
			insert = 1 + helper2(i, j - 1, s1, s2, dp);
			delete = 1 + helper2(i - 1, j, s1, s2, dp);
			replace = 1 + helper2(i - 1, j - 1, s1, s2, dp);
			return dp[i][j] = Math.min(insert, Math.min(delete, replace));
		}

	}

	public static void main(String[] args) {
		System.out.println(minDistance1("horse", "ros"));
		System.out.println(minDistance1("intention", "execution"));
		System.out.println(minDistance1("abc", "bc"));
	}
}
