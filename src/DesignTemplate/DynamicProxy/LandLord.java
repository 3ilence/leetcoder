package DesignTemplate.DynamicProxy;

public class LandLord implements Rent{
    @Override
    public void rent() {
        System.out.println("房东想出租房子");
    }
}
