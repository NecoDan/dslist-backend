package com.devsuperior.dslist.core.usecase.picpay_challenge;

import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;

import java.math.BigDecimal;

public interface BalanceManagerPicPayUserCase {
    void updateSenderUserBalance(UserPicPay userPicPaySender, BigDecimal transactionValue);

    void updateReceiverUserBalance(UserPicPay userPicPayReceiver, BigDecimal transactionValue);
}
