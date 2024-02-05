package leetCode.medium;

public class M0034_FindFirstandLastPositionofElementinSortedArray {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1: O(n)
	public static int[] searchRange1(int[] nums, int target) {
		int left = -1;
		int right = -1;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target && left == -1) {
				left = i;
				right = i;
			} else if (nums[i] == target) {
				right = i;
			}
		}

		return new int[] { left, right };
	}

	// solution 2: O(logn)
	public static int searchLeft2(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] == target && mid > 0 && nums[mid - 1] == target) {
				right = mid - 1;
			} else {
				return mid;
			}
		}

		return -1;
	}

	public static int searchRight2(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else if (nums[mid] == target && mid < nums.length - 1 && nums[mid + 1] == target) {
				left = mid + 1;
			} else {
				return mid;
			}
		}

		return -1;
	}

	public static int[] searchRange2(int[] nums, int target) {
		int left = -1;
		int right = -1;

		left = searchLeft2(nums, target);
		right = searchRight2(nums, target);

		return new int[] { left, right };
	}

	// solution 3: O(logn)
	public static int searchLeft3(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int ans = -1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] == target) {
				ans = mid;
			}

			if (nums[mid] >= target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return ans;
	}

	public static int searchRight3(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int ans = -1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] == target) {
				ans = mid;
			}

			if (nums[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return ans;
	}

	public static int[] searchRange3(int[] nums, int target) {
		int left = -1;
		int right = -1;

		left = searchLeft3(nums, target);
		right = searchRight3(nums, target);

		return new int[] { left, right };
	}

	public static void main(String[] args) {
		printNums(searchRange1(new int[] { 5, 7, 7, 8, 8, 10 }, 8));
		printNums(searchRange1(new int[] { 5, 7, 7, 8, 8, 10 }, 6));
		printNums(searchRange1(new int[0], 0));

		printNums(searchRange2(new int[] { 5, 7, 7, 8, 8, 10 }, 8));
		printNums(searchRange2(new int[] { 5, 7, 7, 8, 8, 10 }, 6));
		printNums(searchRange2(new int[0], 0));
		printNums(searchRange2(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0));

		printNums(searchRange3(new int[] { 5, 7, 7, 8, 8, 10 }, 8));
		printNums(searchRange3(new int[] { 5, 7, 7, 8, 8, 10 }, 6));
		printNums(searchRange3(new int[0], 0));
		printNums(searchRange3(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0));
	}
}