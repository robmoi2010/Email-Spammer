package com.goglotek.email.service.impl;

import com.goglotek.email.model.Account;
import com.goglotek.email.model.Template;
import com.goglotek.email.repository.TemplateRepository;
import com.goglotek.email.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    @Override
    public List<Template> findByAccount(Account account) {
        Template t = new Template();
        t.setAccounts(Arrays.asList(account));
        return templateRepository.findAll(Example.of(t, ExampleMatcher.matching().withIgnoreNullValues()));
    }
}
