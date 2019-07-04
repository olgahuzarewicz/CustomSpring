import configuration.Configuration;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ApplicationContext(Configuration.class);
        //Object bean1 = context.getBean("companyDAO");
        Object bean = getBean(context);

        EmployeesDao.main();
    }

    static Object getBean(ApplicationContext context) throws Exception {
        return context.getBean("service");
    }
}
