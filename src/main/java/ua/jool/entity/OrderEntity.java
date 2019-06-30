package ua.jool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.jool.enums.PaymentEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column
    private LocalDate date;

    @Column
    private PaymentEnum payment;

    @Column(columnDefinition = "DECIMAL(6,2)", nullable = false)
    private BigDecimal price;
}
