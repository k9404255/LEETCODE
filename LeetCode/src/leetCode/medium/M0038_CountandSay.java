package leetCode.medium;

public class M0038_CountandSay {
	// solution 1: recursive
	public static String countAndSay1(int n) {
		if (n == 1) {
			return "1";
		}

		StringBuilder sb = new StringBuilder();
		String str = countAndSay1(n - 1);
		char prev = str.charAt(0);
		int count = 1;
		for (int i = 1; i < str.length(); i++) {
			char cur = str.charAt(i);
			if (prev == cur) {
				count++;
			} else if (prev != cur) {
				sb.append(count).append(prev);
				count = 1;
				prev = cur;
			}
		}

		sb.append(count).append(prev);

		return sb.toString();
	}

	// solution 2: iterative
	public static String countAndSay2(int n) {
		String ans = "1";

		for (int i = 2; i <= n; i++) {
			StringBuilder sb = new StringBuilder();
			int len = ans.length();
			char prev = ans.charAt(0);
			int count = 1;
			for (int j = 1; j < len; j++) {
				char cur = ans.charAt(j);
				if (prev == cur) {
					count++;
				} else if (prev != cur) {
					sb.append(count).append(prev);
					count = 1;
					prev = cur;
				}
			}
			sb.append(count).append(prev);
			ans = sb.toString();
		}

		return ans;
	}

	// solution 3: recursive
	public static String countAndSay3(int n) {
		if (n == 1)
			return "1";

		String s = countAndSay3(n - 1);

		int c = 0;
		StringBuilder ans = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			c++;
			if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
				ans.append(c).append(s.charAt(i));
				c = 0;
			}
		}

		return ans.toString();
	}

	// solution 4: iterative
	public static String countAndSay4(int n) {
		String ans = "1";

		for (int i = 2; i <= n; i++) {
			int c = 0;
			StringBuilder sb = new StringBuilder();

			for (int j = 0; j < ans.length(); j++) {
				c++;
				if (j == ans.length() - 1 || ans.charAt(j) != ans.charAt(j + 1)) {
					sb.append(c).append(ans.charAt(j));
					c = 0;
				}
			}
			ans = sb.toString();
		}

		return ans;
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(countAndSay1(i));
		}

		for (int i = 1; i <= 10; i++) {
			System.out.println(countAndSay2(i));
		}

		for (int i = 1; i <= 10; i++) {
			System.out.println(countAndSay3(i));
		}

		for (int i = 1; i <= 10; i++) {
			System.out.println(countAndSay4(i));
		}
	}
}
