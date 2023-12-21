package leetCode.easy;

public class ClimbingStairs_70 {
	// Solution 1: Bottom-up
	public static int climbStairs1(int n) {
		// 找規律: F(1)=1, F(2)=2, F(3)=3, F(4)=5, F(5)=8, F(6)=13...
		int[] stairs = new int[46];
		stairs[1] = 1;
		stairs[2] = 2;
		for (int i = 3; i <= n; i++) {
			stairs[i] = stairs[i - 1] + stairs[i - 2];
		}

		return stairs[n];
	}

	// Solution 2: Top-down (效能問題，會重複計算)
	public static int climbStairs2(int n) {
		if (n <= 1) {
			return 1;
		}

		return climbStairs2(n - 1) + climbStairs2(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(climbStairs1(2));
		System.out.println(climbStairs1(3));

		System.out.println(climbStairs2(2));
		System.out.println(climbStairs2(3));
	}
}
