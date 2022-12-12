package Tree;

import datastructure.Tree.TreeNode;

/**
 * @ClassName : LowestCommonAncestorInBST
 * @Author : Silence
 * @Date: 2022/12/12 14:23
 * @Description : 235. 二叉搜索树的最近公共祖先
 */
public class LowestCommonAncestorInBST {

    /**
     * 迭代做法
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //TreeNode fatherP = null, fatherQ = null;
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val > p.val && root.val < q.val) {
                //if (fatherP == null && fatherQ == null) {
                return root;
                //}
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else if (root.val < p.val && root.val > q.val) {
                //if (fatherP == null && fatherQ == null) {
                return root;
                //}
            } else if (root.val == p.val) {
                return root;
            } else if (root.val == q.val) {
                return root;
            }
        }
        return root;
    }
}
