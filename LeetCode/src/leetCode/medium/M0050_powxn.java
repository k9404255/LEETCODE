package leetCode.medium;

public class M0050_powxn {
	// solution 1
	public static double myPow1(double x, int n) {
		return Math.pow(x, n);
	}

	// solution 2: brute-force (Time Limit Exceeded)
	public static double myPow2(double x, int n) {
		double ans = 1;
		for (int i = 0; i < Math.abs(n); i++) {
			ans *= x;
		}

		return n < 0 ? 1 / ans : ans;
	}

	// solution 3
	public static double myPow3(double x, int n) {
		if (n < 0) {
			n = -n;
			x = 1 / x;
		}

		double pow = 1;

		while (n != 0) {
			if ((n & 1) != 0) {
				pow *= x;
			}

			x *= x;
			n >>>= 1;
		}

		return pow;
	}

	public static void main(String[] args) {
//		System.out.println(myPow1(2.00000, 10));
//		System.out.println(myPow1(2.10000, 3));
//		System.out.println(myPow1(2.00000, -2));
//
//		System.out.println(myPow2(2.00000, 10));
//		System.out.println(myPow2(2.10000, 3));
//		System.out.println(myPow2(2.00000, -2));
//
//		System.out.println(myPow3(2.00000, 10));
//		System.out.println(myPow3(2.10000, 3));
//		System.out.println(myPow3(2.00000, -2));

		System.out.println(myPow3(2.00000, 10));
	}
}
