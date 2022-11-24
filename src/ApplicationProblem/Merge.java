package ApplicationProblem;

import java.util.*;

/**
 * @ClassName : Merge
 * @Author : Silence
 * @Date: 2022/11/24 16:03
 * @Description : 56. 合并区间
 */
public class Merge {

    /**
     * 这题的难度在于这题的输出比较怪，首先res的length是不确定的，所以一开始用什么来存储合并结果需要一点思考
     * 一旦确定用List来存储合并结果并且知道排序的话，题目就清晰了
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<Integer> list = new ArrayList<>();
        int len = intervals.length;
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        list.add(intervals[0][0]);
        list.add(intervals[0][1]);
        for (int i = 1; i < len; i++) {
            int lastLeft = list.get(list.size() - 2);
            int lastRight = list.get(list.size() - 1);
            if (intervals[i][0] > lastRight) {
                list.add(intervals[i][0]);
                list.add(intervals[i][1]);
            } else {
                list.remove(list.size() - 1);
                list.remove(list.size() - 1);
                list.add(lastLeft);
                list.add(Math.max(intervals[i][1], lastRight));
            }
        }
        int[][] res = new int[list.size() / 2][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = list.get(i * 2);
            res[i][1] = list.get(i * 2 + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        new Merge().merge(new int[][] {{1,2}, {2,6}, {8, 10}});
    }

    public int[][] merge2(int[][] intervals) {
        BitSet bitSet = new BitSet();
        int max = 0;
        for (int[] interval : intervals) {
            int temp = interval[1] * 2 + 1;
            bitSet.set(interval[0] * 2, temp, true);
            max = temp >= max ? temp : max;
        }

        int index = 0, count = 0;
        while (index < max) {
            int start = bitSet.nextSetBit(index);
            int end = bitSet.nextClearBit(start);

            int[] item = {start / 2, (end - 1) / 2};
            intervals[count++] = item;

            index = end;
        }
        int[][] ret = new int[count][2];
        for (int i = 0; i < count; i++) {
            ret[i] = intervals[i];
        }

        return ret;
    }

}
