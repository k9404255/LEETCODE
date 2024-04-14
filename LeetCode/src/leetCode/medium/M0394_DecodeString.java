package leetCode.medium;

import java.util.Stack;

public class M0394_DecodeString {
	// solution 1
	public static String decodeString1(String s) {
		int count = 0;
		int k = 0;

		int left = 0;
		StringBuilder sb = new StringBuilder();
		StringBuilder num = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (Character.isDigit(c) && k == 0) {
				num.append(c);
			} else if (c == '[') {
				if (k == 0) {
					k = Integer.valueOf(num.toString());
					left = i + 1;
				}
				count++;
			} else if (c == ']') {
				count--;
			} else if (Character.isLetter(c) && k == 0) {
				sb.append(c);
			}

			if (k != 0 && count == 0) {
				String subStr = s.substring(left, i);
				if (subStr.contains("[")) {
					subStr = decodeString1(subStr);
				}

				while (k > 0) {
					sb.append(subStr);
					k--;
				}
				num.setLength(0);
			}
		}

		return sb.toString();
	}

	// solution 2
	public static String decodeString2(String s) {
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		for (char c : s.toCharArray()) {
			if (c != ']') {
				stack.push(c);
			} else {
				sb.setLength(0);
				while (Character.isLetter(stack.peek())) {
					sb.append(stack.pop());
				}
				String subStr = sb.reverse().toString();
				stack.pop(); // remove '['

				sb.setLength(0);
				while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
					sb.append(stack.pop());
				}
				int k = Integer.valueOf(sb.reverse().toString());

				while (k > 0) {
					for (char cc : subStr.toCharArray()) {
						stack.push(cc);
					}
					k--;
				}
			}
		}

		sb.setLength(0);
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.reverse().toString();
	}

	public static void main(String[] args) {
//		System.out.println(decodeString1("100[leetcode]"));
//		System.out.println(decodeString1("3[a]2[bc]"));
//		System.out.println(decodeString1("3[a2[c]]"));
//		System.out.println(decodeString1("2[abc]3[cd]ef")); // abcabccdcdcdef

		System.out.println(decodeString2("100[leetcode]"));
		System.out.println(decodeString2("3[a]2[bc]"));
		System.out.println(decodeString2("3[a2[c]]"));
		System.out.println(decodeString2("2[abc]3[cd]ef")); // abcabccdcdcdef
	}
}
