package com.goglotek.email.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "outbox_attachment")
public class OutboxAttachment implements Serializable {

    @Id
    @Column(name = "outbox_attachment_id")
    @SequenceGenerator(name = "outbox_attachment_sequence", sequenceName = "outbox_attachment_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outbox_attachment_sequence")
    private Long outboxAttachmentId;

    @ManyToOne
    @JoinColumn(name = "outbox_id", insertable = true, updatable = true, nullable = false, foreignKey = @ForeignKey(name = "FK_outboxattachment_outbox"))
    private Outbox outbox;
    @ManyToOne
    @JoinColumn(name = "attachment_id", insertable = true, updatable = true, nullable = false, foreignKey = @ForeignKey(name = "FK_outboxattachment_attachment"))
    private Attachment attachment;

    public Long getOutboxAttachmentId() {
        return outboxAttachmentId;
    }

    public void setOutboxAttachmentId(Long outboxAttachmentId) {
        this.outboxAttachmentId = outboxAttachmentId;
    }

    public Outbox getOutbox() {
        return outbox;
    }

    public void setOutbox(Outbox outbox) {
        this.outbox = outbox;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
