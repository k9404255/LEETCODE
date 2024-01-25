package leetCode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class E0014_LongestCommonPrefix {
	// solution 1
	public static String longestCommonPrefix1(String[] strs) {
		int minSize = Arrays.stream(strs).min(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() > o2.length()) {
					return 1;
				}
				return -1;
			}
		}).orElse("").length();

		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < minSize; i++) {
			char c = strs[0].charAt(i);
			boolean flag = true;
			for (int j = 1; j < strs.length; j++) {
				if (strs[j].charAt(i) != c) {
					flag = false;
					break;
				}
			}

			if (flag) {
				list.add(c);
				continue;
			}
			break;
		}

		return list.stream().map(String::valueOf).collect(Collectors.joining());
	}

	// solution 2 (faster)
	public static String longestCommonPrefix2(String[] strs) {
		Arrays.sort(strs); // 按字母排序
		String s1 = strs[0];
		String s2 = strs[strs.length - 1];
		int idx = 0;
		while (idx < s1.length() && idx < s2.length()) {
			if (s1.charAt(idx) == s2.charAt(idx)) {
				idx++;
			} else {
				break;
			}
		}
		return s1.substring(0, idx);
	}

	public static void main(String[] args) {
		System.out.println(longestCommonPrefix1(new String[] { "flower", "flow", "flight" }));
		System.out.println(longestCommonPrefix1(new String[] { "dog", "racecar", "car" }));

		System.out.println(longestCommonPrefix2(new String[] { "flower", "flow", "flight" }));
		System.out.println(longestCommonPrefix2(new String[] { "dog", "racecar", "car" }));
	}
}
