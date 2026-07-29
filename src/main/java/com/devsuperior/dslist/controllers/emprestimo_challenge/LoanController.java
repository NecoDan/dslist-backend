package com.devsuperior.dslist.controllers.emprestimo_challenge;

import com.devsuperior.dslist.emprestimos_challenge.dto.CustomerLoanRequest;
import com.devsuperior.dslist.emprestimos_challenge.dto.CustomerLoanResponse;
import com.devsuperior.dslist.emprestimos_challenge.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping(value = "/customer-loans")
    @Operation(summary = "Cria uma nova solicitacao emprestimos", description = "Cria uma nova solicitacao emprestimos disponiveis ao cliente com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitacao criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CustomerLoanResponse> customersLoans(@Valid @RequestBody CustomerLoanRequest request) {
        return ResponseEntity.ok().body(loanService.checkLoanEligibility(request));
    }
}
