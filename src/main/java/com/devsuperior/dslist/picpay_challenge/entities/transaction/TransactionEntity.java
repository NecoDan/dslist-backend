package com.devsuperior.dslist.picpay_challenge.entities.transaction;

import com.devsuperior.dslist.picpay_challenge.domain.Transaction;
import com.devsuperior.dslist.picpay_challenge.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transaction", schema = "picpay")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransactionEntity implements Serializable {

    @Serial private static final long serialVersionUID = -8833417529045805652L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public TransactionEntity(Transaction entity){
        BeanUtils.copyProperties(entity, this);
        this.sender = new UserEntity(entity.getSender());
        this.receiver = new UserEntity(entity.getReceiver());
    }
}
