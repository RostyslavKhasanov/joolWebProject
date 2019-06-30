package ua.jool.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackDTO {

    private Long id;

    @Length(max = 1000, min = 10, message = "Size of comment to big or less than 10")
    private String text;

    private LocalDate date;

    private UserDTO user;

    private GameDTO game;
}
