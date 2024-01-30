package leetCode.medium;

public class M0005_LongestPalindromicSubstring {
	// solution 1: brute-force
	public static boolean isPalindrome(String s) {
		int n = s.length();
		for (int i = 0; i < n / 2; i++) {
			if (s.charAt(i) != s.charAt(n - i - 1)) {
				return false;
			}
		}

		return true;
	}

	public static String longestPalindrome1(String s) {
		for (int n = s.length(); n > 0; n--) {
			for (int i = 0; i <= s.length() - n; i++) {
				String subString = s.substring(i, i + n);
				if (isPalindrome(subString)) {
					return subString;
				}
			}
		}

		return null;
	}

	// solution 2: expand from center
	public static String expandFromCenter(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}

		return s.substring(left + 1, right);
	}

	public static String longestPalindrome2(String s) {
		String maxStr = "";

		for (int i = 0; i < s.length(); i++) {
			String oddStr = expandFromCenter(s, i, i);
			String evenStr = expandFromCenter(s, i, i + 1);

			if (oddStr.length() > maxStr.length()) {
				maxStr = oddStr;
			}

			if (evenStr.length() > maxStr.length()) {
				maxStr = evenStr;
			}
		}

		return maxStr;
	}

	public static void main(String[] args) {
		System.out.println(longestPalindrome1("babad"));
		System.out.println(longestPalindrome1("cbbd"));

		System.out.println(longestPalindrome2("babad"));
		System.out.println(longestPalindrome2("cbbd"));
	}
}