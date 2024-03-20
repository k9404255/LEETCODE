package leetCode.medium;

import java.util.Arrays;

public class M0198_HouseRobber {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ", ");
		}
		System.out.println();
	}

	// solution 1: recursive (Time Limit Exceeded)
	public static int rob1(int[] nums) {
		int n = nums.length;

		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return nums[0];
		} else if (n == 2) {
			return Math.max(nums[0], nums[1]);
		}

		int max1 = nums[0] + rob1(Arrays.copyOfRange(nums, 2, n)); // select 1st house
		int max2 = nums[1] + rob1(Arrays.copyOfRange(nums, 3, n)); // select 2nd house

		return Math.max(max1, max2);
	}

	// solution 2: recursive (memorization)
	public static int rob2(int[] nums) {
		int[] dp = new int[nums.length];
		Arrays.fill(dp, -1);

		return helper2(nums, 0, nums.length - 1, dp);
	}

	public static int helper2(int[] nums, int i, int j, int[] dp) {
		int n = j - i + 1;

		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return nums[i];
		} else if (n == 2) {
			return Math.max(nums[i], nums[i + 1]);
		}

		if (dp[i] != -1) {
			return dp[i];
		}

		int max1 = nums[i] + helper2(nums, i + 2, j, dp); // select 1st house
		int max2 = nums[i + 1] + helper2(nums, i + 3, j, dp); // select 2nd house

		int max = Math.max(max1, max2);

		dp[i] = max;

		return max;
	}

	// solution 3: dp
	public static int rob3(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];

		if (n == 1) {
			return nums[0];
		} else if (n == 2) {
			return Math.max(nums[0], nums[1]);
		} else if (n == 3) {
			return Math.max(nums[1], nums[0] + nums[2]);
		}

		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		dp[2] = Math.max(nums[1], nums[0] + nums[2]);
		for (int i = 3; i < n; i++) {
			dp[i] = Math.max(nums[i] + dp[i - 2], nums[i] + dp[i - 3]);
		}

		return Math.max(dp[n - 1], dp[n - 2]);
	}

	// solution 4: recursive (Time Limit Exceeded)
	public static int rob4(int[] nums) {
		int n = nums.length;

		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return nums[0];
		}

		int max1 = nums[0] + rob4(Arrays.copyOfRange(nums, 2, n)); // select 1st house
		int max2 = rob4(Arrays.copyOfRange(nums, 1, n)); // 1st not included

		return Math.max(max1, max2);
	}

	// solution 5: recursive (memorization)
	public static int rob5(int[] nums) {
		int[] dp = new int[nums.length + 1];
		Arrays.fill(dp, -1);

		return helper5(nums, 0, dp);
	}

	public static int helper5(int[] nums, int cur, int[] dp) {
		if (cur >= nums.length) {
			return 0;
		}

		if (dp[cur] != -1) {
			return dp[cur];
		}

		int take = nums[cur] + helper5(nums, cur + 2, dp);
		int nonTake = helper5(nums, cur + 1, dp);

		return dp[cur] = Math.max(take, nonTake);
	}

	// solution 6: dp
	public static int rob6(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = nums[0];

		for (int i = 2; i <= n; i++) {
			int take = nums[i - 1] + dp[i - 2];
			int notake = dp[i - 1];
			dp[i] = Math.max(take, notake);
		}

		return dp[n];
	}

	// solution 7: best
	public static int rob7(int[] nums) {
		int prev = nums[0];
		int prevPrev = 0;

		for (int i = 1; i < nums.length; i++) {
			int steal = nums[i] + prevPrev;
			int skip = prev;
			prevPrev = prev;
			prev = Math.max(steal, skip);
		}

		return prev;
	}

	public static void main(String[] args) {
//		System.out.println(rob1(new int[] { 1, 2, 3, 1 })); // 4
//		System.out.println(rob1(new int[] { 2, 7, 9, 3, 1 })); // 12
//		System.out.println(rob1(new int[] { 10, 7, 9, 99 })); // 109

//		System.out.println(rob2(new int[] { 1, 2, 3, 1 })); // 4
//		System.out.println(rob2(new int[] { 2, 7, 9, 3, 1 })); // 12
//		System.out.println(rob2(new int[] { 10, 7, 9, 99 })); // 109

//		System.out.println(rob3(new int[] { 1, 2, 3, 1 })); // 4
//		System.out.println(rob3(new int[] { 2, 7, 9, 3, 1 })); // 12
//		System.out.println(rob3(new int[] { 10, 7, 9, 99 })); // 109

//		System.out.println(rob4(new int[] { 1, 2, 3, 1 })); // 4
//		System.out.println(rob4(new int[] { 2, 7, 9, 3, 1 })); // 12
//		System.out.println(rob4(new int[] { 10, 7, 9, 99 })); // 109

//		System.out.println(rob5(new int[] { 1, 2, 3, 1 })); // 4
//		System.out.println(rob5(new int[] { 2, 7, 9, 3, 1 })); // 12
//		System.out.println(rob5(new int[] { 10, 7, 9, 99 })); // 109

		System.out.println(rob6(new int[] { 1, 2, 3, 1 })); // 4
		System.out.println(rob6(new int[] { 2, 7, 9, 3, 1 })); // 12
		System.out.println(rob6(new int[] { 10, 7, 9, 99 })); // 109
	}
}
