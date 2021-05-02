package BagQuestion;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

//5 4000
//1000 290 920 480 1500
//4190
//从数组中任选数，找出大于4000的最小的和
public class BagQuestion2 {
static Set<Integer> set = new TreeSet<>();
static int res = 0;
public static void main(String[] args) {
Scanner in = new Scanner(System.in);
int n = in.nextInt();
int m = in.nextInt();
int[] price = new int[n];
for (int i = 0; i < n; i ++) {
    price[i] = in.nextInt();
}
dfs(price,m,0,0);
//利用treeset排好序的特点
//只要我把所有大于m的结果都加入到treeset里面
//最后只要取出第一个就行了，这样就免了比较
//但是这样好像也失去了剪枝的机会，这题dfs能剪枝吗？
for (int e : set) {
    res = e;
    break;
}
//这是用递归做的
System.out.println(res);
//这是非递归做的
System.out.println(noDigui(n,m,price));
}

static int dfs(int[] price, int min, int deep, int sum) {
if (deep == price.length)
    return sum;
//加入选择买这个商品
int sum1 = dfs(price,min,deep+1,sum + price[deep]);
//假如选择不买
int sum2 = dfs(price,min,deep+1,sum);
if (sum1 > min   )
    set.add(sum1);
if (sum2 > min)
    set.add(sum2);
return Math.min(sum1,sum2);
}

static int noDigui(int n, int m, int[] price) {
int[][] res = new int[n+1][n+1];
for (int i = 0; i <= n; i++) {
    //把这看成是一个背包问题，所有物品的重都为1，背包容量为物品的数量，即物品最多能都被装进背包
    //只是我们的目标不是得到最大的价值，而是为了得到大于某个值m的最小价值
    for (int j = 0; j <= n; j++) {
        //代表给的容量
        if (i ==0 || j == 0)
            res[i][j] = 0;
        else if (1 <= j) {
            int x = 0, y = 0;
            if (res[i - 1][j] < m ) {
                x = res[i - 1][j];
            }
            if (res[i-1][j-1] < m) {
                y = res[i - 1][j - 1] + price[i - 1];
            }
            if (x >= m && y >= m) {
                res[i][j] = Math.min(x, y);
            } else if (x >= m) {
                res[i][j] = x;
            } else {
                res[i][j] = y;
            }
        }

    }
}
return res[n][n];
}
}
