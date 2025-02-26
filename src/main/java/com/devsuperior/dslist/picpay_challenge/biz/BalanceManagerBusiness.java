package com.devsuperior.dslist.picpay_challenge.biz;

import com.devsuperior.dslist.picpay_challenge.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service("BalanceManagerBusiness")
@RequiredArgsConstructor
public class BalanceManagerBusiness {

    private final UserBusiness userBusiness;

    public void updateSenderUserBalance(User userSender, BigDecimal transactionValue) {

        validateTransactionUser(userSender, "enviador");
        validateTransactionValue(transactionValue, "enviado");

        userSender.setBalance(userSender.getBalance().subtract(transactionValue));
        userBusiness.saveUser(userSender);
    }

    public void updateReceiverUserBalance(User userReceiver, BigDecimal transactionValue) {

        validateTransactionUser(userReceiver, "recebedor");
        validateTransactionValue(transactionValue, "recebido");

        userReceiver.setBalance(userReceiver.getBalance().add(transactionValue));
        userBusiness.saveUser(userReceiver);
    }

    private void validateTransactionUser(User user, String messageContent){
        if (Objects.isNull(user))
            throw new IllegalArgumentException(String.format("Usuário %s da transação inválido!", messageContent));
    }

    private void validateTransactionValue(BigDecimal transactionValue, String messageContent) {
        if (Objects.isNull(transactionValue))
            throw new IllegalArgumentException(String.format("Valor %s da transação inválido!", messageContent));
    }
}
