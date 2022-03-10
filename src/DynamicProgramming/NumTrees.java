package DynamicProgramming;

/**
 * @ClassName : NumTrees
 * @Author : Silence
 * @Date: 2022/3/5 20:24
 * @Description : 96. 不同的二叉搜索树
 */
public class NumTrees {

    /**
     *求长为n的二叉搜索树一共有多少种
     * @param n n
     * @return 长为n二叉搜索树种数
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        //对于节点数为n的二叉搜索树，1，2，...，n都是可以作为根节点的，当节点i作为根节点，那么左子树节点数为i-1，右子树节点数为n-i
        //左右子树的种数相乘即节点i作为根节点的二叉搜索树种数
        //很明显状态转移方程就出来了
        //需要注意的是1，2，3，4组成的二叉搜索树和5，6，7，8组成的二叉搜索树是没有区别的
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
}
