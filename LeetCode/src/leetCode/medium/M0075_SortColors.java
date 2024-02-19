package leetCode.medium;

import java.util.Arrays;

public class M0075_SortColors {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1: bubble sort
	public static void sortColors1(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j + 1] < nums[j]) {
					int tmp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = tmp;
				}
			}
		}
	}

	// solution 2: count element
	public static void sortColors2(int[] nums) {
		int[] count = new int[3];

		for (int i = 0; i < nums.length; i++) {
			count[nums[i]]++;
		}

		Arrays.fill(nums, 0, count[0], 0);
		Arrays.fill(nums, count[0], count[0] + count[1], 1);
		Arrays.fill(nums, count[0] + count[1], nums.length, 2);
	}

	// solution 3
	public static void sortColors3(int[] nums) {
		int left = 0;
		int mid = 0;
		int right = nums.length - 1;

		while (mid <= right) {
			if (nums[mid] == 0) {
				swap(nums, mid, left);
				mid++;
				left++;
			} else if (nums[mid] == 1) {
				mid++;
			} else if (nums[mid] == 2) {
				swap(nums, mid, right);
				right--;
			}
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		int[] nums;

		nums = new int[] { 2, 0, 2, 1, 1, 0 };
		sortColors1(nums);
		printNums(nums);

		nums = new int[] { 2, 0, 1 };
		sortColors1(nums);
		printNums(nums);

		nums = new int[] { 2, 0, 2, 1, 1, 0 };
		sortColors2(nums);
		printNums(nums);

		nums = new int[] { 2, 0, 1 };
		sortColors2(nums);
		printNums(nums);

		nums = new int[] { 2, 0, 2, 1, 1, 0 };
		sortColors3(nums);
		printNums(nums);

		nums = new int[] { 2, 0, 1 };
		sortColors3(nums);
		printNums(nums);
	}
}
