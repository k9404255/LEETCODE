package leetCode.easy;

public class E121_BestTimetoBuyandSellStock {
	// Solution 1: brute-force (time limit exceeded)
	public static int maxProfit1(int[] prices) {
		int maxProfit = 0;

		for (int i = 0; i < prices.length - 1; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				int sell = prices[j];
				int buy = prices[i];
				int profit = sell - buy;
				if (profit > maxProfit) {
					maxProfit = profit;
				}
			}
		}

		return maxProfit;
	}

	// Solution 2
	public static int maxProfit2(int[] prices) {
		int maxProfit = 0;
		int lowBuy = prices[0];

		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < lowBuy) {
				lowBuy = prices[i];
			} else if (maxProfit < prices[i] - lowBuy) {
				maxProfit = prices[i] - lowBuy;
			}
		}

		return maxProfit;
	}

	// Solution 3
	public static int maxProfit3(int[] prices) {
		int maxProfit = 0;
		int lowBuy = prices[0];

		for (int i = 1; i < prices.length; i++) {
			lowBuy = Math.min(lowBuy, prices[i]);
			maxProfit = Math.max(maxProfit, prices[i] - lowBuy);
		}

		return maxProfit;
	}

	public static void main(String[] args) {
		System.out.println(maxProfit1(new int[] { 7, 1, 5, 3, 6, 4 }));
		System.out.println(maxProfit1(new int[] { 7, 6, 4, 3, 1 }));

		System.out.println(maxProfit2(new int[] { 7, 1, 5, 3, 6, 4 }));
		System.out.println(maxProfit2(new int[] { 7, 6, 4, 3, 1 }));

		System.out.println(maxProfit3(new int[] { 7, 1, 5, 3, 6, 4 }));
		System.out.println(maxProfit3(new int[] { 7, 6, 4, 3, 1 }));
	}
}