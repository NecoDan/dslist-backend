package com.devsuperior.dslist.picpay_challenge.biz;

import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.dto.external.AuthorizationDTO;
import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionDTO;
import com.devsuperior.dslist.picpay_challenge.entities.transaction.TransactionEntity;
import com.devsuperior.dslist.picpay_challenge.ports.AuthorizationPicPayPort;
import com.devsuperior.dslist.picpay_challenge.ports.TransactionPicPayPort;
import com.devsuperior.dslist.picpay_challenge.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service("TransactionPicPayBusiness")
@RequiredArgsConstructor
public class TransactionBusiness implements TransactionPicPayPort {

    private final UserBusiness userBusiness;
    private final TransactionRepository transactionRepository;
    private final AuthorizationPicPayPort authorizationPicPayPort;
    private final BalanceManagerBusiness balanceManagerBusiness;

    @Override
    public void createTransaction(TransactionDTO transactionDTO) throws Exception {
        User userSender = this.userBusiness.findUserById(transactionDTO.senderId());
        User userReceiver = this.userBusiness.findUserById(transactionDTO.receiverId());

        final var transactionValue = transactionDTO.value();
        userBusiness.validateTransaction(userSender, transactionValue);

        if (!isAuthorizedTransaction(userSender, transactionValue)) {
            throw new Exception("Transação não autorizada!");
        }

        this.transactionRepository.save(new TransactionEntity(
                        Transaction.builder()
                                .amount(transactionValue)
                                .receiver(userReceiver)
                                .sender(userSender)
                                .createdAt(LocalDateTime.now())
                                .build()
                )
        );

        balanceManagerBusiness.updateSenderUserBalance(userSender, transactionValue);
        balanceManagerBusiness.updateReceiverUserBalance(userReceiver, transactionValue);
    }

    public boolean isAuthorizedTransaction(User userSender, BigDecimal value) throws Exception {
        final AuthorizationDTO authorizationDTO = authorizationPicPayPort.getAuthorizationTransaction()
                .orElseThrow(() ->
                        new InvalidDataAccessApiUsageException("Falha ao obter dados autorização da transação API PicPay."));

        return authorizationDTO.isAuthorization();
    }
}
