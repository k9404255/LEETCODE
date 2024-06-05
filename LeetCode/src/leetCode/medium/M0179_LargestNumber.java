package leetCode.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class M0179_LargestNumber {
	// solution 1
	public static String largestNumber1(int[] nums) {
		List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

		Collections.sort(list, (a, b) -> {
			String s1 = String.valueOf(a);
			String s2 = String.valueOf(b);
			int len1 = s1.length();
			int len2 = s2.length();

			if (len1 == len2) {
				return b.compareTo(a);
			}

			return (s2 + s1).compareTo(s1 + s2);
		});

		StringBuilder sb = new StringBuilder();
		list.forEach(n -> {
			sb.append(n);
		});

		if (list.get(0) == 0) {
			return "0";
		}

		return sb.toString();
	}

	// solution 2
	public static String largestNumber2(int[] nums) {
		String[] s = new String[nums.length];

		for (int i = 0; i < nums.length; i++) {
			s[i] = String.valueOf(nums[i]);
		}

		Arrays.sort(s, (a, b) -> (b + a).compareTo(a + b));

		return s[0].equals("0") ? "0" : String.join("", s);
	}

	public static void main(String[] args) {
		System.out.println(largestNumber1(new int[] { 10, 2 }));
		System.out.println(largestNumber1(new int[] { 3, 30, 34, 5, 9 }));
		System.out.println(largestNumber1(new int[] { 34323, 3432 }));
		System.out.println(largestNumber1(new int[] { 3, 30 }));
		System.out.println(largestNumber1(new int[] { 533, 5334 }));
		System.out.println(largestNumber1(new int[] { 999999991, 9 }));
		System.out.println(largestNumber1(new int[] { 0, 0 }));

		System.out.println(largestNumber2(new int[] { 10, 2 }));
		System.out.println(largestNumber2(new int[] { 3, 30, 34, 5, 9 }));
		System.out.println(largestNumber2(new int[] { 34323, 3432 }));
		System.out.println(largestNumber2(new int[] { 3, 30 }));
		System.out.println(largestNumber2(new int[] { 533, 5334 }));
		System.out.println(largestNumber2(new int[] { 999999991, 9 }));
		System.out.println(largestNumber2(new int[] { 0, 0 }));
	}
}
