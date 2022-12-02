package GreedyAlgorithm;

import java.util.Arrays;

/**
 * @ClassName : LengthOfLIS
 * @Author : Silence
 * @Date: 2022/3/21 16:47
 * @Description : 300. 最长递增子序列
 */
public class LengthOfLIS {

    /**
     * 想过单调栈做，但是单调栈不能解决"最长"
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int res = 1;
        int[] dp = new int[nums.length];//以i结尾的LIS
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
