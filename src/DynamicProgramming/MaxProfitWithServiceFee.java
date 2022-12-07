package DynamicProgramming;

/**
 * @ClassName : MaxProfitWithServiceFee
 * @Author : Silence
 * @Date: 2022/12/7 20:30
 * @Description : 714. 买卖股票的最佳时机含手续费
 */
public class MaxProfitWithServiceFee {

    /**
     * 同理可以进行状态压缩
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length, res = 0;
        int[][] dp = new int[len][2];
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }
}
