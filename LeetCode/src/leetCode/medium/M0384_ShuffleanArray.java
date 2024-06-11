package leetCode.medium;

import java.util.Arrays;
import java.util.Random;

public class M0384_ShuffleanArray {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1
	static class Solution1 {
		private int[] originalNums;
		private Random rand;

		public Solution1(int[] nums) {
			this.originalNums = Arrays.copyOf(nums, nums.length);
			rand = new Random();
		}

		public int[] reset() {
			return originalNums;
		}

		public int[] shuffle() {
			int[] ans = new int[originalNums.length];
			boolean added[] = new boolean[originalNums.length];

			for (int i = 0; i < ans.length; i++) {
				int index = rand.nextInt(ans.length);
				if (!added[index]) {
					added[index] = true;
					ans[i] = originalNums[index];
				} else {
					i--;
				}
			}

			return ans;
		}
	}

	static class Solution2 {
		private int[] originalNums;
		private Random rand;

		public Solution2(int[] nums) {
			this.originalNums = Arrays.copyOf(nums, nums.length);
			rand = new Random();
		}

		public int[] reset() {
			return originalNums;
		}

		public int[] shuffle() {
			int[] shuffle = originalNums.clone();
			
			// suffle the nums (index)nums.length-1 to 0th index
			for (int i = originalNums.length - 1; i > 0; i--) {
				int index = rand.nextInt(i + 1);
				// swap nums
				int temp = shuffle[i];
				shuffle[i] = shuffle[index];
				shuffle[index] = temp;
			}
			
			return shuffle;
		}
	}

	public static void main(String[] args) {
		Solution2 obj = new Solution2(new int[] { 1, 2, 3 });
		printNums(obj.reset());
		printNums(obj.shuffle());
	}
}
