package com.example.gateway.controller;

import com.example.gateway.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/gateway/accounts")
public class GatewayAccountController {

    private final String ACCOUNT_SERVICE_URL = "http://localhost:8081/api/accounts";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        ResponseEntity<AccountDto[]> response = restTemplate.getForEntity(ACCOUNT_SERVICE_URL, AccountDto[].class);
        List<AccountDto> accounts = Arrays.asList(response.getBody());
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{nik}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String nik) {
        ResponseEntity<AccountDto> response = restTemplate.getForEntity(ACCOUNT_SERVICE_URL + "/" + nik, AccountDto.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        ResponseEntity<AccountDto> response = restTemplate.postForEntity(ACCOUNT_SERVICE_URL, accountDto, AccountDto.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping("/{nik}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String nik, @RequestBody AccountDto accountDto) {
        HttpEntity<AccountDto> requestEntity = new HttpEntity<>(accountDto);
        ResponseEntity<AccountDto> response = restTemplate.exchange(
                ACCOUNT_SERVICE_URL + "/" + nik,
                HttpMethod.PUT,
                requestEntity,
                AccountDto.class
        );
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping("/{nik}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String nik) {
        restTemplate.delete(ACCOUNT_SERVICE_URL + "/" + nik);
        return ResponseEntity.noContent().build();
    }
}
