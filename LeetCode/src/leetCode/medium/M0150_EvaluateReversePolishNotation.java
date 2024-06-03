package leetCode.medium;

import java.util.Stack;

public class M0150_EvaluateReversePolishNotation {
	// solution 1
	public static int evalRPN1(String[] tokens) {
		Stack<Integer> stack = new Stack<>();

		for (String str : tokens) {
			if (str.equals("+")) {
				int num1 = stack.pop();
				int num2 = stack.pop();
				stack.add(num2 + num1);
			} else if (str.equals("-")) {
				int num1 = stack.pop();
				int num2 = stack.pop();
				stack.add(num2 - num1);
			} else if (str.equals("*")) {
				int num1 = stack.pop();
				int num2 = stack.pop();
				stack.add(num2 * num1);
			} else if (str.equals("/")) {
				int num1 = stack.pop();
				int num2 = stack.pop();
				stack.add(num2 / num1);
			} else {
				stack.add(Integer.parseInt(str));
			}
		}

		return stack.peek();
	}

	public static void main(String[] args) {
		System.out.println(evalRPN1(new String[] { "2", "1", "+", "3", "*" }));
		System.out.println(evalRPN1(new String[] { "4", "13", "5", "/", "+" }));
		System.out.println(
				evalRPN1(new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" }));
	}
}
