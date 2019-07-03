package configuration;

import annotation.Bean;
import dao.CompanyDAO;
import dao.CompanyDAOImpl;
import dao2.CompanyDAO2;
import dao2.CompanyDAOImpl2;
import service.Service;
import service.ServiceImpl;

public class Configuration {

    @Bean(name = "service")
    public Service service() {
        return new ServiceImpl();
    }

    @Bean(name = "companyDAO")
    public CompanyDAO companyDAO() {
        return new CompanyDAOImpl();
    }

    @Bean(name = "companyDAO2")
    public CompanyDAO2 companyDAO2() {
        return new CompanyDAOImpl2();
    }
}