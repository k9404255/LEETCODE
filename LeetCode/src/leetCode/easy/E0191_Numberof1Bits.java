package leetCode.easy;

public class E0191_Numberof1Bits {
	// solution 1: String
	public static int hammingWeight1(int n) {
		String s = Integer.toBinaryString(n);
		s = s.replace("0", "");
		return s.length();
	}

	// solution 2: bit count
	public static int hammingWeight2(int n) {
		return Integer.bitCount(n);
	}

	// solution 3: caculate
	public static int hammingWeight3(int n) {
		int count = 0;
		if (n > 0) {
			while (n != 0) {
				count += n % 2;
				n /= 2;
			}
		} else if (n < 0) {
			n = ~n;
			while (n != 0) {
				count += n % 2;
				n /= 2;
			}
			count = 32 - count;
		}

		return count;
	}

	public static void main(String[] args) {
		System.out.println(hammingWeight1(-3));
		System.out.println(hammingWeight2(-3));
		System.out.println(hammingWeight3(-3));
	}
}