package leetCode.easy;

import java.util.HashMap;
import java.util.Map;

public class E0387_FirstUniqueCharacterinaString {
	// solution 1: Hash Map
	public static int firstUniqChar1(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.get(c) == 1) {
				return i;
			}
		}

		return -1;
	}

	// solution 2: compare first and last index
	public static int firstUniqChar2(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (s.indexOf(c) == s.lastIndexOf(c)) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(firstUniqChar1("leetcode"));
		System.out.println(firstUniqChar1("loveleetcode"));
		System.out.println(firstUniqChar1("aabb"));

		System.out.println(firstUniqChar2("leetcode"));
		System.out.println(firstUniqChar2("loveleetcode"));
		System.out.println(firstUniqChar2("aabb"));
	}
}