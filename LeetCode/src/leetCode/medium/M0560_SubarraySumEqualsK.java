package leetCode.medium;

import java.util.HashMap;

public class M0560_SubarraySumEqualsK {
	public static void printNums(int[][] nums) {
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(nums[i][j] + " ");
			}
			System.out.println();
		}
	}

	// solution 1: brute-force
	public static int subarraySum1(int[] nums, int k) {
		int ans = 0;

		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum += nums[j];
				if (sum == k) {
					ans++;
				}
			}
		}

		return ans;
	}

	// solution 2: Memory Limit Exceeded
	public static int subarraySum2(int[] nums, int k) {
		int ans = 0;
		int n = nums.length;
		int[][] dp = new int[n][n];

		// dp[0][1] = dp[0][0]+nums[1]
		// dp[1][2] = dp[1][1]+nums[2]
		// dp[2][3] = dp[2][2]+nums[3]
		// dp[0][2] = dp[0][1]+nums[2]
		// dp[1][3] = dp[1][2]+nums[3]
		// dp[0][3] = dp[0][2]+nums[3]
		for (int len = 0; len < n; len++) {
			for (int i = 0; i < n - len; i++) {
				if (len == 0) {
					dp[i][i] = nums[i];
				} else {
					dp[i][i + len] = dp[i][i + len - 1] + nums[i + len];
				}

				if (dp[i][i + len] == k) {
					ans++;
				}
			}
		}

		return ans;
	}

	// solution 3
	public static int subarraySum3(int[] nums, int k) {
		int ans = 0;
		int n = nums.length;
		int[][] dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			if (i == 0) {
				dp[0][i] = nums[i];
			} else {
				dp[0][i] = dp[0][i - 1] + nums[i];
			}
			if (dp[0][i] == k) {
				ans++;
			}
		}

		for (int i = 1; i < n; i++) {
			for (int j = i; j < n; j++) {
				dp[i][j] = dp[0][j] - dp[0][i - 1];
				if (dp[i][j] == k) {
					ans++;
				}
			}
		}

		return ans;
	}

	// solution 4
	// https://leetcode.com/problems/subarray-sum-equals-k/solutions/803317/java-solution-with-detailed-explanation/
	public static int subarraySum4(int[] nums, int k) {
		int count = 0;

		int[] sum = new int[nums.length + 1];
		sum[0] = 0;
		for (int i = 1; i <= nums.length; i++)
			sum[i] = sum[i - 1] + nums[i - 1];

		for (int start = 0; start < sum.length; start++) {
			for (int end = start + 1; end < sum.length; end++) {
				if (sum[end] - sum[start] == k)
					count++;
			}
		}

		return count;
	}

	// solution 5
	// https://leetcode.com/problems/subarray-sum-equals-k/solutions/803317/java-solution-with-detailed-explanation/
	// https://anj910.medium.com/leetcode-560-subarray-sum-equals-k-%E4%B8%AD%E6%96%87-44c4f97693d0
	public static int subarraySum5(int[] nums, int k) {
		int count = 0;
		int sum = 0;

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1); // 以免之後遇到情況像是nums[0, i]的總和已經是k

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			// sum[end] - sum[start] == k
			// sum[end] - k == sum[start]
			if (map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}

			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}

		return count;
	}

	public static void main(String[] args) {
		System.out.println(subarraySum1(new int[] { 1, 1, 1 }, 2));
		System.out.println(subarraySum1(new int[] { 1, 2, 3 }, 3));

		System.out.println(subarraySum2(new int[] { 1, 1, 1 }, 2));
		System.out.println(subarraySum2(new int[] { 1, 2, 3 }, 3));

		System.out.println(subarraySum3(new int[] { 1, 1, 1 }, 2));
		System.out.println(subarraySum3(new int[] { 1, 2, 3 }, 3));
	}
}
