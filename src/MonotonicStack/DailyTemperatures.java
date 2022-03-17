package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName : DailyTemperatures
 * @Author : Silence
 * @Date: 2022/3/17 10:21
 * @Description : 739.每日温度
 */
public class DailyTemperatures {

    /**
     * 单调栈
     * @param temperatures 温度
     * @return res
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; ) {
            while (stack.isEmpty() || temperatures[stack.peek()] >= temperatures[i]) {
                stack.push(i);
                i++;
                if (i == temperatures.length) {
                    return res;
                }
            }
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int tmp = stack.pop();
                res[tmp] = i - tmp;
            }
        }
        return res;
    }

    /**
     * 另一种方法，比单调栈更快
     * @param T T
     * @return res
     */
    public int[] dailyTemperatures2(int[] T) {
        int length = T.length;
        int[] result = new int[length];

        //从右向左遍历
        for (int i = length - 2; i >= 0; i--) {
            // j+= result[j]是利用已经有的结果进行跳跃
            for (int j = i + 1; j < length; j+= result[j]) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                } else if (result[j] == 0) { //遇到0表示后面不会有更大的值，那当然当前值就应该也为0
                    result[i] = 0;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i : new DailyTemperatures().dailyTemperatures(new int[] {73,74,75,71,69,72,76,73})) {
            System.out.print(i + " ");
        }
    }
}
