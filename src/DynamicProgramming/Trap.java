package DynamicProgramming;

/**
 * @ClassName : Trap
 * @Author : Silence
 * @Date: 2022/3/13 12:40
 * @Description : 42. 接雨水
 */
public class Trap {

    /**
     * 对于下标 i，下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值，下标 i 处能接的雨水量等于下标 i 处的水能到达的最大高度减去height[i]。
     * @param height height
     * @return 雨水体积
     */
    public int trap(int[] height) {
        int res = 0;
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i-1]);
        }
        for (int i = len-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i+1]);
        }
        int tmp;
        for (int i = 0; i < len; i++) {
            tmp = Math.min(leftMax[i], rightMax[i]);
            if (tmp > height[i]) {
                res += tmp - height[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new DynamicProgramming.Trap().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
