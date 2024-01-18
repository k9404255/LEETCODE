package leetCode.easy;

import java.util.Arrays;

public class MissingNumber_268 {
	// solution 1: sort, time: O(nlogn), space: O(1)
	public static int missingNumber1(int[] nums) {
		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i) {
				return i;
			}
		}

		return nums.length;
	}

	// solution 2: num array, time: O(n), space: O(1)
	public static int missingNumber2(int[] nums) {
		int[] counts = new int[10000];

		for (int i = 0; i < nums.length; i++) {
			counts[nums[i]] = 1;
		}

		for (int i = 0; i < nums.length + 1; i++) {
			if (counts[i] == 0) {
				return i;
			}
		}

		return 0;
	}

	// solution 3: math: 0 + 1 + 2 + 3 +. . . . . . + n = (n*(n+1))/2
	public static int missingNumber3(int[] nums) {
		int n = nums.length;
		int expectSum = n * (n + 1) / 2;

		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}

		return expectSum - sum;
	}

	public static void main(String[] args) {
		System.out.println(missingNumber1(new int[] { 3, 0, 1 }));
		System.out.println(missingNumber1(new int[] { 0, 1 }));
		System.out.println(missingNumber1(new int[] { 9, 6, 4, 2, 3, 5, 7, 0, 1 }));

		System.out.println(missingNumber2(new int[] { 3, 0, 1 }));
		System.out.println(missingNumber2(new int[] { 0, 1 }));
		System.out.println(missingNumber2(new int[] { 9, 6, 4, 2, 3, 5, 7, 0, 1 }));

		System.out.println(missingNumber3(new int[] { 3, 0, 1 }));
		System.out.println(missingNumber3(new int[] { 0, 1 }));
		System.out.println(missingNumber3(new int[] { 9, 6, 4, 2, 3, 5, 7, 0, 1 }));
	}
}