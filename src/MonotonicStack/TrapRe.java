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
