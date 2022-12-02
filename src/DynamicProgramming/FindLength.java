package DynamicProgramming;

/**
 * @ClassName : FindLength
 * @Author : Silence
 * @Date: 2022/3/21 19:06
 * @Description : 718. 最长重复子数组
 */
public class FindLength {

    /**
     * 很多这种在两个数组或者字符串之间找共同子数组的都是类似的dp数组，比如编辑距离
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];//以m结尾和以n结尾的最长公共子数组长度
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}
