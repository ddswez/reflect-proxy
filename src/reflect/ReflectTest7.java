package reflect;

import java.lang.annotation.Annotation;

/**
 * Created by lpf on 17/4/21.
 * 注解
 */

@MyAnnotation(name = "haha")
public class ReflectTest7 {
    public static void main(String[] args) throws Exception{
        Class clazz = ReflectTest7.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation)annotation;
                System.out.println(myAnnotation);
                System.out.println("name : " + myAnnotation.name());
            }
        }
    }
}

