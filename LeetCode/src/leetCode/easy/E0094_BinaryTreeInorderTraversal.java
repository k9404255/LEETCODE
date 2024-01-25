package leetCode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E0094_BinaryTreeInorderTraversal {
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

	// Solution 1
	public List<Integer> inorderTraversal1(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();

		if (root == null) {
			return list;
		}

		list.addAll(inorderTraversal1(root.left));
		list.add(root.val);
		list.addAll(inorderTraversal1(root.right));

		return list;
	}

	// Solution 2
	public List<Integer> inorderTraversal2(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();

		if (root == null) {
			return list;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode curNode = root;
		while (curNode != null || !stack.empty()) {
			if (curNode != null) {
				stack.push(curNode);
				curNode = curNode.left;
			} else {
				curNode = stack.pop();
				list.add(curNode.val);
				curNode = curNode.right;
			}
		}

		return list;
	}

	public static void main(String[] args) {

	}

}
