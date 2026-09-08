package com.devsuperior.dslist.adapter.in.http.controllers.picpay_challenge;

import com.devsuperior.dslist.core.usecase.picpay_challenge.TransactionPicPayCreateUseCase;
import com.devsuperior.dslist.core.usecase.picpay_challenge.TransactionPicPayGetsUseCase;
import com.devsuperior.dslist.core.usecase.picpay_challenge.input.TransactionPicPayInput;
import com.devsuperior.dslist.core.usecase.picpay_challenge.output.TransactionPicPayOutput;
import com.devsuperior.dslist.utils.logs.MdcUtils;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/picpay/transactions")
@RequiredArgsConstructor
@Hidden
@Slf4j
public class TransactionPicPayController {

    private final TransactionPicPayCreateUseCase transactionPicPayCreateUseCase;
    private final TransactionPicPayGetsUseCase transactionPicPayGetsUseCase;

    @GetMapping
    public ResponseEntity<List<TransactionPicPayOutput>> getAll() {
        try {
            MdcUtils.putTransactionIdRandom();
            log.info("PICPAY_CHALLENGE - Inicializando a busca de todas as transações salva(s) & registrada(s).");

            return ResponseEntity.ok(transactionPicPayGetsUseCase.getAll());
        } finally {
            MdcUtils.clear();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransactionPicPayOutput> findById(@PathVariable Long id) {
        try {
            MdcUtils.putTransactionIdRandom();
            log.info("PICPAY_CHALLENGE - Inicializando a busca de transação salva por ID: {}.", id);

            return ResponseEntity.ok(transactionPicPayGetsUseCase.getById(id));
        } finally {
            MdcUtils.clear();
        }
    }

    @PostMapping
    public ResponseEntity<TransactionPicPayOutput> create(@RequestBody TransactionPicPayInput input) throws Exception {
        try {
            MdcUtils.putTransactionIdRandom();
            log.info("PICPAY_CHALLENGE - Inicializando a criação de uma nova transação por meio do payload: {}.", input);

            return new ResponseEntity<>(
                    transactionPicPayCreateUseCase.createTransaction(input),
                    HttpStatus.CREATED
            );
        } finally {
            MdcUtils.clear();
        }
    }
}
