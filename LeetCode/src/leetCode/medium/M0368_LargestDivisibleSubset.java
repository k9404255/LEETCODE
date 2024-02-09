package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M0368_LargestDivisibleSubset {
	// solution 1: recursion
	public static List<Integer> largestDivisibleSubset1(int[] nums) {
		Arrays.sort(nums);
		List<Integer> ans = new ArrayList<Integer>();
		helper1(nums, 0, 1, new ArrayList<Integer>(), ans);

		return ans;
	}

	public static void helper1(int[] nums, int curIdx, int prevDivisor, List<Integer> tmpList, List<Integer> ans) {
		if (curIdx == nums.length) {
			if (tmpList.size() > ans.size()) {
				ans.clear();
				ans.addAll(tmpList);
			}
			return;
		}

		if (nums[curIdx] % prevDivisor == 0) {
			tmpList.add(nums[curIdx]);
			helper1(nums, curIdx + 1, nums[curIdx], tmpList, ans);
			tmpList.remove(tmpList.size() - 1);
		}

		helper1(nums, curIdx + 1, prevDivisor, tmpList, ans);
	}

	// solution 2: Memoization
	public static List<Integer> largestDivisibleSubset2(int[] nums) {
		Arrays.sort(nums);
		List<Integer> ans = new ArrayList<Integer>();
		int[] dp = new int[nums.length + 1];
		Arrays.fill(dp, -1);

		helper2(nums, 0, 1, new ArrayList<Integer>(), ans, dp);

		return ans;
	}

	public static void helper2(int[] nums, int curIdx, int prevDivisor, List<Integer> tmpList, List<Integer> ans,
			int[] dp) {
		if (curIdx == nums.length) {
			if (tmpList.size() > ans.size()) {
				ans.clear();
				ans.addAll(tmpList);
			}
			return;
		}

		if (tmpList.size() > dp[curIdx] && nums[curIdx] % prevDivisor == 0) {
			dp[curIdx] = tmpList.size();
			tmpList.add(nums[curIdx]);
			helper2(nums, curIdx + 1, nums[curIdx], tmpList, ans, dp);
			tmpList.remove(tmpList.size() - 1);
		}

		helper2(nums, curIdx + 1, prevDivisor, tmpList, ans, dp);
	}

	// solution 3: dp
	public static List<Integer> largestDivisibleSubset3(int[] nums) {
		// Sorted Input: [1, 2, 4, 5, 8, 10]
		// i = 0 dp: [[1], [2], [4], [5], [8], [10]]
		// i = 1 dp: [[1], [1, 2], [4], [5], [8], [10]]
		// i = 2 dp: [[1], [1, 2], [1, 2, 4], [5], [8], [10]]
		// i = 3 dp: [[1], [1, 2], [1, 2, 4], [1, 5], [8], [10]]
		// i = 4 dp: [[1], [1, 2], [1, 2, 4], [1, 5], [1, 2, 4, 8], [10]]
		// i = 5 dp: [[1], [1, 2], [1, 2, 4], [1, 5], [1, 2, 4, 8], [1, 2, 10]]
		// Output: [1, 2, 4, 8]

		int n = nums.length;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		Arrays.sort(nums);

		int maxIdx = 0;
		int maxSize = 1;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] % nums[j] == 0) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					if (dp[i] > maxSize) {
						maxSize = dp[i];
						maxIdx = i;
					}
				}
			}
		}

		List<Integer> ans = new ArrayList<Integer>();
		int num = nums[maxIdx];
		for (int i = maxIdx; i >= 0; i--) {
			if (num % nums[i] == 0 && dp[i] == maxSize) {
				maxSize--;
				num = nums[i];
				ans.add(nums[i]);
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(largestDivisibleSubset1(new int[] { 1 }));
		System.out.println(largestDivisibleSubset1(new int[] { 1, 2, 3 }));
		System.out.println(largestDivisibleSubset1(new int[] { 1, 2, 4, 8 }));
		System.out.println(largestDivisibleSubset1(new int[] { 5, 9, 18, 54, 108, 540, 90, 180, 360, 720 }));
		System.out.println(largestDivisibleSubset1(new int[] { 5, 9, 18, 54, 90, 540 }));

		System.out.println(largestDivisibleSubset2(new int[] { 1 }));
		System.out.println(largestDivisibleSubset2(new int[] { 1, 2, 3 }));
		System.out.println(largestDivisibleSubset2(new int[] { 1, 2, 4, 8 }));
		System.out.println(largestDivisibleSubset2(new int[] { 5, 9, 18, 54, 108, 540, 90, 180, 360, 720 }));
		System.out.println(largestDivisibleSubset2(new int[] { 5, 9, 18, 54, 90, 540 }));

		System.out.println(largestDivisibleSubset3(new int[] { 1 }));
		System.out.println(largestDivisibleSubset3(new int[] { 1, 2, 3 }));
		System.out.println(largestDivisibleSubset3(new int[] { 1, 2, 4, 8 }));
		System.out.println(largestDivisibleSubset3(new int[] { 5, 9, 18, 54, 108, 540, 90, 180, 360, 720 }));
		System.out.println(largestDivisibleSubset3(new int[] { 5, 9, 18, 54, 90, 540 }));
	}
}
