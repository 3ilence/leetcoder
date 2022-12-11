package Tree;

import datastructure.Tree.TreeNode;

/**
 * @ClassName : TrimBST
 * @Author : Silence
 * @Date: 2022/12/11 22:43
 * @Description : 669. 修剪二叉搜索树
 */
public class TrimBST {

    /**
     * 题解做法，迭代
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST2(TreeNode root, int low, int high) {
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        if (root == null) {
            return null;
        }
        for (TreeNode node = root; node.left != null; ) {
            if (node.left.val < low) {
                node.left = node.left.right;
            } else {
                node = node.left;
            }
        }
        for (TreeNode node = root; node.right != null; ) {
            if (node.right.val > high) {
                node.right = node.right.left;
            } else {
                node = node.right;
            }
        }
        return root;
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            dfs(root.left, low, high, root);
            dfs(root.right, low, high, root);
            return root;
        }
    }

    public void dfs(TreeNode root, int low, int high, TreeNode father) {
        if (root == null) {
            return;
        }
        if (root.val < low) {
            father.left = root.right;
            dfs(root.right, low, high, father);
        } else if (root.val > high) {
            father.right = root.left;
            dfs(root.left, low, high, father);
        } else {
            dfs(root.left, low, high, root);
            dfs(root.right, low, high, root);
        }
    }
}
