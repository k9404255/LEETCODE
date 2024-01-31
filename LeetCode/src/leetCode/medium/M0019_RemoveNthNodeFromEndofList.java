package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0019_RemoveNthNodeFromEndofList {
	public class ListNode {
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

	public ListNode removeNthFromEnd1(ListNode head, int n) {
		List<ListNode> list = new ArrayList<ListNode>();

		ListNode tmp = head;
		while (tmp != null) {
			list.add(tmp);
			tmp = tmp.next;
		}

		int idx = list.size() - n - 1;
		if (idx >= 0) {
			list.get(idx).next = list.get(idx).next.next;
		} else {
			head = head.next;
		}

		return head;
	}

	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode fast = head;
		ListNode slow = head;

		for (int i = 0; i < n; i++) {
			fast = fast.next;
		}

		if (fast == null) {
			return head.next;
		}

		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}

		slow.next = slow.next.next;

		return head;
	}

}
