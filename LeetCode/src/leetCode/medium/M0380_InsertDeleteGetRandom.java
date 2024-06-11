package leetCode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class M0380_InsertDeleteGetRandom {
	// solution 1
	class RandomizedSet1 {
		private Set<Integer> set = new HashSet<>();
		private Random rand = new Random();

		public RandomizedSet1() {

		}

		public boolean insert(int val) {
			return set.add(val);
		}

		public boolean remove(int val) {
			return set.remove(val);
		}

		public int getRandom() {
			return new ArrayList<>(set).get(rand.nextInt(set.size()));
		}
	}

	// solution 2
	class RandomizedSet2 {
		private ArrayList<Integer> list;
		private Map<Integer, Integer> map;

		public RandomizedSet2() {
			list = new ArrayList<>();
			map = new HashMap<>();
		}

		public boolean search(int val) {
			return map.containsKey(val);
		}

		public boolean insert(int val) {
			if (search(val))
				return false;

			list.add(val);
			map.put(val, list.size() - 1);
			return true;
		}

		public boolean remove(int val) {
			if (!search(val))
				return false;

			int index = map.get(val);
			list.set(index, list.get(list.size() - 1)); // 把list最後一個元素補到要被移除的元素的洞
			map.put(list.get(index), index);
			list.remove(list.size() - 1);
			map.remove(val);

			return true;
		}

		public int getRandom() {
			Random rand = new Random();
			return list.get(rand.nextInt(list.size()));
		}
	}
}
