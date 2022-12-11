package Tree;

import datastructure.Tree.TreeNode;

/**
 * @ClassName : ConvertBST
 * @Author : Silence
 * @Date: 2022/12/11 22:02
 * @Description : 538. 把二叉搜索树转换为累加树
 */
public class ConvertBST {

    int pre = 0;

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        //右中左
        convertBST(root.right);
        root.val = root.val + pre;
        pre = root.val;
        convertBST(root.left);
        return root;
    }
}
