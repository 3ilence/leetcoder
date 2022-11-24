package DynamicProgramming;

/**
 * @ClassName : UniquePaths
 * @Author : Silence
 * @Date: 2022/11/24 16:17
 * @Description : 62. 不同路径
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + 1 < n) {
                    dp[i+1][j] += dp[i][j];
                }
                if (j+1 < m) {
                    dp[i][j+1] += dp[i][j];
                }
            }
        }
        return dp[n-1][m-1];
    }
}
