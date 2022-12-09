package Tree;

import datastructure.Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : RobIII
 * @Author : Silence
 * @Date: 2022/12/7 22:29
 * @Description : 337. 打家劫舍 III
 */
public class RobIII {

    Map<TreeNode, Integer> yes = new HashMap<>();
    Map<TreeNode, Integer> no = new HashMap<>();

    /**
     * 首先二叉树相关的一般都是递归来做，然后因为需要记录状态值：选根节点的最大值和不选根节点的最大值
     * 使用全局变量HashMap来记录状态值
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        yes.put(null, 0);
        no.put(null, 0);
        dfs(root);
        return Math.max(yes.get(root), no.get(root));
    }

    public void dfs(TreeNode root) {
        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }

        yes.put(root, root.val + no.get(root.left) + no.get(root.right));
        no.put(root, Math.max(yes.get(root.left), no.get(root.left)) + Math.max(no.get(root.right), yes.get(root.right)));
    }
}
