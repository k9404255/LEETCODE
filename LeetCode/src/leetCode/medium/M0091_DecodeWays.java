package leetCode.medium;

import java.util.HashMap;
import java.util.Map;

public class M0091_DecodeWays {
	// solution 1: recursive (Time Limit Exceeded)
	public static int numDecodings1(String s) {
		int len = s.length();

		if (len == 0) {
			return 1;
		}

		int n1 = 0;
		int n2 = 0;

		String str1 = s.substring(0, 1);
		String str2 = len >= 2 ? s.substring(0, 2) : null;

		if (Integer.valueOf(str1) != 0) {
			n1 = numDecodings1(s.substring(1));
		}

		if (str2 != null && Integer.valueOf(str2) >= 10 && Integer.valueOf(str2) <= 26) {
			n2 = numDecodings1(s.substring(2));
		}

		return n1 + n2;
	}

	// solution 2: memorization
	public static int numDecodings2(String s) {
		return helper2(s, new HashMap<String, Integer>());
	}

	public static int helper2(String s, Map<String, Integer> dp) {
		int len = s.length();

		if (len == 0) {
			return 1;
		}

		if (dp.containsKey(s)) {
			return dp.get(s);
		}

		int n1 = 0;
		int n2 = 0;

		String str1 = s.substring(0, 1);
		String str2 = len >= 2 ? s.substring(0, 2) : null;

		if (Integer.valueOf(str1) != 0) {
			n1 = helper2(s.substring(1), dp);
		}

		if (str2 != null && Integer.valueOf(str2) >= 10 && Integer.valueOf(str2) <= 26) {
			n2 = helper2(s.substring(2), dp);
		}

		dp.put(s, n1 + n2);

		return n1 + n2;
	}

	// solution 3: dp
	public static int numDecodings3(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int n = s.length();
		int[] dp = new int[n + 1];

		// dp[0] means an empty string will have one way to decode, dp[1] means the way
		// to decode a string of size 1.
		dp[0] = 1;
		dp[1] = s.charAt(0) != '0' ? 1 : 0;

		for (int i = 2; i <= n; i++) {
			int first = Integer.valueOf(s.substring(i - 1, i));
			int second = Integer.valueOf(s.substring(i - 2, i));

			if (first >= 1 && first <= 9) {
				dp[i] += dp[i - 1];
			}
			if (second >= 10 && second <= 26) {
				dp[i] += dp[i - 2];
			}
		}

		return dp[n];
	}

	public static void main(String[] args) {
//		System.out.println(numDecodings1("12"));
//		System.out.println(numDecodings1("226"));
//		System.out.println(numDecodings1("06"));
//		System.out.println(numDecodings1("11106"));
//		System.out.println(numDecodings1("27"));
//		System.out.println(numDecodings1("111111111111111111111111111111111111111111111"));

		System.out.println(numDecodings2("12"));
		System.out.println(numDecodings2("226"));
		System.out.println(numDecodings2("06"));
		System.out.println(numDecodings2("11106"));
		System.out.println(numDecodings2("27"));
		System.out.println(numDecodings2("111111111111111111111111111111111111111111111"));

		System.out.println("----------");

//		System.out.println(numDecodings3("12"));
//		System.out.println(numDecodings3("226"));
//		System.out.println(numDecodings3("06"));
		System.out.println(numDecodings3("11106"));
//		System.out.println(numDecodings3("27"));
//		System.out.println(numDecodings3("111111111111111111111111111111111111111111111"));
	}
}
