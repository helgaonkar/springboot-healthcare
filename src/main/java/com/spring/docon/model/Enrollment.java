package com.spring.docon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    @JsonIgnore
    private UUID uuid;

    @JsonIgnore
    private Duration expiry;
}
