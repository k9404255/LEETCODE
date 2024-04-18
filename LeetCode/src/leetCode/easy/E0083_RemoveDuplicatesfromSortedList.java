package leetCode.easy;

public class E0083_RemoveDuplicatesfromSortedList {
	private static void printNode(ListNode head) {
		while (head != null) {
			System.out.print(head.val + " ");
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

	// solution 1
	public static ListNode deleteDuplicates1(ListNode head) {
		ListNode prev = null;
		ListNode cur = head;
		while (cur != null) {
			if (cur != null && prev != null && cur.val == prev.val) {
				prev.next = cur.next;
				cur = cur.next;
			} else {
				prev = cur;
			}
		}

		return head;
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(3);
		ListNode n5 = new ListNode(3);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;

		printNode(deleteDuplicates1(n1));

		n1 = new ListNode(1);
		n2 = new ListNode(1);
		n3 = new ListNode(2);
		n1.next = n2;
		n2.next = n3;

		printNode(deleteDuplicates1(n1));

		n1 = new ListNode(1);
		n2 = new ListNode(1);
		n3 = new ListNode(1);
		n1.next = n2;
		n2.next = n3;

		printNode(deleteDuplicates1(n1));
	}
}
