package com.devsuperior.dslist.core.usecase.picpay_challenge;

import com.devsuperior.dslist.core.usecase.picpay_challenge.input.UserPicPayInput;
import com.devsuperior.dslist.core.usecase.picpay_challenge.output.UserPicPayOutput;

import java.util.List;

public interface UserPicPayUseCase {
    UserPicPayOutput createUser(UserPicPayInput userDTO);

    List<UserPicPayOutput> getAll();

    UserPicPayOutput findById(Long id);
}
