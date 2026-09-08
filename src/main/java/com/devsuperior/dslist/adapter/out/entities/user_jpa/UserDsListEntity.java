package com.devsuperior.dslist.adapter.out.entities.user_jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "userdslist")
@Table(name = "tb_users", schema = "dslistapp")
@Data
//@Profile("!test")
public class UserDsListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    private String email;

    private String password;

    @Column(name = "salario")
    private BigDecimal salary;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
