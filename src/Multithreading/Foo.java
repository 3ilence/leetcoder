package Multithreading;

/**
 * @ClassName : FOO
 * @Author : Silence
 * @Date: 2022/12/2 22:35
 * @Description : 1114. 按序打印
 */
class Foo {
    private Object lock;
    private int flag;
    public Foo() {
        lock = new Object();
        flag = 0;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized(lock) {
            while (flag != 0) {
                lock.wait();//让出线程控制权
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag = 1;
            lock.notifyAll();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (flag != 1) {
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag = 2;
            lock.notifyAll();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (flag != 2) {
                lock.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        foo.first(new Runnable() {
            @Override
            public void run() {
                System.out.println("first");
            }
        });
        foo.second(new Runnable() {
            @Override
            public void run() {
                System.out.println("third");
            }
        });
        foo.third(new Runnable() {
            @Override
            public void run() {
                System.out.println("second");
            }
        });
    }
}
