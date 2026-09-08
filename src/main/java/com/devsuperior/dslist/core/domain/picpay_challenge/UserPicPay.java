package com.devsuperior.dslist.core.domain.picpay_challenge;

import com.devsuperior.dslist.core.usecase.picpay_challenge.output.UserPicPayOutput;
import com.devsuperior.dslist.core.usecase.picpay_challenge.input.UserPicPayInput;
import com.devsuperior.dslist.adapter.out.entities.picpay_challenge.UserPicPayEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPicPay implements Serializable {

    @Serial private static final long serialVersionUID = -2888473316529702128L;

    private Long id;
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String password;
    private BigDecimal balance;
    private TypeUserPicPay userType;
    private LocalDateTime createdAt;

    public UserPicPay(UserPicPayEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public UserPicPay(UserPicPayOutput entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public UserPicPay(UserPicPayInput entity){
        BeanUtils.copyProperties(entity, this);
    }
}
