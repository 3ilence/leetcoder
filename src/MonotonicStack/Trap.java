package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName : Trap
 * @Author : Silence
 * @Date: 2022/3/10 10:51
 * @Description :42. 接雨水
 */
public class Trap {

    /**
     * 单调栈解法。单调栈首先有两点，一是这是个单减栈，因为单增的时候就可以求出雨水，二是栈里存的是下标，因为后面求面积需要下标差
     * 但是会有一个误区，就是遇到单增的时候要出栈，一般人思路是一次性出栈所有不符合单减的元素，但是这样在求面积的时候会比较复杂
     * 最好的解法就是一次出栈一个，将该下标目前能接的雨水求出来
     * @param height height
     * @return 雨水体积
     */
    public int trap(int[] height) {
        int res = 0;
        int len = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currentHeight = Math.min(height[left], height[i]) - height[top];//注意这里高度还要减去自己的高度
                res += currentHeight * currWidth;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Trap().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
