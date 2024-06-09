package leetCode.medium;

public class M0328_OddEvenLinkedList {
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

	public static void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}

	// solution 1
	public static ListNode oddEvenList1(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}

		ListNode cur = head;
		ListNode evenHead = head.next;
		ListNode tmp = null;
		int i = 0;
		while (cur.next.next != null) {
			tmp = cur.next;
			cur.next = cur.next.next;
			cur = tmp;
			i++;
		}

		if (i % 2 == 0) {
			ListNode oddTail = cur;
			oddTail.next = evenHead;
		} else if (i % 2 == 1) {
			ListNode evenTail = cur;
			ListNode oddTail = cur.next;
			evenTail.next = null;
			oddTail.next = evenHead;
		}

		return head;
	}

	// solution 2
	public static ListNode oddEvenList2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode odd = head;
		ListNode even = head.next;
		ListNode evenHead = head.next;

		while (even != null && even.next != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}

		odd.next = evenHead;

		return head;
	}

	public static void main(String[] args) {
//		ListNode n5 = new ListNode(5);
		ListNode n4 = new ListNode(4);
		ListNode n3 = new ListNode(3, n4);
		ListNode n2 = new ListNode(2, n3);
		ListNode n1 = new ListNode(1, n2);
		printList(oddEvenList2(n1));
	}
}
