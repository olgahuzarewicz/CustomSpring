package companyDao;

import annotation.Autowired;
import annotation.Qualifier;
import companyDao2.CompanyDao2;

public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    @Qualifier(name = "companyDao2")
    private CompanyDao2 companyDao2;

    public CompanyDaoImpl() {
        System.out.println("created CompanyDaoImpl");
    }

}
