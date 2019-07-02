import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Configuration {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Bean{
        String name() default "";
    }

    @Bean(name="mojaNazwa")
    public Service cosTamCosTam() {
        return new ServiceImpl();
    }

    @Bean(name="mojaInnaNazwa")
    public Service inneCosTamCosTam() {
        return new ServiceImpl();
    }
}