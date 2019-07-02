public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ApplicationContext(Configuration.class);
        Service serviceA = context.getBean("mojaNazwa", Service.class);

        //System.out.println(serviceA.getValue());

    }
}
