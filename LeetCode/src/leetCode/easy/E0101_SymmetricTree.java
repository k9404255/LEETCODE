package leetCode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E0101_SymmetricTree {
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

	public static List<Integer> preOrderLeftFirst(TreeNode root) {
		List<Integer> list = new ArrayList<>();

		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode curNode = root;
		while (curNode != null || !s.empty()) {
			if (curNode != null) {
				list.add(curNode.val);
				s.push(curNode);
				curNode = curNode.left;
			} else {
				curNode = s.pop();
				curNode = curNode.right;
				list.add(null);
			}
		}

		return list;
	}

	public static List<Integer> preOrderRightFirst(TreeNode root) {
		List<Integer> list = new ArrayList<>();

		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode curNode = root;
		while (curNode != null || !s.empty()) {
			if (curNode != null) {
				list.add(curNode.val);
				s.push(curNode);
				curNode = curNode.right;
			} else {
				curNode = s.pop();
				curNode = curNode.left;
				list.add(null);
			}
		}

		return list;
	}

	public static boolean isSymmetric1(TreeNode root) {
		List<Integer> list1 = preOrderLeftFirst(root.left);
		List<Integer> list2 = preOrderRightFirst(root.right);

		return list1.equals(list2);
	}

	public static boolean isMirror(TreeNode n1, TreeNode n2) {
		if (n1 == null && n2 == null) {
			return true;
		}

		if (n1 == null || n2 == null) {
			return false;
		}

		return n1.val == n2.val && isMirror(n1.left, n2.right) && isMirror(n1.right, n2.left);
	}

	public static boolean isSymmetric2(TreeNode root) {
		if (root == null) {
			return false;
		}

		return isMirror(root.left, root.right);
	}

	public static void main(String[] args) {
//		TreeNode n4 = new TreeNode(3, null, null);
//		TreeNode n5 = new TreeNode(3, null, null);
//		TreeNode n2 = new TreeNode(2, null, n4);
//		TreeNode n3 = new TreeNode(2, null, n5);
//		TreeNode n1 = new TreeNode(1, n2, n3);
//		System.out.println(isSymmetric(n1));

		TreeNode n4 = new TreeNode(3, null, null);
		TreeNode n5 = new TreeNode(4, null, null);
		TreeNode n6 = new TreeNode(4, null, null);
		TreeNode n7 = new TreeNode(3, null, null);
		TreeNode n2 = new TreeNode(2, n4, n5);
		TreeNode n3 = new TreeNode(2, n6, n7);
		TreeNode n1 = new TreeNode(1, n2, n3);
		System.out.println(isSymmetric1(n1));
	}
}
