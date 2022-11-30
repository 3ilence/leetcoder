package Tree;

import datastructure.Tree.TreeNode;

/**
 * @ClassName : MaxPathSum
 * @Author : Silence
 * @Date: 2022/11/30 13:57
 * @Description : 124. 二叉树中的最大路径和
 */
public class MaxPathSum {

    int max;
    public int maxPathSum(TreeNode root) {
        max = root.val;
        dfs(root);
        return max;
    }

    /**
     * 当前节点能为父节点提供的贡献，每次以某个节点为根节点计算最大路径和，最大路径和 = root.val + left + right
     * left和right分别为该节点左右节点能提供的贡献
     * 该节点能为父节点提供的贡献为root.val + Math.max(left, right)，如果贡献小于0，则父节点不需要该子树
     * @param root
     * @return
     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, root.val + left + right);
        return Math.max(0, Math.max(left, right) + root.val);
    }
}
