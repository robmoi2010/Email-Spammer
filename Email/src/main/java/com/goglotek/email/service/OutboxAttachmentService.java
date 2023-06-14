package com.goglotek.email.service;

import com.goglotek.email.model.OutboxAttachment;

public interface OutboxAttachmentService {
    OutboxAttachment save(OutboxAttachment outboxAttachment);
}
