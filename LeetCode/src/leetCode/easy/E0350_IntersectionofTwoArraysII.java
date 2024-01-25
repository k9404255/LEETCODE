package leetCode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class E0350_IntersectionofTwoArraysII {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1: Hash Map
	public static int[] intersect1(int[] nums1, int[] nums2) {
		// List<Integer> list = new ArrayList<Integer>();
		int[] nums = new int[Math.min(nums1.length, nums2.length)];
		int len = 0;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums1.length; i++) {
			map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
		}

		for (int i = 0; i < nums2.length; i++) {
			int count = map.getOrDefault(nums2[i], 0);
			if (count > 0) {
				// list.add(nums2[i]);
				nums[len++] = nums2[i];
				map.put(nums2[i], count - 1);
			}
		}

		// return list.stream().mapToInt(Integer::intValue).toArray();
		return Arrays.copyOf(nums, len);
	}

	// solution 2: sort
	public static int[] intersect2(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);

		int[] nums = new int[Math.min(nums1.length, nums2.length)];
		int count = 0;

		int i = 0;
		int j = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] > nums2[j]) {
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				nums[count++] = nums1[i];
				i++;
				j++;
			}
		}

		return Arrays.copyOf(nums, count);
	}

	public static void main(String[] args) {
		printNums(intersect1(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 }));
		printNums(intersect1(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 8, 4 }));

		printNums(intersect2(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 }));
		printNums(intersect2(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 8, 4 }));
	}
}