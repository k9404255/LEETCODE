package leetCode.medium;

public class M0153_FindMinimuminRotatedSortedArray {
	// solution 1
	public static int findMin1(int[] nums) {
		int n = nums.length;
		int left = 0;
		int right = n - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			int midVal = nums[mid];
			int leftVal = nums[left];
			int rightVal = nums[right];

			if (midVal >= leftVal && midVal <= rightVal) {
				return leftVal;
			} else if (midVal >= leftVal && midVal > rightVal) {
				left = mid + 1;
			} else if (midVal < leftVal && midVal < rightVal) {
				right = mid;
			}

//			1 2 3 4 5 // mid > left && mid < right
//			5 1 2 3 4 // mid < left && mid < right
//			4 5 1 2 3 // mid < left && mid < right
//			3 4 5 1 2 // mid > left && mid > right
//			2 3 4 5 1 // mid > left && mid > right
		}

		return 0;
	}

	// solution 2: solution1刪去多餘code
	public static int findMin2(int[] nums) {
		int n = nums.length;
		int left = 0;
		int right = n - 1;

		while (left < right) {
			int mid = (left + right) / 2;
			int midVal = nums[mid];
			int rightVal = nums[right];

			if (midVal > rightVal) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return nums[left];
	}

	public static void main(String[] args) {
//		System.out.println(findMin1(new int[] { 1, 2, 3, 4, 5 }));
//		System.out.println(findMin1(new int[] { 5, 1, 2, 3, 4 }));
//		System.out.println(findMin1(new int[] { 4, 5, 1, 2, 3 }));
//		System.out.println(findMin1(new int[] { 3, 4, 5, 1, 2 }));
//		System.out.println(findMin1(new int[] { 2, 3, 4, 5, 1 }));
//		System.out.println(findMin1(new int[] { 4, 5, 6, 7, 0, 1, 2 }));
//		System.out.println(findMin1(new int[] { 11, 13, 15, 17 }));
		System.out.println(findMin2(new int[] { 1, 2, 3, 4 }));
		System.out.println(findMin2(new int[] { 4, 1, 2, 3 }));
		System.out.println(findMin2(new int[] { 3, 4, 1, 2 }));
		System.out.println(findMin2(new int[] { 2, 3, 4, 1 }));
	}
}
