package com.devsuperior.dslist.adapter.out.entities.picpay_challenge;

import com.devsuperior.dslist.core.domain.picpay_challenge.TransactionPicPay;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@Table(name = "tb_transaction", schema = "picpay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransactionPicPayEntity implements Serializable {

    @Serial private static final long serialVersionUID = -8833417529045805652L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserPicPayEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserPicPayEntity receiver;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public TransactionPicPayEntity(TransactionPicPay entity){
        BeanUtils.copyProperties(entity, this);
        this.sender = new UserPicPayEntity(entity.getSender());
        this.receiver = new UserPicPayEntity(entity.getReceiver());
    }
}
