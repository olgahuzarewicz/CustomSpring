import configuration.Configuration;
import employee.Employee;

import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new ApplicationContext(Configuration.class);
        //Object bean1 = context.getBean("companyDAO");
        // Object bean = getBean(context);

        EmployeesDaoInterface employeesDao = new EmployeesDao();
        Handler handler = new Handler(employeesDao);
        EmployeesDaoInterface f = (EmployeesDaoInterface) Proxy.newProxyInstance(EmployeesDaoInterface.class.getClassLoader(),
                new Class[]{EmployeesDaoInterface.class},
                handler);

        List<Employee> empList = f.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        Employee emp = new Employee();
        emp.setName("Babu");
        emp.setDepartament("Security");
        emp.setJoinedOn(new Date());
        emp.setSalary(5250L);
        f.insertEmployee(emp);

        System.out.println("---------------------------");

        empList = f.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");

        Employee empObj = f.getEmployeeById(emp.getEmpId());
        System.out.println(empObj);

        System.out.println("---------------------------");
        f.deleteEmployee(emp.getEmpId());

        System.out.println("---------------------------");

        empList = f.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");
    }

    static Object getBean(ApplicationContext context) throws Exception {
        return context.getBean("service");
    }
}
