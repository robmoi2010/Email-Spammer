package com.goglotek.email.service.impl;

import com.goglotek.email.model.AccountTemplate;
import com.goglotek.email.model.AttachmentAccountTemplate;
import com.goglotek.email.repository.AttachmentAccountTemplateRepository;
import com.goglotek.email.service.AttachmentAccountTemplateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class AttachmentAccountTemplateServiceImpl implements AttachmentAccountTemplateService {

  @Autowired
  private AttachmentAccountTemplateRepository attachmentAccountTemplateRepository;

  @Override
  public List<AttachmentAccountTemplate> findByAccountTemplate(AccountTemplate accountTemplate) {
    AttachmentAccountTemplate aat = new AttachmentAccountTemplate();
    aat.setAccountTemplate(accountTemplate);
    return attachmentAccountTemplateRepository.findAll(
        Example.of(aat, ExampleMatcher.matching().withIgnoreNullValues()));
  }
}
