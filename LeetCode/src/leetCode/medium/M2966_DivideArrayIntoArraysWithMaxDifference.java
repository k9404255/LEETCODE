package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class M2966_DivideArrayIntoArraysWithMaxDifference {
	public static void print1Nums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	public static void print2Nums(int[][] nums) {
		if (nums.length == 0) {
			System.out.println("[]");
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			System.out.print("{");
			for (int j = 0; j < nums[i].length; j++) {
				System.out.print(nums[i][j] + " ");
			}
			System.out.print("}, ");
		}
		System.out.println();
	}

	// solution 1
	public static int[][] divideArray1(int[] nums, int k) {
		Arrays.sort(nums);
		List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
		List<int[]> answers = new ArrayList<int[]>();

		while (true) {
			int count = 0;
			int[] subNums = new int[3];
			boolean flag = false;
			for (int i = 0; i < list.size(); i++) {
				int num = list.get(i);
				if (count == 0 || num - subNums[0] <= k) {
					subNums[count] = num;
					count++;
				}
				if (count == 3) {
					answers.add(subNums);
					flag = true;
					break;
				}
			}

			if (!flag) {
				break;
			} else {
				for (int i = 0; i < subNums.length; i++) {
					list.remove(new Integer(subNums[i]));
				}
			}
		}

		if (list.isEmpty()) {
			return answers.toArray(new int[0][0]);
		} else {
			return new int[0][0];
		}
	}

	// solution 2
	public static int[][] divideArray2(int[] nums, int k) {
		Arrays.sort(nums);

		int[][] result = new int[nums.length / 3][3];

		for (int i = 0; i < nums.length; i += 3) {
			if (nums[i + 2] - nums[i] <= k) {
				result[i / 3] = new int[] { nums[i], nums[i + 1], nums[i + 2] };
			} else {
				return new int[0][0];
			}
		}

		return result;
	}

	// solution 3: 與solution2基本一樣，但用array copy擴充大小
	public static int[][] divideArray3(int[] nums, int k) {
		Arrays.sort(nums);

		int[][] result = new int[0][0];

		for (int i = 0; i < nums.length; i += 3) {
			if (nums[i + 2] - nums[i] <= k) {
				result = Arrays.copyOf(result, result.length + 1);
				result[result.length - 1] = new int[] { nums[i], nums[i + 1], nums[i + 2] };
			} else {
				return new int[0][0];
			}
		}

		return result;
	}

	public static void main(String[] args) {
		print2Nums(divideArray1(new int[] { 1, 3, 4, 8, 7, 9, 3, 5, 1 }, 2));
		print2Nums(divideArray1(new int[] { 1, 3, 3, 2, 7, 3 }, 3));

		print2Nums(divideArray2(new int[] { 1, 3, 4, 8, 7, 9, 3, 5, 1 }, 2));
		print2Nums(divideArray2(new int[] { 1, 3, 3, 2, 7, 3 }, 3));
	}
}