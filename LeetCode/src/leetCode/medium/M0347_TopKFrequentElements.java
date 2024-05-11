package leetCode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class M0347_TopKFrequentElements {
	public static void printNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	// solution 1: hash map count
	public static int[] topKFrequent1(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			map.compute(nums[i], (key, val) -> (val == null) ? 0 : val + 1);
		}

		List<Integer> list = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.map(Entry::getKey).toList();

		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			ans.add(list.get(i));
		}

		return ans.stream().mapToInt(Integer::intValue).toArray();
	}

	// solution 2: bucket
	public static int[] topKFrequent(int[] nums, int k) {
		List<Integer>[] bucket = new List[nums.length + 1];
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int num : nums) {
			hm.put(num, hm.getOrDefault(num, 0) + 1);
		}
		for (int key : hm.keySet()) {
			int freq = hm.get(key);
			if (bucket[freq] == null) {
				bucket[freq] = new ArrayList<>();
			}
			bucket[freq].add(key);
		}
		int[] ans = new int[k];
		int pos = 0;
		for (int i = bucket.length - 1; i >= 0; i--) {
			if (bucket[i] != null) {
				for (int j = 0; j < bucket[i].size() && pos < k; j++) {
					ans[pos] = bucket[i].get(j);
					pos++;
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		printNums(topKFrequent1(new int[] { 1, 1, 1, 2, 2, 3 }, 2));
		printNums(topKFrequent1(new int[] { 1 }, 1));
	}
}
