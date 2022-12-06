package GreedyAlgorithm;

/**
 * @ClassName : MaxProfit
 * @Author : Silence
 * @Date: 2022/3/5 9:00
 * @Description : 122. 买卖股票的最佳时机 II
 */
public class MaxProfit {
    /**
     * 很好理解，我可以在第1天到第length-1天卖股票，但是我在卖之前需要先买股票，如果在卖股票时获取最大收益？当然是在最低点买股票
     * 故需要动态记录当天之前的股票最低价pre，那么prices[i] - pre就是今天卖掉股票可以获得的最大利润
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int pre = prices[0];//代表当前日子之前最低的股票价格
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(prices[i] - pre, res);
            pre = Math.min(pre, prices[i]);
        }
        return res;
    }

    /**
     * 贪心
     * @param prices 价格
     * @return 最大利润
     */
    public int maxProfit(int[] prices) {
        // 贪心的策略是所有连续上涨日都买，什么是上涨日，就是说昨天股票价格比今天低
        // 为什么可以这样做？不是每天只能做一个动作吗？要么买要么卖。
        // 第一天买了第二天卖第二天再买第四天卖，其实这和第一天买第四天卖是等价的
        // pn - p1 = pn - p3 + p3 - p1，即等价于每天都买卖
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            if (tmp > 0) profit += tmp;
        }
        return profit;
    }
}
