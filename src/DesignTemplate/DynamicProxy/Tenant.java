package DesignTemplate.DynamicProxy;

public class Tenant {
    public static void main(String[] args) {
        //真实角色
        LandLord landLord = new LandLord();
        //代理角色: 现在还没有
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用程序处理角色来处理我们要调用的接口对象
        pih.setRent(landLord);
        Rent proxy = (Rent) pih.getProxy();//proxy是动态生成的代理角色
        proxy.rent();
    }
}
