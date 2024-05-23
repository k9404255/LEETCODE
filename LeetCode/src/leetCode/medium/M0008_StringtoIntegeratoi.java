package leetCode.medium;

public class M0008_StringtoIntegeratoi {
	// solution 1
	public static int myAtoi1(String s) {
		s = s.trim();

		boolean startRead = false;
		long ans = 0;
		long sign = 1;
		for (char c : s.toCharArray()) {
			if (!startRead && (c == '-' || c == '+')) {
				startRead = true;
				sign = c == '-' ? -1 : 1;
			} else if (!startRead && Character.isDigit(c)) {
				startRead = true;
				ans = c - '0';
			} else if (startRead && Character.isDigit(c)) {
				ans = ans * 10 + c - '0';

				if (sign * ans >= Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				} else if (sign * ans <= Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}
			} else if (startRead && !Character.isDigit(c)) {
				break;
			} else if (!startRead && !Character.isDigit(c) && c != '-' && c != '+') {
				break;
			}
		}

		ans *= sign;

		return (int) ans;
	}

	// solution 2
	public static int myAtoi2(String s) {
		int ans = 0;
		int i = 0;
		int sign = 1;
		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		if (s.length() == 0) {
			return 0;
		}
		while (i < s.length() && s.charAt(i) == ' ') {
			i++;
		}

		if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
			if (s.charAt(i) == '-') {
				sign = -1;
			}
			i++;
		}
		while (i < s.length() && s.charAt(i) - '0' <= 9 && s.charAt(i) - '0' >= 0) {
			int digit = s.charAt(i) - '0';
			if (ans > max / 10 || (ans == max / 10 && digit > max % 10)) {
				return (sign == 1) ? max : min;
			}
			ans = ans * 10 + digit;
			i++;
		}
		return ans * sign;
	}

	public static void main(String[] args) {
//		System.out.println(myAtoi1("42"));
//		System.out.println(myAtoi1("   -042"));
//		System.out.println(myAtoi1("1337c0d3"));
//		System.out.println(myAtoi1("0-1"));
//		System.out.println(myAtoi1("words and 987"));
//		System.out.println(myAtoi1("-91283472332")); // -2147483648
//		System.out.println(myAtoi1("9223372036854775808")); // 2147483647

		System.out.println(myAtoi2("42"));
	}
}
