package com.goglotek.email.service;

import com.goglotek.email.model.AccountTemplate;
import com.goglotek.email.model.Attachment;
import com.goglotek.email.model.AttachmentAccountTemplate;
import java.util.List;

public interface AttachmentAccountTemplateService {

  List<AttachmentAccountTemplate> findByAccountTemplate(AccountTemplate accountTemplate);
}
