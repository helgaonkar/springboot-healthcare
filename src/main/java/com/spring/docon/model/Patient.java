package com.spring.docon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @JsonIgnore
    private String bloodGroup;

    @JsonIgnore
    private Integer height;

    @JsonIgnore
    private Integer weight;

    private Person person =new Person();
}
