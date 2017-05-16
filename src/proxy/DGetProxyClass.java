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
public class DGetProxyClass {
    public static void main(String[] args) throws Exception{
        Class clazz = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);

        // 创建instance object
        Constructor constructor = clazz.getConstructor(InvocationHandler.class);

        Collection proxy2 = (Collection) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        // 调用没有返回值的方法成功
        proxy2.clear();
        // 有返回值 调用报错
//        proxy2.size();

        // 直接创建实例对象
        Collection proxy3 = (Collection) Proxy.newProxyInstance(
                Collection.class.getClassLoader(),
                new Class[]{Collection.class},
                new InvocationHandler() {
                    ArrayList target = new ArrayList();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 在次时 size 为0
                        //ArrayList target = new ArrayList();
                        long beginTime = System.currentTimeMillis();
                        Object retVal = method.invoke(target, args);
                        Thread.sleep(200);
                        long endTime = System.currentTimeMillis();
                        System.out.println(method.getName() + " running time " + (endTime - beginTime));
                        return retVal;
//                        return method.invoke(proxy, args);
                    }
                }
                );
        // 每调用一次add方法就会 调用一次一次handler的invoke方法
        proxy3.add("aaa");
        proxy3.add("bbb");
        proxy3.add("ccc");
        System.out.println(proxy3.size());
        System.out.println(proxy3.getClass().getName());
    }
    /**
     * add running time 203
     add running time 205
     add running time 202
     size running time 202
     0
     com.sun.proxy.$Proxy0



     Exception in thread "main" java.lang.ClassCastException: java.util.ArrayList cannot be cast to java.lang.Boolean
     add running time 203
     at com.sun.proxy.$Proxy0.add(Unknown Source)
     at proxy.DGetProxyClass.main(DGetProxyClass.java:55)
     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     at java.lang.reflect.Method.invoke(Method.java:498)
     at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
     */

    /*
    *  动态生成的类实现了Collection接口, 生成的类有Collection接口中的所有方法和一个
    *  接收了invocationHandler 参数的构造方法
    *  invocationHandler 对象要干什么用? 该方法内部的代码是什么样的? 实现了Collection接口
    *  中的各个方法的代码是怎样的? InvocationHandler接口中定义的invoke方法接受的三个参数什么用?
    *
    *  构造方法接收一个对象,一般情况下都是记住这个对象,并调用这个对象的方法使用,
    *  Collection中各个方法的调用都是调用了invoke方法,
    *
    *   程序调用objProxy.add("aaa")方法时, 涉及到三要输:
    *           objProxy对象
    *           add方法
    *           "aaa"参数
    *   传递到了InvocationHandler的invoke方法的三个参数
    *   如果在invoke方法中调用: method.invoke(proxy, args), 将出现死循环
    *
    *
    *
    *   代理对象调用add方法, --> invoke() -->目标对象执行add方法, -->
    *   代理对象的add方法收到invoke()返回的值
    *       Object obj = proxy3.add("aaa");
    *    也可以在调用目标对象的方法传递参数的时候进行筛选,
    *
    *    现在看调用有返回值的方法报了异常:  调用size方法时, 会调用到invoke方法, size要的是整数,获取的返回值是 null
    *
    *    分析为什么动态类的实例对象的 getClass()方法返回了正确结果?
    *       在代理实例上的 java.lang.Object 中声明的 hashCode、equals 或
    *       toString 方法的调用将按照与编码和指派接口方法调用相同的方式进行编码，
    *       并被指派到调用处理程序的 invoke 方法，如上所述。传递到 invoke 的 Method
    *       对象的声明类是 java.lang.Object。代理类不重写从 java.lang.Object
    *       继承的代理实例的其他公共方法，所以这些方法的调用行为与其对
    *       java.lang.Object 实例的操作一样。
    *
    *
    *    动态代理的工作原理:
    *       客户端调用代理, 代理构造方法接收一个handler对象,客户段调用代理的各个方法,
    *        代理的各个方法把调用请求传递给handler对象,调用其invoke方法, 这个handler对象又把请求分发
    *        给目标对象的相应方法.
    *        可以在handler的invoke方法中添加 打印日志, 计时等功能. 可封装成一个对象进行传递
    *
    *
    *
    *
    * */
}
