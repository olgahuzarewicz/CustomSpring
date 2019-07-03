package dao;

import annotation.Autowired;
import annotation.Qualifier;
import dao2.CompanyDAO2;

public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    @Qualifier(name = "companyDAO2")
    private CompanyDAO2 companyDAO2;

    public CompanyDAOImpl() {
        System.out.println("created CompanyDAOImpl");
    }

}
