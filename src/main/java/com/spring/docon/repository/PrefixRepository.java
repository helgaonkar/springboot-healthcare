package com.spring.docon.repository;

import com.spring.docon.entity.PrefixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrefixRepository extends JpaRepository<PrefixEntity, String> {
}
