package com.goglotek.email.service;

import com.goglotek.email.model.Account;
import com.goglotek.email.model.AccountTemplate;

import java.util.List;

public interface AccountTemplateService {
    List<AccountTemplate> findByAccount(Account account);
}
