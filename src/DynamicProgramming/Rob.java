package DynamicProgramming;

import datastructure.Tree.TreeNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : Rob
 * @Author : Silence
 * @Date: 2022/3/19 12:57
 * @Description : 198.&213&337打家劫舍系列
 */
public class Rob {

    // <https://leetcode-cn.com/problems/house-robber/>
    /**
     * 动态规划
     * @param nums nums
     * @return 能偷窃的最大金钱
     */
    public int rob(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][2];//其实可以进行空间优化，不需要O(n)
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp[len-1][0], dp[len-1][1]);
    }

    // <https://leetcode-cn.com/problems/house-robber-ii/>
    /**
     * 213. 打家劫舍 II
     * @param nums nums
     * @return 能偷窃的最大金钱
     */
    public int rob2(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;//pre代表上次不偷，cur代表上次偷
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }

    // <https://leetcode-cn.com/problems/house-robber-iii/>
    Map<TreeNode, Integer> map = new HashMap<>();

    /**
     * 337. 打家劫舍 III
     * @param root root
     * @return 能偷窃的最大金钱
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }
        int res = 0;
        if (root.left != null && root.right != null ) {
            res = Math.max(rob(root.left) + rob(root.right), root.val + rob(root.left.left) +
                    rob(root.left.right) + rob(root.right.left) + rob(root.right.right));
        } else if (root.left != null) {
            res = Math.max(rob(root.left) + rob(root.right), root.val + rob(root.left.left) +
                    rob(root.left.right));
        } else if (root.right != null) {
            res = Math.max(rob(root.left) + rob(root.right), root.val + rob(root.right.left) + rob(root.right.right));
        } else {
            res = root.val;
        }
        map.put(root, res);
        return res;
    }

    /**
     * 337. 打家劫舍 III。题解。
     * @param root root
     * @return 能偷窃的最大金钱
     */
    public int rob2(TreeNode root) {
        int[] rootStatus = dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }
}
