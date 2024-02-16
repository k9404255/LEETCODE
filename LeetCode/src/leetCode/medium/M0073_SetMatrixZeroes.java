package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0073_SetMatrixZeroes {
	public static void printNums(int[][] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print("[");
			for (int j = 0; j < nums[i].length - 1; j++) {
				System.out.print(nums[i][j] + ", ");
			}
			System.out.println(nums[i][nums[i].length - 1] + "]");
		}
	}

	// solution 1: my way
	public static void setZeroes1(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		List<int[]> list = new ArrayList<int[]>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					list.add(new int[] { i, j });
				}
			}
		}

		for (int[] node : list) {
			int x = node[0];
			int y = node[1];

			for (int i = 0; i < m; i++) {
				matrix[i][y] = 0;
			}

			for (int j = 0; j < n; j++) {
				matrix[x][j] = 0;
			}
		}
	}

	// solution 2: brute-force
	public static void setZeroes2(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int matrix2[][] = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				matrix2[i][j] = matrix[i][j];
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					for (int k = 0; k < n; k++)
						matrix2[i][k] = 0;

					for (int k = 0; k < m; k++)
						matrix2[k][j] = 0;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				matrix[i][j] = matrix2[i][j];
		}

	}

	// solution 3: better
	public static void setZeroes3(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int rowsArray[] = new int[m];
		int colsArray[] = new int[n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					rowsArray[i] = 1;
					colsArray[j] = 1;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rowsArray[i] == 1 || colsArray[j] == 1)
					matrix[i][j] = 0;
			}
		}
	}

	// solution 4: optimal
	public static void setZeroes4(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		boolean isRow0 = false, isCol0 = false;

		for (int j = 0; j < n; j++) {
			if (matrix[0][j] == 0)
				isRow0 = true;
		}

		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0)
				isCol0 = true;
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[0][j] == 0 || matrix[i][0] == 0)
					matrix[i][j] = 0;
			}
		}

		if (isRow0) {
			for (int j = 0; j < n; j++)
				matrix[0][j] = 0;
		}

		if (isCol0) {
			for (int i = 0; i < m; i++)
				matrix[i][0] = 0;
		}
	}

	public static void main(String[] args) {
		int[][] matrix;
		matrix = new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		setZeroes1(matrix);
		printNums(matrix);

		matrix = new int[][] { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		setZeroes1(matrix);
		printNums(matrix);
	}
}
