package Tree;

/**
 * @ClassName : NumTrees
 * @Author : Silence
 * @Date: 2022/11/28 22:10
 * @Description :
 */
public class NumTrees {

    /**
     * 动态规划，关键有两个，一是二叉搜索树1，2，3和二叉搜索树4，5，6没有本质区别；二是选定了根节点，左子树和右子树集合就都确定了
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;dp[0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i ; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
