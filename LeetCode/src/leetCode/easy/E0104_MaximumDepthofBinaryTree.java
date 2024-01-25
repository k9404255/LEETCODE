package leetCode.easy;

public class E0104_MaximumDepthofBinaryTree {
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

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int lDepth = maxDepth(root.left);
		int rDepth = maxDepth(root.right);

		return Math.max(lDepth, rDepth) + 1;
	}

	public static void main(String[] args) {
		TreeNode n4 = new TreeNode(15, null, null);
		TreeNode n5 = new TreeNode(7, null, null);
		TreeNode n3 = new TreeNode(20, n4, n5);
		TreeNode n2 = new TreeNode(9, null, null);
		TreeNode n1 = new TreeNode(3, n2, n3);
		System.out.println(maxDepth(n1));
	}

}
