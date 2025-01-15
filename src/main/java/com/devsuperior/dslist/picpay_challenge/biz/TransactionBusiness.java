package com.devsuperior.dslist.picpay_challenge.biz;

import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.dto.external.AuthorizationDTO;
import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionDTO;
import com.devsuperior.dslist.picpay_challenge.entities.transaction.TransactionEntity;
import com.devsuperior.dslist.picpay_challenge.ports.AuthorizationPicPayPort;
import com.devsuperior.dslist.picpay_challenge.ports.NotificationPicPayPort;
import com.devsuperior.dslist.picpay_challenge.ports.TransactionPicPayPort;
import com.devsuperior.dslist.picpay_challenge.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service("TransactionPicPayBusiness")
@RequiredArgsConstructor
public class TransactionBusiness implements TransactionPicPayPort {

    private final UserBusiness userBusiness;
    private final TransactionRepository transactionRepository;
    private final AuthorizationPicPayPort authorizationPicPayPort;
    private final BalanceManagerBusiness balanceManagerBusiness;
    private final NotificationPicPayPort notificationPicPayPort;

    @Override
    @Transactional
    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User userSender = this.userBusiness.findUserById(transactionDTO.senderId());
        User userReceiver = this.userBusiness.findUserById(transactionDTO.receiverId());

        final var transactionValue = transactionDTO.value();
        userBusiness.validateTransaction(userSender, transactionValue);

        if (!isAuthorizedTransaction(userSender, transactionValue)) {
            throw new Exception("Transação não autorizada!");
        }

        TransactionEntity transactionEntity = new TransactionEntity(
                Transaction.builder()
                        .amount(transactionValue)
                        .receiver(userReceiver)
                        .sender(userSender)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        transactionRepository.save(transactionEntity);
        balanceManagerBusiness.updateSenderUserBalance(userSender, transactionValue);
        balanceManagerBusiness.updateReceiverUserBalance(userReceiver, transactionValue);

        notificationPicPayPort.sendNotification(userSender, "Transação realizada com sucesso.");
        notificationPicPayPort.sendNotification(userReceiver, "Você recebeu uma transferência.");

        return new Transaction(transactionEntity);
    }

    @Override
    public List<Transaction> getAllTransactions(){
        return this.transactionRepository.findAll()
                .stream()
                .map(Transaction::new)
                .toList();
    }

    public boolean isAuthorizedTransaction(User userSender, BigDecimal value) throws Exception {
        final AuthorizationDTO authorizationDTO = authorizationPicPayPort.getAuthorizationTransactionBy()
                .orElseThrow(() ->
                        new InvalidDataAccessApiUsageException("Falha ao obter dados autorização da transação API PicPay."));

        return authorizationDTO.isAuthorization();
    }
}
