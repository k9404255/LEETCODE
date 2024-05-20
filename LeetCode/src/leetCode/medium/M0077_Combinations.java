package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class M0077_Combinations {
	// solution 1
	public static List<List<Integer>> combine1(int n, int k) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		helper1(n, k, 1, ans, new ArrayList<>());

		return ans;
	}

	public static void helper1(int n, int k, int cur, List<List<Integer>> ans, List<Integer> tmpList) {
		if (tmpList.size() == k) {
			ans.add(new ArrayList<>(tmpList));
			return;
		}

		// 數字不能重複，且組合沒有順序性 ex: (1,2) (2,1) 只能算一種組合
		for (int i = cur; i <= n; i++) {
			tmpList.add(i);
			helper1(n, k, i + 1, ans, tmpList);
			tmpList.remove(tmpList.size() - 1);
		}

		// 數字不能重複，且組合有順序性
//		for (int i = 1; i <= n; i++) {
//			if (!tmpList.contains(i)) {
//				tmpList.add(i);
//				helper1(n, k, i + 1, ans, tmpList);
//				tmpList.remove(tmpList.size() - 1);
//			}
//		}

		// 數字可以重複，且組合有順序性
//		for (int i = 1; i <= n; i++) {
//			tmpList.add(i);
//			helper1(n, k, i + 1, ans, tmpList);
//			tmpList.remove(tmpList.size() - 1);
//		}
	}

	public static void main(String[] args) {
		System.out.println(combine1(4, 2));
		System.out.println(combine1(1, 1));
	}
}
