package DynamicProgramming;

/**
 * @ClassName : Rob
 * @Author : Silence
 * @Date: 2022/3/19 12:57
 * @Description : 198. 打家劫舍
 */
public class Rob {

    // <https://leetcode-cn.com/problems/house-robber/>
    /**
     * 动态规划
     * @param nums nums
     * @return 能偷窃的最大金钱
     */
    public int rob(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp[len-1][0], dp[len-1][1]);
    }
}
