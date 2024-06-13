package leetCode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/solutions/1547354/3-different-approaches-for-interview-with-comments/
public class M0378_KthSmallestElementinaSortedMatrix {
	// solution 1: O(n) space
	public static int kthSmallest1(int[][] matrix, int k) {
		int n = matrix.length;
		int[] indices = new int[n];

		int cur = 0;
		int minIdx = -1;
		int min = Integer.MAX_VALUE;
		while (cur < k) {
			minIdx = -1;
			min = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (indices[i] < n && matrix[i][indices[i]] < min) {
					min = matrix[i][indices[i]];
					minIdx = i;
				}
			}

			indices[minIdx]++;

			cur++;
		}

		return min;
	}

	// solution 2: O(n^2) space
	public static int kthSmallest2(int[][] matrix, int k) {
		int n = matrix.length;
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				list.add(matrix[i][j]);
			}
		}

		list.sort(null);

		return list.get(k - 1);
	}

	// solution 3: PriorityQueue
	public static int kthSmallest3(int[][] matrix, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		int n = matrix.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (pq.size() < k) {
					pq.add(matrix[i][j]);
				} else { // equal to k
					if (matrix[i][j] < pq.peek()) { // if incoming element is less than peek
						pq.poll();
						pq.add(matrix[i][j]);
					}
				}
			}
		}

		return pq.peek();
	}

	// solution 4: best (我看起來這解法是錯的)
	public static int kthSmallest4(int[][] matrix, int k) {
		int n = matrix.length;

		int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int count = 0, maxNum = lo;

			for (int r = 0, c = n - 1; r < n; r++) {
				while (c >= 0 && matrix[r][c] > mid)
					c--;

				if (c >= 0) {
					count += (c + 1); // count of nums <= mid in matrix
					maxNum = Math.max(maxNum, matrix[r][c]);
					// mid might be value not in matrix, we need to record the actually max num;
				} else { // it means c < 0
					break;
				}
			}

			// adjust search range
			if (count == k)
				return maxNum;
			else if (count < k)
				lo = mid + 1;
			else
				hi = mid - 1;
		}

		return lo;
	}

	// solution 5
	public static int kthSmallest5(int[][] matrix, int k) {
		int n = matrix.length;
		int low = matrix[0][0];
		int high = matrix[n - 1][n - 1];

		while (low < high) {
			int mid = low + (high - low) / 2;
			int count = lessEqual(matrix, mid);
			if (count < k) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

	public static int lessEqual(int[][] matrix, int target) {
		int count = 0, len = matrix.length, i = len - 1, j = 0;

		while (i >= 0 && j < len) {
			if (matrix[i][j] > target) {
				i--;
			} else {
				count = count + i + 1;
				j++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
//		System.out.println(kthSmallest1(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
//		System.out.println(kthSmallest1(new int[][] { { -5 } }, 1));
		System.out.println(kthSmallest1(new int[][] { { 3, 4 }, { 1, 2 } }, 2));

//		System.out.println(kthSmallest2(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
//		System.out.println(kthSmallest2(new int[][] { { -5 } }, 1));
		System.out.println(kthSmallest2(new int[][] { { 3, 4 }, { 1, 2 } }, 2));

//		System.out.println(kthSmallest3(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
//		System.out.println(kthSmallest3(new int[][] { { -5 } }, 1));
		System.out.println(kthSmallest3(new int[][] { { 3, 4 }, { 1, 2 } }, 2));

//		System.out.println(kthSmallest4(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
//		System.out.println(kthSmallest4(new int[][] { { -5 } }, 1));
		System.out.println(kthSmallest4(new int[][] { { 3, 4 }, { 1, 2 } }, 2));

		System.out.println(kthSmallest5(new int[][] { { 3, 4 }, { 1, 2 } }, 2));
	}
}
