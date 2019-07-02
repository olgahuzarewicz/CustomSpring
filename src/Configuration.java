import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Configuration {

    @Bean(name = "name1")
    public Service method1() {
        return new ServiceImpl();
    }

    @Bean(name = "name2")
    public Service method2() {
        return new ServiceImpl();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Bean {
        String name() default "";
    }
}