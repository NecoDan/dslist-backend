package com.devsuperior.dslist.core.usecase.picpay_challenge;

import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;
import com.devsuperior.dslist.core.ports.picpay_challenge.UserPicPayPort;
import com.devsuperior.dslist.core.usecase.picpay_challenge.input.UserPicPayInput;
import com.devsuperior.dslist.core.usecase.picpay_challenge.output.UserPicPayOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPicPayUseCaseImpl implements UserPicPayUseCase {

    private final UserPicPayPort userPicPayPort;

    @Override
    public UserPicPayOutput createUser(UserPicPayInput userDTO) {
        return UserPicPayOutput.buildFrom(
                userPicPayPort.createUser(
                        new UserPicPay(userDTO)
                )
        );
    }

    @Override
    public List<UserPicPayOutput> getAll() {
        return userPicPayPort.getAll()
                .stream()
                .map(UserPicPayOutput::buildFrom)
                .toList();
    }

    @Override
    public UserPicPayOutput findById(Long id) {
        return UserPicPayOutput.buildFrom(
                userPicPayPort.findUserById(id)
        );
    }
}