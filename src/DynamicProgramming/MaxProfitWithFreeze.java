package DynamicProgramming;

/**
 * @ClassName : MaxProfitWithFreeze
 * @Author : Silence
 * @Date: 2022/12/7 20:07
 * @Description : 309. 最佳买卖股票时机含冷冻期
 */
public class MaxProfitWithFreeze {

    /**
     * 同样的，可以进行状态压缩
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length, res = 0;
        int[][][] dp = new int[len][2][2];//0表示未持有股票，0表示未处于冷冻期
        dp[0][1][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0][0] = Math.max(dp[i-1][0][0], dp[i-1][0][1]);
            dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][1][0] + prices[i]);

            //持有股票不分冷冻期
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][0][0] - prices[i]);
            res = Math.max(dp[i][0][0], res);
            res = Math.max(dp[i][0][1], res);
        }
        return res;
    }
}
