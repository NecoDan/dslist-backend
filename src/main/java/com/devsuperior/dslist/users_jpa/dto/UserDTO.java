package com.devsuperior.dslist.users_jpa.dto;


import com.devsuperior.dslist.users_jpa.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String email;
    private BigDecimal salary;

    public UserDTO(User entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
