package com.goglotek.email.service.impl;

import com.goglotek.email.model.Company;
import com.goglotek.email.repository.CompanyRepository;
import com.goglotek.email.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company save(Company c) {
        return companyRepository.save(c);
    }

    @Override
    public List<Company> findAllActive() {
        Company c = new Company();
        c.setActive(true);
        return companyRepository.findAll(Example.of(c, ExampleMatcher.matching().withIgnoreNullValues()));
    }
}
