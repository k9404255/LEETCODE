package leetCode.easy;

public class E190_ReverseBits {
	// solution 1: easy to understand
	public static int reverseBits1(int n) {
		String s = Integer.toBinaryString(n);
		StringBuilder sb = new StringBuilder().append(s).reverse();
		for (int i = 0; i < 32 - s.length(); i++) {
			sb.append("0");
		}

		return (int) Long.parseLong(sb.toString(), 2);
	}

	// solution 2: bit operation
	public static int reverseBits2(int n) {
		int ans = 0;

		for (int i = 0; i < 32; i++) {
			ans <<= 1;
			ans |= (n & 1);
			n >>= 1;
		}

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(reverseBits1(-3));
		System.out.println(reverseBits2(-3));
	}
}