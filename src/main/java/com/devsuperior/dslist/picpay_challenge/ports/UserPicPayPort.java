package com.devsuperior.dslist.picpay_challenge.ports;

import com.devsuperior.dslist.picpay_challenge.domain.User;

import java.math.BigDecimal;

public interface UserPicPayPort {
    void validateTransaction(User userSender, BigDecimal amount) throws Exception;

    User findUserById(Long id) throws Exception;

    void saveUser(User user);
}
