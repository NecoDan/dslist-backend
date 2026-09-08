package com.devsuperior.dslist.adapter.out.entities.picpay_challenge;


import com.devsuperior.dslist.core.domain.picpay_challenge.UserPicPay;
import com.devsuperior.dslist.core.domain.picpay_challenge.TypeUserPicPay;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "userpicpay")
@Table(name = "tb_user", schema = "picpay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserPicPayEntity implements Serializable {

    @Serial private static final long serialVersionUID = -1339669230444169995L;

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
    private TypeUserPicPay userType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserPicPayEntity(UserPicPay entity){
        BeanUtils.copyProperties(entity, this);
    }
}
