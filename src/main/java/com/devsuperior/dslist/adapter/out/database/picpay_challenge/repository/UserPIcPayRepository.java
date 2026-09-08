package com.devsuperior.dslist.adapter.out.database.picpay_challenge.repository;

import com.devsuperior.dslist.adapter.out.entities.picpay_challenge.UserPicPayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPIcPayRepository extends JpaRepository<UserPicPayEntity, Long> {
    Optional<UserPicPayEntity> findUserByDocument(String document);
}
