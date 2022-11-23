package Sort;

public class mergeSort {
    public int[] tmp;
    public void mergeSort(int[] nums) {
        tmp = new int[nums.length];
        merge(nums, 0, nums.length - 1);
    }

    public void merge(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int mid = (left + right) / 2;
        merge(nums, left, mid);
        merge(nums, mid + 1, right);
        int m = left, n = mid + 1;
        for (int i = left; i <= right; i++)
            tmp[i] = nums[i];
        for (int i = left; i <= right; i++) {
            if (m == mid + 1)
                nums[i] = tmp[n++];
            else if (n == right + 1 || tmp[m] < tmp[n])
                nums[i] = tmp[m++];
            else {
                nums[i] = tmp[n++];
            }
        }
    }

    public static void main(String[] args) {
        mergeSort sorter = new mergeSort();
        int[] nums = {2,4,34,1,5,4,2,5,3,8,56,78,34};
        sorter.mergeSort(nums);
        for (int i : nums)
            System.out.print(i + " ");
    }
}
