package leetCode.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

public class M0215_KthLargestElementinanArray {
	// solution 1: sorting
	public static int findKthLargest1(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}

	// solution 2: min heap
	public static int findKthLargest2(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			minHeap.offer(nums[i]);
		}

		for (int i = k; i < nums.length; i++) {
			if (nums[i] > minHeap.peek()) {
				minHeap.poll();
				minHeap.offer(nums[i]);
			}
		}

		return minHeap.peek();
	}

	// solution 3: quick select
	public static int findKthLargest3(int[] nums, int k) {
		int left = 0;
		int right = nums.length - 1;

		while (true) {
			int pivot_index = (left + right) / 2;
			int new_pivot_index = partition(nums, left, right, pivot_index);
			if (new_pivot_index == nums.length - k) {
				return nums[new_pivot_index];
			} else if (new_pivot_index > nums.length - k) {
				right = new_pivot_index - 1;
			} else {
				left = new_pivot_index + 1;
			}
		}
	}

	public static int partition(int[] nums, int left, int right, int pivot_idx) {
		int pivot = nums[pivot_idx];
		swap(nums, pivot_idx, right);
		int stored_index = left;
		for (int i = left; i < right; i++) {
			if (nums[i] < pivot) {
				swap(nums, i, stored_index);
				stored_index++;
			}
		}

		swap(nums, right, stored_index);

		return stored_index;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		System.out.println(findKthLargest1(new int[] { 3, 2, 1, 5, 6, 4 }, 2));
		System.out.println(findKthLargest1(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4));

		System.out.println(findKthLargest2(new int[] { 3, 2, 1, 5, 6, 4 }, 2));
		System.out.println(findKthLargest2(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4));
	}
}
