package com.devsuperior.dslist.picpay_challenge.domain;

import com.devsuperior.dslist.picpay_challenge.entities.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String password;
    private BigDecimal balance;
    private UserTypeDomain userType;

    public User(UserEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
