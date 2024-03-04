package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0131_PalindromePartitioning {
	public static boolean isPalindrome(String s) {
		int n = s.length();
		for (int i = 0; i < n / 2; i++) {
			if (s.charAt(i) != s.charAt(n - i - 1)) {
				return false;
			}
		}

		return true;
	}

	// solution1: recursive
	public static List<List<String>> partition1(String s) {
		List<List<String>> ans = new ArrayList<>();
		helper1(ans, new ArrayList<>(), s);

		return ans;
	}

	public static void helper1(List<List<String>> ans, List<String> tmp, String s) {
		int n = s.length();
		if (n == 0) {
			ans.add(new ArrayList<>(tmp));
			return;
		}

		for (int i = 1; i <= n; i++) {
			String subStr = s.substring(0, i);
			if (i == 1 || isPalindrome(subStr)) {
				tmp.add(subStr);
				helper1(ans, tmp, s.substring(i));
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(partition1("aab"));
		System.out.println(partition1("a"));
		System.out.println(partition1("aabaa"));
		System.out.println(partition1("aaba"));
	}
}
