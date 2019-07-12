package employee.repository;

import annotation.Transactional;
import employee.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Employee> getEmployeeList() {
        String queryStr = "select emp from Employee emp";
        TypedQuery<Employee> query = entityManager.createQuery(queryStr, Employee.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Long empId) {
        return entityManager.find(Employee.class, empId);
    }

    @Override
    @Transactional
    public void insertEmployee(Employee emp) {
        entityManager.persist(emp);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long empId) {
        Employee emp = entityManager.find(Employee.class, empId);
        if (entityManager.contains(emp)) {
            entityManager.remove(emp);
        }
    }

    @Override
    public void test() {
        System.out.println("This should be called");
    }
}
