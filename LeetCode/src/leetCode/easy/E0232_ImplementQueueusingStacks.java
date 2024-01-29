package leetCode.easy;

import java.util.Stack;

public class E0232_ImplementQueueusingStacks {
	class MyQueue {

		private Stack<Integer> stack1;
		private Stack<Integer> stack2;

		public MyQueue() {
			stack1 = new Stack<Integer>();
			stack2 = new Stack<Integer>();
		}

		public void push(int x) {
			while (!stack1.empty()) {
				stack2.push(stack1.pop());
			}

			stack2.push(x);

			while (!stack2.empty()) {
				stack1.push(stack2.pop());
			}
		}

		public int pop() {
			return stack1.pop();
		}

		public int peek() {
			return stack1.peek();
		}

		public boolean empty() {
			return stack1.empty();
		}
	}
}