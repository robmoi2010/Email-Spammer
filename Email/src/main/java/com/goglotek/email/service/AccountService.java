package com.goglotek.email.service;

import com.goglotek.email.model.Account;
import com.goglotek.email.model.Company;

import java.util.List;

public interface AccountService {
    Account save(Account ac);

    List<Account> findByCompany(Company company);
}
