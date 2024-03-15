package leetCode.medium;

public class M0152_MaximumProductSubarray {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1: brute-force
	public static int maxProduct1(int[] nums) {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length; i++) {
			int sum = 1;
			for (int j = i; j < nums.length; j++) {
				sum *= nums[j];
				max = Math.max(max, sum);
			}
		}

		return max;
	}

	// solution 2
	public static int maxProduct2(int[] nums) {
		int max = nums[0];

		int cur = 1;
		int negativeNum = 0;
		int leftProduct = 1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 0) {
				negativeNum++;
				if (leftProduct == 1) {
					leftProduct = cur * nums[i];
					System.out.println("left: " + leftProduct);
				}
			}

			if (nums[i] == 0) {
				cur = 1;
				max = Math.max(max, 0);
				negativeNum = 0;
				leftProduct = 1;
			} else if (nums[i] > 0 && cur > 0) {
				cur *= nums[i];
				max = Math.max(max, cur);
			} else if (nums[i] > 0 && cur < 0) {
				cur *= nums[i];
				max = Math.max(max, nums[i]);
				max = Math.max(max, cur / leftProduct);
			} else if (nums[i] < 0 && cur < 0) {
				cur *= nums[i];
				max = Math.max(max, cur);
			} else if (nums[i] < 0 && cur > 0 && negativeNum == 1) {
				cur *= nums[i];
			} else if (nums[i] < 0 && cur > 0 && negativeNum >= 3) {
				cur *= nums[i];
				max = Math.max(max, cur / leftProduct);
			}
		}

		return max;
	}

	// solution 3: dp
	public static int maxProduct3(int[] nums) {
		int n = nums.length;
		int min = nums[0];
		int max = nums[0];
		int ans = nums[0];

		for (int i = 1; i < n; i++) {
			if (nums[i] < 0) {
				int tmp = max;
				max = min;
				min = tmp;
			}

			max = Math.max(max * nums[i], nums[i]);
			min = Math.min(min * nums[i], nums[i]);
			ans = Math.max(max, ans);
//			System.out.println("nums: " + nums[i] + ", max: " + max + ", min: " + min + ", ans: " + ans);
		}

		return ans;
	}

	// solution 4
	public static int maxProduct4(int[] nums) {
		int n = nums.length;
		int min = nums[0];
		int max = nums[0];
		int ans = nums[0];

		for (int i = 1; i < n; i++) {
			int tmpMax = max * nums[i];
			int tmpMin = min * nums[i];

			max = Math.max(Math.max(tmpMax, tmpMin), nums[i]);
			min = Math.min(Math.min(tmpMax, tmpMin), nums[i]);

			ans = Math.max(ans, max);
			System.out.println("nums: " + nums[i] + ", max: " + max + ", min: " + min + ", ans: " + ans);
		}

		return ans;
	}

	// solution 5: dp
	public static int maxProduct5(int[] nums) {
		int n = nums.length;
		if (n == 0) {
			return 0;
		}

		int[] dpMax = new int[n];
		dpMax[0] = nums[0];
		int[] dpMin = new int[n];
		dpMin[0] = nums[0];
		int max = nums[0];
		for (int i = 1; i < n; i++) {
			dpMax[i] = Math.max(dpMin[i - 1] * nums[i], Math.max(dpMax[i - 1] * nums[i], nums[i]));
			dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(dpMax[i - 1] * nums[i], nums[i]));
			max = Math.max(max, dpMax[i]);
		}

		return max;
	}

	// solution 6: dp (更新 dp[i] 的时候，我们只用到 dp[i-1]
	// 的信息，再之前的信息就用不到了。所以我们完全不需要一个数组，只需要一个变量去重复覆盖更新即可。)
	public static int maxProduct6(int[] nums) {
		int n = nums.length;
		if (n == 0) {
			return 0;
		}
		int dpMax = nums[0];
		int dpMin = nums[0];
		int max = nums[0];
		for (int i = 1; i < n; i++) {
			// 更新 dpMin 的时候需要 dpMax 之前的信息，所以先保存起来
			int preMax = dpMax;
			dpMax = Math.max(dpMin * nums[i], Math.max(dpMax * nums[i], nums[i]));
			dpMin = Math.min(dpMin * nums[i], Math.min(preMax * nums[i], nums[i]));
			max = Math.max(max, dpMax);
		}
		return max;
	}

	public static void main(String[] args) {
//		System.out.println(maxProduct1(new int[] { 2, 3, -2, 4 }));
//		System.out.println(maxProduct1(new int[] { -2, 0, -1 }));

//		System.out.println(maxProduct2(new int[] { 2, 3, -2, 4 })); // 6
//		System.out.println(maxProduct2(new int[] { -2, 0, -1 })); // 0
//		System.out.println(maxProduct2(new int[] { -2, 3 })); // 3
//		System.out.println(maxProduct2(new int[] { -2, 3, -4 })); // 24
//		System.out.println(maxProduct2(new int[] { -2, 3, -4, -1, 99 })); // 1188
//		System.out.println(maxProduct2(new int[] { -2, -3, -1, -2, -99 })); // 594
//		System.out.println(maxProduct2(new int[] { -2, -3, -1, -99, -3 })); // 891
//		System.out.println(maxProduct2(new int[] { -2, -3, 0, -99, -3 })); // 297
//		System.out.println(maxProduct2(new int[] { -1, -2, -9, -6 })); // 108
//		System.out.println(maxProduct2(new int[] { 6, 3, -10, 0, 2 })); // 18
//		System.out.println(maxProduct2(new int[] { 2, -5, -2, -4, 3 })); // 24
//		System.out.println(maxProduct2(new int[] { -2, 3, 1, 3 })); // 9
//		System.out.println(maxProduct2(new int[] { -2, 0, -3, -3 })); // 9

		System.out.println(maxProduct3(new int[] { 3, 4, -5, 10, 2, -3 }));
		System.out.println(maxProduct3(new int[] { 2, 3, -2, 4 })); // 6
//		System.out.println(maxProduct3(new int[] { -2, 0, -1 })); // 0
//		System.out.println(maxProduct3(new int[] { -2, 3 })); // 3

		System.out.println(maxProduct4(new int[] { 3, 4, -5, 10, 2, -3 }));
//		System.out.println(maxProduct4(new int[] { 2, 3, -2, 4 })); // 6
	}
}
