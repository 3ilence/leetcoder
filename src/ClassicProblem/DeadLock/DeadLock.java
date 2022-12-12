package ClassicProblem.DeadLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : DeadLock
 * @Author : Silence
 * @Date: 2022/12/12 19:52
 * @Description :
 */
public class DeadLock implements Runnable {

    private ReentrantLock lock1, lock2;

    public DeadLock(ReentrantLock lock1, ReentrantLock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        simulate(lock1, lock2);
    }

    public void simulate(ReentrantLock lock1, ReentrantLock lock2) {
        try {
            lock1.lock();
            System.out.println("获取了锁：" + lock1.toString());
            Thread.sleep(100000);
            lock2.lock();
            System.out.println("获取了锁：" + lock2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
            lock1.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        new Thread(new DeadLock(lock1, lock2)).start();
        new Thread(new DeadLock(lock2, lock1)).start();
    }
}
