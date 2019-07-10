import employee.Employee;
import org.junit.Assert;
import org.junit.Test;
import service.TransactionCallback;

import javax.persistence.EntityManager;
import java.util.List;

public class ThreadLocalStorageTest {

    public static EmployeesDaoProxy employeesDaoProxy;
    public static EmployeesDao employeesDao;
    public static List<Employee> empList1;
    public static List<Employee> empList2;

    @Test
    public void getEmployeeListOneThread() {
        // given
        employeesDaoProxy = new EmployeesDaoProxy();

        // when
        empList1 = employeesDaoProxy.getEmployeeList();

        // then
        Assert.assertEquals(8, empList1.size());
    }

    @Test
    public void getEmployeeListTwoThreads() throws InterruptedException {
        // given
        employeesDaoProxy = null;
        employeesDao = null;
        empList1 = null;
        empList2 = null;

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("thread 0");
                System.out.println("here in thread 0 " + Thread.currentThread().getName());
                employeesDaoProxy = new EmployeesDaoProxy();
                empList1 = employeesDaoProxy.getEmployeeList();
            }
        };


        // when
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread 1");
                System.out.println("here in thread 1 " + Thread.currentThread().getName());

                TransactionCallback<List<Employee>> transaction = new TransactionCallback<>() {

                    @Override
                    public List<Employee> execute(EntityManager em) {
                        employeesDao = new EmployeesDao();
                        System.out.println("inside DAO Proxy: local storage thread: " + Thread.currentThread().getName() + " value: " + ThreadLocalEntityManager.getThreadLocalValue());
                        return employeesDao.getEmployeeList();
                    }
                };

                TransactionTemplate.runInTransaction(transaction);


                try {
                    employeesDao = new EmployeesDao();
                    empList2 = employeesDao.getEmployeeList();
                } catch (NullPointerException e) {
                    System.out.println("NullPointerException");
                }
            }
        };
        thread.start();
        thread.join();

        thread1.start();
        thread1.join();

        // then
        System.out.println("done");
        System.out.println("empList1: " + empList1);
        System.out.println("empList2: " + empList2);

        Assert.assertEquals(8, empList1.size());
        Assert.assertEquals(null, empList2);
    }
}