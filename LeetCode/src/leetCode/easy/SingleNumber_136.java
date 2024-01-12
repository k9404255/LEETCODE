package leetCode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleNumber_136 {
	// solution 1
	public static int singleNumber1(int[] nums) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			int index = list.indexOf(nums[i]);
			if (index != -1) {
				list.remove(index);
			} else {
				list.add(nums[i]);
			}
		}

		return list.get(0);
	}

	// solution 2: xor
	public static int singleNumber2(int[] nums) {
		int ans = nums[0];
		for (int i = 1; i < nums.length; i++) {
			ans ^= nums[i];
		}

		return ans;
	}

	// solution 3: sort (not linear runtime)
	public static int singleNumber3(int[] nums) {
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i += 2) {
			if (nums[i] != nums[i + 1]) {
				return nums[i];
			}
		}

		return nums[nums.length - 1];
	}

	public static void main(String[] args) {
		System.out.println(singleNumber1(new int[] { 2, 2, 1 }));
		System.out.println(singleNumber1(new int[] { 4, 1, 2, 1, 2 }));
		System.out.println(singleNumber1(new int[] { 1 }));

		System.out.println(singleNumber2(new int[] { 2, 2, 1 }));
		System.out.println(singleNumber2(new int[] { 4, 1, 2, 1, 2 }));
		System.out.println(singleNumber2(new int[] { 1 }));

		System.out.println(singleNumber3(new int[] { 2, 2, 1 }));
		System.out.println(singleNumber3(new int[] { 4, 1, 2, 1, 2 }));
		System.out.println(singleNumber3(new int[] { 1 }));
	}
}
