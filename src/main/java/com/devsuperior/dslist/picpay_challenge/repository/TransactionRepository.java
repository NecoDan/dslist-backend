package com.devsuperior.dslist.picpay_challenge.repository;

import com.devsuperior.dslist.picpay_challenge.entities.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
