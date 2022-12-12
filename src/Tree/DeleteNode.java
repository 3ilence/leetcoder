package Tree;

import datastructure.Tree.TreeNode;

/**
 * @ClassName : DeleteNode
 * @Author : Silence
 * @Date: 2022/12/12 9:38
 * @Description : 450. 删除二叉搜索树中的节点
 */
public class DeleteNode {

    /**
     * 迭代
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode2(TreeNode root, int key) {
        TreeNode head = root;
        TreeNode pre = null;
        boolean left = false;
        while (root != null && root.val != key) {
            pre = root;
            if (root.val > key) {
                root = root.left;
                left = true;
            } else {
                root = root.right;
                left = false;
            }
        }
        if (root == null) {
            return head;
        }
        if (pre == null) {
            TreeNode tmp = root.left;
            if (tmp == null) {
                return root.right;
            } else {
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                tmp.right = root.right;
                return root.left;
            }
        } else {
            TreeNode tmp = root.left;
            if (tmp == null) {
                tmp = root.right;
            } else {
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                tmp.right = root.right;
                tmp = root.left;
            }
            if (left) {
                pre.left = tmp;
            } else {
                pre.right = tmp;
            }
            return head;
        }
    }


    /**
     * 递归
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        return dfs(root, key, new TreeNode(), true);
    }

    public TreeNode dfs(TreeNode root, int key, TreeNode father, boolean left) {
        if (root == null) {
            return root;
        }

        if (root.val == key) {
            TreeNode tmp = root.left;
            if (tmp == null) {
                if (left) {
                    father.left = root.right;
                } else {
                    father.right = root.right;
                }
                return root.right;
            }
            while (tmp.right != null) {
                tmp = tmp.right;
            }
            tmp.right = root.right;
            if (left) {
                father.left = root.left;
            } else {
                father.right = root.left;
            }
            return root.left;
        } else if (root.val > key) {
            dfs(root.left, key, root, true);

        } else {
            dfs(root.right, key, root, false);
        }
        return root;
    }

}
