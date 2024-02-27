package leetCode.medium;

public class M0105_ConstructBinaryTreefromPreorderandInorderTraversal {
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

	public static TreeNode buildTree(int[] preorder, int[] inorder) {

		return null;
	}

	public static void main(String[] args) {
		buildTree(new int[] { 3, 9, 20, 15, 7 }, new int[] { 9, 3, 15, 20, 7 });
		buildTree(new int[] { -1 }, new int[] { -1 });
	}
}
