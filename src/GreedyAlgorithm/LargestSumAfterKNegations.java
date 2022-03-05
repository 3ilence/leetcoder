package GreedyAlgorithm;

import java.util.Arrays;

/**
 * @ClassName : LargestSumAfterKNegations
 * @Author : Silence
 * @Date: 2022/3/5 15:35
 * @Description : 1005. K 次取反后最大化的数组和
 */
public class LargestSumAfterKNegations {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        if (nums[0] >= 0) {
            for (int a : nums) {
                sum += a;
            }
            return k % 2 == 0 ? sum : sum - 2 * nums[0];
        } else {
            int flag = Integer.MAX_VALUE;//最小绝对值，为了应对所有负数都变成正数了但是k尚未归零
            for (int a : nums) {
                if (a < 0) {
                    if (k > 0) {
                        sum += -a;
                        k--;
                    } else {
                        sum += a;
                    }
                } else {
                    sum += a;
                }
                flag = Math.min(flag, Math.abs(a));
            }
            return k % 2 == 0 ? sum : sum - 2 * flag;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LargestSumAfterKNegations().largestSumAfterKNegations(new int[] {-1,2,3}, 1));
    }
}
