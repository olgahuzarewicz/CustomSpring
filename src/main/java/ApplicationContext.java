import annotation.Bean;
import configuration.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

class ApplicationContext {

    private Configuration config;

    private HashMap<String, Object> beans = new HashMap<>();

    public ApplicationContext(Class<?> cls) throws Exception {
//        System.out.println(cls.getName());
        this.config = (Configuration) cls.getDeclaredConstructor().newInstance();
    }

    public <R> R getBean(String name) throws Exception {

//        System.out.println("Searched annotation: " + name);

        if (beans.containsKey(name)) {
            return (R) beans.get(name);
        }

        Class cls = config.getClass();

        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {
//            System.out.println("Current method name: " + method.getName());

            Method methodcall = cls.getDeclaredMethod(method.getName());

            Annotation[] annotations = methodcall.getDeclaredAnnotations();

            for (Annotation anno : annotations) {

                Bean annotation = (Bean) anno;
//                System.out.println("key of annotation: "
//                        + annotation.name());

                if (annotation.name().equals(name)) {
                    // if(methodcall.getReturnType().equals(className)){
                    R bean = (R) methodcall.invoke(config);
                    beans.put(name, bean);

                    // tu mam stworzony obiekt
                    // i tu trzeba cos wstrzyknac

                    // biore sobie beana i sprawdzam jakie ma pola
                    // szukam adnotacji i wypelniam pola z getBean()

                    Class beanCls = bean.getClass();

                    for (Field field : beanCls.getDeclaredFields()) {
//                            System.out.println("filedName: " + field.getName());

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

                    return bean;
                }
                //}

            }
        }
        beans.put(name, null);
        return null;
    }
}

