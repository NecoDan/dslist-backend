package com.devsuperior.dslist.picpay_challenge.service;

import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionDTO;
import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionResponseDTO;
import com.devsuperior.dslist.picpay_challenge.dto.request.TransactionRequestDTO;
import com.devsuperior.dslist.picpay_challenge.ports.TransactionPicPayPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionPicPayPort transactionPicPayPort;

    public List<TransactionResponseDTO> getAll() {
        return transactionPicPayPort.getAllTransactions()
                .stream()
                .map(TransactionResponseDTO::new)
                .toList();
    }

    public List<TransactionResponseDTO> getAllWithForEach() {
        List<TransactionResponseDTO> list = new ArrayList<>();

        transactionPicPayPort.getAllTransactions()
                .forEach(transaction -> {
                    list.add(new TransactionResponseDTO(transaction));
                });

        return list;
    }

    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionDTO) throws Exception {
        final Transaction transaction = transactionPicPayPort.createTransaction(
                new TransactionDTO(transactionDTO.getValue(), transactionDTO.getSenderId(), transactionDTO.getReceiverId())
        );

        return new TransactionResponseDTO(transaction).createMessageSucess();
    }

    public TransactionResponseDTO getById(Long id) {
        return new TransactionResponseDTO(transactionPicPayPort.getById(id));
    }
}
