package leetCode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class M0114_FlattenBinaryTreetoLinkedList {
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

	// solution 1
	public static void flatten1(TreeNode root) {
		if (root == null) {
			return;
		}

		List<Integer> preOrder = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
		TreeNode cur = root;

		while (cur != null || !s.empty()) {
			if (cur != null) {
				preOrder.add(cur.val);
				s.add(cur);
				cur = cur.left;
			} else {
				cur = s.pop();
				cur = cur.right;
			}
		}

		preOrder.remove(0);
		cur = root;
		cur.left = null;
		cur.right = null;
		while (!preOrder.isEmpty()) {
			TreeNode n = new TreeNode(preOrder.remove(0), null, null);
			cur.right = n;
			cur = cur.right;
		}
	}

	// solution 2
	public static void flatten2(TreeNode root) {
		if (root == null) {
			return;
		}

		List<Integer> preOrder = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
		s.push(root);

		TreeNode cur = new TreeNode();
		while (!s.empty()) {
			cur.right = s.pop();
			cur.left = null;
			cur = cur.right;
			preOrder.add(cur.val);

			if (cur.right != null) {
				s.push(cur.right);
			}
			if (cur.left != null) {
				s.push(cur.left);
			}
		}

		System.out.println(preOrder);
	}

	public static void main(String[] args) {
		TreeNode n1_1 = new TreeNode(3, null, null);
		TreeNode n1_2 = new TreeNode(4, null, null);
		TreeNode n1_3 = new TreeNode(2, n1_1, n1_2);
		TreeNode n1_4 = new TreeNode(6, null, null);
		TreeNode n1_5 = new TreeNode(5, null, n1_4);
		TreeNode n1_6 = new TreeNode(1, n1_3, n1_5);
		flatten2(n1_6);
	}
}
