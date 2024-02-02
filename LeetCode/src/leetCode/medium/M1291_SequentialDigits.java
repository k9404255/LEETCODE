package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M1291_SequentialDigits {
	// solution 1: slide window
	public static List<Integer> sequentialDigits1(int low, int high) {
		List<Integer> ans = new ArrayList<Integer>();
		int lowLen = String.valueOf(low).length();
		int maxLen = String.valueOf(high).length();

		for (int curLen = lowLen; curLen <= maxLen; curLen++) {
			for (int i = 1; i <= 10 - curLen; i++) {
				int sum = 0;
				for (int j = 0; j < curLen; j++) {
					sum += (i + j) * Math.pow(10, curLen - 1 - j);
				}

				if (sum >= low && sum <= high) {
					ans.add(sum);
				} else if (sum > high) {
					break;
				}
			}
		}

		return ans;
	}

	// solution 2
	public static List<Integer> sequentialDigits2(int low, int high) {
		List<Integer> ans = new ArrayList<>();

		for (int i = 1; i <= 9; i++) {
			int sum = i;
			int nextDigit = i + 1;

			while (sum <= high && nextDigit <= 9) {
				sum = sum * 10 + nextDigit;

				if (sum >= low && sum <= high) {
					ans.add(sum);
				}

				nextDigit++;
			}
		}

		ans.sort(null);

		return ans;
	}

	// solution 3
	public static List<Integer> sequentialDigits3(int low, int high) {
		List<Integer> ans = new ArrayList<>();
		String digits = "123456789";
		int lowLen = String.valueOf(low).length();
		int maxLen = String.valueOf(high).length();

		for (int i = lowLen; i <= maxLen; i++) {
			for (int j = 0; j <= digits.length() - i; j++) {
				int val = Integer.parseInt(digits.substring(j, j + i));
				if (val >= low && val <= high) {
					ans.add(val);
				}
			}
		}

		return ans;
	}

	// solution 4
	public static List<Integer> sequentialDigits4(int low, int high) {
		List<Integer> ans = new ArrayList<>();
		String digits = "123456789";

		for (int i = 0; i < 9; i++) {
			int cutLen = 2;
			int sum = 1;

			while (sum <= high && i + cutLen <= 9) {
				sum = Integer.parseInt(digits.substring(i, i + cutLen));
				if (sum >= low && sum <= high) {
					ans.add(sum);
				}

				cutLen++;
			}
		}

		ans.sort(null);

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(sequentialDigits1(100, 10000));
		System.out.println(sequentialDigits1(1000, 13000));

		System.out.println(sequentialDigits2(1, 13000));
		System.out.println(sequentialDigits2(1000, 13000));

		System.out.println(sequentialDigits3(1, 13000));
		System.out.println(sequentialDigits3(1000, 13000));

		System.out.println(sequentialDigits4(1, 13000));
		System.out.println(sequentialDigits4(1000, 13000));
	}
}