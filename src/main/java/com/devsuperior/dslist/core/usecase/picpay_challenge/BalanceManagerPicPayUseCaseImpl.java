package com.devsuperior.dslist.core.usecase.picpay_challenge;

import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;
import com.devsuperior.dslist.core.ports.picpay_challenge.UserPicPayPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BalanceManagerPicPayUseCaseImpl implements BalanceManagerPicPayUserCase {

    private final UserPicPayPort userPicPayPort;

    @Override
    public void updateSenderUserBalance(UserPicPay userPicPaySender, BigDecimal transactionValue) {

        validateTransactionUser(userPicPaySender, "enviador");
        validateTransactionValue(transactionValue, "enviado");

        userPicPaySender.setBalance(userPicPaySender.getBalance().subtract(transactionValue));
        userPicPayPort.saveUser(userPicPaySender);
    }

    @Override
    public void updateReceiverUserBalance(UserPicPay userPicPayReceiver, BigDecimal transactionValue) {

        validateTransactionUser(userPicPayReceiver, "recebedor");
        validateTransactionValue(transactionValue, "recebido");

        userPicPayReceiver.setBalance(userPicPayReceiver.getBalance().add(transactionValue));
        userPicPayPort.saveUser(userPicPayReceiver);
    }

    private void validateTransactionUser(UserPicPay userPicPay, String messageContent) {
        if (Objects.isNull(userPicPay))
            throw new IllegalArgumentException(String.format("Usuário %s da transação inválido!", messageContent));
    }

    private void validateTransactionValue(BigDecimal transactionValue, String messageContent) {
        if (Objects.isNull(transactionValue))
            throw new IllegalArgumentException(String.format("Valor %s da transação inválido!", messageContent));
    }
}
