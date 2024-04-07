package leetCode.easy;

import java.util.Arrays;

public class E0027_RemoveElement {
	// solution 1
	public static int removeElement1(int[] nums, int val) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == val) {
				nums[i] = Integer.MAX_VALUE;
			} else {
				count++;
			}
		}

		Arrays.sort(nums);
		return count;
	}

	// solution 2
	public static int removeElement2(int[] nums, int val) {
		int n = nums.length;
		int lastIdx = n - 1;

		int i = 0;
		while (i <= lastIdx) {
			if (nums[i] == val) {
				// swap
				int tmp = nums[lastIdx];
				nums[lastIdx] = nums[i];
				nums[i] = tmp;

				lastIdx--;
			} else {
				i++;
			}
		}

		return lastIdx + 1;
	}

	// solution 3
	public static int removeElement3(int[] nums, int val) {
		int idx = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[idx] = nums[i];
				idx++;
			}
		}

		return idx;
	}

	public static void main(String[] args) {
//		System.out.println(removeElement1(new int[] { 3, 2, 2, 3 }, 3));
//		System.out.println(removeElement1(new int[] { 0, 1, 2, 2, 3, 0, 4, 2 }, 2));

//		System.out.println(removeElement2(new int[] { 3, 2, 2, 3 }, 3));
//		System.out.println(removeElement2(new int[] { 0, 1, 2, 2, 3, 0, 4, 2 }, 2));
//		System.out.println(removeElement2(new int[] { 2, 2, 2, 2, 3, 2, 3 }, 3));
//		System.out.println(removeElement2(new int[] { 2, 2, 2, 2 }, 2));

		System.out.println(removeElement3(new int[] { 3, 2, 2, 3 }, 3));
		System.out.println(removeElement3(new int[] { 0, 1, 2, 2, 3, 0, 4, 2 }, 2));
		System.out.println(removeElement3(new int[] { 2, 2, 2, 2, 3, 2, 3 }, 3));
		System.out.println(removeElement3(new int[] { 2, 2, 2, 2 }, 2));
	}

}
