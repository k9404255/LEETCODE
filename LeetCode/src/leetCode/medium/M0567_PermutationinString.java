package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M0567_PermutationinString {
	public static void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// solution 1: Time Limit Exceeded
	public static boolean checkInclusion1(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return false;
		}

		return helper1(s1.toCharArray(), 0, s2);
	}

	public static boolean helper1(char[] s1, int cur, String s2) {
		if (cur == s1.length) {
			return s2.contains(new String(s1));
		}

		for (int i = cur; i < s1.length; i++) {
			swap(s1, i, cur);
			if (helper1(s1, cur + 1, s2)) {
				return true;
			}
			swap(s1, cur, i);
		}

		return false;
	}

	// solution 2: window
	public static boolean checkInclusion2(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return false;
		}

		Map<Character, List<Integer>> position = new HashMap<>();
		for (char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
			position.put(c, new ArrayList<>());
		}

		Map<Character, Integer> count = new HashMap<>();
		for (char c : s1.toCharArray()) {
			if (count.containsKey(c)) {
				count.put(c, count.get(c) + 1);
			} else {
				count.put(c, 1);
			}
		}

		int left = -1;
		for (int i = 0; i < s2.length(); i++) {
			char c1 = s2.charAt(i);

			if (s1.indexOf(c1) >= 0 && position.get(c1).size() != count.get(c1)) {
				position.get(c1).add(i);
				if (left == -1) {
					left = i;
				}
			} else if (s1.indexOf(c1) >= 0 && position.get(c1).size() == count.get(c1)) {
				int right = position.get(c1).get(0);
				for (int j = left; j <= right; j++) {
					position.get(s2.charAt(j)).remove(0);
				}
				left = right + 1;
				position.get(c1).add(i);

			} else {
				left = -1;
				position.values().forEach(list -> {
					list.clear();
				});
			}

			if (left != -1 && i - left + 1 == s1.length()) {
				return true;
			}
		}

		return false;
	}

	// solution 3
	// https://leetcode.com/problems/permutation-in-string/solutions/3139946/easy-java-sliding-window-solution-beats-100-online-submissions/
	public static boolean checkInclusion3(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return false;
		}

		int[] count1 = new int[26];
		for (char c : s1.toCharArray()) {
			count1[c - 'a']++;
		}
		
		int[] count2 = new int[26];
		int i = 0;
		while (i < s1.length()) {
			count2[s2.charAt(i++) - 'a']++;
		}

		if (Arrays.equals(count1, count2)) {
			return true;
		}

		int j = 0;
		while (i < s2.length()) {
			count2[s2.charAt(j++) - 'a']--;
			count2[s2.charAt(i++) - 'a']++;

			if (Arrays.equals(count1, count2)) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
//		System.out.println(checkInclusion1("ab", "eidbaooo"));
//		System.out.println(checkInclusion1("ab", "eidboaoo"));
//		System.out.println(checkInclusion1("dinitrophenylhydrazine", "acetylphenylhydrazine"));
//		System.out.println(checkInclusion1(
//				"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef",
//				"bcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefg"));

//		System.out.println(checkInclusion2("ab", "eidbaooo")); // true
//		System.out.println(checkInclusion2("ab", "eidboaoo")); // false
//		System.out.println(checkInclusion2("dinitrophenylhydrazine", "acetylphenylhydrazine")); // false
//		System.out.println(checkInclusion2(
//				"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef",
//				"bcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefg"));
//		System.out.println(checkInclusion2("adc", "dfcda")); // true
//		System.out.println(checkInclusion2("adc", "dcda"));// true
//		System.out.println(checkInclusion2("adc", "dcdda"));// false
//		System.out.println(checkInclusion2("adc", "cdc"));// false
//		System.out.println(checkInclusion2("adcf", "fcdcfa"));// true
//		System.out.println(checkInclusion2("ccabf", "cfcbca"));// true
//		System.out.println(checkInclusion2("ccabf", "cfcbcca"));// false

		System.out.println(checkInclusion3("ab", "eidbaooo")); // true
		System.out.println(checkInclusion3("ab", "eidboaoo")); // false
		System.out.println(checkInclusion3("dinitrophenylhydrazine", "acetylphenylhydrazine")); // false
		System.out.println(checkInclusion3(
				"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef",
				"bcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefg"));
		System.out.println(checkInclusion3("adc", "dfcda")); // true
		System.out.println(checkInclusion3("adc", "dcda"));// true
		System.out.println(checkInclusion3("adc", "dcdda"));// false
		System.out.println(checkInclusion3("adc", "cdc"));// false
		System.out.println(checkInclusion3("adcf", "fcdcfa"));// true
		System.out.println(checkInclusion3("ccabf", "cfcbca"));// true
		System.out.println(checkInclusion3("ccabf", "cfcbcca"));// false
	}
}
