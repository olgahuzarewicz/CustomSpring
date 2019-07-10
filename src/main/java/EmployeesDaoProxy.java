import employee.Employee;
import service.TransactionCallback;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeesDaoProxy implements EmployeesDaoInterface {

    private EmployeesDao employeesDao;

    @Override
    public void originalMethod(String s) {
        System.out.println(s);
    }

    @Override
    public List<Employee> getEmployeeList() {
        TransactionCallback<List<Employee>> transaction = new TransactionCallback<>() {

            @Override
            public List<Employee> execute(EntityManager em) {
                employeesDao = new EmployeesDao();
                System.out.println("inside DAO Proxy: local storage thread: " + Thread.currentThread().getName() + " value: " + ThreadLocalEntityManager.getThreadLocalValue());
                return employeesDao.getEmployeeList();
            }
        };

        return TransactionTemplate.runInTransaction(transaction);
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        TransactionCallback<Employee> transaction = new TransactionCallback<>() {

            @Override
            public Employee execute(EntityManager em) {
                employeesDao = new EmployeesDao();
                return employeesDao.getEmployeeById(empId);
            }
        };

        return TransactionTemplate.runInTransaction(transaction);
    }

    @Override
    public void insertEmployee(Employee emp) {
        TransactionCallback<Employee> transaction = new TransactionCallback<>() {

            @Override
            public Employee execute(EntityManager em) {
                employeesDao = new EmployeesDao();
                employeesDao.insertEmployee(emp);
                return emp;
            }
        };

        TransactionTemplate.runInTransaction(transaction);
    }

    @Override
    public void deleteEmployee(Long empId) {
        TransactionCallback transaction = new TransactionCallback() {

            @Override
            public Employee execute(EntityManager em) {
                employeesDao = new EmployeesDao();
                employeesDao.deleteEmployee(empId);
                return null;
            }
        };

        TransactionTemplate.runInTransaction(transaction);
    }

}
