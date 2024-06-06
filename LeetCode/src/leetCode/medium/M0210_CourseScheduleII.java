package leetCode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class M0210_CourseScheduleII {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1
	public static int[] findOrder1(int numCourses, int[][] prerequisites) {
		int[][] adj = new int[numCourses][numCourses];
		int[] indegree = new int[numCourses];

		for (int[] pair : prerequisites) {
			int i = pair[1];
			int j = pair[0];
			adj[i][j] = 1;
			indegree[j]++;
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		List<Integer> list = new ArrayList<>();
		while (!queue.isEmpty()) {
			int idx = queue.poll();
			list.add(idx);

			for (int i = 0; i < numCourses; i++) {
				if (adj[idx][i] == 1) {
					adj[idx][i] = 0; // 可有可無
					indegree[i]--;
					if (indegree[i] == 0) {
						queue.add(i);
					}
				}
			}
		}

		return list.size() != numCourses ? new int[] {} : list.stream().mapToInt(Integer::intValue).toArray();
	}

	// solution 2
	public static int[] findOrder2(int numCourses, int[][] prerequisites) {
		List<Integer>[] adj = new List[numCourses];
		int[] indegree = new int[numCourses];

		for (int[] pair : prerequisites) {
			int i = pair[1];
			int j = pair[0];

			if (adj[i] == null) {
				adj[i] = new ArrayList<>();
			}

			adj[i].add(j);
			indegree[j]++;
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		List<Integer> list = new ArrayList<>();
		while (!queue.isEmpty()) {
			int idx = queue.poll();
			list.add(idx);

			if (adj[idx] != null) {
				for (int next : adj[idx]) {
					indegree[next]--;
					if (indegree[next] == 0) {
						queue.offer(next);
					}
				}
			}
		}

		return list.size() != numCourses ? new int[] {} : list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) {
		printNums(findOrder1(2, new int[][] { { 1, 0 } }));
		printNums(findOrder1(4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } }));
		printNums(findOrder1(1, new int[][] {}));

	}
}
