package com.digi.app.transaction.entities;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    private String digiTransactionId;
    private String drAccountNo;
    private String crAccountNo;
    private String transactionDate;
    private String narrative;
    private String amount;
    private String transactionId;
    private String inputter;
    private String authorizer;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    public String getDigiTransactionId() {
        return digiTransactionId;
    }

    public void setDigiTransactionId(String digiTransactionId) {
        this.digiTransactionId = digiTransactionId;
    }

    public String getDrAccountNo() {
        return drAccountNo;
    }

    public void setDrAccountNo(String drAccountNo) {
        this.drAccountNo = drAccountNo;
    }

    public String getCrAccountNo() {
        return crAccountNo;
    }

    public void setCrAccountNo(String crAccountNo) {
        this.crAccountNo = crAccountNo;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getInputter() {
        return inputter;
    }

    public void setInputter(String inputter) {
        this.inputter = inputter;
    }

    public String getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(String authorizer) {
        this.authorizer = authorizer;
    }
}
