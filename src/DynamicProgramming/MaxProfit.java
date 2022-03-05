package DynamicProgramming;

/**
 * @ClassName : MaxProfit
 * @Author : Silence
 * @Date: 2022/3/5 9:00
 * @Description : 122. 买卖股票的最佳时机 II
 */
public class MaxProfit {
    /**
     * 动规解法
     * @param prices 价格
     * @return 最大利润
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        // 一开始我有0元钱，买股票钱变负，卖股票钱增加
        int[][] dp = new int[prices.length][2];//第i天是否持有股票，dp[i][0]表示不持有
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][1] + prices[i], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }
        return res;
    }
}
