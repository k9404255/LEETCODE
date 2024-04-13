package leetCode.easy;

public class E0067_AddBinary {
	// solution1: NumberFormatException
	public static String addBinary1(String a, String b) {
		Long longA = Long.parseLong(a, 2);
		Long longB = Long.parseLong(b, 2);

		return Long.toBinaryString(longA + longB);
	}

	// solution2
	public static String addBinary2(String a, String b) {
		int i = a.length() - 1;
		int j = b.length() - 1;
		StringBuilder sb = new StringBuilder();

		int carry = 0;
		while (i >= 0 || j >= 0 || carry > 0) {
			int num1 = i >= 0 ? a.charAt(i) - '0' : 0;
			int num2 = j >= 0 ? b.charAt(j) - '0' : 0;
			int sum = num1 + num2 + carry;
			carry = sum / 2;
			sum = sum % 2;

			sb.append(sum);
			i--;
			j--;
		}

		return sb.reverse().toString();
	}

	public static void main(String[] args) {
//		System.out.println(addBinary1("11", "1"));
//		System.out.println(addBinary1("1010", "1011"));
//		System.out.println(addBinary1(
//				"10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
//				"110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"));

		System.out.println(addBinary2("11", "1"));
		System.out.println(addBinary2("1010", "1011"));
		System.out.println(addBinary2(
				"10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
				"110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"));

	}
}
