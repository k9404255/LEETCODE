package leetCode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class M0230_KthSmallestElementinaBST {
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

	// solution 1: inorder
	public static int kthSmallest1(TreeNode root, int k) {
		int count = 0;

		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);
		while (!stack.empty()) {
			if (root != null && root.left != null) {
				stack.add(root.left);
				root = root.left;
			} else {
				count++;
				TreeNode node = stack.pop();

				if (count == k) {
					return node.val;
				}

				if (node.right != null) {
					stack.add(node.right);
				}
				root = node.right;
			}
		}

		return 0;
	}

	// solution 2: inorder
	public static int kthSmallest2(TreeNode root, int k) {
		int count = 0;

		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.empty()) {
			if (root != null) {
				stack.add(root);
				root = root.left;
			} else {
				count++;
				root = stack.pop();

				if (count == k) {
					return root.val;
				}

				root = root.right;
			}
		}

		return 0;
	}

	// solution 3: recursion
	public static int kthSmallest3(TreeNode root, int k) {
		List<Integer> result = new ArrayList<>();
		inorderTraversal(root, result);
		
		return result.get(k-1);
	}

	private static void inorderTraversal(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}

		inorderTraversal(root.left, result);
		result.add(root.val);
		inorderTraversal(root.right, result);
	}

	public static void main(String[] args) {
//		TreeNode n8 = new TreeNode(8);
//		TreeNode n9 = new TreeNode(9);
//		TreeNode n7 = new TreeNode(7, n8, n9);
//		TreeNode n6 = new TreeNode(6);
//		TreeNode n5 = new TreeNode(5, n6, null);
//		TreeNode n4 = new TreeNode(4, n7, null);
//		TreeNode n3 = new TreeNode(3, n5, n4);
//		TreeNode n2 = new TreeNode(2);
//		TreeNode n1 = new TreeNode(1, n3, n2);
//		System.out.println(kthSmallest1(n1, 3));

		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2, n1, null);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3, n2, n4);
		TreeNode n6 = new TreeNode(6);
		TreeNode n5 = new TreeNode(5, n3, n6);
		System.out.println(kthSmallest3(n5, 4));
	}
}
