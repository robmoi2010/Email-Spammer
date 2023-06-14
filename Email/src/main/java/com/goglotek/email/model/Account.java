package com.goglotek.email.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "account_id")
  @SequenceGenerator(name = "account_sequence", sequenceName = "account_sq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
  private Long accountId;
  @Column(name = "created_date", nullable = false)
  private Date createdDate;
  @Column(name = "modified_date", nullable = false)
  private Date modifiedDate;
  @Column(name = "active", nullable = false)
  private Boolean active;
  @Column(name = "email_address", unique = true, nullable = false)
  private String emailAddress;

  @Column(name = "phone_number", nullable = true)
  private String[] phoneNumber;
  @Column(name = "outbox_count", nullable = false)
  private Long outboxCount;
  @ManyToOne
  @JoinColumn(name = "company_id", insertable = true, updatable = true, nullable = false, foreignKey = @ForeignKey(name = "FK_account_company"))
  private Company company;

  public String[] getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String[] phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Long getOutboxCount() {
    return outboxCount;
  }

  public void setOutboxCount(Long outboxCount) {
    this.outboxCount = outboxCount;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
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

  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
}
