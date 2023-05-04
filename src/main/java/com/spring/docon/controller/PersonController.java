package com.spring.docon.controller;

import com.spring.docon.model.Account;
import com.spring.docon.model.Person;
import com.spring.docon.response.PersonResponse;
import com.spring.docon.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/persons")
    public ResponseEntity<PersonResponse> addUser(@RequestBody Person person) {
        PersonResponse personResponse = personService.addUser(person);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/accounts/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId){
        Account account= personService.getAccounts(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);

    }
}
