package DoublePointer;

import java.util.Deque;

/**
 * @ClassName : Trap
 * @Author : Silence
 * @Date: 2022/3/13 12:27
 * @Description : 42. 接雨水
 */
public class Trap {

    /**
     * 双指针。其实就是动态规划的优化。关键还是：下标i处雨水能到达的高度取决于i左右两侧最大值的较小者
     * @param height height
     * @return 雨水体积
     */
    public int trap(int[] height) {
        int res = 0;
        int len = height.length;
        int left = 0, right = len - 1;
        int leftMax = 0, rightMax = 0;//leftMax是指left左边的最大值，rightMax是指右边的最大值
        while (left <= right) {
            if (leftMax <= rightMax) {
                //因为下标i处雨水能到达的高度取决于i左右两侧最大值的较小者，故可以求出下标left处最终雨水能到达的高度
                if (leftMax - height[left] > 0) {
                    res += leftMax - height[left];
                }
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                //可以求出下标right处最终雨水能到达的高度
                if (rightMax - height[right] > 0) {
                    res += rightMax - height[right];
                }
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new DoublePointer.Trap().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
