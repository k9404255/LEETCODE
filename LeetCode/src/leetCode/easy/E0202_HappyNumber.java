package leetCode.easy;

import java.util.HashSet;
import java.util.Set;

public class E0202_HappyNumber {
	// solution 1: String
	public static boolean isHappy1(int n) {
		Set<Integer> set = new HashSet<Integer>();
		while (!set.contains(n)) {
			set.add(n);
			if (n == 1) {
				return true;
			}

			String s = String.valueOf(n);
			n = 0;
			for (int i = 0; i < s.length(); i++) {
				int x = Integer.parseInt(s.substring(i, i + 1));
				n += x * x;
			}
		}

		return false;
	}

	// solution 2: mod
	public static boolean isHappy2(int n) {
		Set<Integer> set = new HashSet<Integer>();
		while (!set.contains(n)) {
			set.add(n);
			if (n == 1) {
				return true;
			}

			int sum = 0;
			while (n != 0) {
				int x = n % 10;
				sum += x * x;
				n /= 10;
			}
			n = sum;
		}

		return false;
	}

	public static void main(String[] args) {
		System.out.println(isHappy1(19));
		System.out.println(isHappy1(2));
		System.out.println(isHappy1(7));

		System.out.println(isHappy2(19));
		System.out.println(isHappy2(2));
		System.out.println(isHappy2(7));
	}
}