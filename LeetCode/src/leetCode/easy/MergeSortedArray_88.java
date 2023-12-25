package leetCode.easy;

import java.util.Arrays;

public class MergeSortedArray_88 {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	public static void merge1(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;

		while (j >= 0) {
			if (i >= 0 && nums1[i] > nums2[j]) {
				nums1[k--] = nums1[i--];
			} else {
				nums1[k--] = nums2[j--];
			}
		}
	}

	public static void merge2(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;
		while (i >= 0 && j >= 0) {
			if (nums1[i] > nums2[j]) {
				nums1[k--] = nums1[i--];
			} else {
				nums1[k--] = nums2[j--];
			}
		}
		while (j >= 0) {
			nums1[k--] = nums2[j--];
		}
	}

	public static void merge3(int[] nums1, int m, int[] nums2, int n) {
		for (int i = m; i < m + n; i++) {
			nums1[i] = nums2[i - m];
		}
		Arrays.sort(nums1);
	}

	public static void main(String[] args) {
		int[] nums1;
		nums1 = new int[] { 1, 2, 3, 0, 0, 0 };
		merge1(nums1, 3, new int[] { 2, 5, 6 }, 3);
		printNums(nums1);

		nums1 = new int[] { 1 };
		merge1(nums1, 1, new int[] {}, 0);
		printNums(nums1);

		nums1 = new int[] { 0 };
		merge1(nums1, 0, new int[] { 1 }, 1);
		printNums(nums1);

		nums1 = new int[] { 4, 0, 0, 0, 0, 0 };
		merge1(nums1, 1, new int[] { 1, 2, 3, 5, 6 }, 5);
		printNums(nums1);

		nums1 = new int[] { -1, 0, 0, 3, 3, 3, 0, 0, 0 };
		merge1(nums1, 6, new int[] { 1, 2, 2 }, 3);
		printNums(nums1);
	}
}