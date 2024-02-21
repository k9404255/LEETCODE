package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class M0102_BinaryTreeLevelOrderTraversal {
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

	public static class HeightNode {
		int height;
		TreeNode n;

		HeightNode(TreeNode n, int height) {
			this.height = height;
			this.n = n;
		}
	}

	// solution 1: BFS (queue)
	public static List<List<Integer>> levelOrder1(TreeNode root) {
		if (root == null) {
			return new ArrayList<List<Integer>>();
		}

		Map<Integer, List<Integer>> map = new HashMap<>();

		Queue<HeightNode> q = new LinkedList<>();
		q.add(new HeightNode(root, 1));

		while (!q.isEmpty()) {
			HeightNode curNode = q.poll();
			if (map.containsKey(curNode.height)) {
				map.get(curNode.height).add(curNode.n.val);
			} else {
				map.put(curNode.height, new ArrayList<>(Arrays.asList(curNode.n.val)));
			}

			if (curNode.n.left != null) {
				q.add(new HeightNode(curNode.n.left, curNode.height + 1));
			}
			if (curNode.n.right != null) {
				q.add(new HeightNode(curNode.n.right, curNode.height + 1));
			}
		}

		return new ArrayList<>(map.values());
	}

	// solution 2
	public static List<List<Integer>> levelOrder2(TreeNode root) {
		if (root == null) {
			return new ArrayList<List<Integer>>();
		}

		List<List<Integer>> ans = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			int curLevelNums = q.size();
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < curLevelNums; i++) {
				TreeNode curNode = q.poll();
				list.add(curNode.val);
				if (curNode.left != null) {
					q.add(curNode.left);
				}
				if (curNode.right != null) {
					q.add(curNode.right);
				}
			}
			ans.add(list);
		}

		return ans;
	}

	// solution 3: DFS (Recursion)
	public static List<List<Integer>> levelOrder3(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		levelOrder3(root, 0, ans);
		return ans;
	}

	public static List<List<Integer>> levelOrder3(TreeNode root, int level, List<List<Integer>> ans) {
		if (root == null)
			return ans;

		if (ans.size() == level)
			ans.add(new ArrayList<>());

		ans.get(level).add(root.val);
		System.out.println(ans);
		levelOrder3(root.left, level + 1, ans);
		levelOrder3(root.right, level + 1, ans);

		return ans;
	}

	public static void main(String[] args) {
		TreeNode n1_1 = new TreeNode(15);
		TreeNode n1_2 = new TreeNode(7);
		TreeNode n1_3 = new TreeNode(20, n1_1, n1_2);
		TreeNode n1_4 = new TreeNode(9);
		TreeNode n1_5 = new TreeNode(3, n1_4, n1_3);

		System.out.println(levelOrder1(n1_5));
		System.out.println(levelOrder2(n1_5));
		System.out.println(levelOrder3(n1_5));

		TreeNode n2_1 = new TreeNode(15);
		TreeNode n2_2 = new TreeNode(7);
		TreeNode n2_3 = new TreeNode(9, n2_1, n2_2);
		TreeNode n2_4 = new TreeNode(20);
		TreeNode n2_5 = new TreeNode(3, n2_3, n2_4);

		System.out.println(levelOrder1(n2_5));
		System.out.println(levelOrder2(n2_5));
		System.out.println(levelOrder3(n2_5));
	}
}
