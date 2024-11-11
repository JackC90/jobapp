package com.jc.jobapp.company.impl;

import java.util.List;
import java.util.Optional;
import com.jc.jobapp.company.Company;
import com.jc.jobapp.company.CompanyRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Service;

import com.jc.jobapp.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;
    private Long nextId = 1L;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(@RequestBody Company company) {
        company.setId(nextId++);
        companyRepository.save(company);
        return company;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse( null);
    }

    @Override
    public Company updateCompany(Long id, Company input) {
        Optional<Company> jobOptional = companyRepository.findById(id);

        if (jobOptional.isPresent()) {
            Company company = jobOptional.get();
            company.setName(input.getName());
            company.setDescription(input.getDescription());
            return company;
        }
        return null;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
}
