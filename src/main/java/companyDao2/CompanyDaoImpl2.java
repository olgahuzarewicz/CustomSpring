package companyDao2;

import annotation.Transactional;

public class CompanyDaoImpl2 implements CompanyDao2 {
    public CompanyDaoImpl2() {
        System.out.println("created CompanyDaoImpl2");
    }


    @Transactional
    @Override
    public void test() {
        System.out.println("test CompanyDaoImpl2");
    }
}
