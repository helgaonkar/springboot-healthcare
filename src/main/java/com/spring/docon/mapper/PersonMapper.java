package com.spring.docon.mapper;

import com.spring.docon.entity.PersonEntity;
import com.spring.docon.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(source = "account", target = "accountEntity")
    PersonEntity modelToEntity(Person person);
}
