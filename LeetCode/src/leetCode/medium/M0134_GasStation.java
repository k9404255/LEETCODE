package leetCode.medium;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class M0134_GasStation {
	// solution 1: brute-force (Time Limit Exceeded)
	public static int canCompleteCircuit1(int[] gas, int[] cost) {
		int n = gas.length;

		for (int i = 0; i < n; i++) {
			int remain = gas[i];
			int curCost = cost[i];
			int idx = (i + 1) % n;
			while (remain >= curCost) {
				if (idx == i) {
					return i;
				}
				remain = remain - curCost + gas[idx % n];
				curCost = cost[idx % n];
				idx = (idx + 1) % n;
			}
		}

		return -1;
	}

	// solution 2: sorting (Time Limit Exceeded)
	// https://www.baeldung.com/java-indices-array-after-sorting
	public static int canCompleteCircuit2(int[] gas, int[] cost) {
		int n = gas.length;

		int[] remains = new int[n];
		for (int i = 0; i < n; i++) {
			remains[i] = gas[i] - cost[i];
		}

		List<Integer> indices = IntStream.range(0, n).boxed().sorted(Comparator.comparingInt(i -> remains[i]))
				.collect(Collectors.toList());
		Collections.reverse(indices);

		for (int i : indices) {
			int remain = remains[i];
			int idx = (i + 1) % n;
			while (remain >= 0) {
				if (idx == i) {
					return i;
				}
				remain += remains[idx % n];
				idx = (idx + 1) % n;
			}
		}

		return -1;
	}

	// solution 3
	public static int canCompleteCircuit3(int[] gas, int[] cost) {
		int sGas = 0, sCost = 0, res = 0, total = 0;

		for (int i = 0; i < gas.length; i++) {
			sGas += gas[i];
			sCost += cost[i];
		}

		if (sGas < sCost) {
			return -1;
		}
		
		for (int i = 0; i < gas.length; i++) {
			total += gas[i] - cost[i];
			if (total < 0) {
				total = 0;
				res = i + 1;
			}
		}
		
		return res;
	}

	public static void main(String[] args) {
//		System.out.println(canCompleteCircuit1(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 }));
//		System.out.println(canCompleteCircuit1(new int[] { 2, 3, 4 }, new int[] { 3, 4, 3 }));
//		System.out.println(canCompleteCircuit1(new int[] { 5, 8, 2, 8 }, new int[] { 6, 5, 6, 6 }));
//
//		System.out.println(canCompleteCircuit2(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 }));
//		System.out.println(canCompleteCircuit2(new int[] { 2, 3, 4 }, new int[] { 3, 4, 3 }));
//		System.out.println(canCompleteCircuit2(new int[] { 5, 8, 2, 8 }, new int[] { 6, 5, 6, 6 }));
//
//		System.out.println(canCompleteCircuit3(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 }));
//		System.out.println(canCompleteCircuit3(new int[] { 2, 3, 4 }, new int[] { 3, 4, 3 }));
//		System.out.println(canCompleteCircuit3(new int[] { 5, 8, 2, 8 }, new int[] { 6, 5, 6, 6 }));
		System.out.println(canCompleteCircuit3(new int[] { 1, 1, 2, 1 }, new int[] { 2, 2, 1, 1 }));
	}
}
