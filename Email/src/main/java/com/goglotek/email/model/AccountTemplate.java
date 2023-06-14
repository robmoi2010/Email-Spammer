package com.goglotek.email.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_template")
public class AccountTemplate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "account_template_id")
    @SequenceGenerator(name = "account_template_sequence", sequenceName = "account_template_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_template_sequence")
    private Long accountTemplateId;
    @ManyToOne
    @JoinColumn(name = "template_id", insertable = true, updatable = true, nullable = false, foreignKey = @ForeignKey(name = "FK_accounttemplate_template"))
    private Template template;
    @ManyToOne
    @JoinColumn(name = "account_id", insertable = true, updatable = true, nullable = false, foreignKey = @ForeignKey(name = "FK_accounttemplate_account"))
    private Account account;

    public Long getAccountTemplateId() {
        return accountTemplateId;
    }

    public void setAccountTemplateId(Long accountTemplateId) {
        this.accountTemplateId = accountTemplateId;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
