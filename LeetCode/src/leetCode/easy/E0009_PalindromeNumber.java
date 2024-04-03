package leetCode.easy;

public class E0009_PalindromeNumber {
	// solution 1
	public static boolean isPalindrome1(int x) {
		String str = String.valueOf(x);
		int n = str.length();
		for (int i = 0; i < n / 2; i++) {
			if (str.charAt(i) != str.charAt(n - i - 1)) {
				return false;
			}
		}
		return true;
	}

	// solution 2
	public static boolean isPalindrome2(int x) {
		int reverse = 0;
		int tmp = x;
		while (tmp > 0) {
			reverse = reverse * 10 + tmp % 10;
			tmp /= 10;
		}

		return reverse == x;
	}

	public static void main(String[] args) {
		System.out.println(isPalindrome2(121));
		System.out.println(isPalindrome2(321));
	}
}
