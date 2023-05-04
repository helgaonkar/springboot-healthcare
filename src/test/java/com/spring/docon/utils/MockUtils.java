package com.spring.docon.utils;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.entity.PersonEntity;

public class MockUtils {

    public static PatientEntity patientEntity(){
        PatientEntity patientEntity=new PatientEntity();
        patientEntity.setPatientId(1L);

        PersonEntity personEntity =new PersonEntity();
        personEntity.setFirstName("rajesh");
        personEntity.setLastName("chavan");

        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setEmailId("rk@gmail.com");
        patientEntity.setPersonEntity(personEntity);
        patientEntity.getPersonEntity().setAccountEntity(accountEntity);
        return patientEntity;
    }
}
