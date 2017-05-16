package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by lpf on 17/4/21.
 *
 * 获取方法参数泛型
 */
public class ReflectTest6 {
    public static void main(String[] args) throws Exception{
        /**
         * 通过反射获取泛型参数的类型
         * 获取Map内泛型的参数类型、上例的String,Integer
         * 说明、直接通过类对象没有办法直接获取、采用Method类对象提供的方法处理
         * Map<String,Integer> map = new HashMap<String,Integer>();
         *
          */

        Method method = ReflectTest6.class.getMethod("apply", Map.class);
        Type[] types = method.getGenericParameterTypes();
        ParameterizedType pType = (ParameterizedType)types[0];
        System.out.println(pType);
        System.out.println(pType.getRawType());
        System.out.println(pType.getActualTypeArguments()[0]); //返回的是数组、取第一个值
        System.out.println(pType.getActualTypeArguments()[1]);//取第二个值


        Type type = method.getGenericReturnType();
        ParameterizedType pt = (ParameterizedType)type;
        System.out.println(pt); System.out.println(pt.getRawType());
        System.out.println(pt.getActualTypeArguments()[0]);

        /*java.util.Map<java.lang.String, java.lang.Integer>
        interface java.util.Map
        class java.lang.String
        class java.lang.Integer
        java.util.Collection<java.lang.String>
        interface java.util.Collection
        class java.lang.String*/

    }

    public static Collection<String> apply(Map<String,Integer> map){
        Collection<String> list = new ArrayList<String>();
        list.add("java");
        return list;
    }
}
