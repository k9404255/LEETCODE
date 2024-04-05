package leetCode.medium;

public class M0238_ProductofArrayExceptSelf {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1: brute-force O(n^2)
	public static int[] productExceptSelf1(int[] nums) {
		int n = nums.length;
		int[] ans = new int[n];

		for (int i = 0; i < n; i++) {
			int product = 1;
			for (int j = 0; j < n; j++) {
				if (i != j) {
					product *= nums[j];
				}
			}
			ans[i] = product;
		}

		return ans;
	}

	// solution 2
	public static int[] productExceptSelf2(int[] nums) {
		int n = nums.length;
		int product = 1;
		int count = 0;

		for (int i = 0; i < n; i++) {
			if (nums[i] != 0) {
				product *= nums[i];
			} else {
				count++;
			}
		}

		int[] ans = new int[n];
		if (count > 1) {
			return ans;
		} else if (count == 1) {
			for (int i = 0; i < n; i++) {
				if (nums[i] == 0) {
					ans[i] = product;
				}
			}
		} else {
			for (int i = 0; i < n; i++) {
				ans[i] = product / nums[i];
			}
		}

		return ans;
	}

	// solution 3: extra space
	public static int[] productExceptSelf3(int[] nums) {
		int n = nums.length;
		int[] prefix = nums.clone();
		int[] suffix = nums.clone();
		int[] ans = new int[n];

		for (int i = 1; i < n; i++) {
			prefix[i] = prefix[i - 1] * prefix[i];
		}

		for (int i = n - 2; i >= 0; i--) {
			suffix[i] = suffix[i + 1] * suffix[i];
		}

		for (int i = 1; i < n - 1; i++) {
			ans[i] = prefix[i - 1] * suffix[i + 1];
		}
		ans[0] = suffix[1];
		ans[n - 1] = prefix[n - 2];

		return ans;
	}

	// solution 4: extra space
	public static int[] productExceptSelf4(int[] nums) {
		int n = nums.length;
		int pre[] = new int[n];
		int suff[] = new int[n];
		pre[0] = 1;
		suff[n - 1] = 1;

		for (int i = 1; i < n; i++) {
			pre[i] = pre[i - 1] * nums[i - 1];
		}
		for (int i = n - 2; i >= 0; i--) {
			suff[i] = suff[i + 1] * nums[i + 1];
		}

		int ans[] = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = pre[i] * suff[i];
		}
		return ans;
	}

	// solution 5: without extra space
	public static int[] productExceptSelf5(int[] nums) {
		int n = nums.length;
		int ans[] = new int[n];
		ans[0] = 1;

		// calculate prefix
		for (int i = 1; i < n; i++) {
			ans[i] = ans[i - 1] * nums[i - 1];
		}

		printNums(ans);

		int curr = 1;
		for (int i = n - 1; i >= 0; i--) {
			ans[i] *= curr;
			curr *= nums[i];
		}

		return ans;
	}

	public static void main(String[] args) {
//		printNums(productExceptSelf1(new int[] { 1, 2, 3, 4 }));
//		printNums(productExceptSelf1(new int[] { -1, 1, 0, -3, 3 }));
//
//		printNums(productExceptSelf2(new int[] { 1, 2, 3, 4 }));
//		printNums(productExceptSelf2(new int[] { -1, 1, 0, -3, 3 }));
//
//		printNums(productExceptSelf3(new int[] { 1, 2, 3, 4 }));
//		printNums(productExceptSelf3(new int[] { -1, 1, 0, -3, 3 }));

//		printNums(productExceptSelf4(new int[] { 1, 2, 3, 4 }));
//		printNums(productExceptSelf4(new int[] { -1, 1, 0, -3, 3 }));

		printNums(productExceptSelf5(new int[] { 1, 2, 3, 4 }));
		printNums(productExceptSelf5(new int[] { -1, 1, 0, -3, 3 }));
	}
}
