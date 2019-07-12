import annotation.Bean;
import companyDao.CompanyDao;
import companyDao.CompanyDaoImpl;
import org.mockito.Mockito;
import serviceDao.ServiceDao;
import serviceDao.ServiceDaoImpl;

public class MockitoTestConfiguration {

    @Bean(name = "testService")
    public ServiceDao testService() {
        return Mockito.mock(ServiceDaoImpl.class);
    }

    @Bean(name = "testCompanyDAO")
    public CompanyDao testCompanyDAO() {
        return Mockito.mock(CompanyDaoImpl.class);
    }
}
