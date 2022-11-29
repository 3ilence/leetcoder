package Tree;

import datastructure.Tree.TreeNode;

/**
 * @ClassName : IsSymmetric
 * @Author : Silence
 * @Date: 2022/11/28 22:20
 * @Description : 101. 对称二叉树
 */
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isEqual(root.left, root.right);
    }

    public boolean isEqual(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        } else if (l != null && r != null) {
            return l.val == r.val && isEqual(l.left, r.right) && isEqual(l.right, r.left);
        } else {
            return false;
        }
    }
}
