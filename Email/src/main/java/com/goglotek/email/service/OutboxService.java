package com.goglotek.email.service;

import com.goglotek.email.model.Outbox;
import org.springframework.stereotype.Service;

public interface OutboxService {
    Outbox save(Outbox outbox);
}
