package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0199_BinaryTreeRightSideView {
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

	// solution 1: bfs
	public static List<Integer> rightSideView1(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		List<TreeNode> list = new ArrayList<>();

		if (root == null) {
			return ans;
		}

		list.add(root);
		while (!list.isEmpty()) {
			int n = list.size();
			ans.add(list.get(n - 1).val);

			for (int i = 0; i < n; i++) {
				TreeNode node = list.remove(0);
				if (node.left != null) {
					list.add(node.left);
				}

				if (node.right != null) {
					list.add(node.right);
				}
			}
		}

		return ans;
	}

	// solution 2: dfs
	public List<Integer> rightSideView2(TreeNode root) {
		List<Integer> A = new ArrayList<>();
		rec(root, 0, A);
		return A;
	}

	void rec(TreeNode root, int level, List<Integer> A) {
		if (root == null)
			return;
		if (level == A.size())
			A.add(root.val);// every first node in that level is part of right side

		rec(root.right, level + 1, A);
		rec(root.left, level + 1, A);// level order from right side view
	}

	public static void main(String[] args) {

	}
}
