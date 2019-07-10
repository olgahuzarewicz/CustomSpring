import annotation.Bean;
import dao.CompanyDAO;
import dao.CompanyDAOImpl;
import org.mockito.Mockito;
import service.Service;
import service.ServiceImpl;

public class MockitoTestConfiguration {

    @Bean(name = "testService")
    public Service testService() {
        return Mockito.mock(ServiceImpl.class);
    }

    @Bean(name = "testCompanyDAO")
    public CompanyDAO testCompanyDAO() {
        return Mockito.mock(CompanyDAOImpl.class);
    }
}
