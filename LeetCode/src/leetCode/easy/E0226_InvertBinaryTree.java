package leetCode.easy;

import java.util.LinkedList;
import java.util.Queue;

public class E0226_InvertBinaryTree {
	public class TreeNode {
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

	// solution 1: recursive
	public TreeNode invertTree1(TreeNode root) {
		if (root == null) {
			return null;
		}

		TreeNode left = invertTree1(root.left);
		TreeNode right = invertTree1(root.right);

		root.left = right;
		root.right = left;

		return root;
	}

	public void swapChild(TreeNode root) {
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
	}

	// solution 2: iterative
	public TreeNode invertTree2(TreeNode root) {
		if (root == null) {
			return null;
		}

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode tmp = q.remove();
			swapChild(tmp);

			if (tmp.left != null) {
				q.add(tmp.left);
			}
			if (tmp.right != null) {
				q.add(tmp.right);
			}
		}

		return root;
	}
}