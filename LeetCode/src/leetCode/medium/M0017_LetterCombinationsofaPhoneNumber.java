package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M0017_LetterCombinationsofaPhoneNumber {
	// solution 1
	public static List<String> letterCombinations1(String digits) {
		Map<Character, List<String>> digit2CharList = new HashMap<Character, List<String>>();
		digit2CharList.put('2', Arrays.asList("a", "b", "c"));
		digit2CharList.put('3', Arrays.asList("d", "e", "f"));
		digit2CharList.put('4', Arrays.asList("g", "h", "i"));
		digit2CharList.put('5', Arrays.asList("j", "k", "l"));
		digit2CharList.put('6', Arrays.asList("m", "n", "o"));
		digit2CharList.put('7', Arrays.asList("p", "q", "r", "s"));
		digit2CharList.put('8', Arrays.asList("t", "u", "v"));
		digit2CharList.put('9', Arrays.asList("w", "x", "y", "z"));

		List<String> ans = new ArrayList<String>();

		for (int i = 0; i < digits.length(); i++) {
			List<String> strList = digit2CharList.get(digits.charAt(i));

			if (ans.isEmpty()) {
				ans = strList;
			} else {
				List<String> tmpList = new ArrayList<String>();
				ans.forEach(str1 -> {
					strList.forEach(str2 -> {
						tmpList.add(str1 + str2);
					});
				});
				ans = tmpList;
			}
		}

		return ans;
	}

	// solution 2: recursive
	public static List<String> letterCombinations2(String digits) {
		List<String> answers = new ArrayList<String>();
		if (digits.isEmpty()) {
			return answers;
		}

		Map<Character, String> digit2CharList = new HashMap<Character, String>();
		digit2CharList.put('2', "abc");
		digit2CharList.put('3', "def");
		digit2CharList.put('4', "ghi");
		digit2CharList.put('5', "jkl");
		digit2CharList.put('6', "mno");
		digit2CharList.put('7', "pqrs");
		digit2CharList.put('8', "tuv");
		digit2CharList.put('9', "wxyz");

		helper(digits, digit2CharList, 0, new StringBuilder(), answers);

		return answers;
	}

	public static void helper(String digits, Map<Character, String> map, int curIdx, StringBuilder sb,
			List<String> answers) {
		if (curIdx == digits.length()) {
			answers.add(sb.toString());
			return;
		}

		String str = map.get(digits.charAt(curIdx));
		for (int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(i));
			helper(digits, map, curIdx + 1, sb, answers);
			sb.deleteCharAt(sb.length() - 1);
		}

	}

	public static void main(String[] args) {
		System.out.println(letterCombinations1("23"));
		System.out.println(letterCombinations1(""));
		System.out.println(letterCombinations1("2"));
		System.out.println(letterCombinations1("9"));

		System.out.println(letterCombinations2("23"));
		System.out.println(letterCombinations2(""));
		System.out.println(letterCombinations2("2"));
		System.out.println(letterCombinations2("9"));
	}
}