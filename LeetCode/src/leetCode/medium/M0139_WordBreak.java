package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class M0139_WordBreak {
	// solution 1: recursive (Time Limit Exceeded)
	public static boolean wordBreak1(String s, List<String> wordDict) {
		return helper1(s, wordDict, new ArrayList<>());
	}

	public static boolean helper1(String s, List<String> wordDict, List<String> tmp) {
		int len = s.length();
		if (len == 0) {
			return true;
		}

		boolean result = false;
		for (int i = 1; i <= len; i++) {
			String subStr = s.substring(0, i);
			if (wordDict.contains(subStr)) {
				tmp.add(subStr);
				result = helper1(s.substring(i), wordDict, tmp);
				tmp.remove(tmp.size() - 1);
			}

			if (result) {
				break;
			}
		}

		return result;
	}

	// solution 2: recursive (Time Limit Exceeded)
	public static boolean wordBreak2(String s, List<String> wordDict) {
		List<String> ans = new ArrayList<>();
		helper2(s, wordDict, new ArrayList<>(), ans);
		System.out.println(ans);

		return !ans.isEmpty();
	}

	public static void helper2(String s, List<String> wordDict, List<String> tmp, List<String> ans) {
		int len = s.length();
		if (len == 0) {
			ans.addAll(tmp);
			return;
		}

		if (!ans.isEmpty()) {
			return;
		}

		for (int i = 1; i <= len; i++) {
			String subStr = s.substring(0, i);
			if (wordDict.contains(subStr)) {
				tmp.add(subStr);
				helper2(s.substring(i), wordDict, tmp, ans);
				tmp.remove(tmp.size() - 1);
			}

			if (!ans.isEmpty()) {
				return;
			}
		}
	}

	// solution 3: dp
	public static boolean wordBreak3(String s, List<String> wordDict) {
		int n = s.length();
		boolean dp[] = new boolean[n + 1];
		dp[0] = true;

		for (int i = 1; i <= n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
				}
			}
		}

		return dp[n];
	}

	// solution 4: memorization
	public static boolean wordBreak4(String s, List<String> wordDict) {
		Map<String, Boolean> memo = new HashMap<>();
		Set<String> wordSet = new HashSet<>(wordDict);

		return helper4(s, wordSet, memo);
	}

	public static boolean helper4(String s, Set<String> wordSet, Map<String, Boolean> memo) {
		if (memo.containsKey(s)) {
			return memo.get(s);
		}

		if (wordSet.contains(s)) {
			return true;
		}

		for (int i = 1; i < s.length(); i++) {
			String subStr = s.substring(0, i);
			if (wordSet.contains(subStr) && helper4(s.substring(i), wordSet, memo)) {
				memo.put(s, true);
				return true;
			}
		}

		memo.put(s, false);
		return false;
	}

	public static void main(String[] args) {
//		System.out.println(wordBreak1("leetcode", Arrays.asList("leetcode")));
//		System.out.println(wordBreak1("leetcode", Arrays.asList("leet", "code")));
//		System.out.println(wordBreak1("applepenapple", Arrays.asList("apple", "pen")));
//		System.out.println(wordBreak1("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));

//		System.out.println(wordBreak2("leetcode", Arrays.asList("leet", "code")));
//		System.out.println(wordBreak2("applepenapple", Arrays.asList("apple", "pen")));
//		System.out.println(wordBreak2("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
//
//		System.out.println(wordBreak3(
//				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
//				Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa",
//						"aaaaaaaaaa")));

		System.out.println(wordBreak4("leetcode", Arrays.asList("leetcode")));
		System.out.println(wordBreak4("leetcode", Arrays.asList("leet", "code")));
		System.out.println(wordBreak4("applepenapple", Arrays.asList("apple", "pen")));
		System.out.println(wordBreak4("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));

		System.out.println(wordBreak4(
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
				Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa",
						"aaaaaaaaaa")));
	}
}
