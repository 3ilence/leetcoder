package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName : TrapRe
 * @Author : Silence
 * @Date: 2022/11/23 15:48
 * @Description : 42.接雨水
 */
public class TrapRe {

    /**
     * 我尝试用单调栈解接雨水，犯了两个错误，第一个错误最为致命，我试图在出现单调增的时候把栈中所有小于height[i]的元素都出栈，导致计算复杂
     * 第一个错误解决后，第二个错误就是出栈时雨水面积的计算，width * height，其中width = i- stack.peek() + 1，这是非常重要的，因为每次出栈虽然只出了一个，但是增加的雨水确是一个下标范围
     * height = Math.min(height[stack.peek()], height[i])， 事实上该坐标处的最终雨水高度应该是Math.min(leftMax, rightMax)，也就是说此时该点的雨水还没有全部加到res中
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int len = height.length, res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len;) {
            if (stack.isEmpty() || height[stack.peek()] >= height[i]) {
                stack.push(i);
                i++;
            } else {
                int index = stack.pop();
                if (!stack.isEmpty()) {
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    //res += (i - index) * (Math.min(height[stack.peek()], height[i]) - height[index]);
                    //new int[] {4,2,0,3,2,5}
                    res += (i - (stack.peek() + 1)) * (Math.min(height[stack.peek()], height[i]) - height[index]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new TrapRe().trap(new int[] {4,2,0,3,2,5});
    }
}
