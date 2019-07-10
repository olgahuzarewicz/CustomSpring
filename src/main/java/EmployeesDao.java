import employee.Employee;

import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeesDao implements EmployeesDaoInterface {

    @Override
    public void originalMethod(String s) {
        System.out.println("inside DAO: local storage thread: " + Thread.currentThread().getName() + " value: " + ThreadLocalEntityManager.getThreadLocalValue());
        System.out.println(s);
    }

    @Override
    public List<Employee> getEmployeeList() {
        String queryStr = "select emp from Employee emp";
        System.out.println("inside DAO: local storage thread: " + Thread.currentThread().getName() + " value: " + ThreadLocalEntityManager.getThreadLocalValue());
        TypedQuery<Employee> query = ThreadLocalEntityManager.getThreadLocalValue().createQuery(queryStr, Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return ThreadLocalEntityManager.getThreadLocalValue().find(Employee.class, empId);
    }

    @Override
    public void insertEmployee(Employee emp) {
        ThreadLocalEntityManager.getThreadLocalValue().persist(emp);
    }

    @Override
    public void deleteEmployee(Long empId) {
        Employee emp = ThreadLocalEntityManager.getThreadLocalValue().find(Employee.class, empId);
        if (ThreadLocalEntityManager.getThreadLocalValue().contains(emp)) {
            ThreadLocalEntityManager.getThreadLocalValue().remove(emp);
        }
    }
}
