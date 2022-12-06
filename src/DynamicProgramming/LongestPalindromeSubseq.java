package DynamicProgramming;

import java.util.Arrays;

/**
 * @ClassName : LongestPalindromeSubseq
 * @Author : Silence
 * @Date: 2022/12/6 10:26
 * @Description : 516. 最长回文子序列
 */
public class LongestPalindromeSubseq {

    /**
    * 动态规划，自己想没想出来，看了dp数组的定义才写出来
     * dp[i][j]表示s.substring(i, j + 1)的最长回文子串的长度，dp[i][j] = Math.max(dp[i+1][j-1] + 2, dp[i+1][j], dp[i][j-1])
     * 比如dp[3][5], 如果s.charAt(3) != s.charAt(5), 那么就从dp[4][5]和dp[3][4]中挑更大的那个，这样相应的就比较s.charAt(3)和s.charAt(6)或者
     * s.charAt(2)和s.charAt(5)
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 > j - 1) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j - 1], dp[i + 1][j]));
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }

            }
        }
        return dp[0][len - 1];
    }

    /**
     * 可以把问题转换为s和s.reverse()的最长公共子序列问题，即LCS问题
     * @param s
     * @return
     */
    public int longestPalindromeSubseq2(String s) {
        int len = s.length(), res = 0;
        String reverse = reverse(s);
        int[][] dp = new int[len + 1][len + 1];//dp[i][j]表示前i个字符组成的序列和前j个字符组成的序列的最长公共子序列
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (s.charAt(i - 1) == reverse.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                res = Math.max(dp[i][j], res);
            }
        }
        return  res;
    }

    public String reverse(String s) {
        char tmp;
        char[] arr = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < j; i++, j-- ) {
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return new String(arr);
    }
}
