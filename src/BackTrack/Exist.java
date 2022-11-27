package BackTrack;

/**
 * @ClassName : Exist
 * @Author : Silence
 * @Date: 2022/11/27 14:02
 * @Description : 79. 单词搜索
 */
public class Exist {
    boolean[][] flag;

    public boolean exist(char[][] board, String word) {
        flag = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && BackTrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean BackTrack(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length) {
            return false;
        }
        if (j < 0 || j >= board[0].length) {
            return false;
        }
        if (flag[i][j]) {
            return false;
        }
        if (board[i][j] == word.charAt(index)) {
            flag[i][j] = true;
            if (BackTrack(board, word, i , j - 1, index + 1)) {
                return true;
            }
            if (BackTrack(board, word, i - 1, j , index + 1)) {
                return true;
            }
            if (BackTrack(board, word, i + 1, j , index + 1)) {
                return true;
            }
            if (BackTrack(board, word, i, j + 1, index + 1)) {
                return true;
            }
            flag[i][j] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        new Exist().exist(new char[][]{{'A'}, {'B'}, {'C'}}, "ABC");
    }
}
