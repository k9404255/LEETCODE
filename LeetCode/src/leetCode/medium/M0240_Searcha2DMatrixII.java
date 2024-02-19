package leetCode.medium;

public class M0240_Searcha2DMatrixII {
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;

		int i = 0;
		int j = n - 1;

		while (i < m && j >= 0) {
			if (matrix[i][j] == target) {
				return true;
			} else if (matrix[i][j] > target) {
				j--;
			} else if (matrix[i][j] < target) {
				i++;
			}
		}

		return false;
	}
}