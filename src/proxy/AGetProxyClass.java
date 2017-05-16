package proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * 获取Collection的代理类
 * Created by lpf on 17/4/25.
 */
public class AGetProxyClass {
    public static void main(String[] args) {
        Class clazz = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
//        Class clazz = Proxy.getProxyClass(Runnable.class.getClassLoader(), Runnable.class);
        String clazzName = clazz.getName();
        System.out.println(clazzName);
        System.out.println("---------------- get constructors --------------");
//        打印所以的构造方法以及参数
        Constructor[] constructs = clazz.getConstructors();
        for (Constructor constructor : constructs) {
            String constructName = constructor.getName();
            StringBuilder sBuilder = new StringBuilder(constructName);
            sBuilder.append('(');
            Class[] clazzTypes = constructor.getParameterTypes();
            for (Class clazzType : clazzTypes) {
                sBuilder.append(clazzType.getName()).append(',');
            }
            if (clazzTypes != null && clazzTypes.length > 0) {
                sBuilder.deleteCharAt(sBuilder.length() - 1);
            }
            sBuilder.append(')');
            System.out.println(sBuilder);
        }
//        打印这个接口所拥有的所有的方法以及他们的参数
        System.out.println("---------------- get methods --------------");
        Method[] clazzMethods = clazz.getMethods();
        for (Method clazzMethod : clazzMethods) {
            String methodName = clazzMethod.getName();
            StringBuilder sBilder = new StringBuilder(methodName);
            Class[] methodTypes = clazzMethod.getParameterTypes();
            sBilder.append('(');
            for (Class methodType : methodTypes) {
                sBilder.append(methodType.getName()).append(',');
            }
            if (methodTypes != null && methodTypes.length > 0) {
                sBilder.deleteCharAt(sBilder.length() - 1);
            }
            sBilder.append(')');
            System.out.println(sBilder);
        }
    }

   /* 结果：

    com.sun.proxy.$Proxy0
    ---------------- get constructors --------------
    com.sun.proxy.$Proxy0(java.lang.reflect.InvocationHandler)
    ---------------- get methods --------------
    add(java.lang.Object)
    remove(java.lang.Object)
    equals(java.lang.Object)
    toString()
    hashCode()
    clear()
    isEmpty()
    contains(java.lang.Object)
    size()
    toArray([Ljava.lang.Object;)
    toArray()
    iterator()
    spliterator()
    addAll(java.util.Collection)
    stream()
    forEach(java.util.function.Consumer)
    containsAll(java.util.Collection)
    removeAll(java.util.Collection)
    retainAll(java.util.Collection)
    removeIf(java.util.function.Predicate)
    parallelStream()
    isProxyClass(java.lang.Class)
    newProxyInstance(java.lang.ClassLoader,[Ljava.lang.Class;,java.lang.reflect.InvocationHandler)
    getInvocationHandler(java.lang.Object)
    getProxyClass(java.lang.ClassLoader,[Ljava.lang.Class;)
    wait(long,int)
    wait(long)
    wait()
    getClass()
    notify()
    notifyAll()*/
}
