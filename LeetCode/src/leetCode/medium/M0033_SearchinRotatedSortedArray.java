package leetCode.medium;

public class M0033_SearchinRotatedSortedArray {
	// solution 1: O(n)
	public static int search1(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				return i;
			}
		}

		return -1;
	}

	// solution 2
	public static int search2(int[] nums, int target) {
		// 1. Find pivot
		int left = 0;
		int right = nums.length - 1;
		int pivot = -1;

		while (left < right) {
			int mid = (left + right) / 2;

			if (nums[mid] > nums[mid + 1]) {
				pivot = mid + 1;
				break;
			} else if (nums[mid] > nums[left]) {
				left = mid;
			} else {
				right = mid;
			}
		}

		// 2. Divide nums into two ascending lists and apply binary search to each
		// subList
		if (pivot == -1) {
			return binarySearch(nums, 0, nums.length - 1, target);
		}

		int idx1 = binarySearch(nums, 0, pivot - 1, target);
		int idx2 = binarySearch(nums, pivot, nums.length - 1, target);

		return idx1 == -1 ? (idx2 == -1 ? -1 : idx2) : idx1;
	}

	public static int binarySearch(int[] nums, int left, int right, int target) {
		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			}
		}

		return -1;
	}

	// solution 3: revised binary search
	public static int search3(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] == target) {
				return mid;
			}

			if (nums[mid] >= nums[left]) {
				if (target >= nums[left] && target < nums[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (target > nums[mid] && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		// System.out.println(search1(new int[] {4, 5, 6, 7, 0, 1, 2 }, 0));
		// System.out.println(search1(new int[] {4, 5, 6, 7, 0, 1, 2 }, 3));
		// System.out.println(search1(new int[] {1 }, 0));

		// System.out.println(search2(new int[] {4, 5, 6, 7, 0, 1, 2 }, 0));
		// System.out.println(search2(new int[] {4, 5, 6, 7, 0, 1, 2 }, 3));
		// System.out.println(search2(new int[] {1 }, 0));
		// System.out.println(search2(new int[] {1, 2, 3, 4, 5, 6, 0 }, 0));
		// System.out.println(search2(new int[] {6, 0, 1, 2, 3, 4, 5 }, 0));
		// System.out.println(search2(new int[] {1, 0 }, 0));
		// System.out.println(search2(new int[] {1, 3 }, 0));
		System.out.println(search2(new int[] { 5, 1, 3 }, 3));
	}
}