package SlideWindow;

/**
 * @ClassName : FindLengthOfLCIS
 * @Author : Silence
 * @Date: 2022/3/21 16:35
 * @Description : 674. 最长连续递增序列
 */
public class FindLengthOfLCIS {

    // <https://leetcode-cn.com/problems/longest-increasing-subsequence/>
    /**
     * 典型滑动窗口问题，处理边界的时候有点思考量。我看官方题解说是贪心，感觉有点绕。
     * @param nums nums
     * @return 最长连续递增序列长度
     */
    public int findLengthOfLCIS(int[] nums) {
        int left = 0, right = 0;//注意是闭区间
        int res = 1;
        while (right < nums.length) {
            while (right < nums.length - 1 && nums[right] < nums[right + 1]) {
                right++;
            }
            // 仅当nums[right] < nums[right+1]的时候才会++
            // 故此时的right必有nums[right] > nums[right-1]
            res = Math.max(res, right - left + 1);
            left = right + 1;
            //连续相等序列可以直接跳过
            while (left < nums.length - 1 && nums[left] == nums[left + 1]) {
                left++;
            }
            right = left;
        }
        return res;
    }
}
