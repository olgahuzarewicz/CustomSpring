public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ApplicationContext(Configuration.class);
        Service serviceA = context.getBean("name1", Service.class);
    }
}
