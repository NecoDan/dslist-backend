package com.devsuperior.dslist.controllers.users_jpa;

import com.devsuperior.dslist.users_jpa.dto.UserDTO;
import com.devsuperior.dslist.users_jpa.ports.UserPort;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Hidden
public class UserController {

    private final UserPort userPort;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userPort.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userPort.findAll());
    }

    @GetMapping(value = "/pages")
    public ResponseEntity<Page<UserDTO>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(userPort.findAllPageable(pageable));
    }

    @GetMapping(value = "/fetch/salary/v1")
    public ResponseEntity<Page<UserDTO>> findyBySalaryV1(@RequestParam(defaultValue = "0") Double minSalary,
                                                       @RequestParam(defaultValue = "1000000000000") Double maxSalary,
                                                       Pageable pageable) {

        return ResponseEntity.ok(
                userPort.findAllBySalary(BigDecimal.valueOf(minSalary), BigDecimal.valueOf(maxSalary), pageable)
        );
    }

    @GetMapping(value = "/fetch/salary/v2")
    public ResponseEntity<Page<UserDTO>> findyBySalaryV2(@RequestParam(defaultValue = "0") Double minSalary,
                                                         @RequestParam(defaultValue = "1000000000000") Double maxSalary,
                                                         Pageable pageable) {

        return ResponseEntity.ok(
                userPort.findAllSalaryBy(BigDecimal.valueOf(minSalary), BigDecimal.valueOf(maxSalary), pageable)
        );
    }

    @GetMapping("/fetch/name/v1")
    public ResponseEntity<Page<UserDTO>> findByName(@RequestParam(defaultValue = StringUtils.EMPTY) String name,
                                                    Pageable pageable) {

        return ResponseEntity.ok(userPort.findAllByName(name, pageable));
    }

    @GetMapping("/fetch/name/v2")
    public ResponseEntity<Page<UserDTO>> findByNameFor(@RequestParam(defaultValue = StringUtils.EMPTY) String name,
                                                       Pageable pageable) {

        return ResponseEntity.ok(userPort.findAllByNameFor(name, pageable));
    }
}
