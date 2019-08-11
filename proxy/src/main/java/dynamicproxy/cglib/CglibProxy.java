package dynamicproxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private static CglibProxy instance = new CglibProxy();

    public CglibProxy() {
    }

    public static CglibProxy getInstance() {
        return instance;
    }

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    public <T> T getProxy (Class<T> cls) {
        return (T) Enhancer.create(cls,this);
    }
}
