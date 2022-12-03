package Multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.function.IntConsumer;

/**
 * @ClassName : ZeroEvenOdd
 * @Author : Silence
 * @Date: 2022/12/3 10:24
 * @Description : 1116. 打印零与奇偶数
 */
public class ZeroEvenOdd {
    private int n;
    BlockingQueue<Integer> q1;
    BlockingQueue<Integer> q2;
    BlockingQueue<Integer> q3;

    public ZeroEvenOdd(int n) {
        this.n = n;
        q1 = new SynchronousQueue<>();
        q2 = new SynchronousQueue<>();
        q3 = new SynchronousQueue<>();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i=1; i<=n; i++) {
            printNumber.accept(0);
            if (i % 2 == 1) {
                q2.put(1);
            } else {
                q3.put(1);
            }
            q1.take();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            q3.take();
            printNumber.accept(i);
            q1.add(1);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            q2.take();
            printNumber.accept(i);
            q1.add(1);
        }
    }
}
