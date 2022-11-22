import java.util.Arrays;

/**
 * @ClassName : nextPermutation
 * @Author : Silence
 * @Date: 2022/11/22 13:43
 * @Description : 31.下一个排列
 */
public class nextPermutation {

    /**
     * 思路清晰：最大的位于最低位（个位）的时候排列最小，要找到下一个最大排列，首先考虑的就是将十位和个位互换，然后考虑将百位和个位互换，百位和十位互换
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        //如何判断当前是否是最大排列？成员降序就是最大
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = nums.length - 1; j > i ; j--) {
                if (nums[i] < nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    //移动完之后还需要保证位置i右边是升序
                    Arrays.sort(nums, i+1, nums.length);
                    return;
                }
            }
        }
        for (int i = 0; i < nums.length / 2; i++) {
            int tmp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = tmp;
        }
        return;
    }

    public static void main(String[] args) {
        new nextPermutation().nextPermutation(new int[]{1,3,2});
    }
}
