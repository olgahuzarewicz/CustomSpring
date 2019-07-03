package service;

import annotation.Autowired;
import annotation.Qualifier;
import dao.CompanyDAO;

public class ServiceImpl implements Service {

    @Autowired
    @Qualifier(name = "companyDAO")
    private CompanyDAO companyDAO;

    public ServiceImpl() {
        System.out.println("created ServiceImpl");
    }
}
