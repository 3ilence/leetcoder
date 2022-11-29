package Tree;

import datastructure.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName : IsValidBST
 * @Author : Silence
 * @Date: 2022/11/28 22:11
 * @Description : 98. 验证二叉搜索树
 */
public class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }

    public boolean dfs(TreeNode root, Integer max, Integer min) {
        if (root == null) {
            return true;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        if (min != null && root.val <= min) {
            return false;
        }
        return dfs(root.left, max == null ? root.val : Math.min(max, root.val), min) && dfs(root.right, max, min == null ? root.val : Math.max(root.val, min));
    }

    /**
     * 迭代解法，还可以进行空间优化
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.addLast(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (list.size() > 0 && list.get(list.size() - 1) >= root.val) {
                return false;
            }
            list.add(root.val);
            root = root.right;

        }
        return true;
    }
}
