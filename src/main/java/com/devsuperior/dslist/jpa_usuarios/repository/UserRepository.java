package com.devsuperior.dslist.jpa_usuarios.repository;

import com.devsuperior.dslist.jpa_usuarios.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select obj from User obj where obj.salary >= :minSalary and obj.salary <= :maxSalary")
    Page<User> findAllBySalary(BigDecimal minSalary, BigDecimal maxSalary, Pageable pageable);

    @Query("select obj from User obj where LOWER(obj.name) like LOWER(CONCAT('%', :name,'%'))")
    Page<User> findAllByName(String name, Pageable pageable);

    Page<User> findBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary, Pageable pageable);

    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
