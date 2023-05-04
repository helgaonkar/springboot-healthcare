package com.spring.docon.service;

import com.spring.docon.entity.GenderEntity;
import com.spring.docon.entity.PrefixEntity;
import com.spring.docon.mapper.PrefixMapper;
import com.spring.docon.repository.PatientRepository;
import com.spring.docon.repository.PrefixRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrefixService {

    private final PrefixRepository prefixRepository;
    private final PrefixMapper prefixMapper;

    public PrefixService(PrefixRepository prefixRepository, PrefixMapper prefixMapper) {

        this.prefixRepository = prefixRepository;
        this.prefixMapper = prefixMapper;
    }

    public List<PrefixEntity> getPrefix() {
        List<PrefixEntity> prefix = prefixRepository.findAll();
        if(prefix != null){
            return prefix;
        }
        throw  new RuntimeException("Prefix data not found");
    }

}
