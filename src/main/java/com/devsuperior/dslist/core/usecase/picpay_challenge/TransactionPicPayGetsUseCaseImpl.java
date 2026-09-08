package com.devsuperior.dslist.core.usecase.picpay_challenge;

import com.devsuperior.dslist.core.domain.picpay_challenge.TransactionPicPay;
import com.devsuperior.dslist.core.ports.picpay_challenge.TransactionPicPayPort;
import com.devsuperior.dslist.core.usecase.picpay_challenge.output.TransactionPicPayOutput;
import com.devsuperior.dslist.exceptions.TransactionPicPayNotAuthorizedExcpetion;
import com.devsuperior.dslist.exceptions.TransactionPicPayNotFoundExcpetion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionPicPayGetsUseCaseImpl implements TransactionPicPayGetsUseCase {

    private final TransactionPicPayPort transactionPicPayPort;

    @Override
    public List<TransactionPicPayOutput> getAll() {
        log.info("Buscando todas as transações registradas e salvas.");
        final var listAll = transactionPicPayPort.getAllTransactions();

        if (CollectionUtils.isEmpty(listAll)) {
            throw new TransactionPicPayNotFoundExcpetion("Não foram encontradas transações salva(s) ou existente(s).");
        }

        return listAll
                .stream()
                .map(TransactionPicPayOutput::buildFrom)
                .toList();
    }

    @Override
    public List<TransactionPicPayOutput> getAllWithForEach() {
        log.info("Buscando todas as transações registradas no sistema utilizando forEach.");
        List<TransactionPicPayOutput> list = new ArrayList<>();

        transactionPicPayPort.getAllTransactions()
                .forEach(transaction -> list.add(TransactionPicPayOutput.buildFrom(transaction)));

        return list;
    }

    @Override
    public TransactionPicPayOutput getById(Long id) {
        log.info("Buscando transação pelo ID: {}", id);

        return TransactionPicPayOutput.buildFrom(
                transactionPicPayPort.getById(id)
                        .orElseThrow(() ->
                                new TransactionPicPayNotFoundExcpetion("Nenhuma transadção encontrada pelo ID: %s".formatted(id))
                        )
        );
    }
}
