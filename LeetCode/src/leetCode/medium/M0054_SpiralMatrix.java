package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0054_SpiralMatrix {
	// solution 1
	public static List<Integer> spiralOrder1(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();

		int n = matrix.length;
		int m = matrix[0].length;
		boolean[][] isVisted = new boolean[n][m];

		int i = 0;
		int j = 0;
		while (true) {
			while (j < m && !isVisted[i][j]) {
				ans.add(matrix[i][j]);
				isVisted[i][j] = true;
				j++;
			}
			j--;
			i++;

			if (j < 0 || j >= m || i < 0 || i >= n || isVisted[i][j]) {
				break;
			}

			while (i < n && !isVisted[i][j]) {
				ans.add(matrix[i][j]);
				isVisted[i][j] = true;
				i++;
			}
			i--;
			j--;

			if (j < 0 || j >= m || i < 0 || i >= n || isVisted[i][j]) {
				break;
			}

			while (j >= 0 && !isVisted[i][j]) {
				ans.add(matrix[i][j]);
				isVisted[i][j] = true;
				j--;
			}
			j++;
			i--;

			if (j < 0 || j >= m || i < 0 || i >= n || isVisted[i][j]) {
				break;
			}

			while (i >= 0 && !isVisted[i][j]) {
				ans.add(matrix[i][j]);
				isVisted[i][j] = true;
				i--;
			}
			i++;
			j++;

			if (j < 0 || j >= m || i < 0 || i >= n || isVisted[i][j]) {
				break;
			}
		}

		return ans;
	}

	// solution 2
	public static List<Integer> spiralOrder2(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();
		int n = matrix.length;
		int m = matrix[0].length;
		boolean[][] isVisted = new boolean[n][m];
		String curDir = "r";

		int i = 0;
		int j = 0;
		while (i >= 0 && i < n && j >= 0 && j < m && !isVisted[i][j]) {
			ans.add(matrix[i][j]);
			isVisted[i][j] = true;

			if (curDir == "r") {
				j++;
			} else if (curDir == "d") {
				i++;
			} else if (curDir == "l") {
				j--;
			} else if (curDir == "u") {
				i--;
			}

			if (curDir == "r" && (j == m || isVisted[i][j])) {
				j--;
				i++;
				curDir = "d";
			} else if (curDir == "d" && (i == n || isVisted[i][j])) {
				i--;
				j--;
				curDir = "l";
			} else if (curDir == "l" && (j == -1 || isVisted[i][j])) {
				j++;
				i--;
				curDir = "u";
			} else if (curDir == "u" && (j == -1 || isVisted[i][j])) {
				i++;
				j++;
				curDir = "r";
			}
		}

		return ans;
	}

	// solution 3
	public static List<Integer> spiralOrder3(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();
		int n = matrix.length;
		int m = matrix[0].length;

		int left = 0;
		int right = m - 1;
		int top = 0;
		int bottom = n - 1;

		while (left <= right && top <= bottom) {
			// left to right
			for (int j = left; j <= right; j++) {
				ans.add(matrix[top][j]);
			}
			top++;

			// top to bottom
			for (int i = top; i <= bottom; i++) {
				ans.add(matrix[i][right]);
			}
			right--;

			// right to left
			if (top <= bottom) {
				for (int j = right; j >= left; j--) {
					ans.add(matrix[bottom][j]);
				}
				bottom--;
			}

			// bottom to top
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					ans.add(matrix[i][left]);
				}
				left++;
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(spiralOrder1(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }));
		System.out.println(spiralOrder1(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } }));
		System.out.println(spiralOrder1(new int[][] { { 1 } }));

		System.out.println(spiralOrder2(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }));
		System.out.println(spiralOrder2(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } }));
		System.out.println(spiralOrder2(new int[][] { { 1 } }));

		System.out.println(spiralOrder3(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }));
		System.out.println(spiralOrder3(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } }));
		System.out.println(spiralOrder3(new int[][] { { 1 } }));
	}

}
