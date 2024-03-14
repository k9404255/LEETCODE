package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0148_SortList {
	public static void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.val + ", ");
			head = head.next;
		}
		System.out.println();
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	// solution 1: Time Limit Exceeded
	public static ListNode sortList1(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode newHead = new ListNode(head.val);

		head = head.next;
		while (head != null) {
			ListNode cur = newHead;
			ListNode prev = null;
			while (cur != null && head.val > cur.val) {
				prev = cur;
				cur = cur.next;
			}

			ListNode newNode = new ListNode(head.val);
			newNode.next = cur;
			if (prev == null) {
				newHead = newNode;
			} else {
				prev.next = newNode;
			}

			head = head.next;
		}

		return newHead;
	}

	// solution 2
	public static ListNode sortList2(ListNode head) {
		if (head == null) {
			return null;
		}

		List<Integer> list = new ArrayList<>();
		while (head != null) {
			list.add(head.val);
			head = head.next;
		}

		list.sort(null);

		ListNode newHead = new ListNode(list.remove(0));
		ListNode cur = newHead;
		while (!list.isEmpty()) {
			cur.next = new ListNode(list.remove(0));
			cur = cur.next;
		}

		return newHead;
	}

	// solution 3: merge sort
	public static ListNode sortList3(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode slow = head;
		ListNode fast = head;
		ListNode prev = null;

		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		prev.next = null;

		ListNode left = sortList3(head);
		ListNode right = sortList3(slow);

		return merge(left, right);
	}

	public static ListNode merge(ListNode left, ListNode right) {
		ListNode dummyHead = new ListNode(0);

		ListNode cur = dummyHead;
		while (left != null && right != null) {
			if (left.val < right.val) {
				cur.next = left;
				left = left.next;
			} else {
				cur.next = right;
				right = right.next;
			}
			cur = cur.next;
		}

		if (left != null) {
			cur.next = left;
		}

		if (right != null) {
			cur.next = right;
		}

		return dummyHead.next;
	}

	public static void main(String[] args) {
		ListNode n1_1 = new ListNode(3);
		ListNode n1_2 = new ListNode(1, n1_1);
		ListNode n1_3 = new ListNode(2, n1_2);
		ListNode n1_4 = new ListNode(4, n1_3);
//		printList(sortList1(n1_4));
//		printList(sortList2(n1_4));
		printList(sortList3(n1_4));

		ListNode n2_1 = new ListNode(0);
		ListNode n2_2 = new ListNode(4, n2_1);
		ListNode n2_3 = new ListNode(3, n2_2);
		ListNode n2_4 = new ListNode(5, n2_3);
		ListNode n2_5 = new ListNode(-1, n2_4);
//		printList(sortList1(n2_5));
//		printList(sortList2(n2_5));
		printList(sortList3(n2_5));

		printList(sortList1(null));
		printList(sortList2(null));
		printList(sortList3(null));
	}
}
