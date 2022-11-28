package preSum;

/**
 * @ClassName : MaximalRectangle
 * @Author : Silence
 * @Date: 2022/11/28 13:24
 * @Description : 85. 最大矩形
 */
public class MaximalRectangle {

    /**
     * 前缀和+暴力+剪枝
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] arr = new int[rows][cols];
        int res = 0;
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                if (i == 0 && matrix[i][j] == '1') {
                    arr[i][j] = 1;
                    continue;
                }
                if (matrix[i][j] == '0') {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = arr[i - 1][j] + 1;
                }
                if (res < arr[i][j]) {
                    res = arr[i][j];
                    //System.out.println("i : " + i + ", j : " + j);
                    //System.out.println(res);
                }
            }
        }
        /*for (int[] arr2 :arr) {
            for (int i : arr2) {
                System.out.print(i + " ");
            }
            System.out.println();
        }*/
        //System.out.println(res);
        int count = 0;
        int index;
        int compute;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                count = 0;
                //剪枝，如果k * cols <= res,那么也没有遍历的意义了
                if (arr[i][j] * cols   <= res) {
                    continue;
                }
                index = j + 1;
                while (index < cols && arr[i][j] <= arr[i][index++]) {
                    count++;
                }
                index = j - 1;
                while (index >= 0 && arr[i][j] <= arr[i][index--]) {
                    count++;
                }
                compute = (count + 1) * arr[i][j];
                //System.out.println("count : " + count + ", compute : " + compute);
                if (res < compute) {
                    res = compute;
                }

            }
        }
        return res;
    }
}
