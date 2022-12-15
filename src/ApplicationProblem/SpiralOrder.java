package ApplicationProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : SpiralOrder
 * @Author : Silence
 * @Date: 2022/12/15 10:34
 * @Description : 54. 螺旋矩阵
 */
public class SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int direction = 1;//1=右，2=下，3=左，4=上
        int r = n - 1, down = m - 1, l = 0, top = 0;
        int i = 0, j = 0;
        int count = 0;
        List<Integer> res = new ArrayList<>();
        while (count < m * n) {
            res.add(matrix[i][j]);
            if (direction == 1) {
                if (j < r) {
                    j++;
                } else {
                    top++;
                    i++;
                    direction = 2;
                }
            } else if (direction == 2) {
                if (i < down) {
                    i++;
                } else {
                    r--;
                    j--;
                    direction = 3;
                }
            } else if (direction == 3) {
                if (j > l) {
                    j--;
                } else {
                    down--;
                    i--;
                    direction = 4;
                }
            } else if (direction == 4) {
                if (i > top) {
                    i--;
                } else {
                    l++;
                    j++;
                    direction = 1;
                }
            }
            count++;
        }
        return res;
    }
}

