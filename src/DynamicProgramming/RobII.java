package DynamicProgramming;

/**
 * @ClassName : RobII
 * @Author : Silence
 * @Date: 2022/12/7 22:19
 * @Description : 213. 打家劫舍 II
 */
public class RobII {

    /**
     * 可以空间压缩
     * 房屋维城一个环。正常计算最后会得到dp[len-1][0]和dp[len-1][1]，分别表示最后一间房屋不偷和偷的时候最大战利品
     * 因为首尾同时只能取一个，要么取首，要么取尾，那么我们分两次计算，第一次不要尾，第二次不要首即可
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = nums.length, res = nums[0];
        int[][] dp = new int[len][2];//其实可以进行空间优化，不需要O(n)
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        res = Math.max(res, dp[len-1][0]);
        //倒序进行状态转移得到最后的dp[0][0]就是第一间屋子不偷的最大战利品
        dp[len - 1][1] = nums[len - 1];
        dp[len - 1][0] = 0;
        for (int i = len - 2; i >= 0; i--) {
            dp[i][0] = Math.max(dp[i+1][0], dp[i+1][1]);
            dp[i][1] = dp[i+1][0] + nums[i];
        }
        res = Math.max(res, dp[0][0]);
        return res;
    }
}
