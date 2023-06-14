package com.goglotek.email.service.impl;

import com.goglotek.email.model.Outbox;
import com.goglotek.email.repository.OutboxRepository;
import com.goglotek.email.service.OutboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutboxServiceImpl implements OutboxService {
    @Autowired
    private OutboxRepository outboxRepository;

    @Override
    public Outbox save(Outbox outbox) {
        return outboxRepository.save(outbox);
    }
}
