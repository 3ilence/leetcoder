package BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : Subsets
 * @Author : Silence
 * @Date: 2022/3/1 19:31
 * @Description : 78. 子集
 */
public class Subsets {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();

    /**
     * 78. 子集
     * @param nums nums
     * @return 所有组合组成的列表
     */
    public List<List<Integer>> subsets(int[] nums) {
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
            tmp.add(nums[i]);
            backTrack(nums, i + 1, length);
            tmp.remove(tmp.size() - 1);
        }
    }
}
