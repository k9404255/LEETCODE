package leetCode.medium;

import java.util.Stack;

public class M0098_ValidateBinarySearchTree {
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
	public static boolean isValidBST1(TreeNode root) {
		if (root == null) {
			return true;
		} else if ((root.left != null && root.left.val >= root.val)
				|| (root.right != null && root.right.val <= root.val)) {
			return false;
		}

		int val = root.val;
		return isValidBST1(root.left, val, Long.MIN_VALUE) && isValidBST1(root.right, Long.MAX_VALUE, val);
	}

	public static boolean isValidBST1(TreeNode root, long min, long max) {
		if (root == null) {
			return true;
		}

		TreeNode left = root.left;
		TreeNode right = root.right;
		if ((left == null || (left.val < root.val && left.val > max))
				&& (right == null || (right.val > root.val && right.val < min))) {

			return isValidBST1(root.left, root.val, max) && isValidBST1(root.right, min, root.val);
		}

		return false;
	}

	// solution 2
	private static long minVal = Long.MIN_VALUE;

	public static boolean isValidBST2(TreeNode root) {
		if (root == null)
			return true;
		if (!isValidBST2(root.left))
			return false;

		System.out.println("min: " + minVal + ", root: " + root.val);
		if (minVal >= root.val)
			return false;

		minVal = root.val;

		if (!isValidBST2(root.right))
			return false;

		return true;
	}

	// solution 3
	public static boolean isValidBST3(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode prevNode = null;
		TreeNode curNode = root;

		while (curNode != null || !stack.empty()) {
			if (curNode != null) {
				stack.push(curNode);
				curNode = curNode.left;
			} else {
				curNode = stack.pop();

				if (prevNode != null && curNode.val <= prevNode.val) {
					return false;
				}

				prevNode = curNode;
				curNode = curNode.right;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		TreeNode n1_1 = new TreeNode(1);
		TreeNode n1_3 = new TreeNode(3);
		TreeNode n1_2 = new TreeNode(2, n1_1, n1_3);

		System.out.println(isValidBST3(n1_2));

		TreeNode n2_1 = new TreeNode(3);
		TreeNode n2_2 = new TreeNode(6);
		TreeNode n2_3 = new TreeNode(4, n2_1, n2_2);
		TreeNode n2_4 = new TreeNode(1);
		TreeNode n2_5 = new TreeNode(5, n2_4, n2_3);

		System.out.println(isValidBST3(n2_5));

		TreeNode n3_1 = new TreeNode(3);
		TreeNode n3_2 = new TreeNode(7);
		TreeNode n3_3 = new TreeNode(6, n3_1, n3_2);
		TreeNode n3_4 = new TreeNode(4);
		TreeNode n3_5 = new TreeNode(5, n3_4, n3_3);

		System.out.println(isValidBST1(n3_5));

		TreeNode n4_1 = new TreeNode(0);
		TreeNode n4_2 = new TreeNode(2);
		TreeNode n4_3 = new TreeNode(4);
		TreeNode n4_4 = new TreeNode(6);
		TreeNode n4_5 = new TreeNode(1, n4_1, n4_2);
		TreeNode n4_6 = new TreeNode(5, n4_3, n4_4);
		TreeNode n4_7 = new TreeNode(3, n4_5, n4_6);

		System.out.println(isValidBST1(n4_7));

		TreeNode n5_1 = new TreeNode(31);
		TreeNode n5_2 = new TreeNode(58, n5_1, null);
		TreeNode n5_3 = new TreeNode(-57, null, n5_2);
		TreeNode n5_4 = new TreeNode(98, n5_3, null);

		System.out.println(isValidBST1(n5_4));

		TreeNode n6_1 = new TreeNode(2147483645);
		TreeNode n6_2 = new TreeNode(2147483647);
		TreeNode n6_3 = new TreeNode(2147483646, n6_1, n6_2);
		TreeNode n6_4 = new TreeNode(-2147483648);
		TreeNode n6_5 = new TreeNode(2147483644, n6_4, n6_3);

		System.out.println(isValidBST1(n6_5));
	}

}
