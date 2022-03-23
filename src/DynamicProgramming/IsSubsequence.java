package DynamicProgramming;

/**
 * @ClassName : IsSubsequence
 * @Author : Silence
 * @Date: 2022/3/23 13:20
 * @Description : 392. 判断子序列
 */
public class IsSubsequence {

    // <https://leetcode-cn.com/problems/is-subsequence/>
    /**
     * 可以当成是最长公共子序列来做，如果最长公共子序列长度等于s.length()，说明s为t的子序列
     * @param s s
     * @param t t
     * @return res
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];//
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n] == m;
    }
}
