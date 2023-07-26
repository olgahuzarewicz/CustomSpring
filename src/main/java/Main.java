import configuration.ApplicationContext;
import configuration.Configuration;
import employee.entity.Employee;
import employee.repository.EmployeeDao;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new ApplicationContext(Configuration.class);

        EmployeeDao bean1 = context.getBean("employeesDao");

        List<Employee> empList = bean1.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        Employee emp = new Employee();
        emp.setName("Babu");
        emp.setDepartament("Security");
        emp.setJoinedOn(new Date());
        emp.setSalary(5250L);
        bean1.insertEmployee(emp);

        System.out.println("---------------------------");

        empList = bean1.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");

        Employee empObj = bean1.getEmployeeById(emp.getEmpId());
        System.out.println(empObj);

        System.out.println("---------------------------");
        bean1.deleteEmployee(emp.getEmpId());

        System.out.println("---------------------------");

        empList = bean1.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");

        bean1.test();
    }
}
