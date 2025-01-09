package com.devsuperior.dslist.picpay_challenge.service;

import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.dto.internal.UserDTO;
import com.devsuperior.dslist.picpay_challenge.dto.request.UserRequestDTO;
import com.devsuperior.dslist.picpay_challenge.ports.UserPicPayPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserPicPayService")
@RequiredArgsConstructor
public class UserService {

    private final UserPicPayPort userPicPayPort;

    public UserDTO createUser(UserRequestDTO userDTO){
        return new UserDTO(
                userPicPayPort.createUser(
                        new User(userDTO)
                )
        );
    }

    public List<UserDTO> getAll(){
        return userPicPayPort.getAll()
                .stream()
                .map(UserDTO::new)
                .toList();
    }
}