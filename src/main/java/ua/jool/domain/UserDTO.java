package ua.jool.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import ua.jool.enums.GenderEnum;
import ua.jool.validation.CheckEmailExists;
import ua.jool.validation.CheckEmailValid;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class UserDTO {

    private Long id;


    @NotNull(message = "Field username can't be null")
    @Size(min = 3, message = "Min 3 symbols")
    private String username;

    @NotNull(message = "Field password can't be null")
    @Size(min = 4, message = "Min size 4")
    private String password;

    @NotNull(message = "Field passwordConfirm can't be null")
    private String passwordConfirm;

    @CheckEmailExists
    @CheckEmailValid
    @NotNull(message = "Field email can't be null")
    private String email;

    @NotNull(message = "Field age can't be null")
    @Range(max = 99, message = "Max age 99")
    private int age;

    private String image;

    @NotNull(message = "Field gender can't be null")
    private GenderEnum gender;

}
