package leetCode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class M0200_NumberofIslands {
	public static void printGrid(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// solution 1: BFS
	public static int numIslands1(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int ans = 0;

		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (grid[i][j] == '1') {
					ans++;
					// BFS
					grid[i][j] = '2'; // mark discovered
					queue.add(new int[] { i, j });
					while (!queue.isEmpty()) {
						int[] coordinate = queue.remove();
						int x = coordinate[0];
						int y = coordinate[1];

						if (x - 1 >= 0 && grid[x - 1][y] == '1') {
							grid[x - 1][y] = '2';
							queue.add(new int[] { x - 1, y });
						}
						if (x + 1 < m && grid[x + 1][y] == '1') {
							grid[x + 1][y] = '2';
							queue.add(new int[] { x + 1, y });
						}
						if (y - 1 >= 0 && grid[x][y - 1] == '1') {
							grid[x][y - 1] = '2';
							queue.add(new int[] { x, y - 1 });
						}
						if (y + 1 < n && grid[x][y + 1] == '1') {
							grid[x][y + 1] = '2';
							queue.add(new int[] { x, y + 1 });
						}
					}
				}

			}
		}

		return ans;
	}

	// solution 2: DFS
	private static final int[] delRow = { 1, -1, 0, 0 };
	private static final int[] delCol = { 0, 0, -1, 1 };

	public static int numIslands2(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int ans = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					ans++;
					dfs(grid, m, n, i, j);
				}

			}
		}

		return ans;
	}

	public static void dfs(char[][] grid, int m, int n, int i, int j) {
		if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
			return;
		}

		grid[i][j] = '2';
		for (int k = 0; k < 4; k++) {
			int y = i + delRow[k];
			int x = j + delCol[k];

			dfs(grid, m, n, y, x);
		}
	}

	public static void main(String[] args) {
//		System.out.println(numIslands1(new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
//				{ '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } }));
//		System.out.println(numIslands1(new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
//				{ '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } }));

		System.out.println(numIslands2(new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
				{ '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } }));
		System.out.println(numIslands2(new char[][] { { '1', '0' }, { '0', '1' } }));
		System.out.println(numIslands2(new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
				{ '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } }));
	}
}
