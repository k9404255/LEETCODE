package leetCode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class M0003_LongestSubstringWithoutRepeatingCharacters {
	// solution 1: Hash Set (Time Limit Exceeded)
	public static int lengthOfLongestSubstring1(String s) {
		if (s.isEmpty()) {
			return 0;
		}

		Set<Character> set = new HashSet<Character>();

		for (int n = s.length(); n >= 2; n--) {
			set.clear();

			for (int i = 0; i <= s.length() - n; i++) {
				set.clear();

				for (int j = 0; j < n; j++) {
					set.add(s.charAt(i + j));
				}

				if (set.size() == n) {
					return n;
				}
			}
		}

		return 1;
	}

	// solution 2: Hash Set, Two Pointers, sliding window
	public static int lengthOfLongestSubstring2(String s) {
		int left = 0;
		int maxLen = 0;
		Set<Character> set = new HashSet<Character>();

		for (int right = 0; right < s.length(); right++) {
			if (!set.contains(s.charAt(right))) {
				maxLen = Math.max(maxLen, right - left + 1);
			} else {
				while (set.contains(s.charAt(right))) {
					set.remove(s.charAt(left));
					left++;
				}
			}

			set.add(s.charAt(right));
		}

		return maxLen;
	}

	// solution 3: Hash Map, sliding window
	public static int lengthOfLongestSubstring3(String s) {
		// Map to store the character and its latest idx while traversing the string
		// from left to right
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int left = 0;
		int max = 0;

		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);

			// If the current character has previously been seen and
			// the previous occurrence (idx) of the char >= the starting position of the
			// window to be explored (i)
			if (map.containsKey(c) && map.get(c) >= left) {
				left = map.get(c) + 1;
			}

			map.put(c, right);
			max = Math.max(max, right - left + 1);
		}

		return max;
	}

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring1("abcabcbb"));
		System.out.println(lengthOfLongestSubstring1("bbbbb"));
		System.out.println(lengthOfLongestSubstring1("pwwkew"));

		System.out.println(lengthOfLongestSubstring2("abcabcbb"));
		System.out.println(lengthOfLongestSubstring2("bbbbb"));
		System.out.println(lengthOfLongestSubstring2("pwwkew"));
		System.out.println(lengthOfLongestSubstring2("abcbdef"));

		System.out.println(lengthOfLongestSubstring3("abcabcbb"));
		System.out.println(lengthOfLongestSubstring3("bbbbb"));
		System.out.println(lengthOfLongestSubstring3("pwwkew"));
		System.out.println(lengthOfLongestSubstring3("abcbdef"));
	}
}