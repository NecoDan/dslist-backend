package com.devsuperior.dslist.itau_v1_challenge.service;

import com.devsuperior.dslist.itau_v1_challenge.domain.TransactionItau;
import com.devsuperior.dslist.itau_v1_challenge.dto.internal.TransactionItauResponseDTO;
import com.devsuperior.dslist.itau_v1_challenge.dto.request.TransactionItauRequestDTO;
import com.devsuperior.dslist.itau_v1_challenge.ports.TransactionItauPort;
import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionDTO;
import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionResponseDTO;
import com.devsuperior.dslist.picpay_challenge.dto.request.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionItauService {

    private final TransactionItauPort transactionItauPort;

    public List<TransactionItauResponseDTO> getAll() {
        return transactionItauPort.getAllTransactionsInMemory()
                .stream()
                .map(TransactionItauResponseDTO::new)
                .toList();
    }

    public TransactionItauResponseDTO createTransaction(TransactionItauRequestDTO transactionDTO) {
        return new TransactionItauResponseDTO(
                transactionItauPort.createTransactionInMemory(
                        new TransactionItau(transactionDTO)
                )
        );
    }

    public TransactionItauResponseDTO getById(final String transactionId) {
        return new TransactionItauResponseDTO(
                transactionItauPort.getByIdInMemory(transactionId)
                        .orElseThrow(() ->
                                new IllegalStateException(String.format("Nenhuma transação encontrada por meio do id da transação %s.", transactionId))
                        )
        );
    }

    public String deleteById(final String transactionId) {
        //           transactionItauPort.deleteById();
        return StringUtils.EMPTY;
    }
}
