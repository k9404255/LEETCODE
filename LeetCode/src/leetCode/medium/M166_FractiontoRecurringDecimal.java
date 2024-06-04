package leetCode.medium;

import java.util.HashMap;
import java.util.Map;

public class M166_FractiontoRecurringDecimal {
	// solution 1
	public static String fractionToDecimal1(int numerator, int denominator) {
		long num = (long) numerator;
		long deno = (long) denominator;
		if (num % deno == 0) {
			return String.valueOf(num / deno);
		}

		boolean isNegative = num < 0 ^ deno < 0;
		num = Math.abs(num);
		deno = Math.abs(deno);

		Map<Long, Integer> map = new HashMap<>();

		StringBuilder sb = new StringBuilder();
		if (isNegative) {
			sb.append("-");
		}

		sb.append(num / deno).append(".");
		num %= deno;
		while (num != 0) {
			num *= 10;
			long quotian = num / deno;
			if (map.containsKey(num)) { // cycle detected
				sb.insert(map.get(num), "(");
				sb.append(")");
				return sb.toString();
			} else {
				map.put(num, sb.length());
				sb.append(quotian);
			}
			num %= deno;
		}

		return sb.toString();
	}

	// solution 2
	public static String fractionToDecimal2(int numerator, int denominator) {
		boolean isNegative = (numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0) ? true : false;
		long numeratorL = Math.abs((long) numerator);
		long denominatorL = Math.abs((long) denominator);

		Map<Long, Integer> previousRemains = new HashMap<Long, Integer>();
		StringBuilder sb = new StringBuilder();
		long quotian = numeratorL / denominatorL;
		sb.append(quotian);

		numeratorL %= denominatorL;

		if (numeratorL != 0) {
			sb.append(".");
		}

		int quotianIndex = 0;
		while (numeratorL != 0) {
			numeratorL *= 10;
			quotian = Math.abs(numeratorL / denominatorL);
			if (!previousRemains.containsKey(numeratorL)) {
				sb.append(quotian);
				previousRemains.put(numeratorL, quotianIndex++);
			} else {
				int firstIndex = 1 + previousRemains.get(numeratorL) + sb.indexOf(".");
				sb.insert(firstIndex, '(');
				sb.append(")");
				break;
			}
			numeratorL %= denominatorL;
		}

		if (isNegative) {
			sb.insert(0, "-");
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(fractionToDecimal1(1, 2));
		System.out.println(fractionToDecimal1(2, 1));
		System.out.println(fractionToDecimal1(4, 333));
		System.out.println(fractionToDecimal1(1, 333));
		System.out.println(fractionToDecimal1(-50, 8));
		System.out.println(fractionToDecimal1(50, -8));
		System.out.println(fractionToDecimal1(-1, -2147483648));
		System.out.println(fractionToDecimal1(-2147483648, 1));

		System.out.println("----");

		System.out.println(fractionToDecimal2(1, 2));
		System.out.println(fractionToDecimal2(2, 1));
		System.out.println(fractionToDecimal2(4, 333));
		System.out.println(fractionToDecimal2(1, 333));
		System.out.println(fractionToDecimal2(-50, 8));
		System.out.println(fractionToDecimal2(50, -8));
		System.out.println(fractionToDecimal2(-1, -2147483648));
		System.out.println(fractionToDecimal2(-2147483648, 1));
	}
}
