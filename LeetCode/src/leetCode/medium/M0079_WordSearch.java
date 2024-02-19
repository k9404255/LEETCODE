package leetCode.medium;

public class M0079_WordSearch {
	public static boolean exist1(char[][] board, String word) {
		
		
		return true;
	}

	public static void main(String[] args) {
		System.out.println(exist1(
				new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "ABCCED"));
		System.out.println(
				exist1(new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "SEE"));
		System.out.println(exist1(
				new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "ABCB"));
	}
}
