package BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : SubsetsWithDup
 * @Author : Silence
 * @Date: 2022/3/1 20:09
 * @Description : 90. 子集 II
 */
public class SubsetsWithDup {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();

    /**
     * 90. 子集 II
     * @param nums nums
     * @return 所有组合组成的列表
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);//排序
        res.add(new ArrayList<>());
        int length = 1;
        for (; length <= nums.length; length++ ) {
            backTrack(nums, 0, length);
        }
        return res;
    }

    public void backTrack(int[] nums, int start, int length) {
        if (tmp.size() == length) {
            res.add(new ArrayList<>(tmp));
        }
        if (length - tmp.size() > nums.length - start) {
            return;
        }

        for (int i = start; i < nums.length; i++ ) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }//去重
            tmp.add(nums[i]);
            backTrack(nums, i + 1, length);
            tmp.remove(tmp.size() - 1);
        }
    }
}
