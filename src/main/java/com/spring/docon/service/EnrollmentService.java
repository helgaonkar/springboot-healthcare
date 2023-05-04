package com.spring.docon.service;

import com.spring.docon.entity.EnrollmentEntity;
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.mapper.EnrollmentMapper;
import com.spring.docon.model.Enrollment;
import com.spring.docon.repository.EnrollmentRepository;
import com.spring.docon.repository.PatientRepository;
import com.spring.docon.response.EnrollmentResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Component
@Service
@Log4j2
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final EnrollmentMapper enrollmentMapper;

    private final PatientRepository patientRepository;

    private KafkaProducer kafkaProducer;

    private EnrollmentResponse enrollmentResponse = new EnrollmentResponse();

    private Enrollment enrollment = new Enrollment();

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper, PatientRepository patientRepository, KafkaProducer kafkaProducer) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.patientRepository = patientRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public EnrollmentResponse createEnrollment(Long patientId, Enrollment enrollment) {

        log.info("Retrieving patient details");

        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient id not found " + patientId));

        if (patientEntity != null) {
            EnrollmentEntity enrollmentEntity = enrollmentMapper.modelToEntity(enrollment);
            enrollmentEntity.setPatientEntity(patientEntity);

            Duration duration = Duration.ofDays(15);
            enrollmentEntity.setExpiry(duration);

            enrollmentRepository.save(enrollmentEntity);
            log.info("Enrollment details saved successfully.");

            enrollmentResponse.setEnrollmentID(enrollmentEntity.getEnrollmentId());
            log.info("Response id : {}", enrollmentResponse.getEnrollmentID());

            kafkaProducer.producer(enrollmentEntity);
        }
        return enrollmentResponse;
    }

/*    public Enrollment getEnrollment(UUID enrollmentId) {
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findByUUID(enrollmentId)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment id not found " + enrollmentId));

        Enrollment enrollment = new Enrollment();
        enrollment.setUuid(enrollmentEntity.getEnrollmentId());
        return enrollment;
    }*/

    public EnrollmentResponse getEnrollment(UUID enrollmentId) {
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findByUUID(enrollmentId);
        enrollmentResponse.setEnrollmentID(enrollmentEntity.getEnrollmentId());
        log.info("Enrollment id : ", enrollmentResponse.getEnrollmentID());
        return enrollmentResponse;
    }


}
