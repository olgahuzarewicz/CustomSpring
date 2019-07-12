package configuration;

import annotation.Bean;
import companyDao.CompanyDao;
import companyDao.CompanyDaoImpl;
import companyDao2.CompanyDao2;
import companyDao2.CompanyDaoImpl2;
import employee.repository.EmployeeDao;
import employee.repository.EmployeeDaoImpl;
import serviceDao.ServiceDao;
import serviceDao.ServiceDaoImpl;

import javax.persistence.EntityManager;

public class Configuration {

    @Bean(name = "serviceDao")
    public ServiceDao serviceDao() {
        return new ServiceDaoImpl();
    }

    @Bean(name = "companyDao")
    public CompanyDao companyDAO() {
        return new CompanyDaoImpl();
    }

    @Bean(name = "companyDao2")
    public CompanyDao2 companyDAO2() {
        return new CompanyDaoImpl2();
    }

    @Bean(name = "employeesDao")
    public EmployeeDao employeesDao() {
        return new EmployeeDaoImpl();
    }

    @Bean(name = "entityManager")
    public EntityManager entityManager() {
        return new EntityManagerProxy();
    }
}