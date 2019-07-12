package configuration;

import annotation.Transactional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Handler implements InvocationHandler {
    private final Object proxy;

    public Handler(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws IllegalArgumentException, InvocationTargetException, IllegalAccessException {

        Method[] methods = this.proxy.getClass().getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().equals(method.getName()) && !m.isAnnotationPresent(Transactional.class)) {
                return method.invoke(this.proxy, args);
            }
        }
        return TransactionTemplate.runInTransaction(em -> method.invoke(this.proxy, args));
    }
}
