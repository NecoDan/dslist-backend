package com.devsuperior.dslist.controllers.picpay_challenge;

import com.devsuperior.dslist.picpay_challenge.dto.internal.TransactionResponseDTO;
import com.devsuperior.dslist.picpay_challenge.dto.request.TransactionRequestDTO;
import com.devsuperior.dslist.picpay_challenge.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/picpay/transactions")
@RequiredArgsConstructor
public class TransactionPicPayController    {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAll() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransactionResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@RequestBody TransactionRequestDTO transactionDTO) throws Exception {
        return new ResponseEntity<>(transactionService.createTransaction(transactionDTO), HttpStatus.CREATED);
    }
}
