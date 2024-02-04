package com.bank.transfer.transferSystem.response;

import com.bank.transfer.transferSystem.entity.Transfers;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferResponse {
    private String message;
    private Transfers data;

    public TransferResponse(String message, Transfers data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Transfers getData() {
        return data;
    }
}
