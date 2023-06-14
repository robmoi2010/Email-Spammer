package com.goglotek.email.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "outbox")
public class Outbox implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "outbox_id")
    @SequenceGenerator(name = "outbox_sequence", sequenceName = "outbox_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outbox_sequence")
    private Long outboxId;
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;
    @Column(name = "message", nullable = false, length = 100000)
    private String message;
    @Column(name = "send_successful", nullable = false)
    private Boolean sendSuccessful;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = true, updatable = true, nullable = false, foreignKey = @ForeignKey(name = "FK_outbox_account"))
    private Account account;

    public Boolean isSendSuccessful() {
        return sendSuccessful;
    }

    public void setSendSuccessful(Boolean sendSuccessful) {
        this.sendSuccessful = sendSuccessful;
    }

    public Long getOutboxId() {
        return outboxId;
    }

    public void setOutboxId(Long outboxId) {
        this.outboxId = outboxId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
