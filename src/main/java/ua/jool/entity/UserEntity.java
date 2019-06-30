package ua.jool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.jool.enums.GenderEnum;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column
    private String image;

    @Column(name = "gender")
    private GenderEnum gender;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user")
    private List<FeedbackEntity> feedbacks;
}
