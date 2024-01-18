package leetCode.easy;

import java.util.LinkedList;
import java.util.List;

public class MoveZeroes_283 {
    public static void printNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    // solution 1: shift array
    public static void moveZeroes1(int[] nums) {
        int countZero = 0;
        int n = nums.length;

        for (int i = 0; i < n - countZero - 1;) {
            if (nums[i] == 0) {
                countZero++;
                for (int j = i; j < n - countZero; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[n - countZero] = 0;
            } else {
                i++;
            }
        }
    }

    // solution 2: list
    public static void moveZeroes2(int[] nums) {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                list.add(nums[i]);
            }
        }

        int n = list.size();
        for (int i = 0; i < nums.length; i++) {
            if (i < n) {
                nums[i] = list.remove(0);
            } else {
                nums[i] = 0;
            }
        }
    }

    // solution 3: best
    public static void moveZeroes3(int[] nums) {
        int countNonZero = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[countNonZero] = nums[i];
                countNonZero++;
            }
        }

        for (int i = countNonZero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // solution 4: best
    public static void moveZeroes4(int[] nums) {
        int zeroIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && zeroIdx == -1) {
                zeroIdx = i;
            } else if (nums[i] != 0 && zeroIdx != -1) {
                nums[zeroIdx] = nums[i];
                nums[i] = 0;
                zeroIdx++;
            }
        }
    }

    public static void main(String[] args) {
        moveZeroes1(new int[] {0, 1, 0, 3, 12 });
        moveZeroes1(new int[] {0 });
        moveZeroes1(new int[] {0, 0, 1 });

        moveZeroes2(new int[] {0, 1, 0, 3, 12 });
        moveZeroes2(new int[] {0 });
        moveZeroes2(new int[] {0, 0, 1 });

        moveZeroes3(new int[] {0, 1, 0, 3, 12 });
        moveZeroes3(new int[] {0 });
        moveZeroes3(new int[] {0, 0, 1 });

        moveZeroes4(new int[] {0, 1, 0, 3, 12 });
        moveZeroes4(new int[] {0 });
        moveZeroes4(new int[] {0, 0, 1 });
    }
}