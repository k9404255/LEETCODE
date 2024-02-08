package leetCode.medium;

public class M0048_RotateImage {
	public static void print(int[][] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++) {
				System.out.print(nums[i][j] + " ");
			}
			System.out.println();
		}
	}

	// solution 1: extra space, not in-place
	public static void rotate1(int[][] matrix) {
		// 0 0 --> 0 2
		// 0 1 --> 1 2
		// 0 2 --> 2 2
		// 1 0 --> 0 1
		// 1 1 --> 1 1
		// 1 2 --> 2 1
		// 2 0 --> 0 0
		// 2 1 --> 1 0
		// 2 2 --> 2 0

		int n = matrix.length;
		int[][] copy = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				copy[i][j] = matrix[i][j];
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[j][n - i - 1] = copy[i][j];
			}
		}
	}

	// solution 2: in-place
	public static void rotate2(int[][] matrix) {
		int n = matrix.length;
		int temp = 0;

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n - i - 1; j++) {
				temp = matrix[n - j - 1][i];
				matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
				matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
				matrix[j][n - i - 1] = matrix[i][j];
				matrix[i][j] = temp;
			}
		}
	}

	// solution 3: in-place (tranpose + reverse)
	public static void rotate3(int[][] matrix) {
		int n = matrix.length;

		// 1. tranpose
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}

		// 2. reverse
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n / 2; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][n - j - 1];
				matrix[i][n - j - 1] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix;

		matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotate1(matrix);
		print(matrix);

		matrix = new int[][] { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
		rotate1(matrix);
		print(matrix);

		matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotate2(matrix);
		print(matrix);

		matrix = new int[][] { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
		rotate2(matrix);
		print(matrix);

		matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		rotate3(matrix);
		print(matrix);

		matrix = new int[][] { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
		rotate3(matrix);
		print(matrix);
	}

}
