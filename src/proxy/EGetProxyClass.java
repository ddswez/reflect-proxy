package proxy;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 获取Collection的代理类 InvocationHandler 基本写法
 * Created by lpf on 17/4/25.
 */
public class EGetProxyClass {
    public static void main(String[] args) throws Exception{
        ArrayList target = new ArrayList();
        Collection proxy3 = (Collection) getProxy(target, new MyAdvice());
        // 每调用一次add方法就会 调用一次一次handler的invoke方法
        proxy3.add("aaa");
        proxy3.add("bbb");
        proxy3.add("ccc");
        System.out.println(proxy3.size());
        System.out.println(proxy3.getClass().getName());
    }

    private static Object getProxy(final Object target, final Advice advice) {
        Object proxy3 = Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new InvocationHandler() {

                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            // 在次时 size 为0
                            //ArrayList target = new ArrayList();
                            advice.beforeMethod(method);
                            Object retVal = method.invoke(target, args);
                            advice.afterMethod(method);
                            return retVal;
                        }
                    }
                    );
        return proxy3;
    }

}
