package leetCode.medium;

public class M0371_SumofTwoIntegers {
	// solution 1:
	// https://leetcode.com/problems/sum-of-two-integers/solutions/84290/java-simple-easy-understand-solution-with-explanation/
	// https://medium.com/@smalldragon89/%E6%BC%94%E7%AE%97%E6%B3%95%E7%AD%86%E8%A8%98-371-sum-of-two-integers-368a8977fd68
	public static int getSum1(int a, int b) {
		if (a == 0) {
			return b;
		}

		if (b == 0) {
			return a;
		}

		while (b != 0) {
			System.out.println("a: " + Integer.toBinaryString(a) + ", b: " + Integer.toBinaryString(b) + ", a&b: "
					+ Integer.toBinaryString(a & b));

			int carry = a & b;
			a = a ^ b;
			b = carry << 1;
		}

		return a;
	}

	public static void main(String[] args) {
		System.out.println(getSum1(1, 3));
//		System.out.println(getSum1(3, 5));
//		System.out.println(getSum1(0, 0));
//		System.out.println(getSum1(-1, -2));
//		System.out.println(getSum1(-1, 2));
//		System.out.println(getSum1(-2, 1));
	}
}
