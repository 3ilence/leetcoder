package ClassicProblem.Producerconsumer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : ClassicProblem.Producerconsumer.Producer
 * @Author : Silence
 * @Date: 2022/12/10 17:34
 * @Description :
 */
public class Producer implements Runnable {

    private BlockingQueue<Integer> queue;

    private ReentrantLock lock;
    private List<Integer> list;
    final private int MAX_LENGTH = 5;
    Condition produce;
    Condition consume;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public Producer(ReentrantLock lock, List list, Condition produce, Condition consume) {
        this.lock = lock;
        this.list = list;
        this.produce = produce;
        this.consume = consume;
    }

    @Override
    public void run() {
        //produceByBlockingQueue();
        while (true) {
            produceByLock();
        }
    }

    private void produceByBlockingQueue() {
        try {
            queue.put(new Random().nextInt(50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void produceByLock() {
        try {
            lock.lock();
            if (list.size() == MAX_LENGTH) {
                produce.await();//将该线程加入到produceCondition的等待队列中，并且释放锁
            }
            list.add(new Random().nextInt(50));
            consume.signalAll();//唤醒consumeCondition的等待队列中的所有线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
