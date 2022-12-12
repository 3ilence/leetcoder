package ClassicProblem.Singleton;

/**
 * @ClassName : Singleton
 * @Author : Silence
 * @Date: 2022/12/12 20:43
 * @Description :
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {
        // 构造方法私有化的
    }

    // 这种写法不能防止反射攻击
    public Singleton getInstance() {
        if (singleton == null) {
            // 使用双重校验的方式，第一次判断null不要加锁，因为非常影响性能
            synchronized (singleton) { // synchronized(Singleton.class)
                // 第二次校验是有必要的，因为可能会有两个线程同时进入同步代码块，那么这两个线程进行实例化只是先后的问题
                if (singleton == null) {
                    singleton = new Singleton();
                    // 1.分配内存空间 2.初始化 3.将对象指向分配的内存空间
                }
            }
        }
        return singleton;
    }

    public static Singleton getInstanceByEnum() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    public enum SingletonEnum {
        INSTANCE;

        private Singleton singleton;

        private SingletonEnum() {
            singleton = new Singleton();
        }

        public Singleton getInstance() {
            return singleton;
        }
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstanceByEnum() == Singleton.getInstanceByEnum());
    }
}
