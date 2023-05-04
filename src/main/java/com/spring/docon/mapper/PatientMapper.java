package com.spring.docon.mapper;

import com.spring.docon.entity.PatientEntity;
import com.spring.docon.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(source = "person", target = "personEntity")
    @Mapping(source = "person.account", target = "personEntity.accountEntity")
    PatientEntity modelToEntity(Patient patient);

//    @Mapping(source = "personEntity", target = "person")
//    @Mapping(source = "personEntity.accountEntity", target = "person.account")
    Patient entityToModel(Optional<PatientEntity> patientEntity);
}
