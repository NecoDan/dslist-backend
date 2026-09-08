package com.devsuperior.dslist.adapter.in.http.controllers.picpay_challenge;

import com.devsuperior.dslist.core.usecase.picpay_challenge.UserPicPayUseCase;
import com.devsuperior.dslist.core.usecase.picpay_challenge.input.UserPicPayInput;
import com.devsuperior.dslist.core.usecase.picpay_challenge.output.UserPicPayOutput;
import com.devsuperior.dslist.utils.logs.MdcUtils;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/picpay/users")
@RequiredArgsConstructor
@Hidden
@Slf4j
public class UserPicPayController {

    private final UserPicPayUseCase userPicPayUseCase;

    @GetMapping
    public ResponseEntity<List<UserPicPayOutput>> getAll() {
        try {
            MdcUtils.putTransactionIdRandom();
            log.info("PICPAY_CHALLENGE - Inicializando a busca de todos os usuários salvo(s) & registrado(s).");

            return ResponseEntity.ok(userPicPayUseCase.getAll());
        } finally {
            MdcUtils.clear();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserPicPayOutput> findById(@PathVariable Long id) {
        try {
            MdcUtils.putTransactionIdRandom();
            log.info("PICPAY_CHALLENGE - Inicializando a busca de um usuário salvo por ID: {}.", id);

            return ResponseEntity.ok(userPicPayUseCase.findById(id));
        } finally {
            MdcUtils.clear();
        }
    }

    @PostMapping
    public ResponseEntity<UserPicPayOutput> create(@RequestBody UserPicPayInput userDTO) {
        try {
            MdcUtils.putTransactionIdRandom();
            log.info("PICPAY_CHALLENGE - Inicializando a criação de um novo usuário através do payload: {}.", userDTO);

            return new ResponseEntity<>(userPicPayUseCase.createUser(userDTO), HttpStatus.CREATED);
        } finally {
            MdcUtils.clear();
        }
    }
}
