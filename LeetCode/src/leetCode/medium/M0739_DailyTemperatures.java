package leetCode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class M0739_DailyTemperatures {
	// solution 1: brute-force
	public int[] dailyTemperatures1(int[] temperatures) {
		int[] answer = new int[temperatures.length];

		for (int i = 0; i < temperatures.length; i++) {
			answer[i] = 0;
			for (int j = i + 1; j < temperatures.length; j++) {
				answer[i]++;
				if (temperatures[i] < temperatures[j]) {
					break;
				} else if (j == temperatures.length - 1) {
					answer[i] = 0;
				}
			}
		}

		return answer;
	}

	// solution 2: list
	public int[] dailyTemperatures2(int[] temperatures) {
		int[] answer = new int[temperatures.length];
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < temperatures.length - 1; i++) {
			int curTemp = temperatures[i];
			int nextTemp = temperatures[i + 1];

			if (nextTemp > curTemp) {
				answer[i] = 1;
				List<Integer> tmpList = new ArrayList<Integer>(list);
				for (int j = 0; j < tmpList.size(); j++) {
					int idx = tmpList.get(j);
					int temp = temperatures[idx];

					if (nextTemp > temp) {
						answer[idx] = i + 1 - idx;
						list.remove(new Integer(idx));
					}
				}
			} else {
				list.add(i);
			}
		}

		return answer;
	}

	// solution 3: stack
	public int[] dailyTemperatures3(int[] temperatures) {
		int[] answers = new int[temperatures.length];
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < temperatures.length; i++) {
			while (!stack.empty() && temperatures[stack.peek()] < temperatures[i]) {
				int idx = stack.pop();
				answers[idx] = i - idx;
			}

			stack.push(i);
		}

		return answers;
	}
}
