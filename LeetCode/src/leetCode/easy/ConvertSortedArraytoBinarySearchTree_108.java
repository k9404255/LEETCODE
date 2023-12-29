package leetCode.easy;

import java.util.Arrays;

public class ConvertSortedArraytoBinarySearchTree_108 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static void printArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println("");
	}

	public static TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0 || nums == null) {
			return null;
		}

		int rootIdx = nums.length / 2;
		TreeNode leftNode = sortedArrayToBST(Arrays.copyOfRange(nums, 0, rootIdx));
		TreeNode rightNode = sortedArrayToBST(Arrays.copyOfRange(nums, rootIdx + 1, nums.length));
		TreeNode root = new TreeNode(nums[rootIdx], leftNode, rightNode);

		return root;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { -10, -3, 0, 5, 9 };
		sortedArrayToBST(nums);
	}
}
