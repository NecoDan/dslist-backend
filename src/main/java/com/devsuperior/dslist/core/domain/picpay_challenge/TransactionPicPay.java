package com.devsuperior.dslist.core.domain.picpay_challenge;

import com.devsuperior.dslist.adapter.out.entities.picpay_challenge.TransactionPicPayEntity;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionPicPay implements Serializable {

    @Serial private static final long serialVersionUID = -3157259025497473548L;

    private Long id;
    private BigDecimal amount;
    private UserPicPay sender;
    private UserPicPay receiver;
    private LocalDateTime createdAt;

    public TransactionPicPay(TransactionPicPayEntity entity){
        BeanUtils.copyProperties(entity, this);
        this.sender = new UserPicPay(entity.getSender());
        this.receiver = new UserPicPay(entity.getReceiver());
    }
}
