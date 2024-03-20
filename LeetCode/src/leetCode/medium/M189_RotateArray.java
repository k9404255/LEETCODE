package leetCode.medium;

public class M189_RotateArray {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ", ");
		}
		System.out.println();
	}

	// solution 1: Time Limit Exceeded
	public static void rotate1(int[] nums, int k) {
		int n = nums.length;
		k = k % n;
		while (k > 0) {
			int tmp = nums[n - 1];
			for (int i = n - 1; i >= 1; i--) {
//				System.out.println("i: " + i + ", x: " + (i - k + n) % n); // +n以維持正數
				nums[i] = nums[(i - k + n) % n]; // +n以維持正數
//				printNums(nums);
			}
			nums[0] = tmp;
			k--;
		}
	}

	// solution 2: extra space
	public static void rotate2(int[] nums, int k) {
		int n = nums.length;
		k = k % n;
		int[] tmpNums = nums.clone();
		for (int i = n - 1; i >= 0; i--) {
//			System.out.println("i: " + i + ", x: " + (i - k + n) % n); // +n以維持正數
			nums[i] = tmpNums[(i - k + n) % n]; // +n以維持正數
			printNums(nums);
		}
	}

	// solution 3: reverse
	public static void rotate3(int[] nums, int k) {
		int n = nums.length;
		k = k % n;
		printNums(nums);
		reverse(nums, 0, n - k - 1);
		printNums(nums);
		reverse(nums, n - k, n - 1);
		printNums(nums);
		reverse(nums, 0, n - 1);
		printNums(nums);
	}

	public static void reverse(int[] nums, int i, int j) {
		while (i < j) {
			int tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
			i++;
			j--;
		}
	}

	public static void main(String[] args) {
		int[] nums;

//		nums = new int[] { 1, 2, 3, 4, 5, 6, 7 };
//		rotate3(nums, 2); // 6,7,1,2,3,4,5
//		printNums(nums);

		nums = new int[] { 1, 2, 3, 4, 5 };
		rotate3(nums, 2); // 2 3 4 1

		printNums(nums);
	}
}
