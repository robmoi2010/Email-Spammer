package com.goglotek.email.repository;

import com.goglotek.email.model.OutboxAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxAttachmentRepository extends JpaRepository<OutboxAttachment, Long> {
}
