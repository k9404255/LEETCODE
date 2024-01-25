package leetCode.easy;

import java.util.HashMap;
import java.util.Map;

public class E0013_RomanToInteger {
	// solution 1 - brute-force
	public static int romanToInt1(String s) {
		Map<String, Integer> romanRule = new HashMap<String, Integer>();
		romanRule.put("I", 1);
		romanRule.put("V", 5);
		romanRule.put("X", 10);
		romanRule.put("L", 50);
		romanRule.put("C", 100);
		romanRule.put("D", 500);
		romanRule.put("M", 1000);
		romanRule.put("IV", 4);
		romanRule.put("IX", 9);
		romanRule.put("XL", 40);
		romanRule.put("XC", 90);
		romanRule.put("CD", 400);
		romanRule.put("CM", 900);

		int result = 0;
		for (int i = 0; i < s.length();) {
			String twoChars = s.substring(i, Math.min(i + 2, s.length()));
			if (romanRule.containsKey(twoChars)) {
				result += romanRule.get(twoChars);
				i = Math.min(i + 2, s.length());
				continue;
			}

			String oneChar = s.substring(i, i + 1);
			if (romanRule.containsKey(oneChar)) {
				result += romanRule.get(oneChar);
				i++;
			}
		}

		return result;
	}

	// solution 2 - find pattern (當一個數字後面接著較大的數字，代表當前數字要撿掉)
	public static int romanToInt2(String s) {
		Map<Character, Integer> romanValues = new HashMap<>();
		romanValues.put('I', 1);
		romanValues.put('V', 5);
		romanValues.put('X', 10);
		romanValues.put('L', 50);
		romanValues.put('C', 100);
		romanValues.put('D', 500);
		romanValues.put('M', 1000);

		int result = 0;

		for (int i = s.length() - 1; i >= 0; i--) {
			int currValue = romanValues.get(s.charAt(i));

			if (i < s.length() - 1 && currValue < romanValues.get(s.charAt(i + 1))) {
				result -= currValue;
			} else {
				result += currValue;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// 第4筆測資答案不一樣，但因為沒有這種測資所以不知道哪個對

		System.out.println(romanToInt1("III"));
		System.out.println(romanToInt1("LVIII"));
		System.out.println(romanToInt1("MCMXCIV"));
		System.out.println(romanToInt1("VL"));

		System.out.println(romanToInt2("III"));
		System.out.println(romanToInt2("LVIII"));
		System.out.println(romanToInt2("MCMXCIV"));
		System.out.println(romanToInt2("VL"));
	}
}
