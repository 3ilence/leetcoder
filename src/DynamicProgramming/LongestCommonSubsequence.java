package DynamicProgramming;

/**
 * @ClassName : LongestCommonSubsequence
 * @Author : Silence
 * @Date: 2022/3/21 19:08
 * @Description : 1143. 最长公共子序列
 */
public class LongestCommonSubsequence {

    // <https://leetcode-cn.com/problems/longest-common-subsequence/>
    /**
     * 动态规划
     * @param text1 text1
     * @param text2 text2
     * @return res
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];//dp[i][j]表示text1.substring(0,i+1)和text2.substring(0,j+1)的最长公共子序列的长度
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
