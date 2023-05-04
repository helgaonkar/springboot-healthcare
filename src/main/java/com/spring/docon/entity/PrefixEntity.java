package com.spring.docon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prefix")
public class PrefixEntity {

    @Id
    @Column(name = "prefix")
    private String prefix;

    @Column(name = "abbreviation")
    private String abbreviation;


}
