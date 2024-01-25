package leetCode.easy;

import java.util.HashSet;
import java.util.Set;

public class E160_IntersectionofTwoLinkedLists {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	// solution 1: hash set
	public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
		Set<ListNode> set = new HashSet<ListNode>();
		while (headA != null) {
			set.add(headA);
			headA = headA.next;
		}

		while (headB != null) {
			if (set.contains(headB)) {
				return headB;
			}

			headB = headB.next;
		}

		return null;
	}

	// solution 2: faster
	public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
		ListNode a = headA;
		ListNode b = headB;
		int lenA = 0;
		int lenB = 0;

		while (a != null) {
			lenA++;
			a = a.next;
		}

		while (b != null) {
			lenB++;
			b = b.next;
		}

		while (lenA > lenB) {
			lenA--;
			headA = headA.next;
		}

		while (lenB > lenA) {
			lenB--;
			headB = headB.next;
		}

		while (headA != headB) {
			headA = headA.next;
			headB = headB.next;
		}

		return headA;
	}
}