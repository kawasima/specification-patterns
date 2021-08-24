package net.unit8.example.trust.adapter.persistence;

import lombok.Data;
import net.unit8.example.trust.domain.RegularUser;

import javax.persistence.*;

@Entity
@Table(name = "block_users")
@Data
public class BlockUserJpaEntity {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blocker_id")
    private RegularUser blocker;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blocked_id")
    private RegularUser blocked;
}
