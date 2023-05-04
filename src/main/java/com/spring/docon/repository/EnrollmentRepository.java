package com.spring.docon.repository;

import com.spring.docon.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, UUID> {

    @Query("SELECT e FROM EnrollmentEntity e WHERE e.enrollmentId = ?1")
    EnrollmentEntity findByUUID(UUID enrollmentId);


}
