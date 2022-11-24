package GreedyAlgorithm;

/**
 * @ClassName : MaxSubArray
 * @Author : Silence
 * @Date: 2022/11/24 14:21
 * @Description : 53. 最大子数组和
 */
public class MaxSubArray {
    public int MaxSubArray(int[] nums) {
        int res = nums[0] , sum = 0;
        for (int i : nums) {
            sum += i;
            if (sum < i) {
                sum = i;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
