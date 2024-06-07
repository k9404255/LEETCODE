package leetCode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class M0227_BasicCalculatorII {
	// solution 1: Time Limit Exceeded
	public static int calculate1(String s) {
		s = s.replace(" ", "");

		List<String> list = new ArrayList<>();

		int prev = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!Character.isDigit(c)) {
				list.add(s.substring(prev, i));
				list.add(String.valueOf(c));
				prev = i + 1;
			}

			if (i == s.length() - 1) {
				list.add(s.substring(prev, i + 1));
			}
		}

		int i = 0;
		while (i < list.size()) {
			if (list.get(i).equals("*")) {
				int n1 = Integer.valueOf(list.remove(i - 1));
				list.remove(i - 1);
				int n2 = Integer.valueOf(list.remove(i - 1));
				list.add(i - 1, String.valueOf(n1 * n2));
			} else if (list.get(i).equals("/")) {
				int n1 = Integer.valueOf(list.remove(i - 1));
				list.remove(i - 1);
				int n2 = Integer.valueOf(list.remove(i - 1));
				list.add(i - 1, String.valueOf(n1 / n2));
			} else {
				i++;
			}
		}

		i = 0;
		while (i < list.size()) {
			if (list.get(i).equals("+")) {
				int n1 = Integer.valueOf(list.remove(i - 1));
				list.remove(i - 1);
				int n2 = Integer.valueOf(list.remove(i - 1));
				list.add(i - 1, String.valueOf(n1 + n2));
			} else if (list.get(i).equals("-")) {
				int n1 = Integer.valueOf(list.remove(i - 1));
				list.remove(i - 1);
				int n2 = Integer.valueOf(list.remove(i - 1));
				list.add(i - 1, String.valueOf(n1 - n2));
			} else {
				i++;
			}
		}

		return Integer.valueOf(list.get(0));
	}

	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	// solution 2: using stack
	public static int calculate2(String s) {
		Stack<Integer> st = new Stack<>();

		int num = 0;
		char operator = '+'; // 理解成第一個數字前的符號

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (Character.isDigit(c)) {
				num = num * 10 + (c - '0');
			}

			if (isOperator(c) || i == s.length() - 1) {
				if (operator == '+')
					st.push(num);
				else if (operator == '-')
					st.push(-num);
				else if (operator == '*')
					st.push(st.pop() * num);
				else if (operator == '/')
					st.push(st.pop() / num);

				num = 0;
				operator = c;
			}
		}

		int ans = 0;

		while (!st.isEmpty()) {
			ans += st.pop();
		}

		return ans;
	}

	// solution 3: without stack
	public static int calculate3(String s) {
		int num = 0;
		char operator = '+';
		int last = 0, sum = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (Character.isDigit(c)) {
				num = num * 10 + (c - '0');
			}

			if (isOperator(c) || i == s.length() - 1) {
				if (operator == '+') {
					sum += last;
					last = num;
				} else if (operator == '-') {
					sum += last;
					last = -num;
				} else if (operator == '*')
					last *= num;
				else if (operator == '/')
					last /= num;

				num = 0;
				operator = c;
			}
		}

		return sum += last;
	}

	public static void main(String[] args) {
		System.out.println(calculate1("3+2*2"));
		System.out.println(calculate1(" 3/2"));
		System.out.println(calculate1("3+5 / 2 "));
		System.out.println(calculate1("30+50*10+20"));
		System.out.println(calculate1("30+50*10*10/10+10"));
		System.out.println(calculate1("0"));
		System.out.println(calculate1("0-2147483647"));

		System.out.println("-------");

		System.out.println(calculate2("3+2*2"));
		System.out.println(calculate2(" 3/2"));
		System.out.println(calculate2("3+5 / 2 "));
		System.out.println(calculate2("30+50*10+20"));
		System.out.println(calculate2("30+50*10*10/10+10"));
		System.out.println(calculate2("0"));
		System.out.println(calculate2("0-2147483647"));

		System.out.println("-------");

		System.out.println(calculate3("3+2*2"));
		System.out.println(calculate3(" 3/2"));
		System.out.println(calculate3("3+5 / 2 "));
		System.out.println(calculate3("30+50*10+20"));
		System.out.println(calculate3("30+50*10*10/10+10"));
		System.out.println(calculate3("0"));
		System.out.println(calculate3("0-2147483647"));
	}
}
