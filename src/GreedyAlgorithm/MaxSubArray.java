package GreedyAlgorithm;

/**
 * @ClassName : MaxSubArray
 * @Author : Silence
 * @Date: 2022/11/24 14:21
 * @Description : 53. 最大子数组和
 */
public class MaxSubArray {

    /**
     * 其实现在想清楚了dp的空间优化这题的贪心做法也就不难想了，这题本质就是动规进行空间优化
     * 我们每次状态转移的时候仅仅依赖上上一个连续和，也就是状态依赖链表长为1，也就没必要dp数组了
     * @param nums
     * @return
     */
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
