package leetCode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class M0128_LongestConsecutiveSequence {
	// solution 1: O(nlogn)
	public static int longestConsecutive1(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		Arrays.sort(nums);

		int max = 1;
		int sum = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				continue;
			} else if (nums[i] - 1 == nums[i - 1]) {
				sum++;
				max = Math.max(max, sum);
			} else {
				max = Math.max(max, sum);
				sum = 1;
			}
		}

		return max;
	}

	// solution 2: hash set (find from the left)
	public static int longestConsecutive2(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}

		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (!set.contains(nums[i] - 1)) {
				int nextNum = nums[i] + 1;
				int sum = 1;
				while (set.contains(nextNum)) {
					sum++;
					nextNum++;
				}
				max = Math.max(max, sum);
			}
		}

		return max;
	}

	// solution 3: hash set (remove both way)
	public static int longestConsecutive3(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}

		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			int n = nums[i];
			int prev = n - 1;
			int next = n + 1;

			int sum = 1;
			while (set.remove(prev)) {
				sum++;
				prev--;
			}
			while (set.remove(next)) {
				sum++;
				next++;
			}

			max = Math.max(max, sum);
		}

		return max;
	}

	public static void main(String[] args) {
		System.out.println(longestConsecutive1(new int[] { 100, 4, 200, 1, 3, 2 }));
		System.out.println(longestConsecutive1(new int[] { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 }));

		System.out.println(longestConsecutive2(new int[] { 100, 4, 200, 1, 3, 2 }));
		System.out.println(longestConsecutive2(new int[] { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 }));
		System.out.println(longestConsecutive2(new int[] { 2, 1, 5, 3 }));

		System.out.println(longestConsecutive3(new int[] { 100, 4, 200, 1, 3, 2 }));
		System.out.println(longestConsecutive3(new int[] { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 }));
		System.out.println(longestConsecutive3(new int[] { 2, 1, 5, 3 }));
	}
}
