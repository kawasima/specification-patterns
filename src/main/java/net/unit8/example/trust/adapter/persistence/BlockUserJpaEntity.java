package net.unit8.example.trust.adapter.persistence;

import lombok.Data;
import net.unit8.example.trust.domain.RegularUser;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "block_users")
@Data
public class BlockUserJpaEntity implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blocker_id")
    private UserJpaEntity blocker;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blocked_id")
    private UserJpaEntity blocked;
}
