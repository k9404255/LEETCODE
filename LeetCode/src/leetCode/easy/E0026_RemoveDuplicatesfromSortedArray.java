package leetCode.easy;

public class E0026_RemoveDuplicatesfromSortedArray {

	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// Solution 1
	public static int removeDuplicates1(int[] nums) {
		int result = 1;
		int length = nums.length;
		for (int i = 0; i + 1 < length;) {
			if (nums[i] != nums[i + 1]) {
				result++;
				i++;
			} else {
				for (int j = i + 1; j < nums.length - 1; j++) {
					nums[j] = nums[j + 1];
				}
				nums[length - 1] = -1;
				length -= 1;
			}
		}

		return result;
	}

	// Solution 2
	public static int removeDuplicates2(int[] nums) {
		int curIdx = 0;

		for (int i = 1; i < nums.length; i++) {
			if (nums[curIdx] != nums[i]) {
				curIdx++;
				nums[curIdx] = nums[i];
			}
		}

		return curIdx;
	}

	public static void main(String[] args) {
		System.out.println(removeDuplicates1(new int[] { 1, 1, 2 }));
		System.out.println(removeDuplicates1(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }));
		System.out.println(removeDuplicates1(new int[] { 1, 1 }));
		System.out.println(removeDuplicates1(new int[] { 1, 2 }));

		System.out.println(removeDuplicates2(new int[] { 1, 1, 2 }));
		System.out.println(removeDuplicates2(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }));
		System.out.println(removeDuplicates2(new int[] { 1, 1 }));
		System.out.println(removeDuplicates2(new int[] { 1, 2 }));
	}
}
