package leetCode.easy;

import java.util.ArrayList;
import java.util.List;

public class E234_PalindromeLinkedList {
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

	// solution 1: String, space complexity: O(n)
	public boolean isPalindrome1(ListNode head) {
		StringBuilder sb = new StringBuilder();
		while (head != null) {
			sb.append(String.valueOf(head.val));
			head = head.next;
		}
		return sb.toString().equals(sb.reverse().toString());
	}

	// solution 2: num array, space complexity: O(n)
	public boolean isPalindrome2(ListNode head) {
		List<Integer> list = new ArrayList<Integer>();
		while (head != null) {
			list.add(head.val);
			head = head.next;
		}

		int n = list.size();
		for (int i = 0; i < n / 2; i++) {
			if (list.get(i) != list.get(n - i - 1)) {
				return false;
			}
		}

		return true;
	}

	public ListNode reverse(ListNode node) {
		ListNode prev = null;
		ListNode current = node;

		while (current != null) {
			ListNode nextNode = current.next;
			current.next = prev;
			prev = current;
			current = nextNode;
		}

		return prev;
	}

	// solution 3: space complexity: O(1)
	public boolean isPalindrome3(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode secondHalfStart = reverse(slow);
		slow.next = null;

		ListNode p1 = head;
		ListNode p2 = secondHalfStart;

		while (p1 != null && p2 != null) {
			if (p1.val != p2.val) {
				return false;
			}
			p1 = p1.next;
			p2 = p2.next;
		}

		return true;
	}

	// solution 4: space complexity: O(1)
	public boolean isPalindrome4(ListNode head) {
		// Edge-case :
		if (head == null && head.next == null) {
			return true;
		}

		// Step 1 --> find mid of list (i.e slow)
		ListNode slow = head;
		ListNode fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		// Step 2 --> reverse list from mid
		ListNode prev = null;
		ListNode current = slow;

		while (current != null) {
			ListNode nextNode = current.next;
			current.next = prev;
			prev = current;
			current = nextNode;
		}

		// put mid.next = null to divide list into two part (here, slow = mid)
		slow.next = null;

		// Step 3 --> Iterate over both the lists and check if the value of nodes are
		// equal or not if not equal then it is not palindrome list
		ListNode p1 = head;
		ListNode p2 = prev;

		while (p1 != null && p2 != null) {
			if (p1.val != p2.val) {
				return false;
			}
			p1 = p1.next;
			p2 = p2.next;
		}

		return true;
	}
}