package com.spring.docon.mapper;


import com.spring.docon.model.Gender;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenderMapper {

    Gender entityToModel(GenderMapper genderMapper);
}
