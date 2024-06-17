package leetCode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// https://leetcode.com/problems/4sum-ii/solutions/1740606/going-from-o-n-4-o-n-3-o-n-2-java-c/
public class M0454_4SumII {
	// solution 1: brute-force (Time Limit Exceeded)
	public static int fourSumCount1(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		int n = nums1.length;

		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int j2 = 0; j2 < n; j2++) {
					for (int k = 0; k < n; k++) {
						if (nums1[i] + nums2[j] + nums3[j2] + nums4[k] == 0) {
							ans++;
						}
					}
				}
			}
		}

		return ans;
	}

	// solution 2: hashset remove replica (Time Limit Exceeded)
	public static int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		int n = nums1.length;
		Map<Integer, Integer> map1 = new HashMap<>();
		Map<Integer, Integer> map2 = new HashMap<>();
		Map<Integer, Integer> map3 = new HashMap<>();
		Map<Integer, Integer> map4 = new HashMap<>();

		for (int i = 0; i < n; i++) {
			map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
		}

		for (int i = 0; i < n; i++) {
			map2.put(nums2[i], map2.getOrDefault(nums2[i], 0) + 1);
		}

		for (int i = 0; i < n; i++) {
			map3.put(nums3[i], map3.getOrDefault(nums3[i], 0) + 1);
		}

		for (int i = 0; i < n; i++) {
			map4.put(nums4[i], map4.getOrDefault(nums4[i], 0) + 1);
		}

		Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toCollection(HashSet::new));
		Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toCollection(HashSet::new));
		Set<Integer> set3 = Arrays.stream(nums3).boxed().collect(Collectors.toCollection(HashSet::new));
		Set<Integer> set4 = Arrays.stream(nums4).boxed().collect(Collectors.toCollection(HashSet::new));

		int ans = 0;
		for (Integer i1 : set1) {
			for (Integer i2 : set2) {
				for (Integer i3 : set3) {
					for (Integer i4 : set4) {
						if (i1 + i2 + i3 + i4 == 0) {
							ans += map1.get(i1) * map2.get(i2) * map3.get(i3) * map4.get(i4);
						}
					}
				}
			}
		}

		return ans;
	}

	// solution 3: O(N^3) Time Limit Exceeded
	public static int fourSumCount3(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int l : nums4)
			map.put(l, map.getOrDefault(l, 0) + 1); // if number already present the inrement with + 1, otherwise add in
													// map
		int count = 0;
		for (int i : nums1)
			for (int j : nums2)
				for (int k : nums3)
					count += map.getOrDefault(-(i + j + k), 0); // we have to find out the -ve of i + j + k
		// & in d we have to find such no that -(i + j + k) + l gives = 0. If it is
		// there get it otherwise get 0
		// and update the count
		return count;
	}

	// solution 4:O(N^2)
	public static int fourSumCount4(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int k : nums3)
			for (int l : nums4)
				map.put(k + l, map.getOrDefault(k + l, 0) + 1);

		int count = 0;
		for (int i : nums1)
			for (int j : nums2)
				count += map.getOrDefault(-(i + j), 0);
		return count;
	}

	public static void main(String[] args) {
//		System.out.println(
//				fourSumCount1(new int[] { 1, 2 }, new int[] { -2, -1 }, new int[] { -1, 2 }, new int[] { 0, 2 }));
//		System.out.println(fourSumCount1(new int[] { 0 }, new int[] { 0 }, new int[] { 0 }, new int[] { 0 }));

		System.out.println(
				fourSumCount2(new int[] { 1, 2 }, new int[] { -2, -1 }, new int[] { -1, 2 }, new int[] { 0, 2 }));
		System.out.println(fourSumCount2(new int[] { 0 }, new int[] { 0 }, new int[] { 0 }, new int[] { 0 }));
		System.out.println(
				fourSumCount2(new int[] { 2, 2 }, new int[] { 2, 2 }, new int[] { -2, -2 }, new int[] { -2, -2 }));
	}
}
