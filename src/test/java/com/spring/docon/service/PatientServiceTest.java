package com.spring.docon.service;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.EnrollmentEntity;
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.entity.PersonEntity;
import com.spring.docon.mapper.PatientMapper;
import com.spring.docon.model.Patient;
import com.spring.docon.model.Person;
import com.spring.docon.repository.PatientRepository;
import com.spring.docon.response.PatientResponse;
import com.spring.docon.utils.MockUtils;
import com.spring.docon.utils.TestUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@Log4j2
class PatientServiceTest {

    @InjectMocks
    private PatientService sut;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @Mock
    private EnrollmentService enrollmentService;

    private Patient patient;

    private Person person =new Person();

    private PersonEntity personEntity =new PersonEntity();

    private AccountEntity accountEntity;

    private PatientEntity patientEntity;

    private EnrollmentEntity enrollmentEntity=new EnrollmentEntity();

    private PatientResponse patientResponse;

    private Patient patientFromJsonFile;

    private PatientEntity patientEntityFromJsonFile;

    @BeforeEach
    void setUp() throws IOException {
        log.info("Before each method called.");
        patientMapper= Mappers.getMapper(PatientMapper.class);
        ReflectionTestUtils.setField(sut,"patientMapper",patientMapper);

        patientFromJsonFile = TestUtils.getObjectFromJsonFile(TestUtils.readFile("src/test/resources/PatientEntity.json"), Patient.class);
    }

    @Test
    @DisplayName("Should add patient successfully")
    void shouldAddPatientSuccessfully() {
        log.info("Inside the shouldAddPatientSuccessfully()");
        patientEntity= MockUtils.patientEntity();

        Mockito.when(patientRepository.save(Mockito.any(PatientEntity.class))).thenReturn(patientEntity);
        log.info("Stubbing - Patient repository method called and patientEntity returned");

        patientResponse=sut.addPatient(patientFromJsonFile);
        log.info("Add patient method called of sut");

        Mockito.verify(patientRepository,Mockito.atLeastOnce()).save(Mockito.any());
        log.info("Verifying if patient repository method called at least once ");

        assertNotNull(patientResponse);
        assertEquals("rajesh", patientEntity.getPersonEntity().getFirstName());
    }


}

