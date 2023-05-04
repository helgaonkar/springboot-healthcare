package com.spring.docon.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @NotBlank(message = "Prefix should not be empty or null")
    private String prefix;

    @NotBlank(message = "First name should not be empty or null.")
    private String firstName;

    @NotBlank(message = "Last name should not be empty or null.")
    private String lastName;

    @NotBlank(message = "Phone number should not be empty or null.")
    private String phoneNumber;

    @NotBlank(message = "Date of birth should not be empty or null.")
    private LocalDate dob;

    @NotBlank(message = "Gender should not be empty or null.")
    private String gender;

    @NotBlank(message = "Role should not be empty or null.")
    private String role;

    private Account account =new Account();
}