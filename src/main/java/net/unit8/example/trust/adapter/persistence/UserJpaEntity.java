package net.unit8.example.trust.adapter.persistence;

import lombok.Data;
import net.unit8.example.trust.domain.UserRank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "user")
@Table(name = "users")
@Data
public class UserJpaEntity {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "rank", nullable = false)
    private UserRank rank;

    @ManyToMany
    @JoinTable(name = "user_companies",
            joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<CompanyJpaEntity> companies = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MotivationJpaEntity> motivations = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<UserJpaEntity> friends = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "name=" + name
                + ", companies=" + companies
                + ", motivations=[" + motivations.stream()
                .map(MotivationJpaEntity::getGrade)
                .map(Objects::toString)
                .collect(Collectors.joining(",")) + "]"
                + ", friends=[" + friends.stream()
                .map(f -> f.id)
                .collect(Collectors.joining(",")) + "]";
    }
}
