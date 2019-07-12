package serviceDao;

import annotation.Autowired;
import annotation.Qualifier;
import companyDao.CompanyDao;

public class ServiceDaoImpl implements ServiceDao {

    @Autowired
    @Qualifier(name = "companyDAO")
    private CompanyDao companyDAO;

    public ServiceDaoImpl() {
        System.out.println("created ServiceDaoImpl");
    }
}
