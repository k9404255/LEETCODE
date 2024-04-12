package leetCode.easy;

public class E0058_LengthofLastWord {
	// solution1
	public static int lengthOfLastWord1(String s) {
		String[] words = s.split(" ");
		return words[words.length - 1].length();
	}

	// solution2
	public static int lengthOfLastWord2(String s) {
		s = s.trim(); // 首尾空白去掉

		int len = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) != ' ') {
				len++;
			} else {
				break;
			}
		}
		
		return len;
	}

	public static void main(String[] args) {
		System.out.println(lengthOfLastWord1("Hello World"));
		System.out.println(lengthOfLastWord1("   fly me   to   the moon  "));
		System.out.println(lengthOfLastWord1("luffy is still joyboy"));
		
		System.out.println(lengthOfLastWord2("Hello World"));
		System.out.println(lengthOfLastWord2("   fly me   to   the moon  "));
		System.out.println(lengthOfLastWord2("luffy is still joyboy"));
	}
}
