package Multithreading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName : H2O
 * @Author : Silence
 * @Date: 2022/12/3 12:16
 * @Description : 1117. H2O 生成
 */
public class H2O {
    // Initialize hydrogen group (H20) limit.
    private Semaphore semaH = new Semaphore(2);
    // Initialize oxygen group (H2O) limit
    private Semaphore semaO = new Semaphore(1);

    // Initialize group count.
    private AtomicInteger groupCount = new AtomicInteger(0);

    private static final int GROUP_H_LIMIT = 2;
    private static final int GROUP_O_LIMIT = 1;
    private static final int GROUP_TOTAL_LIMIT = GROUP_H_LIMIT + GROUP_O_LIMIT;

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // Try to acquire hygrogen permit.
        this.semaH.acquire(1);

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();

        // Increment group molecule count.
        this.groupCount.incrementAndGet();

        resetIfNeeded();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        this.semaO.acquire(1);

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();

        this.groupCount.incrementAndGet();

        resetIfNeeded();
    }

    private void resetIfNeeded() {
        // If the current group is ready, release permits and try another.
        if (this.groupCount.compareAndSet(GROUP_TOTAL_LIMIT, 0)) {
            this.semaH.release(GROUP_H_LIMIT);
            this.semaO.release(GROUP_O_LIMIT);
        }
    }
}
