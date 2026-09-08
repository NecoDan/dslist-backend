package com.devsuperior.dslist.core.usecase.picpay_challenge;

import com.devsuperior.dslist.core.domain.picpay_challenge.TransactionPicPay;
import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;
import com.devsuperior.dslist.core.domain.picpay_challenge.TypeUserPicPay;
import com.devsuperior.dslist.core.ports.picpay_challenge.AuthorizationPicPayPort;
import com.devsuperior.dslist.core.ports.picpay_challenge.NotificationPicPayPort;
import com.devsuperior.dslist.core.ports.picpay_challenge.TransactionPicPayPort;
import com.devsuperior.dslist.core.ports.picpay_challenge.UserPicPayPort;
import com.devsuperior.dslist.core.usecase.picpay_challenge.input.TransactionPicPayInput;
import com.devsuperior.dslist.core.usecase.picpay_challenge.output.TransactionPicPayOutput;
import com.devsuperior.dslist.exceptions.EntityCreateFailedException;
import com.devsuperior.dslist.exceptions.TransactionPicPayNotAuthorizedExcpetion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionPicPayCreateUseCaseImpl implements TransactionPicPayCreateUseCase {

    private final TransactionPicPayPort transactionPicPayPort;
    private final UserPicPayPort userPicPayPort;
    private final BalanceManagerPicPayUserCase balanceManagerPicPayUserCase;
    private final NotificationPicPayPort notificationPicPayPort;
    private final AuthorizationPicPayPort authorizationPicPayPort;

    @Override
    public TransactionPicPayOutput createTransaction(TransactionPicPayInput input) {
        log.info("Iniciando processo de criação de transação. Dados da transação: {}", input);

        try {
            var userSender = this.userPicPayPort.findUserById(input.senderId());
            var userReceiver = this.userPicPayPort.findUserById(input.receiverId());

            final var transactionAmountValue = input.value();
            validateSendUserData(userSender, transactionAmountValue);

            if (!isAuthorizedTransaction(userSender, transactionAmountValue)) {
                log.warn("Transação não autorizada para o usuário: {}. Valor da transação: {}", userSender.getId(), transactionAmountValue);
                throw new TransactionPicPayNotAuthorizedExcpetion("Transação não autorizada!");
            }

            var transaction = TransactionPicPay.builder()
                    .amount(transactionAmountValue)
                    .receiver(userReceiver)
                    .sender(userSender)
                    .createdAt(LocalDateTime.now())
                    .build();

            transactionPicPayPort.createTransaction(transaction);
            updateUsersAndSendNotification(userSender, transactionAmountValue, userReceiver);

            return TransactionPicPayOutput.buildFrom(transaction);
        } catch (Exception e) {
            log.error("Falha ao criar transação. Erro: {}", e.getMessage(), e);
            throw new EntityCreateFailedException("Falhar ao criar transação. Erro: " + e.getMessage());
        }
    }

    private void updateUsersAndSendNotification(UserPicPay userPicPaySender,
                                                BigDecimal transactionAmountValue,
                                                UserPicPay userPicPayReceiver) {

        balanceManagerPicPayUserCase.updateSenderUserBalance(userPicPaySender, transactionAmountValue);
        balanceManagerPicPayUserCase.updateReceiverUserBalance(userPicPayReceiver, transactionAmountValue);

        notificationPicPayPort.sendNotification(userPicPaySender, "Transação realizada com sucesso.");
        notificationPicPayPort.sendNotification(userPicPayReceiver, "Você recebeu uma transferência.");
    }

    private void validateSendUserData(UserPicPay userPicPaySender,
                                      BigDecimal transactionAmountValue) {

        if (userPicPaySender.getUserType() == TypeUserPicPay.MERCHANT) {
            throw new TransactionPicPayNotAuthorizedExcpetion("Usuário do tipo LOJISTA não possui autorização para realizar à transação.");
        }

        if (userPicPaySender.getBalance().compareTo(transactionAmountValue) < BigDecimal.ZERO.doubleValue()) {
            throw new TransactionPicPayNotAuthorizedExcpetion("Saldo insuficiente!");
        }
    }

    public boolean isAuthorizedTransaction(UserPicPay userPicPaySender,
                                           BigDecimal transactionAmountValue) {
        final var authorizationDTO = authorizationPicPayPort.getAuthorizationTransactionBy()
                .orElseThrow(() ->
                        new InvalidDataAccessApiUsageException("Falha ao obter dados autorização da transação API PicPay."));

        return authorizationDTO.isAuthorization();
    }
}
