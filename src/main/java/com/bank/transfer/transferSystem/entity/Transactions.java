package com.bank.transfer.transferSystem.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
@Setter
@Getter
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @NotNull(message = "User ID is required")
    @Column(name = "user_id")
    private Long userId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @DecimalMin(value = "0.00", message = "Admin fee must be greater than or equal to 0")
    @Column(name = "admin_fee")
    private BigDecimal adminFee;

    @Column(name = "transaction_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp transactionDate;

    @Column(name = "transfer_id")
    private Long transferId;

    @NotBlank(message = "Transaction type is required")
    @Size(max = 20, message = "Transaction type must be at most 20 characters")
    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @NotBlank(message = "Channel is required")
    @Size(max = 50, message = "Channel must be at most 50 characters")
    @Column(name = "channel")
    private String channel;

    @Column(name = "memo", columnDefinition = "TEXT")
    private String memo;
}
