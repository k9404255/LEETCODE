package leetCode.medium;

import java.util.Arrays;

// https://leetcode.com/problems/jump-game-ii/solutions/3159282/4-best-approaches-recursion-memorization-tabulation-most-optimized-approach/
public class M0045_JumpGameII {
	// solution 1 (Time Limit Exceeded): Recursion
	public static int jump1(int[] nums) {
		return helper1(nums, 0);
	}

	public static int helper1(int[] nums, int curIdx) {
		if (curIdx >= nums.length - 1) {
			return 0;
		}

		int minStep = nums.length;
		for (int i = 1; i <= nums[curIdx]; i++) {
			int step = helper1(nums, curIdx + i);
			minStep = Math.min(minStep, step + 1);
		}

		return minStep;
	}

	// solution 2: Memoization
	public static int jump2(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, -1);

		return helper2(nums, 0, dp);
	}

	public static int helper2(int[] nums, int curIdx, int[] dp) {
		if (curIdx >= nums.length - 1) {
			return 0;
		}

		if (dp[curIdx] != -1) {
			return dp[curIdx];
		}

		int minStep = nums.length;
		for (int i = 1; i <= nums[curIdx]; i++) {
			int step = helper2(nums, curIdx + i, dp);
			minStep = Math.min(minStep, step + 1);
		}

		dp[curIdx] = minStep;

		return minStep;
	}

	// solution 3: dynamic programming with a bottom-up approach (backward)
	public static int jump3(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		Arrays.fill(dp, n);
		dp[n - 1] = 0;

		for (int i = n - 2; i >= 0; i--) {
			for (int j = 1; j <= nums[i] && i + j < n; j++) {
				dp[i] = Math.min(dp[i], dp[i + j] + 1);
			}
		}

		return dp[0];
	}

	// solution 4: dynamic programming with a bottom-up approach (forward)
	public static int jump4(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		Arrays.fill(dp, n);
		dp[0] = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= nums[i] && i + j < n; j++) {
				dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
			}
		}

		return dp[n - 1];
	}

	// solution 5: greedy
	public static int jump5(int[] nums) {
		int reachable = 0;
		int end = 0;
		int ans = 0;

		for (int i = 0; i < nums.length - 1; i++) {
			reachable = Math.max(reachable, i + nums[i]);

			// if (reachable >= nums.length - 1) {
			// ans++;
			// break;
			// }

			if (i == end) {
				end = reachable;
				ans++;
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		// System.out.println(jump1(new int[] {2, 1, 0 }));
		// System.out.println(jump1(new int[] {2, 3, 1, 1, 4 }));
		// System.out.println(jump1(new int[] {2, 3, 0, 1, 4 }));
		//
		// System.out.println(jump2(new int[] {2, 1, 0 }));
		// System.out.println(jump2(new int[] {2, 3, 1, 1, 4 }));
		// System.out.println(jump2(new int[] {2, 3, 0, 1, 4 }));

		System.out.println(jump3(new int[] { 2, 1, 0 }));
		System.out.println(jump3(new int[] { 2, 3, 1, 1, 4 }));
		System.out.println(jump3(new int[] { 2, 3, 0, 1, 4 }));
	}
}