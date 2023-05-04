package com.spring.docon.service;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.PersonEntity;
import com.spring.docon.mapper.AccountMapper;
import com.spring.docon.mapper.PersonMapper;
import com.spring.docon.model.Account;
import com.spring.docon.model.Person;
import com.spring.docon.repository.AccountRepository;
import com.spring.docon.repository.PersonRepository;
import com.spring.docon.response.PersonResponse;
import com.spring.docon.util.PasswordGenerator;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PersonService {

    PersonEntity personEntity;

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    private final AccountRepository accountEntityRepository;

    private final AccountMapper accountMapper;

    private Account account = new Account();

    @Autowired
    private final KafkaTemplate<String, JSONObject> kafkaTemplate;

    @Value("${topic.name.producer}")
    private String topicName;

    @Autowired
    public PersonService(PersonMapper personMapper, PersonRepository personRepository, AccountRepository accountEntityRepository, AccountMapper accountMapper, KafkaTemplate<String, JSONObject> kafkaTemplate) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.accountEntityRepository = accountEntityRepository;
        this.accountMapper = accountMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public PersonResponse addUser(Person person) {
        personEntity = personMapper.modelToEntity(person);

        String randomPassword = PasswordGenerator.generateRandomPassword(8);
        log.info("Random password {}", randomPassword);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setPassword(randomPassword);
        accountEntity.setEmailId(person.getAccount().getEmailId());

        personEntity.setAccountEntity(accountEntity);
        accountEntityRepository.save(accountEntity);

        personRepository.save(personEntity);
        log.info("user details saved successfully");

        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(personEntity.getPersonId());

        if (personEntity.getAccountEntity().getAccountId() != null) {
            Long accountId = personEntity.getAccountEntity().getAccountId();
            log.info(accountId + " account id");
            getAccounts(accountId);
        }
        return personResponse;
    }

    public Account getAccounts(Long accountId) {
        AccountEntity accountEntity = accountEntityRepository.findById(personEntity.getAccountEntity().getAccountId())
                .orElseThrow(() -> new RuntimeException("Account id not found " + accountId));

        if (accountEntity != null) {
            account = accountMapper.entityToModel(accountEntity);
            log.info("Account details found");
        }
        return account;
    }
}
