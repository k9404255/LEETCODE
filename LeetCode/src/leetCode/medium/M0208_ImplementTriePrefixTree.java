package leetCode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class M0208_ImplementTriePrefixTree {
	// solution 1: list
	static class Trie1 {
		List<String> list;

		public Trie1() {
			list = new ArrayList<>();
		}

		public void insert(String word) {
			list.add(word);
		}

		public boolean search(String word) {
			return list.contains(word);
		}

		public boolean startsWith(String prefix) {
			for (String string : list) {
				if (string.startsWith(prefix)) {
					return true;
				}
			}

			return false;
		}
	}

	// solution 2: TreeSet結構：紅黑樹
	// https://codegym.cc/tw/groups/posts/tw.1111.java-
	static class Trie2 {
		TreeSet<String> tree = new TreeSet<>();

		public Trie2() {
			tree = new TreeSet<>();
		}

		public void insert(String word) {
			tree.add(word);
		}

		public boolean search(String word) {
			return tree.contains(word);
		}

		public boolean startsWith(String prefix) {
			for (String string : tree) {
				if (string.startsWith(prefix)) {
					return true;
				}
			}

			return false;
		}

		public void print() {
			System.out.println(tree);
		}
	}

	// solution 3: 字典樹
	// https://englishandcoding.pixnet.net/blog/post/29962012-leetcode-%E7%AD%86%E8%A8%98%EF%BC%8D208.-imp
	class Trie3 {
		Node root;

		public Trie3() {
			root = new Node();
		}

		public void insert(String word) {
			root.insert(word, 0);
		}

		public boolean search(String word) {
			return root.search(word, 0);
		}

		public boolean startsWith(String prefix) {
			return root.startsWith(prefix, 0);
		}

		class Node {
			Node[] nodes;
			boolean isEnd;

			Node() {
				nodes = new Node[26];
			}

			private void insert(String word, int idx) {
				if (idx >= word.length())
					return;
				int i = word.charAt(idx) - 'a';
				if (nodes[i] == null) {
					nodes[i] = new Node();
				}

				if (idx == word.length() - 1)
					nodes[i].isEnd = true;
				nodes[i].insert(word, idx + 1);
			}

			private boolean search(String word, int idx) {
				if (idx >= word.length())
					return false;
				Node node = nodes[word.charAt(idx) - 'a'];
				if (node == null)
					return false;
				if (idx == word.length() - 1 && node.isEnd)
					return true;

				return node.search(word, idx + 1);

			}

			private boolean startsWith(String prefix, int idx) {
				if (idx >= prefix.length())
					return false;
				Node node = nodes[prefix.charAt(idx) - 'a'];
				if (node == null)
					return false;
				if (idx == prefix.length() - 1)
					return true;

				return node.startsWith(prefix, idx + 1);
			}
		}
	}

	public static void main(String[] args) {
		Trie2 tree = new Trie2();
		tree.insert("a");
		tree.insert("c");
		tree.insert("b");
		tree.insert("aa");
		tree.insert("aab");
		tree.print();
	}
}
