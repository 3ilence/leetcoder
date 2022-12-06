package DynamicProgramming;

/**
 * @ClassName : NumDistinct
 * @Author : Silence
 * @Date: 2022/12/6 15:23
 * @Description : 115. 不同的子序列
 */
public class NumDistinct {

    /**
     * dp[i][j] 代表 s.substring(0, i + 1) -> t.substring(0, j + 1)的可能种数
     * 状态转移方程：dp[i][j] = dp[i-1][j-1] + dp[i][j-1]，其中dp[i-1][j-1]仅当s.charAt(i-1) == s.charAt(j-1)才有
     * 另外注意，i >= j，比较前者只能通过删除字符得到后者，所以长度要大于等于
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];//s.substring(0, i + 1) -> t.substring(0, j + 1)的可能种数
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= i && j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                dp[i][j] += dp[i - 1][j];
            }
        }
        return dp[m][n];
    }
}
