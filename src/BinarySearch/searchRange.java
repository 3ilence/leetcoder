package BinarySearch;

/**
 * @ClassName : searchRange
 * @Author : Silence
 * @Date: 2022/11/22 20:31
 * @Description : 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class searchRange {

    /**
     * binarySearch
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1,-1};
        }
        int left = 0, right = nums.length - 1, mid = 0;
        int resl = -1, resr = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                break;
            }
        }
        if (left > right) {
            return new int[] {resl, resr};
        }
        if (left == right) {
            return new int[] {left, right};
        }
        int l = left, r = mid, m;
        while (l <= r) {
            m = l + (r- l ) / 2;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                if(m == 0) {
                    resl = m;
                    break;
                } else if (nums[m] != nums[m-1]) {
                    resl = m;
                    break;
                } else {
                    r = m - 1;
                }
            }
        }
        l = mid;
        r = right;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (nums[m] > target) {
                r = m - 1;
            } else {
                if (m == nums.length - 1) {
                    resr = m;
                    break;
                } else if (nums[m] != nums[m + 1]) {
                    resr = m;
                    break;
                } else {
                    l = m + 1;
                }
            }
        }
        return new int[] {resl,resr};
    }
}
