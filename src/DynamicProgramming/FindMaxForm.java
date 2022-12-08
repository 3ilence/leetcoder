package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : FindMaxForm
 * @Author : Silence
 * @Date: 2022/3/14 19:38
 * @Description : 474,一和零
 */
public class FindMaxForm {


    /**
     * 状态压缩
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm5(String[] strs, int m, int n) {
        int len = strs.length;
        int[] map = new int[len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i < strs[j].length(); i++) {
                if (strs[j].charAt(i) == '0') {
                    map[j]++;
                }
            }
        }

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            int zero = map[i - 1];
            // 有一点就是当反向进行状态转移的时候，j >= zero, k >= one就变成了循环终止条件
            for (int j = m; j >= zero; j--) {
                for (int k = n; k >= strs[i-1].length() - zero; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zero][k - strs[i - 1].length() + zero] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 未进行空间压缩
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm4(String[] strs, int m, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = strs.length;
        for (int j = 0; j < len; j++) {
            int tmp = 0;
            for (int i = 0; i < strs[j].length(); i++) {
                if (strs[j].charAt(i) == '0') {
                    tmp++;
                }
            }
            map.put(j, tmp);
        }

        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int zero = map.get(i - 1);
                    if (j >= zero && k >= (strs[i - 1].length() - zero)) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zero][k - strs[i - 1].length() + zero] + 1);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[len][m][n];
    }

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
