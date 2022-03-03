package BackTrack;

import java.util.*;

/**
 * @ClassName : Permute
 * @Author : Silence
 * @Date: 2022/3/3 14:00
 * @Description : 46. 全排列
 */
public class Permute {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();
    Set<Integer> set = new HashSet<>();//其实也可以用boolean数组来标志是否使用过，内存消耗更小

    public List<List<Integer>> permute(int[] nums) {
        backTrack(nums, 0);
        return res;
    }

    public void backTrack(int[] nums, int k) {
        if (k == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                tmp.add(nums[i]);
            } else {
                continue;
            }
            backTrack(nums, k + 1);
            tmp.remove(tmp.size() - 1);
            set.remove(nums[i]);
        }
    }

    /**
     * 答案解法，思路会更新奇
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
}
