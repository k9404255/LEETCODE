package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

// 想像成0/1背包問題
// https://leetcode.com/problems/partition-equal-subset-sum/solutions/1624101/java-dpoization-to-optimized-dp-detailed-explanation/
// https://www.youtube.com/watch?v=JIiDb2iPW40
// https://leetcode.jp/leetcode-416-partition-equal-subset-sum%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/#google_vignette
public class M0416_PartitionEqualSubsetSum {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static boolean canPartition(int[] nums, int sum) {
		int leftSum = 0;
		int rightSum = sum;
		for (int i = 0; i < nums.length; i++) {
			leftSum += nums[i];
			rightSum -= nums[i];
			if (leftSum == rightSum) {
				return true;
			} else if (leftSum > rightSum) {
				return false;
			}
		}

		return false;
	}

	// solution 1: permutation (Time Limit Exceeded)
	public static boolean canPartition1(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		return helper1(nums, sum, 0);
	}

	public static boolean helper1(int[] nums, int sum, int cur) {
		if (cur == nums.length) {
//			printNums(nums);
			return canPartition(nums, sum);
		}

		for (int i = cur; i < nums.length; i++) {
			swap(nums, i, cur);
			if (helper1(nums, sum, cur + 1)) {
				return true;
			}
			swap(nums, i, cur);
		}

		return false;
	}

	// solution 2: combination (Time Limit Exceeded)
	public static boolean canPartition2(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		List<List<Integer>> combinations = new ArrayList<>();
		for (int i = 1; i <= nums.length; i++) {
			if (helper2(nums, sum, i, 0, new ArrayList<>(), combinations)) {
				return true;
			}
		}

		return false;
	}

	public static boolean helper2(int[] nums, int sum, int k, int cur, List<Integer> list,
			List<List<Integer>> combinations) {
		if (list.size() == k) {
			combinations.add(new ArrayList<>(list));
			if (list.stream().reduce(0, (a, b) -> a + b) * 2 == sum) {
				return true;
			}
		}

		for (int i = cur; i < nums.length; i++) {
			list.add(nums[i]);
			if (helper2(nums, sum, k, i + 1, list, combinations)) {
				return true;
			}
			list.remove(list.size() - 1);
		}

		return false;
	}

	// solution 3: dp
	public static boolean canPartition3(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		if (sum % 2 == 1) {
			return false;
		}

		sum /= 2;

		// dp[i][s]: from the first i elements, if there is a method to pick some
		// numbers whose sum equals s
		// I. dp[i-1][s] == true => dp[i][s] = true
		// II. dp[i-1][s-nums[i]] == true => dp[i][s] = true

		// dp[i][s] = dp[i-1][s] || (s>=nums[i] && dp[i-1][s-nums[i]])
		// return dp[n][sum/2]

		int n = nums.length;
		boolean[][] dp = new boolean[n + 1][sum + 1];
		for (int i = 0; i < n + 1; i++) {
			dp[i][0] = true;
		}

		for (int i = 1; i < n; i++) {
			for (int s = 1; s <= sum; s++) {
				if (s - nums[i] >= 0 && dp[i - 1][s - nums[i]]) {
					dp[i][s] = true;
				} else if (dp[i - 1][s]) {
					dp[i][s] = true;
				}
			}
		}

		return dp[n - 1][sum];
	}

	// solution 4: dp
	public static boolean canPartition4(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		if (sum % 2 == 1) {
			return false;
		}

		sum /= 2;

		int n = nums.length;
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;

		for (int i = 1; i < n; i++) {
			for (int s = sum; s > 0; s--) {
				if (s >= nums[i]) {
					dp[s] = dp[s] || dp[s - nums[i]];
				}
			}
		}

		return dp[sum];
	}

	// solution 5: memorization
	public static boolean canPartition5(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		if (sum % 2 == 1) {
			return false;
		}

		sum /= 2;

		Boolean[][] dp = new Boolean[nums.length + 1][sum + 1];

		return helper5(nums, 0, sum, dp);
	}

	public static boolean helper5(int[] nums, int cur, int sum, Boolean[][] dp) {
		if (sum == 0)
			return true;

		else if (cur >= nums.length || sum < 0)
			return false;

		if (dp[cur][sum] != null)
			return dp[cur][sum];

		return dp[cur][sum] = helper5(nums, cur + 1, sum - nums[cur], dp) || helper5(nums, cur + 1, sum, dp);
	}

	public static void main(String[] args) {
		System.out.println(canPartition1(new int[] { 1, 3, 2 }));
		System.out.println(canPartition1(new int[] { 1, 5, 11, 5 }));
		System.out.println(canPartition1(new int[] { 1, 2, 3, 5 }));
		System.out.println(canPartition1(new int[] { 2, 2, 1, 1 }));

//		System.out.println(canPartition2(new int[] { 1, 3, 2 }));
//		System.out.println(canPartition2(new int[] { 1, 5, 11, 5 }));
//		System.out.println(canPartition2(new int[] { 1, 2, 3, 5 }));
//		System.out.println(canPartition2(new int[] { 2, 2, 1, 1 }));

		System.out.println(canPartition3(new int[] { 1, 3, 2 }));
		System.out.println(canPartition3(new int[] { 1, 5, 11, 5 }));
		System.out.println(canPartition3(new int[] { 1, 2, 3, 5 }));
		System.out.println(canPartition3(new int[] { 2, 2, 1, 1 }));

		System.out.println(canPartition4(new int[] { 1, 3, 2 }));
		System.out.println(canPartition4(new int[] { 1, 5, 11, 5 }));
		System.out.println(canPartition4(new int[] { 1, 2, 3, 5 }));
		System.out.println(canPartition4(new int[] { 2, 2, 1, 1 }));

		System.out.println(canPartition5(new int[] { 1, 3, 2 }));
		System.out.println(canPartition5(new int[] { 1, 5, 11, 5 }));
		System.out.println(canPartition5(new int[] { 1, 2, 3, 5 }));
		System.out.println(canPartition5(new int[] { 2, 2, 1, 1 }));
	}
}
