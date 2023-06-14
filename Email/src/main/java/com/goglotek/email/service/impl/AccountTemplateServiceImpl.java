package com.goglotek.email.service.impl;

import com.goglotek.email.model.Account;
import com.goglotek.email.model.AccountTemplate;
import com.goglotek.email.model.Template;
import com.goglotek.email.repository.AccountTemplateRepository;
import com.goglotek.email.service.AccountTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTemplateServiceImpl implements AccountTemplateService {
    @Autowired
    private AccountTemplateRepository accountTemplateRepository;

    @Override
    public List<AccountTemplate> findByAccount(Account account) {
        AccountTemplate accountTemplate = new AccountTemplate();
        Template t = new Template();
        t.setActive(true);
        accountTemplate.setAccount(account);
        accountTemplate.setTemplate(t);
        return accountTemplateRepository.findAll(Example.of(accountTemplate, ExampleMatcher.matching().withIgnoreNullValues()));
    }
}
