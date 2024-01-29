package leetCode.medium;

public class M0002_AddTwoNumbers {
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

	public static void printList(ListNode l) {
		while (l != null) {
			System.out.print(l.val + " ");
			l = l.next;
		}
		System.out.println();
	}

	// solution 1
	public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
		ListNode head = new ListNode();
		ListNode tail = head;
		int carry = 0;

		while (l1 != null || l2 != null || carry > 0) {
			ListNode newNode = new ListNode();

			if (l1 != null && l2 != null) {
				int sum = l1.val + l2.val + carry;
				newNode.val = sum % 10;
				carry = sum / 10;

				l1 = l1.next;
				l2 = l2.next;
			} else if (l1 != null) {
				int sum = l1.val + carry;
				newNode.val = sum % 10;
				carry = sum / 10;
				l1 = l1.next;
			} else if (l2 != null) {
				int sum = l2.val + carry;
				newNode.val = sum % 10;
				carry = sum / 10;
				l2 = l2.next;
			} else if (carry > 0) {
				newNode.val = carry;
				carry = 0;
			}

			tail.next = newNode;
			tail = newNode;
		}

		return head.next;
	}

	// solution 2: clean version of solution 1
	public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode head = new ListNode();
		ListNode tail = head;
		int carry = 0;

		while (l1 != null || l2 != null || carry > 0) {
			int digits1 = (l1 != null) ? l1.val : 0;
			int digits2 = (l2 != null) ? l2.val : 0;

			int sum = digits1 + digits2 + carry;
			int digit = sum % 10;
			carry = sum / 10;

			ListNode newNode = new ListNode(digit);
			tail.next = newNode;
			tail = tail.next;

			l1 = (l1 != null) ? l1.next : null;
			l2 = (l2 != null) ? l2.next : null;
		}

		return head.next;
	}

	public static void main(String[] args) {
		ListNode n13 = new ListNode(3, null);
		ListNode n12 = new ListNode(4, n13);
		ListNode n11 = new ListNode(2, n12);
		ListNode n23 = new ListNode(4, null);
		ListNode n22 = new ListNode(6, n23);
		ListNode n21 = new ListNode(5, n22);
		printList(addTwoNumbers1(n11, n21));

		// ListNode n17 = new ListNode(9, null);
		// ListNode n16 = new ListNode(9, n17);
		// ListNode n15 = new ListNode(9, n16);
		// ListNode n14 = new ListNode(9, n15);
		// ListNode n13 = new ListNode(9, n14);
		// ListNode n12 = new ListNode(9, n13);
		// ListNode n11 = new ListNode(9, n12);
		// ListNode n24 = new ListNode(9, null);
		// ListNode n23 = new ListNode(9, n24);
		// ListNode n22 = new ListNode(9, n23);
		// ListNode n21 = new ListNode(9, n22);
		// printList(addTwoNumbers1(n11, n21));
	}
}