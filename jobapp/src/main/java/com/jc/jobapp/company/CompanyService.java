package com.jc.jobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company createJob(Company job);
    Company getJobById(Long id);
    Company updateJob(Long id, Company input);
    boolean deleteJobById(Long id);
}
