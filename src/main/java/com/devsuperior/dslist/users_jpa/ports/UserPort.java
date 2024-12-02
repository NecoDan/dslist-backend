package com.devsuperior.dslist.users_jpa.ports;

import com.devsuperior.dslist.users_jpa.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface UserPort {
    List<UserDTO> findAll();

    UserDTO findById(Long id);

    Page<UserDTO> findAllPageable(Pageable pageable);

    Page<UserDTO> findAllBySalary(BigDecimal minSalary, BigDecimal maxSalary, Pageable pageable);

    Page<UserDTO> findAllSalaryBy(BigDecimal minSalary, BigDecimal maxSalary, Pageable pageable);

    Page<UserDTO> findAllByName(String name, Pageable pageable);

    Page<UserDTO> findAllByNameFor(String name, Pageable pageable);

}
