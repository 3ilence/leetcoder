package DynamicProgramming;

/**
 * @ClassName : MinPathSum
 * @Author : Silence
 * @Date: 2022/11/24 16:54
 * @Description : 64. 最小路径和
 */
public class MinPathSum {

    /**
     * 空间复杂度O(mn)，比较不推荐的做法
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else {
                    dp[i][j] = 10000;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < m && j + 1 < n) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + grid[i + 1][j]);
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + grid[i][j + 1]);
                } else if (i + 1 < m) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + grid[i + 1][j]);
                } else if (j + 1 < n) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + grid[i][j + 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 不修改原数组条件下空间优化，空间优化地原因是dp[i][j]只依赖于dp[i][j-1]和dp[i-1]j，即上面那行j后面的和当前这行j前面的
     * 也就是我们始终只依赖于j个步骤，因此我们只保存j个状态，在计算dp[k]的时候, dp[k-1]就相当于是dp[i][j-1]，dp[k]相当于dp[i-1][j]
     * 即滚动数组
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int len = grid[0].length;
        int[] dp = new int[len];
        dp[0] = grid[0][0];
        for (int i = 1; i < len; i++)
            dp[i]=dp[i-1]+grid[0][i];
        for (int i = 1; i < grid.length; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < len; j++)
                dp[j] = Math.min(dp[j-1]+grid[i][j], dp[j]+grid[i][j]);
        }
        return dp[len-1];
    }
}
