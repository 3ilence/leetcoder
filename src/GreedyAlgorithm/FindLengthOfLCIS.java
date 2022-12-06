package GreedyAlgorithm;

/**
 * @ClassName : FindLengthOfLCIS
 * @Author : Silence
 * @Date: 2022/12/6 22:32
 * @Description : 674. 最长连续递增序列
 */
public class FindLengthOfLCIS {

    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length, res = 1;
        int pre = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                pre = pre + 1;
                res = Math.max(res, pre);
            } else {
                pre = 1;
            }
        }
        return res;
    }
}
