package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @ClassName : LargestRectangleArea
 * @Author : Silence
 * @Date: 2022/11/27 14:42
 * @Description : 84. 柱状图中最大的矩形
 */
public class LargestRectangleArea {

    /**
     * 单调栈优化，一次扫描。做法是非严格单增栈，非严格意思是相等也看做单增
     * 当h[stack.peekLast()] > h[i]时，将栈顶出栈，记为tmp，那么i是tmp右边第一个小于i的高度的下标，又因为栈内高度单增，所以h[stack.peekLast()] <= h[tmp]
     * 可以得到面积等于 area = h[tmp] * (i - stack.peekLast() - 1)
     * 唯一的问题在于有可能h[stack.peekLast()] == h[tmp]，那么stack.peekLast()也就不是准确的左边界，实际左边界还要往左移动，也就是说有可能实际面积要大于area
     * 但是这个问题并非问题，假设stack.peekLast是连续重复元素的第一个，那么当它出栈的时候左边界便不会错，并且它的面积也是最大的
     * 也就是说如果存在连续的相同高度的元素，并不会影响最终答案
     * 如果用严格单增栈的话代码还是一样，但是理解起来稍有区别
     * @param heights
     * @return
     */
    public int largestRectangleArea6(int[] heights) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]) {//可以严格单增也可以不，两种都是正确答案，且代码除了这里都一样
                int tmp = stack.pollLast();
                int left = stack.isEmpty() ? -1 : stack.peekLast();
                res = Math.max(res, heights[tmp] * (i - left - 1));
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            int tmp = stack.pollLast();
            int left = stack.isEmpty() ? -1 : stack.peekLast();
            res = Math.max(res, heights[tmp] * (heights.length - left - 1));
        }
        return res;
    }

    /**
     * 刚刚知道了我看这段代码费劲的原因，在Deque中，pop是移除队首也就是栈底的元素，peek是获取队首也就是栈底的元素，pop = removeFirst, peek = peekFirst, push = addFirst
     * 其实找第一个小于height[i]的下标还是简单，就是单增栈，以向右遍历举例，不满足单调的时候就将栈顶出栈，r[stack.pollLast()] = i.
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int len = heights.length;
        //选定高度找宽度
        //单减栈
        //要找到从i往左右两边扩展第一个比i小的
        int[] l = new int[len], r = new int[len];
        Arrays.fill(l, -1); Arrays.fill(r, len);//如果循环执行完毕栈内还有元素，说明这些元素一直单增，所以直接赋初值，省去了处理栈内剩余的数据
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i])
                r[stack.pollLast()] = i;
            stack.addLast(i);//因为是单增栈，他比最小值小，所以加到栈底
        }
        stack.clear();
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]) {
                l[stack.pollLast()] = i;
            }
            stack.push(i);
        }
        for (int i = 0; i < len; i++) {
            res = Math.max(res, heights[i] * (r[i] - l[i] - 1));
        }
        return res;
    }

    /**
     * 再一次做错了，过不了用例：4,2,0,3,2,4,3,4
     * 从下标i往左右扩展，找到第一个比height[i]小的下标
     * @param heights
     * @return
     */
    public int largestRectangleArea5(int[] heights) {
        int res = 0;
        int len = heights.length;
        //选定高度找宽度
        //单减栈
        int[] l = new int[len], r = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; ) {
            if (stack.isEmpty() || heights[stack.peek()] >= heights[i]) {
                stack.push(i);
                i++;
            } else {
                //关键在于单调栈内的下标要连续，不然取的边界值不正确
                while (!stack.isEmpty()) {
                    int tmp = stack.pop();
                    l[tmp] = stack.isEmpty() ? tmp : stack.peekLast();
                }
            }
        }
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            l[tmp] = stack.isEmpty() ? tmp : stack.peekLast();
        }
        for (int i = len - 1; i >= 0;) {
            if (stack.isEmpty() || heights[stack.peek()] >= heights[i]) {
                stack.push(i);
                i--;
            } else {
                while (!stack.isEmpty()) {
                    int tmp = stack.pop();
                    r[tmp] = stack.isEmpty() ? tmp : stack.peekLast();
                }
            }
        }
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            r[tmp] = stack.isEmpty() ? tmp : stack.peekLast();
        }
        for (int i = 0; i < len; i++) {
            res = Math.max(res, heights[i] * (r[i] - l[i] + 1));
        }
        return res;
    }

    /**
     * 单调栈做法，本质就是选定高度找宽度，错误做法，错误的原因是这种做法一次遍历右边界没找准
     * 无法应对1,2,3,4,5这样的用例，3出栈的时候算出的窗口长度为2，但实际窗口长度为3
     * 问题在于窗口右边界有可能错误，但是窗口的左边界是对的；所以可以由此联想，如果从len-1 -> 0再遍历一次
     * 不就能得到右边界了
     * @param heights
     * @return
     */
    public int largestRectangleArea4(int[] heights) {
        int res = 0;
        int len = heights.length;
        //选定高度找宽度
        //单减栈
        //我的思路是出栈的时候tmp = stack.pop()，可以计算出下标tmp的窗口，但是这种做法有问题
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; ) {
            if (stack.isEmpty() || heights[stack.peek()] >= heights[i]) {
                stack.push(i);
                i++;
            } else {
                int tmp = stack.pop();
                res = Math.max(res, heights[tmp] * (stack.size() + 2));
                while (!stack.isEmpty()) {
                    tmp = stack.pop();
                    int flag = heights[tmp] <= heights[i] ? 1 : 0;
                    res = Math.max(res, heights[tmp] * (stack.size() + 1));
                }
            }
        }
        while (!stack.isEmpty()) {
            res = Math.max(res, heights[stack.pop()] * (stack.size() + 1));
        }

        return res;
    }

    /**
     * 动态规划解法，超出内存限制。
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int res = 0;
        int len = heights.length;
        //关键的是窗口里的最矮的，如果最矮的能很高或者窗口能很大，都有机会让面积最大
        int[][] dp = new int[len][len];//窗口里最矮的
        //dp[i][j] = Math.min(heights[j], dp[i][j-1]);
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (j - 1 < i ) {
                    dp[i][j] = heights[j];
                } else {
                    dp[i][j] = Math.min(dp[i][j-1], heights[j]);
                }

                res = Math.max(res, dp[i][j] * (j - i + 1));
            }
        }
        return res;
    }

    /**
     * 动态规划解法，空间优化，超出时间限制。还是那句话，空间能否优化要看状态的依赖的跨度
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        int res = 0;
        int len = heights.length;
        //关键的是窗口里的最矮的，如果最矮的能很高或者窗口能很大，都有机会让面积最大
        int[] dp = new int[len];//窗口里最矮的
        //dp[i][j] = Math.min(heights[j], dp[i][j-1]);
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (j - 1 < i ) {
                    dp[j] = heights[j];
                } else {
                    dp[j] = Math.min(dp[j-1], heights[j]);
                }

                res = Math.max(res, dp[j] * (j - i + 1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new LargestRectangleArea().largestRectangleArea(new int[] {4,2,0,3,2,4,3,4});
    }
}
