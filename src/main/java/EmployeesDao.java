import employee.Employee;

import javax.persistence.Query;
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
        empDao.deleteEmployee(emp.getEmpId());

        System.out.println("---------------------------");

        empList = empDao.getEmployeeList();
        System.out.println("emp size: " + empList.size());
        empList.stream().forEach(System.out::println);

        System.out.println("---------------------------");
    }

    public List<Employee> getEmployeeList() {
        return TransactionTemplate.runInTransaction(em -> {
            System.out.println("Get Employee List is here:");
            String queryStr = "select emp from Employee emp";
            Query query = em.createQuery(queryStr);
            List<Employee> empList = query.getResultList();
            return empList;
        });
    }

    public Employee getEmployeeById(Long empId) {
        return TransactionTemplate.runInTransaction(em -> {
            System.out.println("Get By ID is here:");
            Employee emp = em.find(Employee.class, empId);
            System.out.println(emp);
            return emp;
        });
    }

    public void insertEmployee(Employee emp) {
        TransactionTemplate.runInTransaction(em -> {
            System.out.println("Inserting is here:");
            System.out.println(emp);
            em.persist(emp);
            return null;
        });
    }

    public void deleteEmployee(Long empId) {
        TransactionTemplate.runInTransaction(em -> {
            System.out.println("Deleting:");
            Employee emp = em.find(Employee.class, empId);
            System.out.println(emp);
            if (em.contains(emp)) {
                em.remove(emp);
            }
            return null;
        });
    }
}
