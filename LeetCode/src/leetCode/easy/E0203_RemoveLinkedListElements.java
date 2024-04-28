package leetCode.easy;

public class E0203_RemoveLinkedListElements {
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
	
	// solution 1
	public ListNode removeElements1(ListNode head, int val) {
		ListNode cur = head;
		ListNode prev = new ListNode(0);
		prev.next = cur;
		ListNode dummy = prev;
		while (cur != null) {
			if (cur.val == val) {
				prev.next = cur.next;
			} else {
				prev = cur;
			}

			cur = cur.next;
		}

		return dummy.next;
	}

}
