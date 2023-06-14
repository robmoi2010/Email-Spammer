package com.goglotek.email.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "attachment")
public class Attachment implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "attachment_id")
  @SequenceGenerator(name = "attachment_sequence", sequenceName = "attachment_sq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment_sequence")
  private Long attachmentId;
  @Column(name = "created_date", nullable = false)
  private Date createdDate;

  @Column(name = "modified_date", nullable = false)
  private Date modifiedDate;
  @Column(name = "file_path", nullable = false)
  private String filePath;

  @Column(name = "template", nullable = false)
  private Boolean template;

  public Boolean getTemplate() {
    return template;
  }

  public void setTemplate(Boolean template) {
    this.template = template;
  }

  public Long getAttachmentId() {
    return attachmentId;
  }

  public void setAttachmentId(Long attachmentId) {
    this.attachmentId = attachmentId;
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

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

}
