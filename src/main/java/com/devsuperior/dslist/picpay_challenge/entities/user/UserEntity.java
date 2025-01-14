package com.devsuperior.dslist.picpay_challenge.entities.user;


import com.devsuperior.dslist.picpay_challenge.domain.User;
import com.devsuperior.dslist.picpay_challenge.domain.UserTypeDomain;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "users")
@Table(name = "user", schema = "picpay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserTypeDomain userType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserEntity(User entity){
        BeanUtils.copyProperties(entity, this);
    }
}
