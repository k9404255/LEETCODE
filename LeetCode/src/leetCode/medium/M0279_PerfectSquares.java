package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M0279_PerfectSquares {
	// solution 1: recursion (Time Limit Exceeded)
	public static int numSquares1(int n) {
		return helper1(n, 1, new ArrayList<Integer>());
	}

	public static int helper1(int n, int cur, List<Integer> list) {
		if (n == 0) {
			return 0;
		}

		if (n <= 0) {
			return 10000;
		}

		int min = 10000;
		for (int i = cur; i * i <= n; i++) {
			int square = i * i;
			// list.add(square);
			int num = helper1(n - square, i, list);
			min = Math.min(min, num + 1);
			// list.remove(list.size() - 1);
		}

		return min;
	}

	// solution 2: recursion with Memoization
	public static int numSquares2(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);

		return helper2(n, 1, dp);
	}

	public static int helper2(int n, int cur, int[] dp) {
		if (n <= 0) {
			return 0;
		}

		if (dp[n] != -1) {
			return dp[n];
		}

		int min = 10000;
		for (int i = cur; i * i <= n; i++) {
			int square = i * i;
			int num = helper2(n - square, i, dp);
			min = Math.min(min, num + 1);
		}

		dp[n] = min;

		return min;
	}

	// solution 3: dp
	public static int numSquares3(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, 10000);
		dp[0] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j * j <= i; j++) {
				int square = j * j;
				dp[i] = Math.min(dp[i], dp[i - square] + 1);
			}
		}

		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(numSquares1(12));
		System.out.println(numSquares1(13));

		System.out.println(numSquares2(12));
		System.out.println(numSquares2(13));

		System.out.println(numSquares3(12));
		System.out.println(numSquares3(13));
		System.out.println(numSquares3(4));
		System.out.println(numSquares3(8));
	}

}
