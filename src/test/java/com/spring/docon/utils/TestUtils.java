package com.spring.docon.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.entity.PersonEntity;
import lombok.extern.log4j.Log4j2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
public class TestUtils {
    public static final PatientEntity getPatientEntity(){
        AccountEntity accountEntity=new AccountEntity(1L,"rakesh@gmail.com","1234");
        PersonEntity personEntity =new PersonEntity(5L,"Mr","Rakesh","Chavan","2023-04-17",878784,"Male","ADMIN", accountEntity);
        PatientEntity patientEntity=new PatientEntity(1L,"AB+",123,100, personEntity);
        return patientEntity;
    }

    public static String readFile(String filePath) throws IOException {
        String fileData = null;
        try {
            fileData = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (FileNotFoundException e) {
            log.error("File Not Found={}", filePath);
        } catch (IOException e) {
            log.error("File Reading Issue={}", filePath);
        }
        return fileData;
    }

    public static <T> T getObjectFromJsonFile(String content, Class<T> objectToCast) throws IOException {
        T object = null;
        try {
            object = new ObjectMapper().readValue(content, objectToCast);
        } catch (Exception e) {
            log.error("File not found for respected path with exception message={}", e.getMessage());
        }
        return object;
    }
}
