package Tree;

import datastructure.Tree.TreeNode;

/**
 * @ClassName : TrimBST
 * @Author : Silence
 * @Date: 2022/12/11 22:43
 * @Description : 669. 修剪二叉搜索树
 */
public class TrimBST {

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
