package leetCode.medium;

import java.util.HashSet;
import java.util.Set;

public class M0142_LinkedListCycleII {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	// solution 1: hashset
	public ListNode detectCycle1(ListNode head) {
		Set<ListNode> set = new HashSet<>();

		while (head != null) {
			if (set.contains(head)) {
				return head;
			}

			set.add(head);
			head = head.next;
		}

		return null;
	}

	// solution 2: two pointers (https://ithelp.ithome.com.tw/articles/10223721)
	public ListNode detectCycle2(ListNode head) {
		ListNode walker = head;
		ListNode runner = head;

		while (runner != null && runner.next != null) {
			walker = walker.next;
			runner = runner.next.next;

			// 偵測到loop，尋找head
			if (runner == walker) {
				walker = head;

				while (walker != runner) {
					walker = walker.next;
					runner = runner.next;
				}

				return walker;
			}
		}

		return null;
	}

	public static void main(String[] args) {

	}
}
