package com.spring.docon.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dob;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column
    private String role;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    public PersonEntity(long userId, String mr, String rakesh, String chavan, String s, int phoneNumber, String male, String admin, AccountEntity accountEntity) {
    }
}
