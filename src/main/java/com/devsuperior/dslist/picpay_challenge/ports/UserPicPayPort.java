package com.devsuperior.dslist.picpay_challenge.ports;

import com.devsuperior.dslist.picpay_challenge.domain.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserPicPayPort {
    void validateTransaction(User userSender, BigDecimal amount) throws Exception;

    List<User> getAll();

    User findUserById(Long id);

    User createUser(User user);

    void saveUser(User user);
}
