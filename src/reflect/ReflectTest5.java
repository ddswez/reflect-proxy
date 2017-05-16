package reflect;

import java.lang.reflect.Method;

/**
 * 根据指定的类名,类字段名和所对应的数据,得到该类的实例
 * Created by lpf on 17/4/21.
 */
public class ReflectTest5 {
    public static void main(String[] args) throws Exception {
        Base base = getJavaBean("reflect.Person1", "setUsername", "hellojava");
        Person1 p1 = (Person1) base;
        System.out.println("p1.username = " + p1.getUsername());
        Base base2 = getJavaBean("reflect.Person2", "setEmail", "hellojava@java.com");
        Person2 p2 = (Person2) base2;
        System.out.println("p2.email = " + p2.getEmail());
        //p1.username = hellojava
//        p2.email = hellojava@java.com

    }

    public static Base getJavaBean(String beanName, String setMethod, String setValue) {
        Base base = null; // 抽象基类
        try {
            Class cls = Class.forName(beanName); //传入的javabean类必须要有个无参数的构造方法public权限的
            base = (Base) cls.newInstance(); // 向上转型
            Class[] paraTypes = new Class[]{String.class}; //参数类型数组
            Method method = cls.getMethod(setMethod, paraTypes);
            method.invoke(base, setValue);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return base;
    }
}

abstract class Base { // 定义抽象基类
}

class Person1 extends Base {
    private String username;
    private String password;

    //通过字节码newInstance实例时候出现异常
    //因为定义了带两个参数的构造方法
   /* public Person1(String username, String password) { this.username = username;
    this.password = password;
    }*/


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class Person2 extends Base {
    private String realname;
    private String email;

    /*public Person2(String realname, String email) { this.realname = realname;
    this.email = email;
    }*/
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

/*java.lang.InstantiationException: reflect.Person1
        at java.lang.Class.newInstance(Class.java:427)
        at reflect.ReflectTest5.getJavaBean(ReflectTest5.java:23)
        at reflect.ReflectTest5.main(ReflectTest5.java:11)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
        Caused by: java.lang.NoSuchMethodException: reflect.Person1.<init>()
        at java.lang.Class.getConstructor0(Class.java:3082)
        at java.lang.Class.newInstance(Class.java:412)
        ... 7 more
        Exception in thread "main" java.lang.NullPointerException
        at reflect.ReflectTest5.main(ReflectTest5.java:13)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)*/
