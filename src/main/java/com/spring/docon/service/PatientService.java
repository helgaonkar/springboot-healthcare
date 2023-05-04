package com.spring.docon.service;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.mapper.PatientMapper;
import com.spring.docon.model.Enrollment;
import com.spring.docon.model.Patient;
import com.spring.docon.repository.PatientRepository;
import com.spring.docon.response.PatientResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@Log4j2
public class PatientService {

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    private final EnrollmentService enrollmentService;

    private Patient patient = new Patient();

    private final Enrollment enrollment = new Enrollment();

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper, EnrollmentService enrollmentService) {
        this.patientMapper = patientMapper;
        this.patientRepository = patientRepository;
        this.enrollmentService = enrollmentService;
    }

    public PatientResponse addPatient(Patient patient) {
        log.info("Adding patient details.");
        PatientEntity patientEntity = patientMapper.modelToEntity(patient);
        patientRepository.save(patientEntity);

        log.info("Patient details saved successfully.");

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setId(patientEntity.getPatientId());
        patientResponse.setMessage("Patient details saved successfully");
        log.info("Response id : {}", patientResponse.getId());

        enrollmentService.createEnrollment(patientEntity.getPatientId(), enrollment);
        log.info("Create enrollment method has been called.");

        return patientResponse;
    }

    public Patient getPatient(Long patientId) {

        log.info("Finding patient details of patient id {}",patientId);

        Optional<PatientEntity> patientEntity = Optional.ofNullable(patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient details not found for patient id " + patientId)));

        if (patientEntity.isPresent()) {

            patient = patientMapper.entityToModel(patientEntity);
            log.info("Retrieving patient details for the patient id {}", patientId);
        }
        return patient;
    }
}
