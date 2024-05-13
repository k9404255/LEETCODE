package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M0300_LongestIncreasingSubsequence {
	// solution 1: recursion (Time Limit Exceeded)
	public static int lengthOfLIS1(int[] nums) {
		return helper1(nums, 0, Integer.MIN_VALUE, 0, new ArrayList<>());
	}

	public static int helper1(int[] nums, int cur, int min, int len, List<Integer> list) {
		if (cur == nums.length) {
//			System.out.println(list);
			return len;
		}

//		System.out.println("num: " + nums[cur] + ", min: " + min);

		int take = 0;
		if (nums[cur] > min) {
			List<Integer> takeList = new ArrayList<>(list);
			takeList.add(nums[cur]);
			take = helper1(nums, cur + 1, nums[cur], len + 1, takeList); // take
		}
		int notTake = helper1(nums, cur + 1, min, len, new ArrayList<>(list)); // not take

		return Math.max(take, notTake);
	}

	// solution 2: recursion
	public static int lengthOfLIS2(int[] nums) {
		return helper2(nums, 0, Integer.MIN_VALUE);
	}

	public static int helper2(int[] nums, int cur, int prev) {
		if (cur == nums.length) {
			return 0;
		}

		int take = 0;
		if (nums[cur] > prev) {
			take = helper2(nums, cur + 1, nums[cur]) + 1;
		}
		int notTake = helper2(nums, cur + 1, prev);

		return Math.max(take, notTake);
	}

	// solution 3: memorization
	public static int lengthOfLIS3(int[] nums) {
		int n = nums.length;
		Integer[][] dp = new Integer[n][n + 1];

		return helper3(nums, 0, -1, dp);
	}

	public static int helper3(int[] nums, int cur, int prevIdx, Integer[][] dp) {
		if (cur == nums.length) {
			return 0;
		}

		// [1,4]
		// [4]
		if (dp[cur][prevIdx + 1] != null) {
			return dp[cur][prevIdx + 1];
		}

		int take = 0;
		if (prevIdx == -1 || nums[cur] > nums[prevIdx]) {
			take = helper3(nums, cur + 1, cur, dp) + 1;
		}
		int notTake = helper3(nums, cur + 1, prevIdx, dp);

		return dp[cur][prevIdx + 1] = Math.max(take, notTake);
	}

	// solution 4: dp
	public static int lengthOfLIS4(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int n = nums.length;
		// dp[i] represents the length of the longest increasing subsequence ending at
		// index i
		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		int max = 1;
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					max = Math.max(max, dp[i]);
				}
			}
		}

		return max;
	}

	public static void main(String[] args) {
//		System.out.println(lengthOfLIS1(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));
//		System.out.println(lengthOfLIS1(new int[] { 0, 1, 0, 3, 2, 3 }));
//		System.out.println(lengthOfLIS1(new int[] { 7, 7, 7, 7, 7, 7, 7 }));
//		System.out.println(lengthOfLIS1(new int[] { 1, 4, 2, 3 }));

//		System.out.println(lengthOfLIS2(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));
//		System.out.println(lengthOfLIS2(new int[] { 0, 1, 0, 3, 2, 3 }));
//		System.out.println(lengthOfLIS2(new int[] { 7, 7, 7, 7, 7, 7, 7 }));
//		System.out.println(lengthOfLIS2(new int[] { 1, 4, 2, 3 }));

//		System.out.println(lengthOfLIS3(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));
//		System.out.println(lengthOfLIS3(new int[] { 0, 1, 0, 3, 2, 3 }));
//		System.out.println(lengthOfLIS3(new int[] { 7, 7, 7, 7, 7, 7, 7 }));
//		System.out.println(lengthOfLIS3(new int[] { 1, 4, 2, 3 }));

		System.out.println(lengthOfLIS4(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));
		System.out.println(lengthOfLIS4(new int[] { 0, 1, 0, 3, 2, 3 }));
		System.out.println(lengthOfLIS4(new int[] { 7, 7, 7, 7, 7, 7, 7 }));
		System.out.println(lengthOfLIS4(new int[] { 1, 4, 2, 3 }));
	}
}
