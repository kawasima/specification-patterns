package net.unit8.example.trust.adapter.persistence;

import lombok.Data;
import net.unit8.example.trust.domain.UserRank;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class UserJpaEntity {
    @Id
    private String id;
    private String name;
    private UserRank rank;

    @OneToMany
    private Set<CompanyJpaEntity> companies;
    @OneToMany
    private Set<MotivationJpaEntity> motivations;

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<UserJpaEntity> friends;
}
