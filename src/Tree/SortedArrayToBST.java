package Tree;

import datastructure.Tree.TreeNode;

import java.util.Arrays;

/**
 * @ClassName : SortedArrayToBST
 * @Author : Silence
 * @Date: 2022/12/11 22:07
 * @Description : 108. 将有序数组转换为二叉搜索树
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return null;
        }
        int index = len / 2;
        TreeNode node = new TreeNode(nums[index]);
        TreeNode left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, index));
        TreeNode right = sortedArrayToBST(Arrays.copyOfRange(nums, index + 1, len));
        node.left = left;
        node.right = right;
        return node;
    }
}
