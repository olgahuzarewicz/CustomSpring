import employee.Employee;
import org.hibernate.Query;
import service.TransactionCallback;

import java.util.Date;
import java.util.List;

public class EmployeesDao {

    public static void main() {

        EmployeesDao empDao = new EmployeesDao();

        Employee emp = new Employee();
        emp.setName("Babu");
        emp.setDepartament("Security");
        emp.setJoinedOn(new Date());
        emp.setSalary(5250L);
        empDao.insertEmployee(emp);

        System.out.println("---------------------------");

        List<Employee> empList = empDao.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");

        Employee empObj = empDao.getEmployeeById(emp.getEmpId());
        System.out.println(empObj);

        System.out.println("---------------------------");
        empDao.deleteEmployee(empObj);

        System.out.println("---------------------------");

        empList = empDao.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");
    }

    public List<Employee> getEmployeeList() {
        return TransactionTemplate.runInTransaction((TransactionCallback<Object>) session -> {
            System.out.println("Get Employee List is here:");
            String queryStr = "select emp from Employee emp";
            Query query = session.createQuery(queryStr);
            List<Employee> empList = query.list();
            return empList;
        });
    }

    public Employee getEmployeeById(Long empId) {
        return TransactionTemplate.runInTransaction((TransactionCallback<Object>) session -> {
            System.out.println("Get By ID is here:");
            Employee emp = session.get(Employee.class, empId);
            System.out.println(emp);
            return emp;
        });
    }

    public void insertEmployee(Employee emp) {
        TransactionTemplate.runInTransaction((TransactionCallback<Object>) session -> {
            System.out.println("Inserting is here:");
            session.save(emp);
            return null;
        });
    }

    public void deleteEmployee(Employee emp) {
        TransactionTemplate.runInTransaction(session -> {
            System.out.println("Deleting is here:");
            session.delete(emp);
            return null;
        });
    }
}
