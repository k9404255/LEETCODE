package leetCode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangle_118 {
	// Solution 1
	public static List<List<Integer>> generate1(int numRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		for (int i = 0; i <= numRows - 1; i++) {
			result.add(new ArrayList<Integer>());
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					result.get(i).add(1);
				} else {
					result.get(i).add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
				}
			}
		}

		return result;
	}

	// Solution 2
	public static List<List<Integer>> generate2(int numRows) {
		if (numRows == 1) {
			List<List<Integer>> result = new ArrayList<List<Integer>>();
			result.add(Arrays.asList(1));
			return result;
		}

		if (numRows == 2) {
			List<List<Integer>> result = new ArrayList<List<Integer>>();
			result.add(Arrays.asList(1));
			result.add(Arrays.asList(1, 1));
			return result;
		}

		List<List<Integer>> preTriangle = generate2(numRows - 1);
		List<Integer> curRow = new ArrayList<Integer>();

		curRow.add(1);
		for (int i = 1; i < numRows - 1; i++) {
			curRow.add(preTriangle.get(numRows - 2).get(i - 1) + preTriangle.get(numRows - 2).get(i));
		}
		curRow.add(1);
		preTriangle.add(curRow);

		return preTriangle;
	}

	public static void main(String[] args) {
		System.out.println(generate1(1));
		System.out.println(generate1(2));
		System.out.println(generate1(5));
		System.out.println(generate1(10));

		System.out.println(generate2(1));
		System.out.println(generate2(2));
		System.out.println(generate2(5));
		System.out.println(generate2(10));
	}
}