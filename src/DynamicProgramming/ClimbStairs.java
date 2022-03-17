package DynamicProgramming;

/**
 * @ClassName : ClimbStairs
 * @Author : Silence
 * @Date: 2022/3/17 10:18
 * @Description : 70.爬楼梯
 */
public class ClimbStairs {

    /**
     * 完全背包。与377. 组合总和 Ⅳ类似，这里顺序不同的序列视为不同的组合
     * @param n n
     * @return 组合种数
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i >= 1) {
                dp[i] += dp[i-1];
            }
            if (i >= 2) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}
