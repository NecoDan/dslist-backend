package com.devsuperior.dslist.controllers.picpay_challenge;

import com.devsuperior.dslist.picpay_challenge.dto.internal.UserResponseDTO;
import com.devsuperior.dslist.picpay_challenge.dto.request.UserRequestDTO;
import com.devsuperior.dslist.picpay_challenge.service.UserService;
import com.devsuperior.dslist.users_jpa.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/picpay/users")
@RequiredArgsConstructor
public class UserPicPayController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }
}
