package com.devsuperior.dslist.picpay_challenge.biz;


import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.domain.UserTypeDomain;
import com.devsuperior.dslist.picpay_challenge.entities.user.UserEntity;
import com.devsuperior.dslist.picpay_challenge.ports.UserPicPayPort;
import com.devsuperior.dslist.picpay_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service("UserPicPayBusiness")
@RequiredArgsConstructor
public class UserBusiness implements UserPicPayPort {

    private final UserRepository userRepository;

    @Override
    public void validateTransaction(User userSender, BigDecimal amount) throws Exception {

        if (userSender.getUserType() == UserTypeDomain.MERCHANT) {
            throw new Exception("Usuário do tipo LOJISTA não possui autorização para realizar à transação.");
        }

        if (userSender.getBalance().compareTo(amount) < BigDecimal.ZERO.doubleValue()) {
            throw new Exception("Saldo insuficiente!");
        }
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(User::new)
                .toList();
    }

    @Override
    public User findUserById(Long id) {
        return new User(this.userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Usuário não encontrado!")
                )
        );
    }

    @Override
    public User createUser(User user) {
        if (isValidUserId(user)){
            return findUserById(user.getId());
        }

        UserEntity userEntity = new UserEntity(user);
        this.userRepository.saveAndFlush(userEntity);

        return new User(userEntity);
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.saveAndFlush(new UserEntity(user));
    }

    private boolean isValidUserId(User user) {
        return (Objects.nonNull(user) && Objects.nonNull(user.getId()) && user.getId() > BigDecimal.ZERO.intValue());
    }

}
