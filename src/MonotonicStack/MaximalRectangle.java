package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @ClassName : MaximalRectangle
 * @Author : Silence
 * @Date: 2022/11/28 12:10
 * @Description : 85. 最大矩形
 */

/**
 * 单调栈解法，利用前缀和把问题变形为largestRectangleArea，再用单调栈求解。刚刚才发现deque.peek()是取队首，也就是栈底元素，和Stack的peek不一样
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length, res = 0;
        int[][] newMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    newMatrix[i][j] = matrix[i][j] - '0';
                } else {
                    if (matrix[i][j] == '1')
                        newMatrix[i][j] = matrix[i][j] - '0' + newMatrix[i - 1][j];
                }
            }
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            int[] l = new int[n], r = new int[n];
            Arrays.fill(r, n);
            Arrays.fill(l, -1);
            for (int j = 0; j < n; j++) {
                while (!stack.isEmpty() && newMatrix[i][stack.peekLast()] > newMatrix[i][j]) {
                    r[stack.pop()] = j;
                }
                stack.addLast(j);
            }
            stack.clear();
            for (int j = n - 1; j >= 0; j--) {
                while (!stack.isEmpty() && newMatrix[i][stack.peekLast()] > newMatrix[i][j]) {
                    l[stack.pop()]  = j;
                }
                stack.addLast(j);
            }
            stack.clear();
            for (int j = 0; j < n; j++) {
                res = Math.max(res, newMatrix[i][j] * (r[j] - l[j] - 1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new MaximalRectangle().maximalRectangle(new char[][] {
                {'0','0','1','0'},{'0','0','1','0'},{'0','0','1','0'},{'0','0','1','1'},{'0','1','1','1'},{'0','1','1','1'},{'1','1','1','1'}
        });
    }
}
