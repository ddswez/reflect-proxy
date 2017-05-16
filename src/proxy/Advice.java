package proxy;

import java.lang.reflect.Method;

/**
 * Created by lpf on 17/4/26.
 */
public interface Advice  {

    void beforeMethod(Method method);
    void afterMethod(Method method);
}
