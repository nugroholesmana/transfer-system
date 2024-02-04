package com.bank.transfer.transferSystem.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long userId;

    @NotBlank(message = "NIK is required")
    @Size(max = 50, message = "NIK must be at most 50 characters")
    private String nik;

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be at most 100 characters")
    private String fullName;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    @Size(max = 15, message = "Phone number must be at most 15 characters")
    private String phoneNumber;

    @Size(min = 6, max = 6, message = "PIN must be exactly 6 characters")
    @JsonIgnore
    private String pin;

    @Size(max = 20, message = "Account number must be at most 20 characters")
    private String accountNumber;

    private BigDecimal balance;
}
