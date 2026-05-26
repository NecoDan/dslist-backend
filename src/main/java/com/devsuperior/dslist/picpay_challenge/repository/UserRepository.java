package com.devsuperior.dslist.picpay_challenge.repository;

import com.devsuperior.dslist.picpay_challenge.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userPicPayRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByDocument(String document);
}
