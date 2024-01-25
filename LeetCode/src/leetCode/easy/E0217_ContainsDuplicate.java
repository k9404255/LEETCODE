package leetCode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class E0217_ContainsDuplicate {
	// solution 1: sort
	public static boolean containsDuplicate1(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				return true;
			}
		}

		return false;
	}

	// solution 2: hash set
	public static boolean containsDuplicate2(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i])) {
				return true;
			}

			set.add(nums[i]);
		}

		return false;
	}

	public static void main(String[] args) {
		System.out.println(containsDuplicate1(new int[] { 1, 2, 3, 1 }));
		System.out.println(containsDuplicate1(new int[] { 1, 2, 3, 4 }));
		System.out.println(containsDuplicate1(new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 }));
		System.out.println(containsDuplicate1(new int[] { 1, 2, 2 }));

		System.out.println(containsDuplicate2(new int[] { 1, 2, 3, 1 }));
		System.out.println(containsDuplicate2(new int[] { 1, 2, 3, 4 }));
		System.out.println(containsDuplicate2(new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 }));
		System.out.println(containsDuplicate2(new int[] { 1, 2, 2 }));
	}
}