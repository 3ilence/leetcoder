package DoublePointer;

/**
 * @ClassName : TrapRe
 * @Author : Silence
 * @Date: 2022/11/23 12:45
 * @Description : 42. 接雨水
 */
public class TrapRe {

    /**
     * 下标为i处能接的雨水体积等于分别向左右两边扩展所能得到的最大高度的较小值减去height[i]
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int len = height.length, res = 0;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i-1]);
        }
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }
        for (int i = 1; i < len - 1; i++) {
            if (height[i] < leftMax[i] && height[i] < rightMax[i]) {
                res += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
        }
        return res;
    }

    public int trap2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int len = height.length, res = 0;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = len - 1;
        while (left <= right) { // 注意最后left == right也是要计算的，left == right的时候并不一定是最高点
            if (leftMax <= rightMax) {
                // rightMax[left] >= rightMax >= leftMax
                if (leftMax > height[left]) {
                    res += leftMax - height[left];
                }
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                // leftMax[right] >= leftMax >= rightMax
                if (rightMax > height[right]) {
                    res += rightMax - height[right];
                }
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        new TrapRe().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1});
    }
}
