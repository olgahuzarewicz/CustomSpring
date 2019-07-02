import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class ApplicationContext {

    private Configuration config;

    ApplicationContext(Class<?> cls) throws Exception {
        System.out.println(cls.getName());
            this.config = (Configuration) cls.getDeclaredConstructor().newInstance();
    }

    <R> R getBean(String name, Class<R> className) throws Exception {

        System.out.println("Searched annotation: " + name);

        Class cls = config.getClass();

        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println("Current method name: " + method.getName());

            Method methodcall = cls.getDeclaredMethod(method.getName(),
                    new Class[]{});

            Annotation[] annotations = methodcall.getDeclaredAnnotations();

            for (Annotation anno : annotations) {

                Configuration.Bean annotation = (Configuration.Bean) anno;
                System.out.println("key of annotation: "
                        + annotation.name());

                if (((Configuration.Bean) anno).name().equals(name)) {
                    if(methodcall.getReturnType().equals(className)){
                        return (R) methodcall.invoke(config);
                    }
                }

            }
        }

        return null;
    }
}

