package leetCode.easy;

import java.util.LinkedList;
import java.util.Queue;

public class E1700_NumberofStudentsUnabletoEatLunch {
	// solution 1
	public static int countStudents1(int[] students, int[] sandwiches) {
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < students.length; i++) {
			queue.add(students[i]);
		}

		int curSandwitch = 0;
		int unableToEat = 0;
		while (!queue.isEmpty() && unableToEat < queue.size()) {
			int firstStudent = queue.poll();
			if (firstStudent == sandwiches[curSandwitch]) {
				curSandwitch++;
				unableToEat = 0;
			} else {
				queue.add(firstStudent);
				unableToEat++;
			}
		}

		return queue.size();
	}

	// solution 2
	public static int countStudents2(int[] students, int[] sandwiches) {
		int[] studentCounts = new int[2];
		for (int i : students) {
			studentCounts[i]++;
		}

		int remain = sandwiches.length;
		for (int i : sandwiches) {
			if (studentCounts[i] == 0) {
				break;
			}

			studentCounts[i]--;
			remain--;
		}

		return remain;
	}

	public static void main(String[] args) {
		System.out.println(countStudents1(new int[] { 1, 1, 0, 0 }, new int[] { 0, 1, 0, 1 }));
		System.out.println(countStudents1(new int[] { 1, 1, 1, 0, 0, 1 }, new int[] { 1, 0, 0, 0, 1, 1 }));

		System.out.println(countStudents2(new int[] { 1, 1, 0, 0 }, new int[] { 0, 1, 0, 1 }));
		System.out.println(countStudents2(new int[] { 1, 1, 1, 0, 0, 1 }, new int[] { 1, 0, 0, 0, 1, 1 }));
	}
}
