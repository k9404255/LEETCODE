package leetCode.medium;

import java.util.Arrays;

public class M0056_MergeIntervals {
	public static void printNums(int[][] nums) {
		if (nums == null) {
			System.out.println("null");
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			System.out.print("[");
			for (int j = 0; j < nums[i].length; j++) {
				System.out.print(nums[i][j] + ", ");
			}
			System.out.print("], ");
		}
		System.out.println();
	}

	// solution 1
	public static int[][] merge1(int[][] intervals) {
		// 1. sorting
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

		int[][] ans = new int[][] { { intervals[0][0], intervals[0][1] } };

		// 2. merge
		for (int i = 1; i < intervals.length; i++) {
			int curStart = intervals[i][0];
			int curEnd = intervals[i][1];
			int n = ans.length;
			int lastEnd = ans[n - 1][1];

			if (lastEnd >= curStart) {
				ans[n - 1][1] = Math.max(ans[n - 1][1], curEnd);
			} else {
				ans = Arrays.copyOf(ans, n + 1);
				ans[n] = intervals[i];
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		printNums(merge1(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } }));
		printNums(merge1(new int[][] { { 1, 4 }, { 4, 5 } }));
		printNums(merge1(new int[][] { { 1, 4 }, { 2, 3 } }));
	}
}