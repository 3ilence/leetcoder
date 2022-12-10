package Tree;

import datastructure.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : FindMode
 * @Author : Silence
 * @Date: 2022/12/10 12:51
 * @Description : 501. 二叉搜索树中的众数
 */
public class FindMode {

    List<Integer> res = new ArrayList<>();
    int lastVal = 1000000;
    int count = 0;
    int maxCount = 0;

    /**
     * 解法肯定是中序遍历，题目要求用递归并且空间复杂度O(n) = 1，递归栈调用不算
     * 我当时不知道为什么没有想到中序遍历的递归写法，我一直怎么用findMode进行递归调用，返回值int[]怎么处理
     * 其实明确递归中序遍历题目还是不难想
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        dfs(root);
        //需要注意的是，二叉树在遍历完时没有对最后的count ? maxCount进行判断
        if (count > maxCount) {
            res.clear();
            res.add(lastVal);
            maxCount = count;
        } else if (count == maxCount) {
            res.add(lastVal);
        }
        // toArray只能得到Object[]，所以要得到int[]只能通过遍历赋值
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        int val = root.val;
        if (val == lastVal) {
            count++;
        } else {
            if (count > maxCount) {
                res.clear();
                res.add(lastVal);
                maxCount = count;
            } else if (count == maxCount) {
                res.add(lastVal);
            }
            lastVal = root.val;
            count = 1;
        }
        dfs(root.right);
    }

}
