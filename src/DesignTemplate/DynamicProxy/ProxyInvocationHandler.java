package DesignTemplate.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用这个类自动生成代理类
 */
public class ProxyInvocationHandler implements InvocationHandler {


    /**
     * 被代理的接口
     */
    private Rent rent;

    /**
     * 初始化
     * @param rent
     */
    public void setRent(Rent rent) {
        this.rent = rent;
    }

    /**
     * 生成得到代理类
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }



    /**
     * 处理代理实例并返回结果
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质就是使用反射机制实现
        seeHouse();
        Object result = method.invoke(rent, args);
        fee();
        return result;
    }

    /**
     * 看房子
     */
    public void seeHouse() {
        System.out.println("中介带看房子");
    }

    public void fee() {
        System.out.println("收中介费");
    }
}
