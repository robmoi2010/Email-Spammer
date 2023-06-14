package com.goglotek.email.service.impl;

import com.goglotek.email.model.Account;
import com.goglotek.email.model.Company;
import com.goglotek.email.repository.AccountRepository;
import com.goglotek.email.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account ac) {
        return accountRepository.save(ac);
    }

    @Override
    public List<Account> findByCompany(Company company) {
        Account ac = new Account();
        ac.setCompany(company);
        return accountRepository.findAll(Example.of(ac, ExampleMatcher.matching().withIgnoreNullValues()));
    }
}
