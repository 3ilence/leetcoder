package DoublePointer;

/**
 * @ClassName : SortColors
 * @Author : Silence
 * @Date: 2022/11/25 16:22
 * @Description : 75. 颜色分类
 */
public class SortColors {

    /**
     * partition或者说双指针
     * @param nums
     */
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1, index = 0;
        while (index <= right && left <= right) {
            if (nums[index] == 0) {
                //下面的这个if是多余的，去掉这个if，并且在left++后面加上index++
                //首先left <= index，如果left < index，nums[left] == 1，因此本次交换完成直接index++，不需要对index处做二次判断
                if (index == left) {
                    index++;
                    left++;
                    continue;
                }
                int tmp = nums[left];
                nums[left] = nums[index];
                nums[index] = tmp;
                left++;
            } else if (nums[index] == 2) {
                int tmp = nums[right];
                nums[right] = nums[index];
                nums[index] = tmp;
                right--;
            } else {
                index++;
            }
        }
    }

    public static void main(String[] args) {
        new SortColors().sortColors(new int[]{2,0,2,1,1,0});
    }
}
