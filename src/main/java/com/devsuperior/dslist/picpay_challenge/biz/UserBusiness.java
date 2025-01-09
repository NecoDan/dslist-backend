package com.devsuperior.dslist.picpay_challenge.biz;


import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.domain.UserTypeDomain;
import com.devsuperior.dslist.picpay_challenge.entities.user.UserEntity;
import com.devsuperior.dslist.picpay_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("UserPicPayBusiness")
@RequiredArgsConstructor
public class UserBusiness {

    private final UserRepository userRepository;

    public void validateTransaction(User userSender, BigDecimal amount) throws Exception {

        if (userSender.getUserType() == UserTypeDomain.MERCHANT) {
            throw new Exception("Usuário do tipo LOJISTA não possui autorização para realizar à transação.");
        }

        if (userSender.getBalance().compareTo(amount) < BigDecimal.ZERO.doubleValue()) {
            throw new Exception("Saldo insuficiente!");
        }
    }

    public User findUserById(Long id) throws Exception {

        return new User(this.userRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Usuário não encontrado!")
                )
        );
    }

    public void saveUser(User user) {
        this.userRepository.save(new UserEntity(user));
    }

}
