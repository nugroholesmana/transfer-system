package com.bank.transfer.transferSystem.controllers;

import com.bank.transfer.transferSystem.entity.Transfers;
import com.bank.transfer.transferSystem.requests.TransfersRequest;
import com.bank.transfer.transferSystem.response.TransferResponse;
import com.bank.transfer.transferSystem.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferResponse> performTransfer(@RequestBody TransfersRequest transferRequest) {
        ResponseEntity<TransferResponse> responseEntity = transferService.transfer(transferRequest);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.OK).body(responseEntity.getBody());
        } else if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseEntity.getBody());
        } else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEntity.getBody());
        }

        // Jika terjadi situasi lain, respons 500 Internal Server Error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
