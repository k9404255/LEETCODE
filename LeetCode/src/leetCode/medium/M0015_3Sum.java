package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class M0015_3Sum {
	// solution 1: brute-force (Time Limit Exceeded)
	public static List<List<Integer>> threeSum1(int[] nums) {
		HashSet<List<Integer>> set = new HashSet<List<Integer>>();

		for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					int v1 = nums[i];
					int v2 = nums[j];
					int v3 = nums[k];
					if (v1 + v2 + v3 == 0) {
						List<Integer> tmp = Arrays.asList(v1, v2, v3);
						Collections.sort(tmp);
						set.add(tmp);
					}
				}
			}
		}

		return new ArrayList<>(set);
	}

	// solution 2: sorting, two pointers
	public static List<List<Integer>> threeSum2(int[] nums) {
		HashSet<List<Integer>> set = new HashSet<List<Integer>>();
		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				int v1 = nums[i];
				int v2 = nums[j];
				int v3 = nums[k];
				int sum = v1 + v2 + v3;

				if (sum == 0) {
					set.add(Arrays.asList(v1, v2, v3));
					k--;
					j++;
				} else if (sum > 0) {
					k--;
				} else if (sum < 0) {
					j++;
				}
			}
		}

		return new ArrayList<>(set);
	}

	public static void main(String[] args) {
		System.out.println(threeSum1(new int[] { -1, 0, 1, 2, -1, -4 }));
		System.out.println(threeSum1(new int[] { 0, 1, 1 }));
		System.out.println(threeSum1(new int[] { 0, 0, 0 }));

		System.out.println(threeSum2(new int[] { -1, 0, 1, 2, -1, -4 }));
		System.out.println(threeSum2(new int[] { 0, 1, 1 }));
		System.out.println(threeSum2(new int[] { 0, 0, 0 }));
	}
}