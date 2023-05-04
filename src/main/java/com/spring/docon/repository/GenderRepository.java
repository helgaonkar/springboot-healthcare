package com.spring.docon.repository;

import com.spring.docon.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, String> {
}
