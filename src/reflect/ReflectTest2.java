package reflect;

import java.lang.reflect.Field;

/**
 * Filed
 * Created by lpf on 17/4/21.
 */
public class ReflectTest2 {
    public static void main(String[] args) throws Exception{
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3,4);
        Class clazz = p1.getClass();

        // 必须是 public
        Field[] fields = clazz.getFields();
        System.out.println("fields.length = " + fields.length);
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getType());
            System.out.println(fields[i].get(p1));
            System.out.println(fields[i].get(p2));
        }
        System.out.println("----------------");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // 不加  declaredField.setAccessible(true); 报异常
            // Exception in thread "main" java.lang.IllegalAccessException:
            // Class reflect.ReflectTest2 can not access a member of class reflect.Point with modifiers "private"
            /**
             *  setAccessible() 是其基类AccessibleObject 的方法,AccessibleObject 类是 Field、Method 和
             *  Constructor 对象的基类。它提供了将反射的对象标记为在使用时取消默认 Java 语言访问控制检查的能力。
             *  对于公共成员、默认（打包）访问成员、受保护成员和私有成员，在分别使用 Field、Method 或 Constructor
             *  对象来设置或获取字段、调用方法，或者创建和初始化类的新实例的时候，会执行访问检查。
             */
            declaredField.setAccessible(true);
            System.out.println(declaredField.getType());
            System.out.println(declaredField.get(p1));
            System.out.println(declaredField.get(p2));
        }
    }
}

/*
fields.length = 1
int
2
4
----------------
int
1
3
int
2
4
*/


