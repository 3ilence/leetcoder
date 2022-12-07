package DynamicProgramming;

/**
 * @ClassName : MaxProfitIV
 * @Author : Silence
 * @Date: 2022/12/7 18:45
 * @Description : 188. 买卖股票的最佳时机 IV
 */
public class MaxProfitIV {

    /**
     * 同理，dp[i]还是只依赖dp[i-1]，可以将二维数组压缩成一维
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int len = prices.length, res = 0;
        int[][][] dp = new int[len][2][k + 1];//0表示不持有股票，0表示还有0次出售机会
        for (int j = k; j > 0; j--) {
            dp[0][1][j] = -prices[0];
        }
        for (int i = 1; i < len; i++) {
            for (int j = k; j >= 0; j--) {
                dp[i][1][j] = Math.max(dp[i-1][1][j], dp[i-1][0][j] - prices[i]);
                if (j < k) {
                    dp[i][0][j] = Math.max(dp[i-1][0][j], dp[i-1][1][j + 1] + prices[i]);
                } else {
                    dp[i][0][j] = dp[i-1][0][j];
                }

                res = Math.max(dp[i][0][j], res);
            }

        }
        return res;
    }
}
