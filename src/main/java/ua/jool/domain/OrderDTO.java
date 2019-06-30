package ua.jool.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.jool.enums.PaymentEnum;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class OrderDTO {

    private Long id;

    @NotNull(message = "Field game can't be null")
    private GameDTO game;

    @NotNull(message = "Field user can't be null")
    private UserDTO user;

    private LocalDate date;

    @NotNull(message = "Field payment can't be null")
    private PaymentEnum payment;

    private BigDecimal price;
}
