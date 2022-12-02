package Hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName : LongestConsecutive
 * @Author : Silence
 * @Date: 2022/12/2 22:09
 * @Description : 128. 最长连续序列
 */
public class LongestConsecutive {

    /**
     * 哈希表，先把所有元素存入哈希表，然后再进行遍历。选定节点，分别往左右扩展，这样就得到了包含该节点的最长连续序列，同时保证只遍历该序列一次
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                continue;
            }
            int right = 1, left = 1;
            while (set.contains(nums[i] + right)) {
                set.remove(nums[i] + right);
                right++;
            }

            while (set.contains(nums[i] - left)) {
                set.remove(nums[i] - left);
                left++;
            }
            set.remove(nums[i]);
            res = Math.max(res, left + right - 1);
        }
        return res;
    }
}
