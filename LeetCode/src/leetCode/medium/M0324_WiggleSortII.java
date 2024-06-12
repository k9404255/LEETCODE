package leetCode.medium;

import java.util.Arrays;

public class M0324_WiggleSortII {
	public static void printNums(int[] nums, int len) {
		for (int i = 0; i < len; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1: O(nlogn) time, O(n) space, not in-place
	public static void wiggleSort1(int[] nums) {
		int len = nums.length;
		int[] tmpNums = Arrays.copyOf(nums, len);
		Arrays.sort(tmpNums);

		int n = len / 2 - (len % 2 == 0 ? 1 : 0);
		int m = len - 1;
		for (int i = 0; i < len; i++) {
			if (i % 2 == 0) {
				nums[i] = tmpNums[n--];
			} else {
				nums[i] = tmpNums[m--];
			}
		}

		// 1,4,5,6,1,1
		// 1 4 1 5 1 6
	}

	// solution 2
	public static void wiggleSort2(int[] nums) {
		int[] val = Arrays.copyOf(nums, nums.length);
		Arrays.sort(val);
		int idx = val.length - 1;
		for (int i = 1; i < nums.length; i += 2)
			nums[i] = val[idx--];
		for (int i = 0; i < nums.length; i += 2)
			nums[i] = val[idx--];
	}

	// solution 3: Quickselect + DNF (Follow Up)
	// https://bclin.tw/2022/03/02/leetcode-324/
	public static void wiggleSort3(int[] nums) {

	}

	public static void main(String[] args) {
		int[] nums;
		nums = new int[] { 1, 5, 1, 1, 6, 4 };
		wiggleSort2(nums);
		printNums(nums, nums.length);

		nums = new int[] { 1, 3, 2, 2, 3, 1 };
		wiggleSort2(nums);
		printNums(nums, nums.length);

		nums = new int[] { 1, 1, 2, 1, 2, 2, 1 };
		wiggleSort2(nums);
		printNums(nums, nums.length);

		nums = new int[] { 4, 5, 5, 6 };
		wiggleSort2(nums);
		printNums(nums, nums.length);
	}
}
