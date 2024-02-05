package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class M0046_Permutations {
	// solution 1
	public static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void helper1(List<List<Integer>> ans, int cur, int[] nums) {
		if (cur == nums.length) {
			ans.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
			return;
		}

		for (int i = cur; i < nums.length; i++) {
			swap(nums, cur, i);
			helper1(ans, cur + 1, nums);
			swap(nums, i, cur);
		}
	}

	public static List<List<Integer>> permute1(int[] nums) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		helper1(ans, 0, nums);

		return ans;
	}

	// solution 2
	public static void helper2(List<List<Integer>> ans, List<Integer> list, int[] nums) {
		if (list.size() == nums.length) {
			ans.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (!list.contains(nums[i])) {
				list.add(nums[i]);
				helper2(ans, list, nums);
				list.remove(list.size() - 1);
			}
		}
	}

	public static List<List<Integer>> permute2(int[] nums) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		helper2(ans, new ArrayList<Integer>(), nums);

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(permute1(new int[] { 1, 2, 3 }));
		System.out.println(permute1(new int[] { 0, 1 }));
		System.out.println(permute1(new int[] { 1 }));

		System.out.println(permute2(new int[] { 1, 2, 3 }));
		System.out.println(permute2(new int[] { 0, 1 }));
		System.out.println(permute2(new int[] { 1 }));
	}
}