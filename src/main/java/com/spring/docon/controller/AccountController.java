package com.spring.docon.controller;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.model.Account;
import com.spring.docon.model.CreatePassword;
import com.spring.docon.response.AccountValidateResponse;
import com.spring.docon.response.PasswordResponse;
import com.spring.docon.service.AccountService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin(originPatterns = "*")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @PatchMapping(value = "/enrollments/{enrollmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PasswordResponse> createPassword(@PathVariable UUID enrollmentId, @RequestBody CreatePassword createPassword) {
        PasswordResponse passwordResponse = accountService.createPassword(enrollmentId, createPassword);
        return new ResponseEntity<>(passwordResponse, HttpStatus.OK);
    }



    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @PostMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountValidateResponse> validatePassword1(@RequestBody Account account) {
        AccountValidateResponse accountValidateResponse = accountService.passwordValidate(account);
        return new ResponseEntity<>(accountValidateResponse, HttpStatus.OK);
    }


}
