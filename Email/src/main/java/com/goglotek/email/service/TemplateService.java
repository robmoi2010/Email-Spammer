package com.goglotek.email.service;

import com.goglotek.email.model.Account;
import com.goglotek.email.model.Template;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TemplateService {
    List<Template> findByAccount(Account account);
}
