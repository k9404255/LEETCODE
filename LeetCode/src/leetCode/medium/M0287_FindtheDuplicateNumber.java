package leetCode.medium;

import java.util.HashSet;
import java.util.Set;

public class M0287_FindtheDuplicateNumber {
	// solution 1: set
	public static int findDuplicate1(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i])) {
				return nums[i];
			}
			set.add(nums[i]);
		}

		return -1;
	}

	// solution 2: binary search (nlogn)
	public static int findDuplicate2(int[] nums) {
		int left = 0;
		int right = nums.length;
		while (left < right) {
			int mid = (left + right) / 2;

			int count = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] <= mid) {
					count++;
				}
			}

			if (count <= mid) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return left;
	}

	// solution 3: O(n) without modifying the array nums and uses only constant
	// extra space.
	// https://leetcode.wang/leetcode-287-Find-the-Duplicate-Number.html
	public static int findDuplicate3(int[] nums) {
		int slow = nums[0];
		int fast = nums[nums[0]];
		// 寻找相遇点
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}

		// slow 从起点出发, fast 从相遇点出发, 一次走一步
		slow = 0;
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}

	public static void main(String[] args) {
		System.out.println(findDuplicate1(new int[] { 1, 3, 4, 2, 2 }));
		System.out.println(findDuplicate1(new int[] { 3, 1, 3, 4, 2 }));
		System.out.println(findDuplicate1(new int[] { 3, 3, 3, 3, 3 }));

		System.out.println(findDuplicate2(new int[] { 1, 3, 4, 2, 2 }));
		System.out.println(findDuplicate2(new int[] { 3, 1, 3, 4, 2 }));
		System.out.println(findDuplicate2(new int[] { 3, 3, 3, 3, 3 }));
		System.out.println(findDuplicate2(new int[] { 3, 2, 1, 3, 3 }));
	}
}
