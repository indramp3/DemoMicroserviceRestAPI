package com.example.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNT_MASTER")
public class Account {

    @Id
    @Column(name = "NIK", updatable = false, nullable = false)
    private String nik;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "DATE_CREATED", updatable = false)
    private LocalDateTime dateCreated;

    // Getters and Setters

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
