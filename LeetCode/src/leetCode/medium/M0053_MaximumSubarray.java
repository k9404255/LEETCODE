package leetCode.medium;

import java.util.Arrays;

public class M0053_MaximumSubarray {
	// solution 1
	public static int maxSubArray1(int[] nums) {
		int max = nums[0];
		// int maxLeft = 0;
		// int maxRight = 0;
		int sum = nums[0];

		// int left = 0;
		for (int i = 1; i < nums.length; i++) {
			int n = nums[i];

			if (sum < 0 && n > 0) {
				// left = i;
				sum = n;
			} else if (sum < 0 && n > sum) {
				// left = i;
				sum = n;
			} else if (sum + n > 0) {
				sum += n;
			} else if (sum + n < 0) {
				sum = n;
				// left = i;
			} else {
				sum += n;
			}

			if (sum > max) {
				max = sum;
				// maxRight = i;
				// maxLeft = left;
			}
		}

		// System.out.println(maxLeft + " " + maxRight);

		return max;
	}

	// solution 2: dp
	public static int maxSubArray2(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n + 1];
		dp[0] = nums[0];
		int max = nums[0];

		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
			max = Math.max(max, dp[i]);
		}

//		for (int i = 0; i < dp.length; i++) {
//			System.out.print(dp[i]+" ");
//		}
//		System.out.println();

		return max;
	}

	// solution 3: recursion
	public static int maxSubArray3(int[] nums) {
		int[] ans = new int[] { Integer.MIN_VALUE };
		helper3(nums, 0, nums.length - 1, ans);

		return ans[0];
	}

	public static int helper3(int[] nums, int left, int right, int[] ans) {
		int curMax = 0;

		if (left == right) {
			curMax = nums[left];
		} else {
			int subMax = helper3(nums, left, right - 1, ans);
			curMax = Math.max(subMax + nums[right], nums[right]);
		}

		if (curMax > ans[0]) {
			ans[0] = curMax;
		}

		return curMax;
	}

	// solution 4: memorization
	public static int maxSubArray4(int[] nums) {
		int[] ans = new int[] { Integer.MIN_VALUE };
		int[] dp = new int[nums.length + 1];
		Arrays.fill(dp, Integer.MIN_VALUE);
		helper4(nums, 0, nums.length - 1, ans, dp);

		return ans[0];
	}

	public static int helper4(int[] nums, int left, int right, int[] ans, int[] dp) {
		if (dp[right] != Integer.MIN_VALUE) {
			return dp[right];
		}

		int curMax = 0;
		if (left == right) {
			curMax = nums[left];
		} else {
			int subMax = helper4(nums, left, right - 1, ans, dp);
			curMax = Math.max(subMax + nums[right], nums[right]);
		}

		if (curMax > ans[0]) {
			ans[0] = curMax;
		}

		dp[right] = curMax;

		return curMax;
	}

	// solution 5
	public static int maxSubArray5(int[] nums) {
		int max = Integer.MIN_VALUE;
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			if (sum > max) {
				max = sum;
			}

			if (sum < 0) {
				sum = 0;
			}
		}

		return max;
	}

	// solution 6
	public static int maxSubArray6(int[] nums) {
		int maxSum = nums[0];
		int currentSum = nums[0];

		for (int i = 1; i < nums.length; i++) {
			currentSum = Math.max(nums[i], currentSum + nums[i]);
			maxSum = Math.max(maxSum, currentSum);
		}

		return maxSum;
	}

	public static void main(String[] args) {
//		System.out.println(maxSubArray1(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
//		System.out.println(maxSubArray1(new int[] { 1 }));
//		System.out.println(maxSubArray1(new int[] { 5, 4, -1, 7, 8 }));
//		System.out.println(maxSubArray1(new int[] { 1, -1, 1 }));
//		System.out.println(maxSubArray1(new int[] { 8, -19, 5, -4, 20 }));

//		System.out.println(maxSubArray2(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
//		System.out.println(maxSubArray2(new int[] { 1 }));
//		System.out.println(maxSubArray2(new int[] { 5, 4, -1, 7, 8 }));
//		System.out.println(maxSubArray2(new int[] { 1, -1, 1 }));
//		System.out.println(maxSubArray2(new int[] { 8, -19, 5, -4, 20 }));

//		System.out.println(maxSubArray3(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
//		System.out.println(maxSubArray3(new int[] { 1 }));
//		System.out.println(maxSubArray3(new int[] { 5, 4, -1, 7, 8 }));
//		System.out.println(maxSubArray3(new int[] { 1, -1, 1 }));
//		System.out.println(maxSubArray3(new int[] { 8, -19, 5, -4, 20 }));

		System.out.println(maxSubArray4(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
		System.out.println(maxSubArray4(new int[] { 1 }));
		System.out.println(maxSubArray4(new int[] { 5, 4, -1, 7, 8 }));
		System.out.println(maxSubArray4(new int[] { 1, -1, 1 }));
		System.out.println(maxSubArray4(new int[] { 8, -19, 5, -4, 20 }));
	}
}
