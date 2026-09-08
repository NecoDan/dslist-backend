package com.devsuperior.dslist.adapter.out.database.users_jpa;

import com.devsuperior.dslist.adapter.out.database.users_jpa.repository.UserDsListRepository;
import com.devsuperior.dslist.core.domain.user_jpa.UserDTO;
import com.devsuperior.dslist.core.ports.user_jpa.UserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDsListAdapter implements UserPort {

    private final UserDsListRepository userDsListRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        log.info("Fetching all users from the database");

        return userDsListRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findById(@PathVariable Long userId) {
        log.info("Fetching user with ID: {}", userId);

        return new UserDTO(userDsListRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found! 404 Not Found!"))
        );
    }

    @Override
    public Page<UserDTO> findAllPageable(Pageable pageable) {
        log.info("Fetching all users with pagination: page number = {}, page size = {}", pageable.getPageNumber(), pageable.getPageSize());
        return userDsListRepository.findAll(pageable).map(UserDTO::new);
    }

    @Override
    public Page<UserDTO> findAllBySalary(BigDecimal minSalary,
                                         BigDecimal maxSalary,
                                         Pageable pageable) {
        log.info("Fetching users with salary between {} and {} with pagination: page number = {}, page size = {}",
                minSalary, maxSalary, pageable.getPageNumber(), pageable.getPageSize());

        return userDsListRepository.findAllBySalary(minSalary, maxSalary, pageable)
                .map(UserDTO::new);
    }

    @Override
    public Page<UserDTO> findAllSalaryBy(BigDecimal minSalary,
                                         BigDecimal maxSalary,
                                         Pageable pageable) {
        log.info("Fetching users with salary between {} and {} with pagination: page number = {}, page size = {}",
                minSalary, maxSalary, pageable.getPageNumber(), pageable.getPageSize());

        return userDsListRepository.findBySalaryBetween(minSalary, maxSalary, pageable)
                .map(UserDTO::new);
    }

    @Override
    public Page<UserDTO> findAllByName(String name, Pageable pageable) {
        log.info("Fetching users with name: {} with pagination: page number = {}, page size = {}", name,
                pageable.getPageNumber(), pageable.getPageSize());

        return userDsListRepository.findAllByName(name, pageable)
                .map(UserDTO::new);
    }

    @Override
    public Page<UserDTO> findAllByNameFor(String name, Pageable pageable) {
        log.info("Fetching users with name containing: {} with pagination: page number = {}, page size = {}", name,
                pageable.getPageNumber(), pageable.getPageSize());

        return userDsListRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(UserDTO::new);
    }
}
