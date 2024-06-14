package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class M0334_IncreasingTripletSubsequence {
	// solution 1: recursive (Time Limit Exceeded)
	public static boolean increasingTriplet1(int[] nums) {
		return helper1(nums, 0, new ArrayList<Integer>());
	}

	public static boolean helper1(int[] nums, int cur, List<Integer> ans) {
		if (ans.size() == 3) {
			if (ans.get(0) < ans.get(1) && ans.get(1) < ans.get(2)) {
				return true;
			} else {
				return false;
			}
		}

		for (int i = cur; i < nums.length; i++) {
			ans.add(nums[i]);
			boolean flag = helper1(nums, i + 1, ans);
			if (flag) {
				return true;
			}
			ans.remove(ans.size() - 1);
		}

		return false;
	}

	// solution 2: brute-force (Time Limit Exceeded)
	public static boolean increasingTriplet2(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] < nums[j]) {
					for (int j2 = j + 1; j2 < nums.length; j2++) {
						if (nums[j] < nums[j2]) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	// solution 3
	public static boolean increasingTriplet3(int[] nums) {
		List<List<Integer>> lists = new ArrayList<>();
		Set<Integer> head = new HashSet<>();

		lists.add(new ArrayList<>(Arrays.asList(nums[0])));
		head.add(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			for (List<Integer> list : lists) {
				int n = list.size();
				if (list.get(n - 1) < nums[i]) {
					list.add(nums[i]);

					if (list.size() == 3) {
						return true;
					}
				} else if (list.get(n - 1) > nums[i] && (n == 1 || (n == 2 && list.get(0) < nums[i]))) {
					list.remove(list.size() - 1);
					list.add(nums[i]);
				}
			}

			if (!head.contains(nums[i])) {
				lists.add(new ArrayList<>(Arrays.asList(nums[i])));
				head.add(nums[i]);
			}
		}

		return false;
	}

	// solution 4: best
	// https://leetcode.com/problems/increasing-triplet-subsequence/solutions/4462160/java-c-o-n-simple-easy-solution-step-by-step-explanation/
	public static boolean increasingTriplet4(int[] nums) {
		if (nums.length < 3)
			return false;

		int first = Integer.MAX_VALUE;
		int second = Integer.MAX_VALUE;

		for (int n : nums) {
			if (n <= first) {
				first = n; // smallest so far
			} else if (n <= second) {
				second = n; // second smallest
			} else {
				System.out.println("first: " + first + ", second: " + second);
				return true; // found a triplet
			}
			System.out.println("first: " + first + ", second: " + second);
		}

		return false;
	}

	public static void main(String[] args) {
//		System.out.println(increasingTriplet1(new int[] { 1, 2, 3, 4, 5 }));
//		System.out.println(increasingTriplet1(new int[] { 5, 4, 3, 2, 1 }));
//		System.out.println(increasingTriplet1(new int[] { 2, 1, 5, 0, 4, 6 }));

//		System.out.println(increasingTriplet2(new int[] { 1, 2, 3, 4, 5 }));
//		System.out.println(increasingTriplet2(new int[] { 5, 4, 3, 2, 1 }));
//		System.out.println(increasingTriplet2(new int[] { 2, 1, 5, 0, 4, 6 }));

//		System.out.println(increasingTriplet3(new int[] { 1, 2, 3, 4, 5 }));
//		System.out.println(increasingTriplet3(new int[] { 5, 4, 3, 2, 1 }));
//		System.out.println(increasingTriplet3(new int[] { 2, 1, 5, 0, 4, 6 }));
//		System.out.println(increasingTriplet3(new int[] { 20, 100, 10, 12, 5, 13 }));
//		System.out.println(increasingTriplet3(new int[] { 1, 5, 0, 4, 1, 3 }));
//		System.out.println(increasingTriplet3(new int[] { 1, 2, 1, 2, 1, 2, 1, 2 }));

		System.out.println(increasingTriplet4(new int[] { 2, 1, 5, 0, 6, 4 }));
	}
}
