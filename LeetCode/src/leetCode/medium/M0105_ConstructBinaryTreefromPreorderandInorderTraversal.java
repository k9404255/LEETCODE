package leetCode.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.wang/leetcode-105-Construct-Binary-Tree-from-Preorder-and-Inorder-Traversal.html
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

	// solution 1
	public static TreeNode buildTree1(int[] preorder, int[] inorder) {
		return buildTreeHelper1(preorder, 0, preorder.length, inorder, 0, inorder.length);
	}

	public static TreeNode buildTreeHelper1(int[] preorder, int p_start, int p_end, int[] inorder, int i_start,
			int i_end) {
		if (p_start >= p_end) {
			return null;
		}

		int rootVal = preorder[p_start];

		int i_root_idx = -1;
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == rootVal) {
				i_root_idx = i;
				break;
			}
		}

		TreeNode root = new TreeNode(rootVal);

		int leftNum = i_root_idx - i_start;
		root.left = buildTreeHelper1(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_idx);
		root.right = buildTreeHelper1(preorder, p_start + leftNum + 1, p_end, inorder, i_root_idx + 1, i_end);

		return root;
	}

	// solution 2: map加速找root index
	public static TreeNode buildTree2(int[] preorder, int[] inorder) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		return buildTreeHelper2(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
	}

	public static TreeNode buildTreeHelper2(int[] preorder, int p_start, int p_end, int[] inorder, int i_start,
			int i_end, Map<Integer, Integer> map) {
		if (p_start >= p_end) {
			return null;
		}

		int rootVal = preorder[p_start];
		int i_root_idx = map.get(rootVal);

		TreeNode root = new TreeNode(rootVal);

		int leftNum = i_root_idx - i_start;
		root.left = buildTreeHelper2(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_idx, map);
		root.right = buildTreeHelper2(preorder, p_start + leftNum + 1, p_end, inorder, i_root_idx + 1, i_end, map);

		return root;
	}

	public static void main(String[] args) {
		buildTree1(new int[] { 3, 9, 20, 15, 7 }, new int[] { 9, 3, 15, 20, 7 });
		buildTree1(new int[] { -1 }, new int[] { -1 });
	}
}
