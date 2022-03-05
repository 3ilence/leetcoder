package DynamicProgramming;

/**
 * @ClassName : MaxSubArray
 * @Author : Silence
 * @Date: 2022/3/5 8:58
 * @Description : 53. 最大子数组和
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSubArray().maxSubArray(new int[] {1,-2}));
    }
}
