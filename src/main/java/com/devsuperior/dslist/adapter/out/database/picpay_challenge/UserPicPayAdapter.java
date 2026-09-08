package com.devsuperior.dslist.adapter.out.database.picpay_challenge;


import com.devsuperior.dslist.adapter.out.database.picpay_challenge.repository.UserPIcPayRepository;
import com.devsuperior.dslist.adapter.out.entities.picpay_challenge.UserPicPayEntity;
import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;
import com.devsuperior.dslist.core.ports.picpay_challenge.UserPicPayPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserPicPayAdapter implements UserPicPayPort {

    private final UserPIcPayRepository userPIcPayRepository;

    @Override
    public List<UserPicPay> getAll() {
        log.info("PICPAY_CHALLENGE - Buscando todos os usuários");

        return this.userPIcPayRepository.findAll()
                .stream()
                .map(UserPicPay::new)
                .toList();
    }

    @Override
    public UserPicPay findUserById(Long id) {
        log.info("PICPAY_CHALLENGE - Buscando usuário por ID: {}", id);

        return new UserPicPay(this.userPIcPayRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Usuário não encontrado!")
                )
        );
    }

    @Override
    public UserPicPay createUser(UserPicPay userPicPay) {
        log.info("PICPAY_CHALLENGE - Criando usuário: {}", userPicPay);

        if (isValidUserId(userPicPay)) {
            return findUserById(userPicPay.getId());
        }

        var userEntity = new UserPicPayEntity(userPicPay);
        this.userPIcPayRepository.saveAndFlush(userEntity);

        return new UserPicPay(userEntity);
    }

    @Override
    public void saveUser(UserPicPay userPicPay) {
        log.info("PICPAY_CHALLENGE - Salvando usuário: {}", userPicPay);
        this.userPIcPayRepository.saveAndFlush(new UserPicPayEntity(userPicPay));
    }

    private boolean isValidUserId(UserPicPay userPicPay) {
        return (Objects.nonNull(userPicPay) && Objects.nonNull(userPicPay.getId()) && userPicPay.getId() > BigDecimal.ZERO.intValue());
    }
}
