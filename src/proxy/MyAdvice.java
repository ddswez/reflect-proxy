package proxy;

import java.lang.reflect.Method;

/**
 * Created by lpf on 17/4/26.
 */
public class MyAdvice implements Advice{
    long beginTime = 0;
    @Override
    public void beforeMethod(Method method) {
        beginTime = System.currentTimeMillis();
        System.out.println(method.getName() + "  MyAdvice begin method");
    }

    @Override
    public void afterMethod(Method method) {
        System.out.println(method.getName() + "  MyAdvice after method");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(method.getName() + " running time " + (endTime - beginTime));

    }
}
