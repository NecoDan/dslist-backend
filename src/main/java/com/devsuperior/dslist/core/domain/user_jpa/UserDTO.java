package com.devsuperior.dslist.core.domain.user_jpa;


import com.devsuperior.dslist.adapter.out.entities.user_jpa.UserDsListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

    @Serial private static final long serialVersionUID = -8351738433255259724L;

    private Long id;
    private String name;
    private String email;
    private BigDecimal salary;

    public UserDTO(UserDsListEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
