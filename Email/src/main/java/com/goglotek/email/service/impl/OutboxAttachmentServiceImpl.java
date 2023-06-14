package com.goglotek.email.service.impl;


import com.goglotek.email.model.OutboxAttachment;
import com.goglotek.email.repository.OutboxAttachmentRepository;
import com.goglotek.email.service.OutboxAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutboxAttachmentServiceImpl implements OutboxAttachmentService {
    @Autowired
    private OutboxAttachmentRepository outboxAttachmentRepository;

    @Override
    public OutboxAttachment save(OutboxAttachment outboxAttachment) {
        return outboxAttachmentRepository.save(outboxAttachment);
    }
}
