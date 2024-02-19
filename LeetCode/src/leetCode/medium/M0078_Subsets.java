package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0078_Subsets {
	// solution 1
	public static List<List<Integer>> subsets1(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();

		for (int len = 0; len <= nums.length; len++) {
			helper1(nums, 0, len, new ArrayList<>(), ans);
		}

		return ans;
	}

	public static void helper1(int[] nums, int cur, int len, List<Integer> tmp, List<List<Integer>> ans) {
		if (tmp.size() == len) {
			ans.add(new ArrayList<>(tmp));
			return;
		}

		for (int i = cur; i < nums.length; i++) {
			tmp.add(nums[i]);
			helper1(nums, i + 1, len, tmp, ans);
			tmp.remove(tmp.size() - 1);
		}
	}

	// solution 2
	public static List<List<Integer>> subsets2(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();

		helper2(nums, 0, new ArrayList<>(), ans);

		return ans;
	}

	public static void helper2(int[] nums, int cur, List<Integer> tmp, List<List<Integer>> ans) {
		ans.add(new ArrayList<>(tmp));

		for (int i = cur; i < nums.length; i++) {
			tmp.add(nums[i]);
			helper2(nums, i + 1, tmp, ans);
			tmp.remove(tmp.size() - 1);
		}
	}

	// solution 3
	public static List<List<Integer>> subsets3(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		ans.add(new ArrayList<>());

		for (int i = 0; i < nums.length; i++) {
			List<List<Integer>> tmp = new ArrayList<>();
			for (List<Integer> list : ans) {
				List<Integer> subset = new ArrayList<>(list);
				subset.add(nums[i]);
				tmp.add(subset);
			}
			ans.addAll(tmp);
		}

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(subsets1(new int[] { 1, 2, 3 }));
		System.out.println(subsets1(new int[] { 0 }));

		System.out.println(subsets2(new int[] { 1, 2, 3 }));
		System.out.println(subsets2(new int[] { 0 }));

		System.out.println(subsets3(new int[] { 1, 2, 3 }));
		System.out.println(subsets3(new int[] { 0 }));
	}
}
