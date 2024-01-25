package leetCode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class E242_ValidAnagram {
	// solution 1: sort
	public static boolean isAnagram1(String s, String t) {
		char[] arr1 = s.toCharArray();
		Arrays.sort(arr1);

		char[] arr2 = t.toCharArray();
		Arrays.sort(arr2);

		return Arrays.equals(arr1, arr2);
	}

	// solution 2: hash map
	public static boolean isAnagram2(String s, String t) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		for (char c : t.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) - 1);
		}

		// for (int x : map.values()) {
		// if (x != 0) {
		// return false;
		// }
		// }

		return map.values().stream().filter(x -> {
			return x != 0;
		}).count() == 0;
	}

	public static void main(String[] args) {
		System.out.println(isAnagram1("anagram", "nagaram"));
		System.out.println(isAnagram1("rat", "car"));

		System.out.println(isAnagram2("anagram", "nagaram"));
		System.out.println(isAnagram2("rat", "car"));
	}
}