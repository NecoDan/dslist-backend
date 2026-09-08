package com.devsuperior.dslist.core.ports.picpay_challenge;

import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;

import java.util.List;

public interface UserPicPayPort {

    List<UserPicPay> getAll();

    UserPicPay findUserById(Long id);

    UserPicPay createUser(UserPicPay userPicPay);

    void saveUser(UserPicPay userPicPay);
}
