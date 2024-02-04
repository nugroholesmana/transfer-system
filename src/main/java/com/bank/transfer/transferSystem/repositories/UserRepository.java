package com.bank.transfer.transferSystem.repositories;

import com.bank.transfer.transferSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNik(String nik);
    User findByAccountNumber(String accountNumber);

    User findByAccountNumberAndPin(String accountNumber, String pin);

}
