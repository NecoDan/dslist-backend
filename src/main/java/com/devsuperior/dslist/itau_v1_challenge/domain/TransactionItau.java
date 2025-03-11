package com.devsuperior.dslist.itau_v1_challenge.domain;

import com.devsuperior.dslist.itau_v1_challenge.dto.request.TransactionItauRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionItau implements Serializable {

    private String id;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    //    public TransactionItau(TransactionEntity entity){
    //        BeanUtils.copyProperties(entity, this);
    //    }

    public TransactionItau(TransactionItauRequestDTO entity) {
        BeanUtils.copyProperties(entity, this);
        this.createdAt = entity.getCreatedAt().toLocalDateTime();
    }
}
