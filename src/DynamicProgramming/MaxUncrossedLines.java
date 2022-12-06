package DynamicProgramming;

/**
 * @ClassName : MaxUncrossedLines
 * @Author : Silence
 * @Date: 2022/3/21 19:53
 * @Description : 1035. 不相交的线
 */
public class MaxUncrossedLines {

    // <https://leetcode-cn.com/problems/uncrossed-lines/>
    /**
     * 其实就是最长公共子序列
     * 论证dp[i-1][j-1] + 1 >= Math.max(dp[i][j-1], dp[i-1][j])
     * 其实很好论证，我们不要忘记了这个问题本质，本质其实就是找两个数列的公共子序列，在给定序列情况下，那么当然是序列长度越长，公共子序列越长
     * 所以dp[i-1][j-1] >= dp[i-2][j-1]，dp[i-1][j-1] + 1 >= dp[i-2][j-1] + 1 >= dp[i-1][j]
     * 同理dp[i-1][j-1] >= dp[i][j-1]
     * @param nums1 nums1
     * @param nums2 nums2
     * @return res
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        //判断线相不相交，下标差的乘积如果是正就相交
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;//这里这样写其实还是经过推敲
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
