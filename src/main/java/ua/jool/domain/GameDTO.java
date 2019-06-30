package ua.jool.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.jool.entity.FeedbackEntity;
import ua.jool.validation.CheckGameExists;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GameDTO {

    private Long id;

    @CheckGameExists
    @NotNull(message = "Field name can't be nul")
    @Size(min = 3, max = 100, message = "name length should be between 3 and 100")
    private String name;

    @NotNull(message = "Field description can't be null")
    @Size(min = 5)
    private String description;

    @DecimalMin(value = "0.0", message = "Min value 0")
    @DecimalMax(value = "9999.99", message = "Max value 9999.99")
    private BigDecimal price;

    private String image;

    @NotNull(message = "Field categories cant't be null")
    private List<CategoryDTO> categories;

    @NotNull(message = "Field developer cant't be null")
    private DeveloperDTO developer;

    private List<FeedbackDTO> feedbackDTOS;
}
