import java.util.Random;

/**
 * @ClassName : QuickSelect
 * @Author : Silence
 * @Date: 2021/11/20 9:53
 * @Description : 查找数组中的第k大元素
 */
public class QuickSelect {
    Random random = new Random();

    /**
     * 查找数组中第k大元素
     *
     * @param a 数组
     * @param l 左边界,inclusive
     * @param r 右边界,inclusive
     * @param index 第k大
     * @return 排序后的a[k]
     */
    public int quickSelect(int[] a, int l, int r, int index) {
        //为了尽可能的每次划分使两边长度都差不多，需要引入随机化
        int q = randomPartition(a, l, r);
        if (q < index) {
            return  quickSelect(a, l, q - 1, index);
        } else if (q > index) {
            return  quickSelect(a, q + 1, r, index);
        } else {
            return a[q];
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        //[0， r-l]
        int i = random.nextInt(r - l + 1) + 1;
        swap(a, i, r);
        return partition(a, l, r);
    }

    /**
     * 最核心的部分，划分。注意划分的方法。这里是选取了a[r]作为基准点，通过当a[j]<=a[r]则j++的方法算出了a[r]的位置
     *
     * @param a 数组
     * @param l 左边界,inclusive
     * @param r 有边界,inclusive
     * @return 返回确定位置了的元素的下标
     */
    public int partition(int[] a, int l, int r) {
        // 以a[r]为基准
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            // 如果a[j] > x并不符合要求，i不执行++，对于后面所有的a[j]<=x，都会想左移动左边不符合要求的个数
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        //最终得到的i其实就是[l,r]之间小于a[r]的元素个数，a[r]的下标即i+1
        swap(a, i + 1, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
