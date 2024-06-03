package leetCode.medium;

public class M0162_FindPeakElement {
	// solution 1: brute-force (O(n))
	public static int findPeakElement1(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if ((i == 0 || nums[i] > nums[i - 1]) && (i == n - 1 || nums[i] > nums[i + 1])) {
				return i;
			}
		}

		return -1;
	}

	// solution 2
	public static int findPeakElement2(int[] nums) {
		int n = nums.length;

		if (n == 1)
			return 0; // single element

		// check if 0th/n-1th index is the peak element
		if (nums[0] > nums[1])
			return 0;
		if (nums[n - 1] > nums[n - 2])
			return n - 1;

		// search in the remaining array
		int start = 1;
		int end = n - 2;

		while (start <= end) {
			int mid = start + (end - start) / 2;
			System.out.println("start: " + start + ", end: " + end + ", mid: " + mid);
			if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
				return mid;
			else if (nums[mid] < nums[mid + 1])
				start = mid + 1;
			else if (nums[mid] < nums[mid - 1])
				end = mid - 1;
		}

		return -1; // dummy return statement
	}

	public static void main(String[] args) {
//		System.out.println(findPeakElement1(new int[] { 1, 2, 3, 1 }));
//		System.out.println(findPeakElement1(new int[] { 1, 2, 1, 3, 5, 6, 4 }));
		System.out.println(findPeakElement1(new int[] { 1, 3, 2, 7, 8, 5 }));

//		System.out.println(findPeakElement2(new int[] { 1, 2, 3, 1 }));
//		System.out.println(findPeakElement2(new int[] { 1, 2, 1, 3, 5, 6, 4 }));
		System.out.println(findPeakElement2(new int[] { 1, 3, 2, 7, 8, 5 }));
	}
}
