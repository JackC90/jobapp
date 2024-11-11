package com.jc.jobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company createCompany(Company company);
    Company getCompanyById(Long id);
    Company updateCompany(Long id, Company input);
    boolean deleteCompanyById(Long id);
}
