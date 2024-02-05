package leetCode.medium;

import java.util.Arrays;

// https://leetcode.com/problems/jump-game/solutions/4120416/4-approach-naive-good-better-best/
public class M0055_JumpGame {
	// solution 1 (Time Limit Exceeded): Recursion O(2^n)
	public static boolean canJump1(int[] nums) {
		return helper1(nums, 0);
	}

	public static boolean helper1(int[] nums, int cur) {
		if (cur >= nums.length - 1) {
			return true;
		}

		for (int i = 1; i <= nums[cur]; i++) {
			if (helper1(nums, cur + i)) {
				return true;
			}
		}

		return false;
	}

	// solution 2: Memoization O(n^2)
	public static boolean canJump2(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, -1);

		return helper2(nums, 0, dp);
	}

	public static boolean helper2(int[] nums, int cur, int[] dp) {
		if (cur >= nums.length - 1) {
			return true;
		}

		// 不重複計算
		if (dp[cur] != -1) {
			return dp[cur] == 1;
		}

		for (int i = 1; i <= nums[cur]; i++) {
			if (helper2(nums, cur + i, dp)) {
				dp[cur] = 1;
				return true;
			}
		}

		dp[cur] = 0;
		return false;
	}

	// solution 3: dynamic programming with a bottom-up approach O(n^2)
	public static boolean canJump3(int[] nums) {
		int n = nums.length;
		boolean[] dp = new boolean[n];
		dp[n - 1] = true;

		for (int i = n - 2; i >= 0; i--) {
			for (int j = 1; j <= nums[i] && i + j < n; j++) {
				if (dp[i + j]) {
					dp[i] = true;
					break;
				}
			}
		}

		return dp[0];
	}

	// solution 4: Greedy O(n)
	public static boolean canJump4(int[] nums) {
		int reachable = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i > reachable) {
				return false;
			}

			reachable = Math.max(reachable, i + nums[i]);
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(canJump1(new int[] { 2, 3, 1, 1, 4 }));
		System.out.println(canJump1(new int[] { 3, 2, 1, 0, 4 }));

		System.out.println(canJump2(new int[] { 2, 3, 1, 1, 4 }));
		System.out.println(canJump2(new int[] { 3, 2, 1, 0, 4 }));

		System.out.println(canJump3(new int[] { 2, 3, 1, 1, 4 }));
		System.out.println(canJump3(new int[] { 3, 2, 1, 0, 4 }));

		System.out.println(canJump4(new int[] { 2, 3, 1, 1, 4 }));
		System.out.println(canJump4(new int[] { 3, 2, 1, 0, 4 }));
	}
}
