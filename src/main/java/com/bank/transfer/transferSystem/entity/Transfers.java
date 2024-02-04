package com.bank.transfer.transferSystem.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transfers")
@Setter
@Getter
public class Transfers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    @JsonIgnore
    private Long transferId;

    @NotBlank(message = "Reference number is required")
    @Size(max = 50, message = "Reference number must be at most 50 characters")
    @Column(name = "reference_number", unique = true)
    private String referenceNumber;

    @NotBlank(message = "Sender account number is required")
    @Size(max = 20, message = "Sender account number must be at most 20 characters")
    @Column(name = "sender_account_number")
    private String senderAccountNumber;

    @NotBlank(message = "Receiver account number is required")
    @Size(max = 20, message = "Receiver account number must be at most 20 characters")
    @Column(name = "receiver_account_number")
    private String receiverAccountNumber;

    @NotBlank(message = "Receiver bank code is required")
    @Size(max = 10, message = "Receiver bank code must be at most 10 characters")
    @Column(name = "receiver_bank_code")
    private String receiverBankCode;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transfer_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp transferDate;

    @NotBlank(message = "Channel is required")
    @Size(max = 50, message = "Channel must be at most 50 characters")
    @Column(name = "channel")
    private String channel;

    @Column(name = "memo", columnDefinition = "TEXT")
    private String memo;

    @NotBlank(message = "Transfer type is required")
    @Size(max = 20, message = "Transfer type must be at most 20 characters")
    @Column(name = "transfer_type", nullable = false)
    private String transferType;
}
