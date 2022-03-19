package DynamicProgramming;

import java.util.Arrays;

/**
 * @ClassName : CoinChange
 * @Author : Silence
 * @Date: 2022/3/19 10:51
 * @Description : 322. 零钱兑换
 */
public class CoinChange {

    // <https://leetcode-cn.com/problems/coin-change/>
    /**
     *完全背包，需要注意的是完全背包的状态方程是一维的
     * @param coins coins
     * @param amount amount
     * @return 最少的零钱
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int len = coins.length;
        int[] dp = new int[amount + 1];
        for (int j = 1; j <= amount; j++) {
            for (int i = 1; i <= len; i++) {
                //dp[j] = Math.min(dp[j], dp[j-coins[i-1]] + 1 )，但是dp[j]和dp[j-coins[i-1]]不一定有效
                //故需要分情况讨论
                if (j == coins[i-1]) {
                    dp[j] = 1;
                } else if (j > coins[i -1]) {
                    if (dp[j] > 0 && (dp[j - coins[i-1]] > 0 || j == coins[i-1])) {
                        dp[j] = Math.min(dp[j], dp[j-coins[i-1]] + 1);
                    } else if (dp[j-coins[i-1]] > 0 || coins[i-1] == j) {
                        dp[j] = dp[j-coins[i-1]] + 1;
                    }
                }
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }

    /**
     * 题解通过把状态数组都赋为最大值，这样就不用分类讨论了
     * @param coins coins
     * @param amount amount
     * @return 最少的零钱
     */
    public int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);//很关键
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[] {1,5,10}, 11));
    }
}
