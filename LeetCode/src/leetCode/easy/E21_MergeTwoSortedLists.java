package leetCode.easy;

public class E21_MergeTwoSortedLists {
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

	// Solution1
	class Solution1 {
		public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
			if (list1 == null) {
				return list2;
			}

			if (list2 == null) {
				return list1;
			}

			ListNode head = null;
			ListNode current = null;
			while (list1 != null && list2 != null) {
				int val1 = list1.val;
				int val2 = list2.val;

				if (val1 <= val2 && head == null) {
					head = new ListNode(val1);
					current = head;
					list1 = list1.next;
					continue;
				} else if (head == null) {
					head = new ListNode(val2);
					current = head;
					list2 = list2.next;
					continue;
				}

				ListNode next = new ListNode();
				if (val1 <= val2) {
					next.val = val1;
					list1 = list1.next;
				} else {
					next.val = val2;
					list2 = list2.next;
				}
				current.next = next;
				current = next;
			}

			if (list1 == null) {
				current.next = list2;
			} else {
				current.next = list1;
			}

			return head;
		}
	}

	// Solution2
	class Solution2 {
		public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
			if (list1 == null) {
				return list2;
			}

			if (list2 == null) {
				return list1;
			}

			ListNode head = new ListNode();
			ListNode current = head;
			while (list1 != null && list2 != null) {
				if (list1.val <= list2.val) {
					current.next = list1;
					list1 = list1.next;
				} else {
					current.next = list2;
					list2 = list2.next;
				}
				current = current.next;
			}

			current.next = list1 == null ? list2 : list1;
			return head.next;
		}
	}

	// Solution3
	class Solution3 {
		public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
			if (list1 == null) {
				return list2;
			}

			if (list2 == null) {
				return list1;
			}

			if (list1.val < list2.val) {
				list1.next = mergeTwoLists(list1.next, list2);
				return list1;
			} else {
				list2.next = mergeTwoLists(list1, list2.next);
				return list2;
			}
		}
	}
}
