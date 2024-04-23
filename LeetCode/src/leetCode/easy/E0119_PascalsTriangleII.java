package leetCode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E0119_PascalsTriangleII {
	// solution 1
	public static List<Integer> getRow1(int rowIndex) {
		List<List<Integer>> triangle = new ArrayList<>();

		triangle.add(Arrays.asList(1));

		for (int i = 1; i <= rowIndex; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(1);
			for (int j = 1; j <= i - 1; j++) {
				int left = triangle.get(i - 1).get(j - 1);
				int right = triangle.get(i - 1).get(j);
				list.add(left + right);
			}
			list.add(1);
			triangle.add(list);
		}

		return triangle.get(rowIndex);
	}

	public static void main(String[] args) {
		System.out.println(getRow1(3));
		System.out.println(getRow1(0));
		System.out.println(getRow1(1));
	}
}
