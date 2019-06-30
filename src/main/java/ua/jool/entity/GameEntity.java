package ua.jool.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "games")
public class GameEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "DECIMAL(6,2)", nullable = false)
    private BigDecimal price;

    @Column(nullable = true)
    private String image;

    @ManyToMany
    @JoinTable(name = "game_category",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private DeveloperEntity developer;

    @OneToMany(mappedBy = "game")
    private List<FeedbackEntity> feedbacks;
}
