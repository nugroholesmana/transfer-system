package com.bank.transfer.transferSystem.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReferenceNumberGenerator {
    private static final Logger logger = LoggerFactory.getLogger(ReferenceNumberGenerator.class);

    public static String generateReferenceNumber(String senderAccountNumber, String receiverAccountNumber) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String timestamp = dateFormat.format(new Date());

            String referenceNumber = "REF" + timestamp + "_" + senderAccountNumber + "_" + receiverAccountNumber;

            logger.info("Generated ReferenceNumber: {}", referenceNumber);

            return referenceNumber;
        } catch (Exception e) {
            logger.error("Error during referenceNumber generation", e);
            throw new RuntimeException("ReferenceNumber generation failed.");
        }
    }
}
