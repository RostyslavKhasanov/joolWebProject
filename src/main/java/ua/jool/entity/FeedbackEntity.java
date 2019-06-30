package ua.jool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "feedbacks")
public class FeedbackEntity extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;
}
