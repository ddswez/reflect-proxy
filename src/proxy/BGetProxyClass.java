package proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * 获取Collection的代理类对象
 * Created by lpf on 17/4/25.
 */
public class BGetProxyClass {
    public static void main(String[] args) throws Exception{
        Class clazz = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);

        // 创建instance object
        Constructor constructor = clazz.getConstructor(InvocationHandler.class);

        class MyInvocationHandler implements InvocationHandler {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        }

        Collection proxy1 = (Collection) constructor.newInstance(new MyInvocationHandler());

        // 结果为null 其实是proxy1.toString()返回的null 如果对象为null proxy1.toString()应该是报空指针
        System.out.println(proxy1.toString());

        // 调用没有返回值的方法成功
        proxy1.clear();
        // 有返回值 调用报错
        //proxy1.size();

    }
}
