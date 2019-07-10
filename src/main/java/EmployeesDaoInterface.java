import employee.Employee;

import java.util.List;

public interface EmployeesDaoInterface {

    void originalMethod(String s);

    List<Employee> getEmployeeList();

    Employee getEmployeeById(Long empId);

    void insertEmployee(Employee emp);

    void deleteEmployee(Long empId);
}
