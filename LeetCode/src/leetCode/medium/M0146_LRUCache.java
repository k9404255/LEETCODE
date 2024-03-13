package leetCode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M0146_LRUCache {
	// solution 1
	static class LRUCache1 {
		private int capacity;
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
		List<Integer> LRUList = new ArrayList<>();

		public LRUCache1(int capacity) {
			this.capacity = capacity;
		}

		public int get(int key) {
			if (!cache.containsKey(key)) {
				return -1;
			}

			LRUList.remove(Integer.valueOf(key));
			LRUList.add(key);

			return cache.get(key);
		}

		public void put(int key, int value) {
			if (cache.containsKey(key)) {
				LRUList.remove(Integer.valueOf(key));
				LRUList.add(key);
			} else if (cache.size() == this.capacity) {
				int removedKey = LRUList.remove(0);
				LRUList.add(key);
				cache.remove(removedKey);
			} else {
				LRUList.add(key);
			}

			cache.put(key, value);
		}
	}

	// solution 2: double linked list
	static class LRUCache2 {
		private class Node {
			int key;
			int value;
			Node next = null;
			Node prev = null;

			public Node(int key, int value) {
				this.key = key;
				this.value = value;
			}
		}

		private class DoubleLinkedList {
			Node dummyHead = new Node(0, 0);
			Node tail = dummyHead;

			public Node getHead() {
				return dummyHead.next;
			}

			public void add(Node node) {
				tail.next = node;
				node.prev = tail;
				tail = node;
			}

			public void remove(Node node) {
				node.prev.next = node.next;

				if (node.next == null) {
					tail = node.prev;
				} else {
					node.next.prev = node.prev;
				}
				
				node.prev = null;
				node.next = null;
			}
		}

		private int capacity;
		private Map<Integer, Node> cache = new HashMap<Integer, Node>();
		private DoubleLinkedList list = new DoubleLinkedList();

		public LRUCache2(int capacity) {
			this.capacity = capacity;
		}

		public int get(int key) {
			if (cache.containsKey(key)) {
				Node node = cache.get(key);
				list.remove(node);
				list.add(node);

				return node.value;
			} else {
				return -1;
			}
		}

		public void put(int key, int value) {
			if (cache.containsKey(key)) {
				Node node = cache.get(key);
				node.value = value;
				list.remove(node);
				list.add(node);
			} else if (cache.size() == this.capacity) {
				Node head = list.getHead();
				list.remove(head);
				cache.remove(head.key);

				Node node = new Node(key, value);
				cache.put(key, node);
				list.add(node);
			} else {
				Node node = new Node(key, value);
				cache.put(key, node);
				list.add(node);
			}
		}
	}

	public static void main(String[] args) {
//		LRUCache1 lRUCache = new LRUCache1(2);
//		lRUCache.put(1, 1); // cache is {1=1}
//		lRUCache.put(2, 2); // cache is {1=1, 2=2}
//		lRUCache.get(1); // return 1
//		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//		lRUCache.get(2); // returns -1 (not found)
//		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//		lRUCache.get(1); // return -1 (not found)
//		lRUCache.get(3); // return 3
//		lRUCache.get(4); // return 4
//
//		lRUCache = new LRUCache1(2);
//		lRUCache.get(2);
//		lRUCache.put(2, 6);
//		lRUCache.get(1);
//		lRUCache.put(1, 5);
//		lRUCache.put(1, 2);
//		lRUCache.get(1);
//		lRUCache.get(2);

		LRUCache2 lRUCache = new LRUCache2(2);
		lRUCache.put(1, 1); // cache is {1=1}
		lRUCache.put(2, 2); // cache is {1=1, 2=2}
		lRUCache.get(1); // return 1
		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		lRUCache.get(2); // returns -1 (not found)
		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		lRUCache.get(1); // return -1 (not found)
		lRUCache.get(3); // return 3
		lRUCache.get(4); // return 4

		lRUCache = new LRUCache2(2);
		lRUCache.get(2);
		lRUCache.put(2, 6);
		lRUCache.get(1);
		lRUCache.put(1, 5);
		lRUCache.put(1, 2);
		lRUCache.get(1);
		lRUCache.get(2);
	}
}
