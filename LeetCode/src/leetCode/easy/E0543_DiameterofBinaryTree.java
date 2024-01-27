package leetCode.easy;

public class E0543_DiameterofBinaryTree {
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
	public int maxDepth1(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int lDepth = maxDepth1(root.left);
		int rDepth = maxDepth1(root.right);

		return Math.max(lDepth, rDepth) + 1;
	}

	public int diameterOfBinaryTree1(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftDepth = maxDepth1(root.left);
		int maxLeftPath = diameterOfBinaryTree1(root.left);

		int rigtDepth = maxDepth1(root.right);
		int maxRightPath = diameterOfBinaryTree1(root.right);

		return Math.max(leftDepth + rigtDepth, Math.max(maxLeftPath, maxRightPath));
	}

	// solution 2
	int ans = 0;

	public int maxDepth2(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int lDepth = maxDepth2(root.left);
		int rDepth = maxDepth2(root.right);

		ans = Math.max(ans, lDepth + rDepth);

		return Math.max(lDepth, rDepth) + 1;
	}

	public int diameterOfBinaryTree2(TreeNode root) {
		if (root == null) {
			return 0;
		}

		maxDepth2(root);

		return ans;
	}
}