package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class M0438_FindAllAnagramsinaString {
	public static void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// solution 1: Memory Limit Exceeded
	public static List<Integer> findAnagrams1(String s, String p) {
		Set<Integer> ans = new HashSet<>();

		List<String> anagrams = new ArrayList<>();
		helper1(p.toCharArray(), 0, anagrams);

		int len = p.length();
		for (String anagram : anagrams) {
			for (int i = 0; i <= s.length() - len; i++) {
				String subStr = s.substring(i, i + len);
//				System.out.println("i: " + i + ", subStr: " + subStr + ", anagram: " + anagram);
				if (subStr.equals(anagram)) {
					ans.add(i);
				}
			}
		}

		return new ArrayList<>(ans);
	}

	public static void helper1(char[] p, int cur, List<String> anagrams) {
		if (cur == p.length) {
			anagrams.add(new String(p));
			return;
		}

		for (int i = cur; i < p.length; i++) {
			swap(p, i, cur);
			helper1(p, cur + 1, anagrams);
			swap(p, cur, i);
		}
	}

	// solution 2: sliding window
	public static List<Integer> findAnagrams2(String s, String p) {
		List<Integer> ans = new ArrayList<>();

		if (s.length() < p.length()) {
			return ans;
		}

		int[] count1 = new int[26];
		for (char c : p.toCharArray()) {
			count1[c - 'a']++;
		}

		int[] count2 = new int[26];
		int i = 0;
		while (i < p.length()) {
			count2[s.charAt(i++) - 'a']++;
		}

		if (Arrays.equals(count1, count2)) {
			ans.add(0);
		}

		int j = 0;
		while (i < s.length()) {
			count2[s.charAt(j++) - 'a']--;
			count2[s.charAt(i++) - 'a']++;

			if (Arrays.equals(count1, count2)) {
				ans.add(j);
			}
		}

		return ans;
	}

	// solution 3: sorting
	public static List<Integer> findAnagrams3(String s, String p) {
		List<Integer> ans = new ArrayList<>();

		if (s.length() < p.length()) {
			return ans;
		}

		char[] pArr = p.toCharArray();
		Arrays.sort(pArr);

		int len = p.length();
		for (int i = 0; i <= s.length() - len; i++) {
			char[] sArr = s.substring(i, i + len).toCharArray();
			Arrays.sort(sArr);

			if (Arrays.equals(sArr, pArr)) {
				ans.add(i);
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(findAnagrams1("cbaebabacd", "abc"));
		System.out.println(findAnagrams1("abab", "ab"));

		System.out.println(findAnagrams2("cbaebabacd", "abc"));
		System.out.println(findAnagrams2("abab", "ab"));

		System.out.println(findAnagrams3("cbaebabacd", "abc"));
		System.out.println(findAnagrams3("abab", "ab"));
	}
}
