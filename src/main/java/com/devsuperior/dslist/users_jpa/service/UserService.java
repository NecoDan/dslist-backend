package com.devsuperior.dslist.users_jpa.service;

import com.devsuperior.dslist.users_jpa.dto.UserDTO;
import com.devsuperior.dslist.users_jpa.ports.UserPort;
import com.devsuperior.dslist.users_jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserPort {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(@PathVariable Long userId) {
        return new UserDTO(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found! 404 Not Found!")));
    }

    @Override
    public Page<UserDTO> findAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::new);
    }

    @Override
    public Page<UserDTO> findAllBySalary(BigDecimal minSalary,
                                         BigDecimal maxSalary,
                                         Pageable pageable) {

        return userRepository.findAllBySalary(minSalary, maxSalary, pageable)
                .map(UserDTO::new);
    }

    @Override
    public Page<UserDTO> findAllSalaryBy(BigDecimal minSalary,
                                         BigDecimal maxSalary,
                                         Pageable pageable) {

        return userRepository.findBySalaryBetween(minSalary, maxSalary, pageable)
                .map(UserDTO::new);
    }

    @Override
    public Page<UserDTO> findAllByName(String name, Pageable pageable) {
        return userRepository.findAllByName(name, pageable)
                .map(UserDTO::new);
    }

    @Override
    public Page<UserDTO> findAllByNameFor(String name, Pageable pageable) {
        return userRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(UserDTO::new);
    }
}
