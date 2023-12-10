package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TwoSum_1 {
	public static void printArray(int[] nums) {
		System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
	}

	// solution 1: brute-force
	public static int[] twoSum1(int[] nums, int target) {
		List<Integer> results = new ArrayList<Integer>();

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					results.addAll(Arrays.asList(i, j));
					return results.stream().mapToInt(ii -> ii).toArray();
				}
			}
		}

		return null;
	}

	// solution 2: Hash Map
	public static int[] twoSum2(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];

			if (map.containsKey(nums[i])) {
				int answer[] = new int[] { map.get(nums[i]), i };
				// printArray(answer);
				return answer;
			}

			map.put(complement, i);
		}
		return null;
	}

	public static void main(String[] args) {
		twoSum1(new int[] { 2, 7, 11, 15 }, 9);
		twoSum1(new int[] { 3, 2, 4 }, 6);
		twoSum1(new int[] { 3, 3 }, 6);

		twoSum2(new int[] { 2, 7, 11, 15 }, 9);
		twoSum2(new int[] { 3, 2, 4 }, 6);
		twoSum2(new int[] { 3, 3 }, 6);
		twoSum2(new int[] { -1, -2, -3, -4, -5 }, -8);
		twoSum2(new int[] { -10, -1, -18, -19 }, -19);
	}
}
