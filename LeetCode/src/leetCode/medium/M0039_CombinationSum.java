package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0039_CombinationSum {
	// backtracing, backtrack
	public static void helper(List<List<Integer>> ans, List<Integer> list, int cur, int[] candidates, int target) {
		if (target < 0) {
			return;
		}

		if (target == 0) {
			ans.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = cur; i < candidates.length; i++) {
			list.add(candidates[i]);
			helper(ans, list, i, candidates, target - candidates[i]);
			list.remove(list.size() - 1);
		}
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		helper(ans, new ArrayList<Integer>(), 0, candidates, target);

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(combinationSum(new int[] { 2, 3, 6, 7 }, 7));
		System.out.println(combinationSum(new int[] { 2, 3, 5 }, 8));
		System.out.println(combinationSum(new int[] { 2 }, 1));
	}
}