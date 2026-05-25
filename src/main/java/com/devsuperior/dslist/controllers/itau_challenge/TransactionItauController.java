package com.devsuperior.dslist.controllers.itau_challenge;

import com.devsuperior.dslist.itau_v1_challenge.dto.internal.TransactionItauResponseDTO;
import com.devsuperior.dslist.itau_v1_challenge.dto.request.TransactionItauRequestDTO;
import com.devsuperior.dslist.itau_v1_challenge.service.TransactionItauService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/itau/transactions")
@RequiredArgsConstructor
public class TransactionItauController {

    private final TransactionItauService transactionItauService;

    @PostMapping(value = "/v1")
    public ResponseEntity<TransactionItauResponseDTO> create(@RequestBody TransactionItauRequestDTO transactionDTO) {
        return new ResponseEntity<>(transactionItauService.createTransaction(transactionDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/v1")
    public ResponseEntity<List<TransactionItauResponseDTO>> getAll() {
        return new ResponseEntity<>(transactionItauService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<TransactionItauResponseDTO> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(transactionItauService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        return new ResponseEntity<>(transactionItauService.deleteById(id), HttpStatus.OK);
    }
}
