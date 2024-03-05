package leetCode.medium;

import java.util.HashMap;
import java.util.Map;

public class M0138_CopyListwithRandomPointer {
	static void printHead(Node head) {
		while (head != null) {
			String randomVal = head.random == null ? "null" : String.valueOf(head.random.val);
			System.out.println("val: " + head.val + ", random: " + randomVal);
			head = head.next;
		}
	}

	static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	// solution 1: my way
	public static Node copyRandomList1(Node head) {
		if (head == null) {
			return null;
		}

		Map<Node, Node> map = new HashMap<>();

		Node newHead = new Node(0);
		Node cur = newHead;
		Node tmp = head;
		while (tmp != null) {
			// get next node
			Node nextNode = null;
			if (map.containsKey(tmp)) {
				nextNode = map.get(tmp);
			} else if (!map.containsKey(tmp)) {
				nextNode = new Node(tmp.val);
				map.put(tmp, nextNode);
			}
			cur.next = nextNode;

			// get random node
			Node randomNode = null;
			if (tmp.random != null && !map.containsKey(tmp.random)) {
				randomNode = new Node(tmp.random.val);
				map.put(tmp.random, randomNode);
			} else if (tmp.random != null && map.containsKey(tmp.random)) {
				randomNode = map.get(tmp.random);
			} else if (tmp.random == null) {
				randomNode = null;
			}

			nextNode.random = randomNode;

			cur = cur.next;
			tmp = tmp.next;
		}

		return newHead.next;
	}

	// solution 2: hashTable
	public static Node copyRandomList2(Node head) {
		if (head == null) {
			return null;
		}

		Map<Node, Node> oldToNew = new HashMap<>();

		Node tmp = head;
		while (tmp != null) {
			oldToNew.put(tmp, new Node(tmp.val));
			tmp = tmp.next;
		}

		tmp = head;
		while (tmp != null) {
			oldToNew.get(tmp).next = oldToNew.get(tmp.next);
			oldToNew.get(tmp).random = oldToNew.get(tmp.random);
			tmp = tmp.next;
		}

		return oldToNew.get(head);
	}

	public static void main(String[] args) {
		Node n1_1 = new Node(1);
		Node n1_2 = new Node(2);
		Node n1_3 = new Node(3);
		n1_1.next = n1_2;
		n1_2.next = n1_3;
		n1_1.random = n1_3;
		n1_3.random = n1_2;
		n1_2.random = n1_3;
		printHead(copyRandomList1(n1_1));

		System.out.println("----------------");
		Node n2_1 = new Node(1);
		Node n2_2 = new Node(2);
		n2_1.next = n2_2;
		n2_1.random = n2_2;
		n2_2.random = n2_2;
		printHead(copyRandomList1(n2_1));

		System.out.println("----------------");
		Node n3_1 = new Node(7);
		Node n3_2 = new Node(13);
		Node n3_3 = new Node(11);
		Node n3_4 = new Node(10);
		Node n3_5 = new Node(1);
		n3_1.next = n3_2;
		n3_2.next = n3_3;
		n3_3.next = n3_4;
		n3_4.next = n3_5;
		n3_2.random = n3_1;
		n3_3.random = n3_5;
		n3_4.random = n3_3;
		n3_5.random = n3_1;
		printHead(copyRandomList1(n3_1));
	}
}
