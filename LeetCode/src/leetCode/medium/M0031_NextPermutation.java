package leetCode.medium;

public class M0031_NextPermutation {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	public static void swap(int i, int j, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void reverse(int i, int[] nums) {
		int left = i;
		int right = nums.length - 1;
		while (left < right) {
			swap(left, right, nums);
			left++;
			right--;
		}

	}

	// https://medium.com/%E5%81%8F%E5%81%8F%E7%9A%84%E6%BC%94%E7%AE%97%E6%B3%95%E7%AD%86%E8%A8%98/%E5%88%B7%E9%A1%8C%E7%AD%86%E8%A8%98-leetcode-next-permutation-b635e132deac
	// https://www.cnblogs.com/grandyang/p/4428207.html
	public static void nextPermutation(int[] nums) {
		int n = nums.length;

		int target = -1;
		for (int i = n - 1; i >= 1; i--) {
			if (nums[i] > nums[i - 1]) {
				target = i - 1;
				break;
			}
		}

		if (target != -1) {
			for (int i = n - 1; i > target; i--) {
				if (nums[i] > nums[target]) {
					swap(i, target, nums);
					break;
				}
			}
		}

		reverse(target + 1, nums);
	}

	public static void main(String[] args) {
		int[] nums1 = new int[] { 1, 2, 3 };
		nextPermutation(nums1);
		printNums(nums1);

		int[] nums2 = new int[] { 3, 2, 1 };
		nextPermutation(nums2);
		printNums(nums2);

		int[] nums3 = new int[] { 1, 1, 5 };
		nextPermutation(nums3);
		printNums(nums3);
	}
}