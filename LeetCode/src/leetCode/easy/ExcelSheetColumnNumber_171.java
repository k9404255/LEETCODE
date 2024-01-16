package leetCode.easy;

public class ExcelSheetColumnNumber_171 {
	public static int titleToNumber(String columnTitle) {
		int ans = 0;
		for (int i = 0; i < columnTitle.length(); i++) {
			int num = columnTitle.charAt(i) - 'A' + 1;
			ans = ans * 26 + num;
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(titleToNumber("A"));
		System.out.println(titleToNumber("AB"));
		System.out.println(titleToNumber("ZY"));
	}
}