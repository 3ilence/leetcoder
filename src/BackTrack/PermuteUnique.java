package BackTrack;

import java.util.*;

/**
 * @ClassName : PermuteUnique
 * @Author : Silence
 * @Date: 2022/3/3 14:13
 * @Description : 47. 全排列 II
 */
public class PermuteUnique {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();
    int[] visited;


    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);//因为nums中可能含有重复的数字，所以为了方便去重，需要排序，且由于是求全排列，排不排序对结果没影响
        visited = new int[nums.length];//0代表没有被选择，1代表已经被选择
        backTrack(nums, 0);
        return res;
    }

    public void backTrack(int[] nums, int k) {
        if (k == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0) {
                continue;
            }
            tmp.add(nums[i]);
            visited[i] = 1;
            backTrack(nums, k + 1);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        new PermuteUnique().permuteUnique(new int[] {0,1,0,0,9});
    }
}
