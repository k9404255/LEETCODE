package leetCode.medium;

import java.util.Arrays;
import java.util.HashMap;

public class M0395_LongestSubstringwithAtLeastKRepeatingCharacters {
	// solution 1: brute-force (Time Limit Exceeded)
	public static int longestSubstring1(String s, int k) {
		int[] chars = new int[26];

		for (int len = s.length(); len >= k; len--) {
			for (int i = 0; i < s.length() - len + 1; i++) {
				Arrays.fill(chars, 0);
				for (Character c : s.substring(i, i + len).toCharArray()) {
					chars[c - 'a']++;
				}

				boolean isValid = true;
				for (int j = 0; j < chars.length; j++) {
					if (chars[j] > 0 && chars[j] < k) {
						isValid = false;
						break;
					}
				}

				if (isValid) {
					return len;
				}
			}
		}

		return 0;
	}

	// solution 2: divide and conquer
	// https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/solutions/1965982/java-easiest-o-n-solution-o-1-space-comments-with-explanations/
	public static int longestSubstring2(String s, int k) {
		// Base Conditions

		// if string length is less than k or string length is 0
		if (s.length() < k || s.length() == 0)
			return 0;
		// if k is 0 or 1 so return whole string length
		if (k <= 1)
			return s.length();

		// small Calculations

		// to store the frequency of string characters
		HashMap<Character, Integer> map = new HashMap<>();
		for (char ch : s.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		// to count till which we find that char whose frequency is less than k
		int l = 0;
		for (int i = 0; i < s.length() && map.get(s.charAt(i)) >= k; i++) {
			l++;
		}

		// if we reached at last index of string or l is equal to length than return the
		// l
		if (l >= s.length() - 1)
			return l;

		// Recursive calls

		// if we find that char whose frequency is less than k then divide the strings
		// into two halves
		// and call the recursion individually
		int l1 = longestSubstring2(s.substring(0, l), k);

		// to optimize the problem find the index till which that char whose frequency
		// is less than k lies
		// for eg.. string = aaallbbb , k=3
		// if we reached third index than instead dividing into substr(0,l) & substr(l)
		// we find till which that char whose //freq less than k lies
		// and did substr(0,l) and then while and then substr(l).. where l==5;
		while (l < s.length() && map.get(s.charAt(l)) < k)
			l++;
		int l2 = longestSubstring2(s.substring(l), k);

		// at last returned the longest substring length which recursion brings to us
		return Math.max(l1, l2);
	}

	public static void main(String[] args) {
//		System.out.println(longestSubstring1("aaabb", 3));
//		System.out.println(longestSubstring1("ababbc", 2));
//		System.out.println(longestSubstring1("aaacbbb", 3));

//		System.out.println(longestSubstring2("aaabb", 3));
//		System.out.println(longestSubstring2("ababbc", 2));
//		System.out.println(longestSubstring2("aaacbbb", 3));
		System.out.println(longestSubstring2("bchhbbdefghiaaacb", 3));
	}
}
