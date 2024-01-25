package leetCode.easy;

import java.util.HashSet;
import java.util.Set;

public class E0141_LinkedListCycle {
	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	// solution 1: two pointers
	public boolean hasCycle1(ListNode head) {
		ListNode walker = head;
		ListNode runner = head;
		while (runner != null && runner.next != null) {
			walker = walker.next;
			runner = runner.next.next;

			if (walker == runner) {
				return true;
			}
		}

		return false;
	}

	// solution 2: hash set
	public boolean hasCycle2(ListNode head) {
		Set<ListNode> set = new HashSet<>();

		ListNode curNode = head;
		while (curNode != null) {
			curNode = curNode.next;
			if (set.contains(curNode)) {
				return true;
			} else {
				set.add(curNode);
			}
		}

		return false;
	}

	public static void main(String[] args) {
	}
}