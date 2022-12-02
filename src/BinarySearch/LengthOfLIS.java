package BinarySearch;

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
     * 动态规划+二分，这种做法我觉得我做几次都不会想到
     * tails[k] 的值代表 长度为 k+1子序列 的尾部元素值。
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }
}
