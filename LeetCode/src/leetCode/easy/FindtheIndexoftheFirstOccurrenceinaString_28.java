package leetCode.easy;

public class FindtheIndexoftheFirstOccurrenceinaString_28 {
	public static int strStr1(String haystack, String needle) {
		if (needle.equals(haystack)) {
			return 0;
		}

		int lenOfNeedle = needle.length();
		int lenOfHaystack = haystack.length();

		if (lenOfNeedle > lenOfHaystack) {
			return -1;
		}

		for (int i = 0; i < lenOfHaystack - lenOfNeedle + 1; i++) {
			String subStr = haystack.substring(i, i + lenOfNeedle);
			if (subStr.equals(needle)) {
				return i;
			}
		}

		return -1;
	}

	public static int strStr2(String haystack, String needle) {
		return haystack.indexOf(needle);
	}

	public static void main(String[] args) {
		System.out.println(strStr1("sadbutsad", "sad"));
		System.out.println(strStr1("leetcode", "leeto"));
		System.out.println(strStr1("hello", "ll"));
		System.out.println(strStr1("abc", "c"));

		System.out.println(strStr2("sadbutsad", "sad"));
		System.out.println(strStr2("leetcode", "leeto"));
		System.out.println(strStr2("hello", "ll"));
	}
}