package leetCode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class M0116_PopulatingNextRightPointersinEachNode {
	public static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}

		@Override
		public String toString() {
			return String.valueOf(this.val);
		}
	};

	// solution 1: bfs
	public static Node connect1(Node root) {
		if (root == null) {
			return null;
		}

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				Node node = queue.poll();

				if (i != n - 1) {
					node.next = queue.peek();
				}

				if (node.left != null) {
					queue.add(node.left);
				}

				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}

		return root;
	}

	// solution 2
	// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/solutions/962728/java-0ms-with-visual-explanation/
	public static Node connect2(Node root) {
		if (root == null) {
			return null;
		}

		if (root.left != null) {
			root.left.next = root.right;
		}

		if (root.right != null && root.next != null) {
			root.right.next = root.next.left;
		}

		connect2(root.left);
		connect2(root.right);

		return root;
	}

	public static void main(String[] args) {
		Node n4 = new Node(4, null, null, null);
		Node n5 = new Node(5, null, null, null);
		Node n6 = new Node(6, null, null, null);
		Node n7 = new Node(7, null, null, null);
		Node n2 = new Node(2, n4, n5, null);
		Node n3 = new Node(3, n6, n7, null);
		Node n1 = new Node(1, n2, n3, null);
		connect1(n1);
	}
}
