package com.spring.docon.mapper;

import com.spring.docon.entity.PrefixEntity;
import com.spring.docon.model.Prefix;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrefixMapper {

    Prefix entityToModel(PrefixEntity prefixEntity);
}
