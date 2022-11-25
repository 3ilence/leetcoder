package DynamicProgramming;

import java.util.Arrays;

/**
 * @ClassName : MinDistance
 * @Author : Silence
 * @Date: 2022/11/25 15:09
 * @Description : 72. 编辑距离
 */
public class MinDistance {

    /**
     * 这是我看了dp数组定义后做的答案，在用例(sea,eat)的时候答案出错，原因是se->e的这个状态计算出错，对应的dp[1][0]
     * 原因在于没有对s1[i] == s2[j]进行判断，没有进行判断也是因为0-1 = -1，所以这种dp数组定义还是有问题，应该声明为dp[m+1][n+1]
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m == 0 && n == 0) {
            return 0;
        }
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }
        int[][] dp = new int[m][n];//word1前i个字符变成word2前j个字符的编辑距离
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 1000;
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (i - 1 >= 0 && j - 1 >= 0) {
                    int tag = dp[i-1][j-1] + 1;
                    if (word1.charAt(i) == word2.charAt(j)) {
                        tag--;
                    }
                    dp[i][j] = Math.min(Math.min(dp[i][j], dp[i-1][j] + 1),
                            tag);
                } else if (i - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + 1);
                } else if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + 1);
                }

            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        new MinDistance().minDistance("sea", "eat");
    }
}
