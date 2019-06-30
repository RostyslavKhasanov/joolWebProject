package ua.jool.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotNull(message = "Field name can't be null")
    private String name;
}
