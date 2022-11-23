package UnionFind;

/**
 * @ClassName : UnionFind.UnionFind
 * @Author : Silence
 * @Date: 2021/10/31 19:16
 * @Description : 并查集
 */
public class UnionFind {
    int[] ancestor;

    public UnionFind(int n) {
        ancestor = new int[n];
        for (int i = 0; i < n; ++i) {
            ancestor[i] = i;
        }
    }

    /**
     * 将index1作为子树合并到数index2
     * @param index1 被合并
     * @param index2
     */
    public void union(int index1, int index2) {
        ancestor[find(index1)] = find(index2);
    }

    public int find(int index) {
        if (ancestor[index] != index) {
            ancestor[index] = find(ancestor[index]);
        }
        return ancestor[index];
    }
}
