package com.bank.transfer.transferSystem.repositories;

import com.bank.transfer.transferSystem.entity.Transfers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfers, Long> {
}
