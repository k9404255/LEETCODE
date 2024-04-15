package leetCode.medium;

import java.util.Arrays;

public class M0322_CoinChange {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1
	public static int coinChange1(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}

		int[] dp = new int[amount + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int coin : coins) {
			for (int j = coin; j <= amount; j++) {
				if (dp[j - coin] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j - coin] + 1, dp[j]);
				}
			}
		}

		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}

	// solution 2: recursion (Time Limit Exceeded)
	public static int coinChange2(int[] coins, int amount) {
		int ans = helper2(coins, amount);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public static int helper2(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		} else if (amount < 0) {
			return Integer.MAX_VALUE;
		}

		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int num = helper2(coins, amount - coin);
			if (num != Integer.MAX_VALUE) {
				min = Math.min(min, num + 1);
			}
		}

		return min;
	}

	// solution 3: recursion with memoization
	public static int coinChange3(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, -1);

		int ans = helper3(coins, amount, dp);

		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public static int helper3(int[] coins, int amount, int[] dp) {
		if (amount == 0) {
			return 0;
		} else if (amount < 0) {
			return Integer.MAX_VALUE;
		}

		if (dp[amount] != -1) {
			return dp[amount];
		}

		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int num = helper3(coins, amount - coin, dp);
			if (num != Integer.MAX_VALUE) {
				min = Math.min(min, num + 1);
			}
		}

		dp[amount] = min;

		return min;
	}

	// solution 4
	public int coinChange4(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {

				if (i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
				}
			}
		}
		return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
	}

	public static void main(String[] args) {
		System.out.println(coinChange1(new int[] { 2 }, 1));
		System.out.println(coinChange1(new int[] { 1, 2, 5 }, 11));
		System.out.println(coinChange1(new int[] { 2 }, 3));
		System.out.println(coinChange1(new int[] { 1 }, 0));
		System.out.println(coinChange1(new int[] { 1, 4, 5 }, 8));
		System.out.println(coinChange1(new int[] { 1, 3, 5 }, 8));

		System.out.println("---------------");

//		System.out.println(coinChange2(new int[] { 2 }, 1));
//		System.out.println(coinChange2(new int[] { 1, 2, 5 }, 100));
//		System.out.println(coinChange2(new int[] { 2 }, 3));
//		System.out.println(coinChange2(new int[] { 1 }, 0));
//		System.out.println(coinChange2(new int[] { 1, 4, 5 }, 8));
//		System.out.println(coinChange2(new int[] { 1, 3, 5 }, 8));

		System.out.println("---------------");
		System.out.println(coinChange3(new int[] { 2 }, 1));
		System.out.println(coinChange3(new int[] { 1, 2, 5 }, 11));
		System.out.println(coinChange3(new int[] { 2 }, 3));
		System.out.println(coinChange3(new int[] { 1 }, 0));
		System.out.println(coinChange3(new int[] { 1, 4, 5 }, 8));
		System.out.println(coinChange3(new int[] { 1, 3, 5 }, 8));
	}
}
