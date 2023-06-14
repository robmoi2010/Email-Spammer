package com.goglotek.email.service;

import com.goglotek.email.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CompanyService {
    public Company save(Company c);

    List<Company> findAllActive();
}
