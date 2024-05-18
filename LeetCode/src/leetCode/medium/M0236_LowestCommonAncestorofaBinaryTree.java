package leetCode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class M0236_LowestCommonAncestorofaBinaryTree {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}

		TreeNode(int x, TreeNode left, TreeNode right) {
			val = x;
			this.left = left;
			this.right = right;
		}
	}

	public static void printNodes(List<TreeNode> list) {
		list.forEach(node -> {
			System.out.print(node.val + " ");
		});
		System.out.println();
	}

	public static Map<TreeNode, TreeNode> preorder(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		List<TreeNode> preorder = new ArrayList<TreeNode>();
		Map<TreeNode, TreeNode> prevMap = new HashMap<>();

		stack.push(root);
		prevMap.put(root, null);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			preorder.add(cur);

			if (cur.right != null) {
				stack.add(cur.right);
				prevMap.put(cur.right, cur);
			}
			if (cur.left != null) {
				stack.add(cur.left);
				prevMap.put(cur.left, cur);
			}
		}

		return prevMap;
	}

	// solution 1: use map to memorize the parent of each node
	public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		Map<TreeNode, TreeNode> prevMap = preorder(root);

		TreeNode cur = p;
		Stack<TreeNode> parents1 = new Stack<>();
		while (cur != null) {
			parents1.add(cur);
			cur = prevMap.get(cur);
		}

		cur = q;
		Stack<TreeNode> parents2 = new Stack<>();
		while (cur != null) {
			parents2.add(cur);
			cur = prevMap.get(cur);
		}

		TreeNode preParent = parents1.pop();
		parents2.pop();
		while (true) {
			if (parents1.isEmpty() || parents2.isEmpty()) {
				return preParent;
			}

			TreeNode curParent1 = parents1.pop();
			TreeNode curParent2 = parents2.pop();

			if (curParent1 != curParent2) {
				return preParent;
			} else {
				preParent = curParent1;
			}
		}
	}

	// solution 2: node to root path
	public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		ArrayList<TreeNode> list1 = new ArrayList<>();
		ArrayList<TreeNode> list2 = new ArrayList<>();
		nodeToRootPath_(root, p.val, list1);
		nodeToRootPath_(root, q.val, list2);

		int i = list1.size() - 1;
		int j = list2.size() - 1;
		TreeNode prev = null;
		while (i >= 0 && j >= 0) {

			if (list1.get(i).val != list2.get(j).val)
				break;

			prev = list1.get(i);
			i--;
			j--;
		}
		return prev;
	}

	public static boolean nodeToRootPath_(TreeNode root, int data, ArrayList<TreeNode> path) {
		if (root == null)
			return false;
		if (root.val == data) {
			path.add(root);
			return true;
		}

		boolean lp = nodeToRootPath_(root.left, data, path);
		if (lp) {
			path.add(root);
			return true;
		}
		boolean rp = nodeToRootPath_(root.right, data, path);
		if (rp) {
			path.add(root);
			return true;
		}

		return false;
	}

	// solution 3
	public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q)
			return root;
		TreeNode left = lowestCommonAncestor3(root.left, p, q);
		TreeNode right = lowestCommonAncestor3(root.right, p, q);
		if (left != null && right != null)
			return root;
		return left != null ? left : right;
	}

	public static void main(String[] args) {
		TreeNode n7 = new TreeNode(7);
		TreeNode n4 = new TreeNode(4);
		TreeNode n6 = new TreeNode(6);
		TreeNode n2 = new TreeNode(2, n7, n4);
		TreeNode n0 = new TreeNode(0);
		TreeNode n8 = new TreeNode(8);
		TreeNode n5 = new TreeNode(5, n6, n2);
		TreeNode n1 = new TreeNode(1, n0, n8);
		TreeNode n3 = new TreeNode(3, n5, n1);
		System.out.println(lowestCommonAncestor1(n3, n5, n1).val); // 3
		System.out.println(lowestCommonAncestor1(n3, n5, n4).val); // 5
		System.out.println(lowestCommonAncestor1(n3, n5, n0).val); // 3
		System.out.println(lowestCommonAncestor1(n3, n6, n4).val); // 5

		System.out.println(lowestCommonAncestor2(n3, n5, n1).val); // 3
		System.out.println(lowestCommonAncestor2(n3, n5, n4).val); // 5
		System.out.println(lowestCommonAncestor2(n3, n5, n0).val); // 3
		System.out.println(lowestCommonAncestor2(n3, n6, n4).val); // 5

		System.out.println(lowestCommonAncestor3(n3, n5, n1).val); // 3
		System.out.println(lowestCommonAncestor3(n3, n5, n4).val); // 5
		System.out.println(lowestCommonAncestor3(n3, n5, n0).val); // 3
		System.out.println(lowestCommonAncestor3(n3, n6, n4).val); // 5
	}
}
