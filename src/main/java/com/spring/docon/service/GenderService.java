package com.spring.docon.service;

import com.spring.docon.entity.GenderEntity;
import com.spring.docon.mapper.GenderMapper;
import com.spring.docon.repository.GenderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class GenderService {

    private final GenderMapper genderMapper;
    private final GenderRepository genderRepository;

    public GenderService(GenderMapper genderMapper, GenderRepository genderRepository) {
        this.genderMapper = genderMapper;
        this.genderRepository = genderRepository;
    }

    public List<GenderEntity> getGender() {
        log.info("Retrieving genders.");
        List<GenderEntity> gender = genderRepository.findAll();
        if (gender != null) {
            return gender;
        }
        throw new RuntimeException("Genders data not found");
    }
}
