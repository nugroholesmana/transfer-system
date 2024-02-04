package com.bank.transfer.transferSystem.services;

import com.bank.transfer.transferSystem.entity.Transactions;
import com.bank.transfer.transferSystem.entity.Transfers;
import com.bank.transfer.transferSystem.entity.User;
import com.bank.transfer.transferSystem.repositories.TransactionRepository;
import com.bank.transfer.transferSystem.repositories.TransferRepository;
import com.bank.transfer.transferSystem.repositories.UserRepository;
import com.bank.transfer.transferSystem.requests.TransfersRequest;
import com.bank.transfer.transferSystem.response.TransferResponse;
import com.bank.transfer.transferSystem.utils.ReferenceNumberGenerator;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Service
public class TransferService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

    @Transactional
    public ResponseEntity<TransferResponse> transfer(TransfersRequest transferRequest) {


        // Validasi PIN
        User sender = userRepository.findByAccountNumberAndPin(transferRequest.getSenderAccountNumber(), transferRequest.getPin());
        if (sender == null) {
            logger.info("Invalid PIN: sender_account_number {}",transferRequest.getSenderAccountNumber());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TransferResponse("Invalid PIN", null));
        }

        // Validasi saldo cukup
        BigDecimal amount = transferRequest.getAmount();
        if (sender.getBalance().compareTo(amount) < 0) {
            logger.info("Insufficient balance: sender_account_number {}", transferRequest.getSenderAccountNumber());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransferResponse("Insufficient balance", null));
        }

        // Validasi Rekening Penerima Jika internal
        if (transferRequest.getTransferType().equals("Internal")){
            User receiver = userRepository.findByAccountNumber(transferRequest.getReceiverAccountNumber());
            if (receiver == null) {
                logger.info("Invalid Account Number: receiver_account_number {}", transferRequest.getReceiverAccountNumber());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TransferResponse("Invalid Account Number", null));

            }

            receiver.setBalance(receiver.getBalance().add(amount));
        }

        // Generate Reference Number
        String referenceNumber = ReferenceNumberGenerator.generateReferenceNumber(transferRequest.getSenderAccountNumber(), transferRequest.getReceiverAccountNumber());

        // Lakukan transfer
        Transfers transfer = new Transfers();
        transfer.setSenderAccountNumber(transferRequest.getSenderAccountNumber());
        transfer.setReceiverAccountNumber(transferRequest.getReceiverAccountNumber());
        transfer.setReceiverBankCode(transferRequest.getReceiverBankCode());
        transfer.setAmount(amount);
        transfer.setChannel(transferRequest.getChannel());
        transfer.setMemo(transferRequest.getMemo());
        transfer.setReferenceNumber(referenceNumber);
        transfer.setTransferDate(new Timestamp(System.currentTimeMillis()));
        transfer.setTransferType(transferRequest.getTransferType());

        transferRepository.save(transfer);

        // Kurangi saldo sender
        sender.setBalance(sender.getBalance().subtract(amount));
        userRepository.save(sender);

        // Buat transaksi
        Transactions transaction = new Transactions();
        transaction.setUserId(sender.getUserId());
        transaction.setAmount(amount);
        transaction.setAdminFee(BigDecimal.ZERO);
        transaction.setTransactionType("Transfer");
        transaction.setChannel(transferRequest.getChannel());
        transaction.setMemo(transferRequest.getMemo());
        transaction.setTransferId(transfer.getTransferId());
        transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));

        logger.info("Transfer successful. ReferenceNumber: {}", referenceNumber);

        transactionRepository.save(transaction);
        TransferResponse response = new TransferResponse("Transfer successful", transfer);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
