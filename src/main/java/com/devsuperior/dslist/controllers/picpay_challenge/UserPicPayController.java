package com.devsuperior.dslist.controllers.picpay_challenge;

import com.devsuperior.dslist.picpay_challenge.dto.internal.UserDTO;
import com.devsuperior.dslist.picpay_challenge.dto.request.UserRequestDTO;
import com.devsuperior.dslist.picpay_challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/picpay/users")
@RequiredArgsConstructor
public class UserPicPayController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserRequestDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
}
