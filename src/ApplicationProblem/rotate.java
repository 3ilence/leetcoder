package ApplicationProblem;

/**
 * @ClassName : rotate
 * @Author : Silence
 * @Date: 2022/11/23 17:23
 * @Description : 48. 旋转图像
 */
public class rotate {

    /**
     * 像拨洋葱一样一层层地拨
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len/2; i++) {
            for ( int j = i; j <= len / 2 - i-1; j++) {// 需要把int j = i 改为int j = 0
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len -j -1][i];
                matrix[len - j - 1][ i] = matrix[len - i-1][ len-j-1];
                matrix[len-i-1][len-j-1] = matrix[j][ len-i-1];
                matrix[j][len-i-1] = tmp;
            }
        }
        return;
    }

    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        new rotate().rotate(new int[][]{{1,2},{3,4}});
    }
}
