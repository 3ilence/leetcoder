package Tree;

import datastructure.Tree.TreeNode;

/**
 * @ClassName : LowestCommonAncester
 * @Author : Silence
 * @Date: 2022/12/10 3:50
 * @Description : 236. 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {


    /**
     * 只有两种情况，q和q在相同子树下，或者p，q在不同子树下
     * 假如我们递归的过程中找到了p，q其中一个就立即返回的话，对于情况一而言，dfs(root.left, p, q)和dfs(root.right, p, q)
     * 其中一个返回答案，另一个返回null
     * 对于情况二而言，dfs(root.left, p, q)和dfs(root.right, p, q)分别返回p和q
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (root != p && root != q) {
            if (l == null) {
                return r;
            } else if (r == null) {
                return l;
            } else {
                return root;
            }
        } else if (root == p) {
            return root;
        } else if (root == q) {
            return root;
        } else {
            return root;
        }
    }
}
