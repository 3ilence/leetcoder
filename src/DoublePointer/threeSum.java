package DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : threeSum
 * @Author : Silence
 * @Date: 2022/11/20 18:31
 * @Description : 15. 三数之和
 */
public class threeSum {

    /**
     * 排序+双指针+二分，一开始是想固定最左和最右，然后二分查找在中间进行查找，这样就是nlogn。
     * 这是一个错误的思路，个人分析得出的错误点在于在找到了一个正确答案之后双指针无法完美的移动，会错过某些情况
     * 正确做法应该是排序+双指针，固定最左边，然后让右边两个指针移动，可以看到和上面错误做法的不同点是我只固定一个点，妄想固定两个点不现实
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0, right = len - 1, target = 0, index = 0;
        while (left < right - 1) {
            target = - (nums[left] + nums[right]);
            if (target > nums[left] && target < nums[right]) {
                index = binarySearch(nums, left, right, target);
                if (index > 0) {
                    int finalIndex = index;
                    int finalLeft = left;
                    int finalRight = right;
                    res.add(new ArrayList<>(){
                        {
                            add(nums[finalLeft]);
                            add(nums[finalIndex]);
                            add(nums[finalRight]);
                        }
                    });
                }
                left++;
            } else if (target <= nums[left]) {
                right --;
            } else {
                left ++;
            }
        }
        return res;
    }

    /**
     * 正确做法，难点在去重
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0, right = len - 1, target = 0, sum = 0;
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) {
                return res;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;//去重很关键
            }
            left = i + 1; right = len - 1;target = - nums[i];
            while (left < right) {
                sum = nums[left] + nums[right];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(right > left && nums[left] == nums[left++ + 1]);//去重很关键
                    while(right > left && nums[right] == nums[right-- - 1]);
                }
            }
        }
        return res;
    }

    public int binarySearch(int[] nums, int left, int right, int target) {
        int mid;
        while (left < right) {
            mid =left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new threeSum().threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
