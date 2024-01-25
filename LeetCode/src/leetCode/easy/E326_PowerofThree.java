package leetCode.easy;

public class E326_PowerofThree {
	// solution 1: iterate
	public static boolean isPowerOfThree1(int n) {
		if (n < 3) {
			return n == 1;
		}

		while (n % 3 == 0) {
			n /= 3;
		}

		return n == 1;
	}

	// solution 2: recursive
	public static boolean isPowerOfThree2(int n) {
		if (n < 3)
			return n == 1;
		if (n % 3 != 0)
			return false;

		return isPowerOfThree2(n / 3);
	}

	public static void main(String[] args) {
		System.out.println(isPowerOfThree1(27));
		System.out.println(isPowerOfThree1(0));
		System.out.println(isPowerOfThree1(-1));
		System.out.println(isPowerOfThree1(18));

		System.out.println(isPowerOfThree2(27));
		System.out.println(isPowerOfThree2(0));
		System.out.println(isPowerOfThree2(-1));
		System.out.println(isPowerOfThree2(18));
	}
}