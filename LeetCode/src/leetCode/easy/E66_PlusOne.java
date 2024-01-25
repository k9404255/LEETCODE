package leetCode.easy;

public class E66_PlusOne {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	public static int[] plusOne(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			if (++digits[i] < 10) {
				return digits;
			}

			digits[i] = 0;
		}

		int[] ans = new int[digits.length + 1];
		ans[0] = 1;
		for (int i = 1; i < ans.length; i++) {
			ans[i] = digits[i - 1];
		}

		return ans;
	}

	public static void main(String[] args) {
		printNums(plusOne(new int[] { 1, 2, 3 }));
		printNums(plusOne(new int[] { 4, 3, 2, 1 }));
		printNums(plusOne(new int[] { 9 }));
	}
}
