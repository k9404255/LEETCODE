package leetCode.easy;

public class E0069_Sqrtx {
	public static int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}

		int left = 1;
		int right = (x / 2) + 1;
		int result = 0;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (mid * mid > x) {
				right = (int) mid - 1;
			} else if (mid * mid < x) {
				left = (int) mid + 1;
				result = (int) mid;
			} else {
				return (int) mid;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		for (int x = 1; x <= 10; x++) {
			System.out.println("X: " + x);
			System.out.println(mySqrt(x));
		}
	}
}