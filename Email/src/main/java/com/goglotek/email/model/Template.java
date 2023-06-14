package com.goglotek.email.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "template")
public class Template implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "template_id")
    @SequenceGenerator(name = "template_sequence", sequenceName = "template_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "template_sequence")
    private Long templateId;
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @Column(name = "active", nullable = false)
    private Boolean active;
    @Column(name = "template", nullable = false, length = 100000)
    private String template;

    @Column(name = "subject", nullable = false)
    private String subject;
    @ManyToMany
    @JoinTable(name = "account_template", joinColumns = @JoinColumn(name = "template_id", referencedColumnName = "template_id"), inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account_id"))
    private List<Account> accounts;

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
