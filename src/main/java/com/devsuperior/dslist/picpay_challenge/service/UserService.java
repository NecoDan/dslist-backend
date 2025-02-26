package com.devsuperior.dslist.picpay_challenge.service;

import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.dto.internal.UserResponseDTO;
import com.devsuperior.dslist.picpay_challenge.dto.request.UserRequestDTO;
import com.devsuperior.dslist.picpay_challenge.ports.UserPicPayPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserPicPayService")
@RequiredArgsConstructor
public class UserService {

    private final UserPicPayPort userPicPayPort;

    public UserResponseDTO createUser(UserRequestDTO userDTO){
        return new UserResponseDTO(
                userPicPayPort.createUser(
                        new User(userDTO)
                )
        );
    }

    public List<UserResponseDTO> getAll(){
        return userPicPayPort.getAll()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    public UserResponseDTO findById(Long id) {
        return new UserResponseDTO(userPicPayPort.findUserById(id));
    }
}