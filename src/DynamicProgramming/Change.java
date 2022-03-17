package DynamicProgramming;

/**
 * @ClassName : Change
 * @Author : Silence
 * @Date: 2022/3/17 9:56
 * @Description : 518. 零钱兑换 II
 */
public class Change {

    /**
     * 完全背包
     * @param amount 目标金额
     * @param coins 零钱数组
     * @return 组合数
     */
    public int change(int amount, int[] coins) {
        int len = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i-1]) {
                    dp[j] += dp[j - coins[i-1]];
                }
            }
        }
        return dp[amount];
    }
}
