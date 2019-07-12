package configuration;

import annotation.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class ApplicationContext {

    private Configuration config;

    private HashMap<String, Object> beans = new HashMap<>();

    public ApplicationContext(Class<?> cls) throws Exception {
        this.config = (Configuration) cls.getDeclaredConstructor().newInstance();
    }

    public <R> R getBean(String name) throws Exception {

        System.out.println("Searched annotation: " + name);

        if (beans.containsKey(name)) {
            return (R) beans.get(name);
        }

        Class cls = config.getClass();

        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {

            Method methodcall = cls.getDeclaredMethod(method.getName());

            Annotation[] annotations = methodcall.getDeclaredAnnotations();

            for (Annotation anno : annotations) {

                Bean annotation = (Bean) anno;

                if (annotation.name().equals(name)) {
                    R bean = (R) methodcall.invoke(config);

                    beans.put(name, bean);

                    Class beanCls = bean.getClass();

                    for (Field field : beanCls.getDeclaredFields()) {

                        Annotation[] fieldAnnotations = field.getDeclaredAnnotations();

                        if (fieldAnnotations != null) {

                            Object injectedBean = this.getBean(field.getName());

                            try {
                                field.setAccessible(true);
                                field.set(bean, injectedBean);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    for (Method beanMethod : beanCls.getDeclaredMethods()) {

                        Annotation[] beanMethodDeclaredAnnotations = beanMethod.getDeclaredAnnotations();

                        for (Annotation an : beanMethodDeclaredAnnotations) {

                            if (an.annotationType().getName().equals("annotation.Transactional")) {
                                Handler handler = new Handler(bean);

                                Class[] interfaces = beanCls.getInterfaces();
                                for (Class iface : interfaces) {
                                    Object f = Proxy.newProxyInstance(beanCls.getClassLoader(),
                                            new Class[]{iface},
                                            handler);
                                    return (R) f;
                                }
                            }
                        }
                    }
                    return bean;
                }
            }
        }
        beans.put(name, null);
        return null;
    }
}

