package GreedyAlgorithm;

/**
 * @ClassName : MaxProfit
 * @Author : Silence
 * @Date: 2022/3/5 9:00
 * @Description : 121. 买卖股票的最佳时机
 */
public class MaxProfit {
    /**
     * 很好理解，我可以在第1天到第length-1天卖股票，但是我在卖之前需要先买股票，如果在卖股票时获取最大收益？当然是在最低点买股票
     * 故需要动态记录当天之前的股票最低价pre，那么prices[i] - pre就是今天卖掉股票可以获得的最大利润
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int pre = prices[0];//代表当前日子之前最低的股票价格
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(prices[i] - pre, res);
            pre = Math.min(pre, prices[i]);
        }
        return res;
    }
}
