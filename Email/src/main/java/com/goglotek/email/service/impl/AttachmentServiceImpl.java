package com.goglotek.email.service.impl;

import com.goglotek.email.model.AccountTemplate;
import com.goglotek.email.model.Attachment;
import com.goglotek.email.model.Template;
import com.goglotek.email.repository.AttachmentRepository;
import com.goglotek.email.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;


    @Override
    public Attachment save(Attachment at) {
        return attachmentRepository.save(at);
    }
}
