package com.devsuperior.dslist.adapter.out.database.picpay_challenge.repository;

import com.devsuperior.dslist.adapter.out.entities.picpay_challenge.TransactionPicPayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionPicPayRepository extends JpaRepository<TransactionPicPayEntity, Long> {
}
