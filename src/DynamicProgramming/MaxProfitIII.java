package DynamicProgramming;

/**
 * @ClassName : MaxProfitIII
 * @Author : Silence
 * @Date: 2022/12/7 17:19
 * @Description : 123. 买卖股票的最佳时机 III
 */
public class MaxProfitIII {

    /**
     * 状态压缩后
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length, res = 0;
        int a = -prices[0], b = -prices[0], c = 0, d = 0;
        for (int i = 1; i < len; i++) {
            d = Math.max(d, b + prices[i]);
            b = Math.max(-prices[i] + c, b);
            c = Math.max(c, a + prices[i]);
            a = Math.max(-prices[i], a);
            res = Math.max(c, res);
            res = Math.max(d, res);
        }
        return res;
    }

    /**
     * 可以明显看到，dp[i]仅仅依赖dp[i-1]，可以进行状态压缩，压缩至O(n)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length, res = 0;
        int[][][] dp = new int[len][2][3];//0表示不持有股票，0表示还有0次出售机会
        dp[0][1][2] = -prices[0];
        dp[0][1][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][1][2] = Math.max(-prices[i] + dp[i-1][0][2], dp[i-1][1][2]);//dp[i-1][0][2] == 0
            dp[i][1][1] = Math.max(-prices[i] + dp[i-1][0][1], dp[i-1][1][1]);
            dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][1][2] + prices[i]);
            dp[i][0][0] = Math.max(dp[i-1][0][0], dp[i-1][1][1] + prices[i]);
            res = Math.max(dp[i][0][1], res);
            res = Math.max(dp[i][0][0], res);
        }
        return res;
    }
}
