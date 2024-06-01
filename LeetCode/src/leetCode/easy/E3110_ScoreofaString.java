package leetCode.easy;

public class E3110_ScoreofaString {
	// solution 1
	public static int scoreOfString1(String s) {
		int n = s.length();
		int sum = 0;
		for (int i = 1; i < n; i++) {
			int c1 = s.charAt(i);
			int c2 = s.charAt(i - 1);

			sum += Math.abs(c1 - c2);
		}

		return sum;
	}

	public static void main(String[] args) {
		System.out.println(scoreOfString1("hello"));
		System.out.println(scoreOfString1("zaz"));
	}
}
