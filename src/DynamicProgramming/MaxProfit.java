package DynamicProgramming;

/**
 * @ClassName : MaxProfit
 * @Author : Silence
 * @Date: 2022/3/5 9:00
 * @Description : 122&123&188&309&714. 买卖股票的最佳时机
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
     * 123. 买卖股票的最佳时机 III。其实这种做法可以优化，类似与188买卖股票4的做法
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

    // <https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/>
    /**
     * 188. 买卖股票的最佳时机 IV。解法与123十分相似，就是不能空间优化
     * @param k 限制次数
     * @param prices 价格
     * @return 最大利润
     */
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        int[][][] dp = new int[len][2][k+1];//dp[i][j][k]代表第i天持有股票股票还剩k次卖的机会
        for (int i = 0; i <= k; i++) {
            dp[0][1][i] = -prices[0];//假装我在第0天的时候已经卖过股票，这样的好处是免去了分类讨论，并且使得dp[len-1][0][0]就是最终答案，因为不管利润最大的时候买了几次，都会到达dp[len-1][0][0]
        }
        for (int i = 1; i < len; i++) {
            for (int j = k; j >= 0; j--) {
                if (j < k) {
                    dp[i][0][j] = Math.max(dp[i-1][0][j], dp[i-1][1][j+1] + prices[i]);
                } else {
                    dp[i][0][j] = dp[i-1][0][j];
                }
                dp[i][1][j] = Math.max(dp[i-1][1][j], dp[i-1][0][j] - prices[i]);
            }
        }
        return dp[len-1][0][0];
    }

    // <https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/>
    /**
     * 309. 最佳买卖股票时机含冷冻期。空间未优化版本。
     * @param prices 价格
     * @return 最大利润
     */
    public int maxProfit5(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int[][][] dp = new int[len][2][2];//i,j,k,k=1表示处于冷冻期
        dp[0][1][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0][0] = Math.max(dp[i-1][0][0], dp[i-1][0][1]);
            dp[i][0][1] = dp[i-1][1][0] + prices[i];//持有股票的时候不可能处于冷冻期
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][0][0] - prices[i]);
        }
        return Math.max(dp[len-1][0][0], dp[len-1][0][1]);
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期。空间优化版本。
     * @param prices 价格
     * @return 最大利润
     */
    public int maxProfit6(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int a = -prices[0], b = 0, c = 0;//有票，无票且冷，无票不冷
        for (int i = 1; i < len; i++) {
            int tmp = c;
            c = Math.max(c, b);
            b = a + prices[i];//持有股票的时候不可能处于冷冻期
            a = Math.max(a, tmp - prices[i]);
        }
        return Math.max(b, c);
    }

    // <https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/>
    /**
     * 714. 买卖股票的最佳时机含手续费。空间优化后。
     * @param prices 价格
     * @param fee 交易费用
     * @return 最大利润
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int a = 0, b = -prices[0];//a代表不持有股票，b代表持有股票
        for (int i = 1; i < len; i++) {
            int tmp = a;
            a = Math.max(a, b + prices[i] - fee);
            b = Math.max(b, tmp - prices[i]);
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(new MaxProfit().maxProfit(2, new int[] {1,4,2,7}));
    }
}
