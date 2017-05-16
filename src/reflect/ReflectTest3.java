package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Construct Method
 * Created by lpf on 17/4/21.
 */
public class ReflectTest3 {
    public static void main(String[] args) throws Exception {
        Constructor con = Test.class.getConstructor(String.class);
        Test result = (Test)con.newInstance("hello java1");
        Test result2 = (Test)con.newInstance("hello java2");
        result.objectMethod(result.getS()); // hello java1
        result2.objectMethod(result2.getS()); // hello java2

        Method method = Test.class.getMethod("staticMethod", String.class);
        method.invoke(null, "hello java3"); //hello java3 第一个参数传入了null表示不和对象绑定
        Method method2 = Test.class.getMethod("objectMethod", String.class);
        method2.invoke(result, "hello java4");//hello java4 传入对象result、因为要调用对象方法

        //method2.invoke(null, "hello java2");//因为是对象方法所以要报错

        Method method3 = Test.class.getDeclaredMethod("privateMethod", int.class);
        method3.setAccessible(true);
        method3.invoke(result, 0);
    }
}


class Test {
    private String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Test(String s) {
        this.s = s;
    }

    public Test() {
    }

    //静态方法不和某个对象绑定的
    public static void staticMethod(String s) {
        System.out.println(s.toString());
    }

    //对象方法是和对象绑定再一起的
    public void objectMethod(String s) {
        System.out.println(s.toString());
    }

    private void privateMethod(int i) {
        System.out.println(i);
    }
}