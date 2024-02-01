package leetCode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class M0022_GenerateParentheses {
	// solution 1 (Time Limit Exceeded): 列出所有字串的排列組合之後，再把不合法的Parenthesis排掉
	public static void swap(int i, int j, char[] str) {
		if (i != j) {
			char temp = str[i];
			str[i] = str[j];
			str[j] = temp;
		}
	}

	public static void dfs(Set<String> set, char[] str, int cur) {
		if (cur == str.length) {
			set.add(new String(str));
			return;
		}

		for (int i = 0; i < str.length; i++) {
			swap(i, cur, str);
			dfs(set, str, cur + 1);
			swap(cur, i, str);
		}

	}

	public static boolean isValid(String s) {
		Map<Character, Character> map = new HashMap<>();
		map.put('(', ')');

		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (map.containsKey(c)) {
				stack.push(map.get(c));
			} else if (stack.empty() || stack.pop() != c) {
				return false;
			}
		}

		return stack.empty();
	}

	public static List<String> generateParenthesis1(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append("()");
		}

		Set<String> set = new HashSet<String>();
		dfs(set, sb.toString().toCharArray(), 0);

		List<String> answer = new ArrayList<String>();
		set.forEach(str -> {
			if (isValid(str)) {
				answer.add(str);
			}
		});

		return answer;
	}

	// solution 2: BackTracking (String)
	public static List<String> generateParenthesis2(int n) {
		List<String> result = new ArrayList<String>();
		helper2(result, "", 0, 0, n);

		return result;
	}

	public static void helper2(List<String> result, String s, int left, int right, int n) {
		if (s.length() == n * 2) {
			result.add(s);
		}

		if (left < n) {
			helper2(result, s + "(", left + 1, right, n);
		}

		if (right < left) {
			helper2(result, s + ")", left, right + 1, n);
		}
	}

	// solution 3: BackTracking (StringBuilder)
	public static List<String> generateParenthesis3(int n) {
		List<String> result = new ArrayList<String>();
		helper3(result, new StringBuilder(), 0, 0, n);

		return result;
	}

	public static void helper3(List<String> result, StringBuilder sb, int left, int right, int n) {
		if (sb.length() == n * 2) {
			result.add(sb.toString());
		}

		if (left < n) {
			sb.append("(");
			helper3(result, sb, left + 1, right, n);
			sb.deleteCharAt(sb.length() - 1);
		}

		if (right < left) {
			sb.append(")");
			helper3(result, sb, left, right + 1, n);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public static void main(String[] args) {
		System.out.println(generateParenthesis1(3));
		System.out.println(generateParenthesis1(1));

		System.out.println(generateParenthesis2(3));
		System.out.println(generateParenthesis2(1));

		System.out.println(generateParenthesis3(3));
		System.out.println(generateParenthesis3(1));
	}
}