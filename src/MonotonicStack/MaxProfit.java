package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName : MaxProfit
 * @Author : Silence
 * @Date: 2022/3/20 13:59
 * @Description : 121. 买卖股票的最佳时机
 */
public class MaxProfit {

    // <https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/>
    /**
     * 就是从数组中找到两个数，使得右边数减去左边数得到的值最大，意外发现能用单调栈
     * @param prices prices
     * @return 最大利润
     */
    public int maxProfit(int[] prices) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i : prices) {
            if (!stack.isEmpty() && stack.peek() > i) {
                res = Math.max(res, stack.peekFirst() - stack.peekLast());
                while (!stack.isEmpty() && stack.peek() > i) {
                    stack.pop();
                }
            }
            stack.push(i);
        }
        if (!stack.isEmpty()) {
            res = Math.max(res, stack.peekFirst() - stack.peekLast());
        }
        return res;
    }

    /**
     * 就是从数组中找到两个数，使得右边数减去左边数得到的值最大，做法是保存遍历过的最小数，并且每次遍历就记录res
     * @param prices prices
     * @return 最大利润
     */
    public int maxProfit2(int[] prices) {
        int res = 0, cur = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(cur + prices[i], res);
            if (-cur > prices[i]) {
                cur = -prices[i];
            }
        }
        return res;
    }
}
