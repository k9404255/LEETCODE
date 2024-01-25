package leetCode.easy;

public class E0125_ValidPalindrome {
	public static boolean isPalindrome(String s) {
		s = s.toLowerCase().replaceAll("[^a-z0-9]", "");

		for (int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
		System.out.println(isPalindrome("race a car"));
		System.out.println(isPalindrome(" "));
	}
}
