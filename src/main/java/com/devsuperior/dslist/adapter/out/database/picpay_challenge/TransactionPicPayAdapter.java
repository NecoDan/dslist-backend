package com.devsuperior.dslist.adapter.out.database.picpay_challenge;

import com.devsuperior.dslist.adapter.out.database.picpay_challenge.repository.TransactionPicPayRepository;
import com.devsuperior.dslist.adapter.out.entities.picpay_challenge.TransactionPicPayEntity;
import com.devsuperior.dslist.core.domain.picpay_challenge.TransactionPicPay;
import com.devsuperior.dslist.core.ports.picpay_challenge.TransactionPicPayPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionPicPayAdapter implements TransactionPicPayPort {

    private final TransactionPicPayRepository transactionPicPayRepository;

    @Override
    @Transactional
    public void createTransaction(TransactionPicPay transactionPicPay) {
        log.info("PICPAY_CHALLENGE - Criando transação: {}", transactionPicPay);
        transactionPicPayRepository.save(new TransactionPicPayEntity(transactionPicPay));
    }

    @Override
    public List<TransactionPicPay> getAllTransactions() {
        log.info("PICPAY_CHALLENGE - Buscando todas as transações");

        return this.transactionPicPayRepository.findAll()
                .stream()
                .map(TransactionPicPay::new)
                .toList();
    }

    @Override
    public Optional<TransactionPicPay> getById(Long id) {
        log.info("PICPAY_CHALLENGE - Buscando transação por ID: {}", id);

        return transactionPicPayRepository.findById(id)
                .or(Optional::empty)
                .map(TransactionPicPay::new);
    }
}
