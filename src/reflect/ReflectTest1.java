package reflect;

/**
 * Created by lpf on 17/4/21.
 *
 * 反射就是将java的各个成分映射成java类
 *
 * 在java类的世界里有许多类比如String类、Integer类、StringBuffer类等等
 * 将这些类对象再抽象成一个Class类、包含类名、包名、接口、字段、方法等属性
 */
public class ReflectTest1 {
    public static void main(String[] args) throws Exception{
        String s1 = "OKCoin_Android";
        Class c1 = s1.getClass();
        Class c2 = String.class;
        Class c3 = Class.forName("java.lang.String");
        System.out.println(c1 == c2);   // true
        System.out.println(c2 == c3);   // true
    }
}
