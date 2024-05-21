package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M0518_CoinChangeII {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1: recursion(Time Limit Exceeded)
	static int ans;

	public static int change1(int amount, int[] coins) {
		ans = 0;
		helper1(coins, amount, 0, 0, new ArrayList<>());

		return ans;
	}

	public static void helper1(int[] coins, int amount, int cur, int sum, List<Integer> list) {
		if (sum == amount) {
			System.out.println(list);
			ans++;
			return;
		}

		if (sum > amount || cur == coins.length) {
			return;
		}

		helper1(coins, amount, cur + 1, sum, new ArrayList<>(list)); // no take
		list.add(coins[cur]);
		helper1(coins, amount, cur, sum + coins[cur], new ArrayList<>(list)); // take
	}

	// solution 2: memorization
	public static int change2(int amount, int[] coins) {
		int[][] dp = new int[coins.length][amount + 1];
		for (int[] i : dp)
			Arrays.fill(i, -1);

		return helper2(coins, coins.length - 1, amount, dp);
	}

	public static int helper2(int[] coins, int cur, int amount, int[][] dp) {
		if (amount == 0) {
			return 1;
		}

		if (cur < 0 || amount < 0) {
			return 0;
		}

		if (dp[cur][amount] != -1) {
			return dp[cur][amount];
		}

		int notTake = helper2(coins, cur - 1, amount, dp); // no take
		int take = helper2(coins, cur, amount - coins[cur], dp); // take

		return dp[cur][amount] = take + notTake;
	}

	// solution 3: dp
	public static int change3(int amount, int[] coins) {
		int[][] dp = new int[coins.length][amount + 1];
		for (int i = 0; i <= amount; i++) {
			if (i % coins[0] == 0)
				dp[0][i] = 1;
		}

		for (int ind = 1; ind < coins.length; ind++) {
			for (int amt = 0; amt <= amount; amt++) {
				int notTake = dp[ind - 1][amt];
				int take = 0;
				if (amt - coins[ind] >= 0) {
					take = dp[ind][amt - coins[ind]];
				}
				dp[ind][amt] = take + notTake;
			}
		}

		return dp[coins.length - 1][amount];
	}

	// solution 4: dp
	public static int change4(int amount, int[] coins) {
		// dp[i] represents the number of ways to make up the amount i using the coins
		// considered so far.
		int[] dp = new int[amount + 1];
		dp[0] = 1;

		for (int coin : coins) {
			for (int j = coin; j <= amount; j++) {
				dp[j] += dp[j - coin];
			}
		}

		return dp[amount];
	}

	public static void main(String[] args) {
//		System.out.println(change1(5, new int[] { 1, 2, 5 }));
//		System.out.println(change1(3, new int[] { 2 }));
//		System.out.println(change1(10, new int[] { 10 }));

//		System.out.println(change2(5, new int[] { 1, 2, 5 }));
//		System.out.println(change2(3, new int[] { 2 }));
//		System.out.println(change2(10, new int[] { 10 }));

		System.out.println(change3(5, new int[] { 1, 2, 5 }));
		System.out.println(change3(3, new int[] { 2 }));
		System.out.println(change3(10, new int[] { 10 }));
	}
}
