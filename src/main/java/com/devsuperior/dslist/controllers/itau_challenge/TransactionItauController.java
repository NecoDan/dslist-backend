package com.devsuperior.dslist.controllers.itau_challenge;

import com.devsuperior.dslist.itau_v1_challenge.dto.internal.TransactionItauResponseDTO;
import com.devsuperior.dslist.itau_v1_challenge.dto.request.TransactionItauRequestDTO;
import com.devsuperior.dslist.itau_v1_challenge.service.TransactionItauService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/itau/transactions")
@RequiredArgsConstructor
@Hidden
public class TransactionItauController {

    private final TransactionItauService transactionItauService;

    @PostMapping(value = "/v1")
    @Operation(summary = "Cria uma nova transação", description = "Cria uma nova transação com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<TransactionItauResponseDTO> create(@RequestBody TransactionItauRequestDTO transactionDTO) {
        return new ResponseEntity<>(transactionItauService.createTransaction(transactionDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/v1")
    public ResponseEntity<List<TransactionItauResponseDTO>> getAll() {
        return ResponseEntity.ok(transactionItauService.getAll());
    }

    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<TransactionItauResponseDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(transactionItauService.getById(id));
    }

    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        transactionItauService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/v1/")
    @Operation(summary = "Deletar transações", description = "Exclusão das transações.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transação excluidas com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteAll() {
        transactionItauService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
