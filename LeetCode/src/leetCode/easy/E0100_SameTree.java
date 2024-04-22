package leetCode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E0100_SameTree {
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

	// solution 1
	public boolean isSameTree1(TreeNode p, TreeNode q) {
		if (p == null || q == null) {
			return p == q;
		}

		Stack<TreeNode> stack = new Stack<>();
		List<TreeNode> list1 = new ArrayList<>();
		while (!stack.empty() || p != null) {
			if (p != null) {
				list1.add(p);
				stack.add(p);
				p = p.left;
			} else {
				list1.add(p);
				p = stack.pop();
				p = p.right;
			}
		}

		stack = new Stack<>();
		List<TreeNode> list2 = new ArrayList<>();
		while (!stack.empty() || q != null) {
			if (q != null) {
				list2.add(q);
				stack.add(q);
				q = q.left;
			} else {
				list2.add(q);
				q = stack.pop();
				q = q.right;
			}
		}

		if (list1.size() != list2.size()) {
			return false;
		}

		for (int i = 0; i < list1.size(); i++) {
			int n1 = list1.get(i) == null ? Integer.MAX_VALUE : list1.get(i).val;
			int n2 = list2.get(i) == null ? Integer.MAX_VALUE : list2.get(i).val;

			if (n1 != n2) {
				return false;
			}
		}

		return true;
	}

	// solution 2: recursive
	public boolean isSameTree2(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		if (p.val != q.val)
			return false;
		return (isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right));
	}

	public static void main(String[] args) {
		System.out.println(null == null);
	}

}
