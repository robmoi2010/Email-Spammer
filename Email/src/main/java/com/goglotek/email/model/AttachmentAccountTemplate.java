package com.goglotek.email.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "attachment_account_template")
public class AttachmentAccountTemplate implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "attachment_account_template_id")
  @SequenceGenerator(name = "attachment_account_template_sequence", sequenceName = "attachment_account_template_sq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment_account_template_sequence")
  private Long attachmentAccountTemplateId;
  @ManyToOne
  @JoinColumn(name = "attachment_id", insertable = true, updatable = true, nullable = false, foreignKey = @ForeignKey(name = "FK_attachmentaccounttemplate_attachment"))
  private Attachment attachment;
  @ManyToOne
  @JoinColumn(name = "account_template_id", insertable = true, updatable = true, nullable = false, foreignKey = @ForeignKey(name = "FK_attachmentaccounttemplate_accounttemplate"))
  private AccountTemplate accountTemplate;

  public Long getAttachmentAccountTemplateId() {
    return attachmentAccountTemplateId;
  }

  public void setAttachmentAccountTemplateId(Long attachmentAccountTemplateId) {
    this.attachmentAccountTemplateId = attachmentAccountTemplateId;
  }

  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }

  public AccountTemplate getAccountTemplate() {
    return accountTemplate;
  }

  public void setAccountTemplate(AccountTemplate accountTemplate) {
    this.accountTemplate = accountTemplate;
  }
}
