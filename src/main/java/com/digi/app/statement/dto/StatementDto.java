package com.digi.app.statement.dto;

public class StatementDto {
    private  String accountNumber;
    private String accountName ;
    private String bookingDateEn;
    private String bookingDateNp;
    private String valueDateEn;
    private String transactionId;
    private String digiTransactionId ;
    private String narrative;
    private String debitAmount;
    private String  creditAmount;
    private String  balance;
    private String transactionType;
    private String chequeNo;
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBookingDateEn() {
        return bookingDateEn;
    }

    public void setBookingDateEn(String bookingDateEn) {
        this.bookingDateEn = bookingDateEn;
    }

    public String getBookingDateNp() {
        return bookingDateNp;
    }

    public void setBookingDateNp(String bookingDateNp) {
        this.bookingDateNp = bookingDateNp;
    }

    public String getValueDateEn() {
        return valueDateEn;
    }

    public void setValueDateEn(String valueDateEn) {
        this.valueDateEn = valueDateEn;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDigiTransactionId() {
        return digiTransactionId;
    }

    public void setDigiTransactionId(String digiTransactionId) {
        this.digiTransactionId = digiTransactionId;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
