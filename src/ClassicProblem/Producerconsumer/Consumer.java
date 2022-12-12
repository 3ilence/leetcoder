package ClassicProblem.Producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : ClassicProblem.Producerconsumer.Consumer
 * @Author : Silence
 * @Date: 2022/12/10 17:35
 * @Description :
 */
public class Consumer implements Runnable{

    private BlockingQueue<Integer> queue;

    private ReentrantLock lock;
    private List<Integer> list;
    final private int MAX_LENGTH = 5;
    Condition produce;
    Condition consume;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public Consumer(ReentrantLock lock, List list, Condition produce, Condition consume) {
        this.lock = lock;
        this.list = list;
        this.produce = produce;
        this.consume = consume;
    }

    @Override
    public void run() {
        while (true) {
            consumeByLock();
        }
    }

    private void consumeByBlockingQueue() {
        try {
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void consumeByLock() {
        try {
            lock.lock();
            if (list.size() == 0) {
                consume.await();//将该线程加入到consumeCondition的等待队列中，并释放锁
            }
            System.out.println(list.remove(0));
            produce.signalAll();//唤醒produceCondition等待队列中的所有线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
//        BlockingQueue<Integer> queue = new SynchronousQueue<>();
//        new Thread(new ClassicProblem.Producerconsumer.Producer(queue)).start();
//        new Thread(new ClassicProblem.Producerconsumer.Consumer(queue)).start();
//        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20);
//        new Thread(new ClassicProblem.Producerconsumer.Producer(queue)).start();
//        new Thread(new ClassicProblem.Producerconsumer.Consumer(queue)).start();
//        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
//        new Thread(new Producer(queue)).start();
//        new Thread(new Consumer(queue)).start();
        List<Integer> list = new ArrayList<>();
        ReentrantLock lock = new ReentrantLock(true);
        Condition produce = lock.newCondition();
        Condition consume = lock.newCondition();
        new Thread(new Producer(lock, list, produce, consume )).start();
        new Thread(new Consumer(lock, list, produce, consume)).start();
    }
}
