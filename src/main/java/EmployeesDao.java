import employee.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import service.TransactionCallback;

import java.util.Date;
import java.util.List;

public class EmployeesDao {

    public static void main() throws Exception {

        EmployeesDao empDao = new EmployeesDao();

        Employee emp = new Employee();
        emp.setEmpId(5L);
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
        TransactionCallback<List<Employee>> transaction = new TransactionCallback<>() {

            @Override
            public List<Employee> execute(Session session) {
                System.out.println("Get Employee List is here:");
                String queryStr = "select emp from Employee emp";
                Query query = session.createQuery(queryStr);
                List<Employee> empList = query.list();
                return empList;
            }
        };

        return TransactionTemplate.runInTransaction(transaction);
    }

    public Employee getEmployeeById(Long empId) {
        TransactionCallback<Employee> transaction = new TransactionCallback<>() {

            @Override
            public Employee execute(Session session) {
                System.out.println("Get By ID is here:");
                Employee emp = session.get(Employee.class, empId);
                System.out.println(emp);
                return emp;
            }
        };

        return TransactionTemplate.runInTransaction(transaction);
    }

    public void insertEmployee(Employee emp) {
        TransactionCallback<Employee> transaction = new TransactionCallback<>() {

            @Override
            public Employee execute(Session session) {
                System.out.println("Inserting is here:");
                session.save(emp);
                return null;
            }
        };

        TransactionTemplate.runInTransaction(transaction);
    }

    public void deleteEmployee(Employee emp) {
        TransactionCallback transaction = new TransactionCallback() {

            @Override
            public Employee execute(Session session) {
                System.out.println("Deleting is here:");
                session.delete(emp);
                return null;
            }
        };

        TransactionTemplate.runInTransaction(transaction);
    }
}
