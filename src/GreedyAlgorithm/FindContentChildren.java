package GreedyAlgorithm;

import java.util.Arrays;

/**
 * @ClassName : FindContentChildren
 * @Author : Silence
 * @Date: 2022/3/3 19:23
 * @Description : 455. 分发饼干
 */
public class FindContentChildren {
    /**
     * 贪心算法，把最大的饼干给胃口最大的小孩，从而达到满足最多的小孩的目的
     * @param g 小孩的胃口
     * @param s 饼干的大小
     * @return 最大能满足的小孩数量
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int j = g.length - 1;
        for (int i = s.length - 1; i >= 0; i--) {
            while (j >= 0 && s[i] < g[j]) {
                j--;
            }
            if (j >= 0) {
                res++;
                j--;
            }
        }
        return res;
    }
}
