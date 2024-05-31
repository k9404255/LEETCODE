package leetCode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class M0103_BinaryTreeZigzagLevelOrderTraversal {
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
	public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();

		if (root == null) {
			return ans;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int depth = 1;
		while (!queue.isEmpty()) {
			int n = queue.size();

			LinkedList<Integer> list = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				TreeNode cur = queue.poll();

				// method 1
				if (depth % 2 == 1) {
					list.add(cur.val);
				} else {
					list.addFirst(cur.val);
				}

				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}

			// method 2
//			if (depth % 2 == 0) {
//				Collections.reverse(list);
//			}

			ans.add(list);
			depth++;
		}

		return ans;
	}

	public static void main(String[] args) {
		TreeNode n15 = new TreeNode(15, null, null);
		TreeNode n7 = new TreeNode(7, null, null);
		TreeNode n20 = new TreeNode(20, n15, n7);
		TreeNode n9 = new TreeNode(9, null, null);
		TreeNode n3 = new TreeNode(3, n9, n20);
		System.out.println(zigzagLevelOrder1(n3));
	}
}
