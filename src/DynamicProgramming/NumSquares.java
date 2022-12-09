package DynamicProgramming;

import java.util.Arrays;

/**
 * @ClassName : NumSquares
 * @Author : Silence
 * @Date: 2022/3/19 12:10
 * @Description : 279.完全平方数
 */
public class NumSquares {

    /**
     * 和numSquares2的区别在于循环的内外层顺序互换了，numSquares那种没有重复的用例，会快一点
     * @param n
     * @return
     */
    public int numSquares3(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int p = i * i;
            if (p > n) {
                break;
            }
            for (int j = p; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - p] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。和322零钱兑换一模一样
     * @param n n
     * @return 完全平方数最小数量
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 稍微优化
     * @param n n
     * @return 完全平方数最小数量
     */
    public int numSquares2(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }

}
