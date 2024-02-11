package leetCode.medium;

public class M0647_PalindromicSubstrings {
	public static boolean isPalindromic(String s) {
		int n = s.length();

		for (int i = 0; i < n / 2; i++) {
			if (s.charAt(i) != s.charAt(n - i - 1)) {
				return false;
			}
		}

		return true;
	}

	// solution 1: O(n^3)
	public static int countSubstrings1(String s) {
		int len = s.length();
		int ans = 0;

		for (int n = 1; n <= len; n++) {
			for (int i = 0; i <= len - n; i++) {
				String subStr = s.substring(i, i + n);
				if (isPalindromic(subStr)) {
					ans++;
				}
			}
		}

		return ans;
	}

	// solution 2: expand from center (O(n^2))
	public static int countSubstrings2(String s) {
		int ans = 0;

		for (int i = 0; i < s.length(); i++) {
			int odd = expandFromCenter(s, i, i);
			int even = expandFromCenter(s, i, i + 1);

			ans += odd + even;
		}

		return ans;
	}

	public static int expandFromCenter(String s, int left, int right) {
		int count = 0;

		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			count++;
			left--;
			right++;
		}

		return count;
	}

	// solution 3: dp
	public static int countSubstrings3(String s) {
		int n = s.length();
		// dp[i][j] stores whether the substring from index i to j is a palindrome.
		boolean[][] dp = new boolean[n][n];
		int ans = 0;

		// len = 1
		for (int i = 0; i < n; i++) {
			dp[i][i] = true;
			ans++;
		}

		// len = 2
		for (int i = 0; i < n - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				dp[i][i + 1] = true;
				ans++;
			}
		}

		// len > 2
		for (int len = 3; len <= n; len++) {
			for (int i = 0; i < n - len + 1; i++) {
				if (s.charAt(i) == s.charAt(i + len - 1) && dp[i + 1][i + len - 2]) {
					dp[i][i + len - 1] = true;
					ans++;
				}
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(countSubstrings1("abc"));
		System.out.println(countSubstrings1("aaa"));

		System.out.println(countSubstrings2("abc"));
		System.out.println(countSubstrings2("aaa"));

		System.out.println(countSubstrings3("abc"));
		System.out.println(countSubstrings3("aaa"));
	}
}
