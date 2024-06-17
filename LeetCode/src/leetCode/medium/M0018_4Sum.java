package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class M0018_4Sum {
	public static List<List<Long>> threeSum(long[] nums, long target) {
		HashSet<List<Long>> set = new HashSet<List<Long>>();
//		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				long v1 = nums[i];
				long v2 = nums[j];
				long v3 = nums[k];
				long sum = v1 + v2 + v3;

				if (sum == target) {
					set.add(new ArrayList<>(Arrays.asList(v1, v2, v3)));
					// set.add(Arrays.asList(v1, v2, v3));
					k--;
					j++;
				} else if (sum > target) {
					k--;
				} else if (sum < target) {
					j++;
				}
			}
		}

		return new ArrayList<>(set);
	}

	// solution 1: based on three sum
	public static List<List<Integer>> fourSum1(int[] nums, int target) {
		Arrays.sort(nums);
		long[] longNums = new long[nums.length];
		for (int i = 0; i < nums.length; i++) {
			longNums[i] = nums[i];
		}
		long longTarget = target;

		HashSet<List<Integer>> set = new HashSet<List<Integer>>();

		for (int i = 0; i < nums.length; i++) {
			long curTarget = longTarget - longNums[i];
			List<List<Long>> tmp = threeSum(Arrays.copyOfRange(longNums, i + 1, nums.length), curTarget);
			for (List<Long> list : tmp) {
				list.add(0, longNums[i]);
				set.add(list.stream().mapToInt(Long::intValue).boxed().collect(Collectors.toList()));
			}
		}

		return new ArrayList<>(set);
	}

	// solution 2: sorting, two pointers, reduced to 3Sum
	public static List<List<Integer>> fourSum2(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Set<List<Integer>> resultSet = new HashSet<>();

		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 3; i++) {
			for (int j = i + 1; j < nums.length - 2; j++) {
				int left = j + 1;
				int right = nums.length - 1;

				while (left < right) {
					long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
					if (sum == target) {
						List<Integer> arr = new ArrayList<>();
						arr.add(nums[i]);
						arr.add(nums[j]);
						arr.add(nums[left]);
						arr.add(nums[right]);

						if (!resultSet.contains(arr)) {
							resultSet.add(arr);
						}

						left++;
						right--;
					} else if (sum <= target) {
						left++;
					} else if (sum > target) {
						right--;
					}
				}
			}
		}

		result.addAll(resultSet);

		return result;
	}

	public static void main(String[] args) {
		System.out.println(fourSum1(new int[] { 1, 0, -1, 0, -2, 2 }, 0));
		System.out.println(fourSum1(new int[] { 2, 2, 2, 2, 2 }, 8));

		System.out.println(fourSum2(new int[] { 1, 0, -1, 0, -2, 2 }, 0));
		System.out.println(fourSum2(new int[] { 2, 2, 2, 2, 2 }, 8));
	}

}
