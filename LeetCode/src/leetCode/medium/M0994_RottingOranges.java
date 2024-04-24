package leetCode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class M0994_RottingOranges {
	public static void printNums(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	// solution 1
	public static int orangesRotting1(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;

		Queue<int[]> queue = new LinkedList<>();
		int minute = 0;
		while (true) {
			// find rotten oranges
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (grid[i][j] == 2) {
						grid[i][j] = 3;
						queue.add(new int[] { i, j });
					}
				}
			}

			if (queue.isEmpty()) {
				break;
			}

			// rot neighbor organes
			boolean isRotten = false;
			while (!queue.isEmpty()) {
				int[] coordinates = queue.poll();
				int x = coordinates[0];
				int y = coordinates[1];

				if (x - 1 >= 0 && grid[x - 1][y] == 1) {
					isRotten = true;
					grid[x - 1][y] = 2;
				}
				if (x + 1 < n && grid[x + 1][y] == 1) {
					isRotten = true;
					grid[x + 1][y] = 2;
				}
				if (y - 1 >= 0 && grid[x][y - 1] == 1) {
					isRotten = true;
					grid[x][y - 1] = 2;
				}
				if (y + 1 < m && grid[x][y + 1] == 1) {
					isRotten = true;
					grid[x][y + 1] = 2;
				}
			}

			if (isRotten) {
				minute++;
			} else {
				break;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					return -1;
				}
			}
		}

		return minute;
	}

	// solution 2
	// https://leetcode.com/problems/rotting-oranges/solutions/3166554/c-bfs-easiest-beginner-friendly-sol-o-n-2-time-and-o-n-2-space/
	public int orangesRotting2(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] visited = grid;
		Queue<int[]> q = new LinkedList<>();
		int countFreshOrange = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] == 2) {
					q.offer(new int[] { i, j });
				}
				if (visited[i][j] == 1) {
					countFreshOrange++;
				}
			}
		}

		if (countFreshOrange == 0) {
			return 0;
		}
		if (q.isEmpty()) {
			return -1;
		}

		int minutes = -1;
		int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int[] cell = q.poll();
				int x = cell[0];
				int y = cell[1];
				for (int[] dir : dirs) {
					int i = x + dir[0];
					int j = y + dir[1];
					if (i >= 0 && i < m && j >= 0 && j < n && visited[i][j] == 1) {
						visited[i][j] = 2;
						countFreshOrange--;
						q.offer(new int[] { i, j });
					}
				}
			}
			minutes++;
		}

		if (countFreshOrange == 0) {
			return minutes;
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(orangesRotting1(new int[][] { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } }));
		System.out.println(orangesRotting1(new int[][] { { 2, 1, 1 }, { 0, 1, 1 }, { 1, 0, 1 } }));
		System.out.println(orangesRotting1(new int[][] { { 0, 2 } }));
		System.out.println(orangesRotting1(new int[][] { { 1, 2 } }));
		System.out.println(orangesRotting1(new int[][] { { 0 } }));
		System.out.println(orangesRotting1(new int[][] { { 1 } }));
	}
}
