package DynamicProgramming;

/**
 * @ClassName : IsSubsequence
 * @Author : Silence
 * @Date: 2022/3/23 13:20
 * @Description : 392. 判断子序列
 */
public class IsSubsequence {

    /**
     * dp[i][j]表示s.substring(0, i)是否是t.substring(0, j)的子序列
     * 其实可以优化，因为dp[0][j] = true是恒成立，这样代码更精简，实践：6ms->4ms
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        int m = s.length(), n = t.length();
        if (m > n) {
            return false;
        }
        // 需要注意的点是""是所有串的子串
        if (m == 0) {
            return true;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];//dp[i][j]表示s.substring(0, i)是否是t.substring(0, j)的子序列
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    if (i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
                if (i == m && dp[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

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
