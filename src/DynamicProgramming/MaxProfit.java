package DynamicProgramming;

/**
 * @ClassName : MaxProfit
 * @Author : Silence
 * @Date: 2022/3/5 9:00
 * @Description : 122&123. 买卖股票的最佳时机
 */
public class MaxProfit {

    // <https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/>
    /**
     * 122. 买卖股票的最佳时机 II
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

    // <https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/>
    /**
     * 123. 买卖股票的最佳时机 III
     * @param prices 价格
     * @return 最大利润
     */
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        int[][][] dp = new int[len][2][3];//dp[i][j][k]代表第i天持有股票股票还剩k次卖的机会
        dp[0][0][2] = 0;
        dp[0][1][2] = -prices[0];
        for (int i = 1; i < len; i++) {
            if (i >= 3) {
                if (i == 3) {
                    dp[i][0][0] = dp[i-1][1][1] + prices[i];
                } else {
                    dp[i][0][0] = Math.max(dp[i-1][0][0], dp[i-1][1][1] + prices[i]);
                }
            }
            if (i >= 2) {
                dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][1][2] + prices[i]);
                if (i == 2) {
                    dp[i][1][1] = dp[i-1][0][1] - prices[i];
                } else {
                    dp[i][1][1] = Math.max(dp[i-1][0][1] - prices[i], dp[i-1][1][1]);
                }
            }
            if (i == 1) {
                dp[i][0][1] = dp[i-1][1][2] + prices[i];
            } else {
                dp[i][0][1] = Math.max(dp[i-1][1][2] + prices[i], dp[i-1][0][1]);
            }

            dp[i][0][2] = dp[i-1][0][2];
            dp[i][1][2] = Math.max(dp[i-1][0][2] - prices[i], dp[i-1][1][2]);
        }
        return Math.max(dp[len-1][0][1], Math.max(0,dp[len-1][0][0]));
    }

    /**
     * 123. 买卖股票的最佳时机 III。题解做法，空间优化，并且避免分类讨论。
     * @param prices 价格
     * @return 最大利润
     */
    public int maxProfit4(int[] prices) {
        int buy1 = -prices[0], buy2 = -prices[0];
        int sell1 = 0, sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            buy2 = Math.max(sell1 - prices[i], buy2);
            sell1 = Math.max(buy1 + prices[i], sell1 );
            sell2 = Math.max(buy2 + prices[i], sell2);
            buy1 = Math.max(buy1, -prices[i]);
        }
        return sell2;
    }

    public static void main(String[] args) {
        System.out.println(new MaxProfit().maxProfit3(new int[] {1,4,2,7}));
    }
}
