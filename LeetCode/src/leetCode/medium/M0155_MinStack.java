package leetCode.medium;

import java.util.Stack;

public class M0155_MinStack {

	static class MinStack1 {
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> minStack = new Stack<>();

		public MinStack1() {

		}

		public void push(int val) {
			stack.push(val);
			if (stack.size() == 1) {
				minStack.push(val);
			} else {
				minStack.push(Math.min(minStack.peek(), val));
			}
		}

		public void pop() {
			stack.pop();
			minStack.pop();
		}

		public int top() {
			return stack.peek();
		}

		public int getMin() {
			return minStack.peek();
		}
	}

	static class MinStack2 {
		Stack<Integer> stack;

		// 使用一個min來紀錄stack中最小的值，注意這邊的min不能用Integer
		// Integer == Integer 的比對方法只保證再-128~127之間會有正確的結果
		int min = Integer.MAX_VALUE;

		/** initialize your data structure here. */
		public MinStack2() {
			this.stack = new Stack<>();
		}

		public void push(int x) {
			// 如果x <= min，先將目前的min放入stack後面，再放入x
			if (x <= min) {
				this.stack.push(min);
				this.min = x;
			}
			this.stack.push(x);
			System.out.println(stack);
		}

		public void pop() {
			// 如果取出的值剛好就是min，因為之前我們把第二小的數放在min前面，所以現在他又變成min了
			if (stack.peek() == this.min) {
				// 因為之前x<=min的時候也壓了第二小的數進來，所以他也要被pop
				this.stack.pop();
				min = this.stack.pop();
			} else {
				this.stack.pop();
			}

		}

		public int top() {
			return this.stack.peek();
		}

		public int getMin() {
			return min;
		}
	}

	public static void main(String[] args) {
		MinStack2 obj = new MinStack2();
		obj.push(-2);
		obj.push(0);
		obj.push(-3);
		System.out.println(obj.getMin());
		obj.pop();
		obj.top();
		System.out.println(obj.getMin());
	}
}
