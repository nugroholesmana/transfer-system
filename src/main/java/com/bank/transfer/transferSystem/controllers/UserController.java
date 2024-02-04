package com.bank.transfer.transferSystem.controllers;

import com.bank.transfer.transferSystem.entity.User;
import com.bank.transfer.transferSystem.repositories.UserRepository;
import javax.validation.Valid;

import com.bank.transfer.transferSystem.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserResponse> insertUser(@Valid @RequestBody User user, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {
                String errorMessages = bindingResult.getAllErrors()
                        .stream()
                        .map(error -> error.getDefaultMessage())
                        .collect(Collectors.joining("; "));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(errorMessages, null));
            }

            // Lakukan validasi unik nik dan accountNumber
            if (userRepository.findByNik(user.getNik()) != null) {
                logger.warn("NIK {} already exists", user.getNik());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new UserResponse("NIK already exists", null));
            }

            if (user.getAccountNumber() != null && userRepository.findByAccountNumber(user.getAccountNumber()) != null) {
                logger.warn("Account number {} already exists", user.getAccountNumber());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new UserResponse("Account number already exists", null));

            }

            // Lakukan operasi-insert
            userRepository.save(user);

            logger.info("User inserted successfully. NIK: {}", user.getNik());

            UserResponse response = new UserResponse("User inserted successfully", user);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error during user insertion", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new UserResponse("Error during user insertion", null));
        }
    }
}
