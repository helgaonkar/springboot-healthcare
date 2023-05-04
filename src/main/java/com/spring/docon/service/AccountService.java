package com.spring.docon.service;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.EnrollmentEntity;
import com.spring.docon.model.Account;
import com.spring.docon.model.CreatePassword;
import com.spring.docon.repository.AccountRepository;
import com.spring.docon.repository.EnrollmentRepository;
import com.spring.docon.response.AccountValidateResponse;
import com.spring.docon.response.PasswordResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class AccountService {

    private final AccountRepository accountRepository;

    private final EnrollmentRepository enrollmentRepository;

    private AccountEntity accountEntity=new AccountEntity();

    private AccountValidateResponse accountValidateResponse=new AccountValidateResponse();
    private PasswordResponse passwordResponse = new PasswordResponse();

    public AccountService(AccountRepository accountRepository, EnrollmentRepository enrollmentRepository) {
        this.accountRepository = accountRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public PasswordResponse createPassword(UUID enrollmentId, CreatePassword createPassword) {
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findByUUID(enrollmentId);

        String email = enrollmentEntity.getPatientEntity().getPersonEntity().getAccountEntity().getEmailId();
        LocalDate dateOfBirth = enrollmentEntity.getPatientEntity().getPersonEntity().getDob();

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        LocalDate
        if (email.equals(createPassword.getEmailId()) && dateOfBirth.isEqual(createPassword.getDob())) {
            log.info("Inside the create password method");
            enrollmentEntity.getPatientEntity().getPersonEntity().getAccountEntity().setPassword(Base64.getEncoder().encodeToString(createPassword.getPassword().getBytes()));
            accountRepository.save(accountEntity);
        } else {
            throw new RuntimeException("Email id or Date of birth does not valid.");
        }
        passwordResponse.setMessage("Your password has been created successfully!");
        return passwordResponse;
    }



    public AccountValidateResponse passwordValidate(Account account) {

        Optional<AccountEntity> optionalAccountEntity= Optional.ofNullable(accountRepository.findByEmailId(account.getEmailId()).orElseThrow(() -> new RuntimeException("email id is not valid")));

        String email=optionalAccountEntity.get().getEmailId();
        String decodedPassword = new String(Base64.getDecoder().decode(optionalAccountEntity.get().getPassword()));

        if(email.equals(account.getEmailId()) && decodedPassword.equals(account.getPassword()))
        {
            accountValidateResponse.setMessage("Your login successfully!");
            accountValidateResponse.setId(optionalAccountEntity.get().getAccountId());
        }
        else {
            accountValidateResponse.setMessage("Email id or Password are not valid.");
            throw new RuntimeException("Email id or Password does not valid.");
        }
        return accountValidateResponse;

    }
}

