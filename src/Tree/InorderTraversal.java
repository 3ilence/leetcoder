package Tree;

import datastructure.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName : InorderTraversal
 * @Author : Silence
 * @Date: 2022/11/28 22:00
 * @Description : 94. 二叉树的中序遍历
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.addLast(root);
                root = root.left;
            }
            //每次循环处理根节点和根的右子树，这样是不是没有处理左子树？其实根节点+右子树就等于上一层的左子树
            root = stack.pollLast();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
