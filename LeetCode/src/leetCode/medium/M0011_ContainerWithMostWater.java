package leetCode.medium;

public class M0011_ContainerWithMostWater {
	// solution 1: brute-force (Time Limit Exceeded)
	public static int maxArea1(int[] height) {
		int max = 0;
		int length = height.length;

		for (int i = 1; i <= length; i++) {
			for (int j = 0; j < height.length - i; j++) {
				int volumn = i * Math.min(height[j], height[i + j]);
				max = Math.max(max, volumn);
			}
		}

		return max;
	}

	// solution 2: two pointers
	public static int maxArea2(int[] height) {
		int n = height.length;
		int left = 0;
		int right = n - 1;
		int max = 0;

		while (left < right) {
			max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
			if (height[left] > height[right]) {
				right--;
			} else {
				left++;
			}
		}

		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxArea1(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
		System.out.println(maxArea1(new int[] { 1, 1 }));

		System.out.println(maxArea2(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
		System.out.println(maxArea2(new int[] { 1, 1 }));
	}
}