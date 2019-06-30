package ua.jool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "developers")
public class DeveloperEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "developer")
    private List<GameEntity> games;

}
