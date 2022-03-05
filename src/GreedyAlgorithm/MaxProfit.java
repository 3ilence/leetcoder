package GreedyAlgorithm;

/**
 * @ClassName : MaxProfit
 * @Author : Silence
 * @Date: 2022/3/5 9:00
 * @Description : 122. 买卖股票的最佳时机 II
 */
public class MaxProfit {
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
