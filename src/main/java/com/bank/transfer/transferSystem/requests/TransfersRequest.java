package com.bank.transfer.transferSystem.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
public class TransfersRequest {
    private String pin;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private BigDecimal amount;
    private String channel;
    private String receiverBankCode;
    private String memo;
    private String transferType;
}
