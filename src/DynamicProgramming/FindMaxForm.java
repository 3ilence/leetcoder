package DynamicProgramming;

/**
 * @ClassName : FindMaxForm
 * @Author : Silence
 * @Date: 2022/3/14 19:38
 * @Description : 474,一和零
 */
public class FindMaxForm {

    /*<https://leetcode-cn.com/problems/ones-and-zeroes/>*/
    /**
     * 01背包问题。空间未优化
     * @param strs strs
     * @param m m
     * @param n n
     * @return 最大满足要求子集长度
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int res = 0;
        int len = strs.length;
        int[] zero = new int[len];
        int[] one = new int[len];
        int index = 0;
        for (String s : strs) {
            for (char a : s.toCharArray()) {
                if (a == '0') {
                    zero[index]++;
                } else {
                    one[index]++;
                }
            }
            index++;
        }
        int[][][] dp = new int[len + 1][m + 1][n + 1];//dp[i][j][k]表示前i个元素的集合的子集个数
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (k >= one[i-1] && j >= zero[i-1]) {
                        dp[i][j][k] = Math.max(Math.max(dp[i][j][k], dp[i-1][j][k]), dp[i-1][j-zero[i-1]][k - one[i-1]] + 1);
                    } else {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k]);
                    }
                    if (i == len) {
                        res = Math.max(res, dp[i][j][k]);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 01背包。空间优化。
     * @param strs strs
     * @param m m
     * @param n n
     * @return 最大满足要求子集长度
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int res = 0;
        int len = strs.length;
        int[] zero = new int[len];
        int[] one = new int[len];
        int index = 0;
        for (String s : strs) {
            for (char a : s.toCharArray()) {
                if (a == '0') {
                    zero[index]++;
                } else {
                    one[index]++;
                }
            }
            index++;
        }
        int[][] dp = new int[m + 1][n + 1];//dp[j][k]表示前i个元素的集合的子集最大长度
        for (int i = 0; i < len; i++) {
            for (int j = m; j >= zero[i]; j--) {
                for (int k = n; k >= one[i]; k--) {
                        dp[j][k] = Math.max(Math.max(dp[j][k], dp[j][k]), dp[j-zero[i]][k - one[i]] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 进一步优化时间复杂度
     * @param strs strs
     * @param m m
     * @param n n
     * @return 最大满足要求子集长度
     */
    public int findMaxForm3(String[] strs, int m, int n) {
        int res = 0;
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];//dp[j][k]表示前i个元素的集合的子集最大长度
        for (int i = 0; i < len; i++) {
            int[] zerosOnes = getZerosOnes(strs[i]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(Math.max(dp[j][k], dp[j][k]), dp[j-zeros][k - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }
}
