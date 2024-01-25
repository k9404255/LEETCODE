package leetCode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class E0169_MajorityElement {
	public static int majorityElement1(int[] nums) {
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);

			if (countMap.get(nums[i]) > nums.length / 2) {
				return nums[i];
			}
		}

		return 0;
	}

	public static int majorityElement2(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}

	public static void main(String[] args) {
		System.out.println(majorityElement1(new int[] { 3, 2, 3 }));
		System.out.println(majorityElement1(new int[] { 2, 2, 1, 1, 1, 2, 2 }));

		System.out.println(majorityElement2(new int[] { 3, 2, 3 }));
		System.out.println(majorityElement2(new int[] { 2, 2, 1, 1, 1, 2, 2 }));
	}
}