package com.example.account.service;

import com.example.account.entity.Account;
import com.example.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(String nik) {
        return accountRepository.findById(nik);
    }

    public Account createAccount(Account account) {
        LocalDateTime now = LocalDateTime.now();
        if (account.getDateCreated() == null) {
            account.setDateCreated(now);
        }

        // Generate account number formatted as 14 digits yyyyMMddHHmmss
        String generatedAccountNumber = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        account.setAccountNumber(generatedAccountNumber);

        return accountRepository.save(account);
    }

    public Account updateAccount(String nik, Account accountDetails) {
        Optional<Account> accountOptional = accountRepository.findById(nik);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setAccountNumber(accountDetails.getAccountNumber());
            account.setCustomerName(accountDetails.getCustomerName());
            account.setBalance(accountDetails.getBalance());
            // Intentionally not updating DATE_CREATED
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found with NIK: " + nik);
        }
    }

    public void deleteAccount(String nik) {
        accountRepository.deleteById(nik);
    }
}
