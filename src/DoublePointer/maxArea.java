package DoublePointer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName : maxArea
 * @Author : Silence
 * @Date: 2022/11/20 15:57
 * @Description : 11. 盛最多水的容器
 */
public class maxArea {

    /**
     * 一开始用单调栈发现不行，单调栈的问题在于有些情况是中间有比两端长，不满足单调栈使用条件。看了答案才知道用双指针，每次收缩height较短的那个指针
     * 这样在(right - left)一定减小的情况下(right-left)*Math.min(height[left],height[right])有可能增大
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        //单调栈
        /*Deque<Integer> stack = new ArrayDeque<>();
        int res = 0, sum = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                stack.pop();
            }
            stack.push(i);
            if (stack.size() >= 2) {
                sum = (stack.peekFirst() - stack.peekLast() ) * height[stack.peekFirst()];
                res = Math.max(res, sum);
            }
        }
        stack.clear();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                stack.pop();
            }
            stack.push(i);
            if (stack.size() >= 2) {
                sum = (stack.peekFirst() - stack.peekLast() ) * height[stack.peekFirst()];
                res = Math.max(res, sum);
            }
        }
        return res;*/
        int left = 0, right = height.length - 1, res = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                res = Math.max(height[left] * (right - left), res);
                left++;
            } else {
                res = Math.max(height[right] * (right - left), res);
                right--;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new maxArea().maxArea(new int[]{1,2,1}));
    }
}
