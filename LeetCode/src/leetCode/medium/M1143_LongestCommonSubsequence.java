package leetCode.medium;

import java.util.Arrays;

public class M1143_LongestCommonSubsequence {
	// solution 1:
	// https://yungshenglu.github.io/2018/05/15/LongestCommonSubsequence1/
	public static int longestCommonSubsequence1(String text1, String text2) {
		int n = text1.length();
		int m = text2.length();
		int[][] dp = new int[n + 1][m + 1]; // 「 text1前 i 個元素」和「 text2前 j 個元素」的 LCS 長度。

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[n][m];
	}

	// solution 2:
	// https://yungshenglu.github.io/2018/05/15/LongestCommonSubsequence1/
	public static int longestCommonSubsequence2(String text1, String text2) {
		int n = text1.length();
		int m = text2.length();
		int[][] dp = new int[n + 1][m + 1]; // 「 text1前 i 個元素」和「 text2前 j 個元素」的 LCS 長度。
		int[][] prev = new int[n + 1][m + 1]; // 記錄每一格的的結果是從哪一格而來

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					prev[i][j] = 0; // 左上格
				} else if (dp[i - 1][j] < dp[i][j - 1]) {
					dp[i][j] = dp[i][j - 1];
					prev[i][j] = 1; // 左格
				} else {
					dp[i][j] = dp[i - 1][j];
					prev[i][j] = 2; // 上格
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		int i = n;
		int j = m;
		int len = dp[n][m];
		while (len > 0) {
			if (prev[i][j] == 0) {
				sb.append(text1.charAt(i - 1));
				len--;
				i--;
				j--;
			} else if (prev[i][j] == 1) {
				j--;
			} else {
				i--;
			}
		}

		System.out.println(sb.reverse().toString());

		return dp[n][m];
	}

	// solution 3: recursion (Time Limit Exceeded)
	public static int longestCommonSubsequence3(String text1, String text2) {
		return helper3(text1, text2, text1.length() - 1, text2.length() - 1);
	}

	public static int helper3(String text1, String text2, int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}

		if (text1.charAt(i) == text2.charAt(j)) {
			return 1 + helper3(text1, text2, i - 1, j - 1);
		} else {
			int top = helper3(text1, text2, i - 1, j);
			int left = helper3(text1, text2, i, j - 1);
			return Math.max(top, left);
		}
	}

	// solution 4: Memoization
	public static int longestCommonSubsequence4(String text1, String text2) {
		int n = text1.length();
		int m = text2.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}

		int ans = helper4(text1, text2, n - 1, m - 1, dp);
		return ans;
	}

	public static int helper4(String text1, String text2, int i, int j, int[][] dp) {
		if (i < 0 || j < 0) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		if (text1.charAt(i) == text2.charAt(j)) {
			return dp[i][j] = 1 + helper4(text1, text2, i - 1, j - 1, dp);
		} else {
			int top = helper4(text1, text2, i - 1, j, dp);
			int left = helper4(text1, text2, i, j - 1, dp);
			return dp[i][j] = Math.max(top, left);
		}
	}

	public static void main(String[] args) {
		System.out.println(longestCommonSubsequence1("abcde", "ace"));
		System.out.println(longestCommonSubsequence1("abc", "abc"));
		System.out.println(longestCommonSubsequence1("abc", "def"));

		System.out.println(longestCommonSubsequence2("abcde", "ace"));
		System.out.println(longestCommonSubsequence2("abc", "abc"));
		System.out.println(longestCommonSubsequence2("abc", "def"));

		System.out.println(longestCommonSubsequence3("abcde", "ace"));
		System.out.println(longestCommonSubsequence3("abc", "abc"));
		System.out.println(longestCommonSubsequence3("abc", "def"));

		System.out.println(longestCommonSubsequence4("abcde", "ace"));
		System.out.println(longestCommonSubsequence4("abc", "abc"));
		System.out.println(longestCommonSubsequence4("abc", "def"));
	}
}
