package leetCode.easy;

public class ReverseLinkedList_206 {
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

	// solution 1: iteratively
	public ListNode reverseList1(ListNode head) {
		ListNode curNode = head;
		ListNode prevNode = null;
		ListNode newHead = null;
		while (curNode != null) {
			newHead = new ListNode(curNode.val, prevNode);
			prevNode = newHead;
			curNode = curNode.next;
		}

		return newHead;
	}

	// solution 2: iteratively (in place)
	public ListNode reverseList2(ListNode head) {
		ListNode cur = head;
		ListNode prev = null;
		while (cur != null) {
			ListNode tmp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = tmp;
		}

		return prev;
	}

	// solution 3: recursively
	public ListNode reverseList3(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode newNode = reverseList3(head.next);
		head.next.next = head;
		head.next = null;

		return newNode;
	}

	public static void main(String[] args) {

	}
}