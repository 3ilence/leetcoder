package BackTrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName : SolveNQueens
 * @Author : Silence
 * @Date: 2022/3/3 15:45
 * @Description : 51. N 皇后
 */
public class SolveNQueens {
    int[][] matrix;
    List<List<String>> res = new ArrayList<>();
    List<String> tmp = new ArrayList<>();
    int[] column;//column[i]表示第j列已经有了几个皇后
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();


    public List<List<String>> solveNQueens(int n) {
        matrix = new int[n][n];
        column = new int[n];
        backTrack(0);
        return res;
    }

    public void backTrack(int k) {
        if (matrix.length == k) {
            for (int i = 0; i < matrix.length; i++) {
                char[] s = new char[k];
                for (int j = 0; j < matrix.length; j++) {
                    s[j] = matrix[i][j] == 0 ? '.' : 'Q';
                }
                tmp.add(new String(s));
            }
            res.add(new ArrayList<>(tmp));
            tmp.clear();
            return;
        }

        for (int j = 0; j < matrix.length; j++) {
            if (column[j] > 0 ) {
                continue;
            }
            if (set1.contains(k - j)) {
                continue;
            }
            if (set2.contains(k + j)) {
                continue;
            }
            set1.add(k - j);
            set2.add(k + j);
            matrix[k][j] = 1;
            column[j]++;
            backTrack(k + 1);
            column[j]--;
            matrix[k][j] = 0;
            set1.remove(k - j);
            set2.remove(k + j);
        }
    }

    public static void main(String[] args) {
        new SolveNQueens().solveNQueens(6);
    }
}
