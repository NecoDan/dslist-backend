package com.devsuperior.dslist.users_jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_users", schema = "dslistapp")
@Data
@Profile("!test")
@AllArgsConstructor
@NoArgsConstructor
public class User {

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
