package leetCode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class M0207_CourseSchedule {
	// solution 1: topology sort
	// https://web.ntnu.edu.tw/~algo/DirectedAcyclicGraph.html
	public static boolean canFinish1(int numCourses, int[][] prerequisites) {
		int n = prerequisites.length;
		int[] ref = new int[numCourses]; // 記錄每一個點被其他多少點連到

		// 記錄每一個點被其他多少點連到
		for (int i = 0; i < n; i++) {
			ref[prerequisites[i][0]]++;
		}

		for (int i = 0; i < numCourses; i++) {
			// 尋找沒有被任何邊連向的點
			int k = 0;
			while (k < numCourses && ref[k] != 0) {
				k++;
			}

			// 找不到則break
			if (k == numCourses) {
				break;
			}

			ref[k] = -1; // visited
			// 將該節點移除 (代表相連的點要更新)
			for (int j = 0; j < n; j++) {
				if (prerequisites[j][1] == k) {
					ref[prerequisites[j][0]]--;
				}
			}
		}

		// 判斷是否殘留節點是有被其他點相連到的 (代表有cycle)
		for (int i = 0; i < numCourses; i++) {
			if (ref[i] > 0) {
				return false;
			}
		}

		return true;
	}

	// solution 2: queue
	public static boolean canFinish2(int n, int[][] prerequisites) {
		int[][] adj = new int[n][n];
		int[] indegree = new int[n]; // 記錄每一個點被其他多少點連到
		List<Integer> ans = new ArrayList<>();

		// build adjency matrix
		for (int[] pair : prerequisites) {
			int i = pair[1];
			int j = pair[0];
			adj[i][j] = 1;
			indegree[j]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		// 尋找沒有被任何邊連向的點
		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			ans.add(cur);

			// remove edge
			for (int i = 0; i < n; i++) {
				if (adj[cur][i] == 1) {
					indegree[i]--;
					if (indegree[i] == 0) {
						queue.add(i);
					}
				}
			}
		}

//		for (int i = 0; i < n; i++) {
//			if (indegree[i] > 0) {
//				return false;
//			}
//		}

		return ans.size() == n;
	}

	// solution 3: queue and array list
	public static boolean canFinish3(int n, int[][] prerequisites) {
		List<Integer>[] adj = new List[n];
		int[] indegree = new int[n]; // 記錄每一個點被其他多少點連到
		List<Integer> ans = new ArrayList<>();

		// build adjency matrix
		for (int[] pair : prerequisites) {
			int i = pair[1];
			int j = pair[0];

			if (adj[i] == null) {
				adj[i] = new ArrayList<>();
			}

			adj[i].add(j);
			indegree[j]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		// 尋找沒有被任何邊連向的點
		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			ans.add(cur);

			// remove edge
			if (adj[cur] != null) {
				for (int next : adj[cur]) {
					indegree[next]--;
					if (indegree[next] == 0) {
						queue.offer(next);
					}
				}
			}
		}

		return ans.size() == n;
	}

	public static void main(String[] args) {
		System.out.println(canFinish1(2, new int[][] { { 1, 0 } }));
		System.out.println(canFinish1(2, new int[][] { { 1, 0 }, { 0, 1 } }));
		System.out.println(canFinish1(3, new int[][] { { 1, 0 }, { 2, 1 }, { 1, 2 } }));

		System.out.println(canFinish2(2, new int[][] { { 1, 0 } }));
		System.out.println(canFinish2(2, new int[][] { { 1, 0 }, { 0, 1 } }));
		System.out.println(canFinish2(3, new int[][] { { 1, 0 }, { 2, 1 }, { 1, 2 } }));
	}
}
