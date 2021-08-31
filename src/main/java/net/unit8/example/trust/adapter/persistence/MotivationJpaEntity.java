package net.unit8.example.trust.adapter.persistence;

import lombok.Data;
import net.unit8.example.trust.domain.MotivationGrade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "motivations")
@Data
@IdClass(MotivationJpaEntity.MotivationKey.class)
public class MotivationJpaEntity {
    @Id
    @ManyToOne
    private UserJpaEntity user;

    @Id
    @ManyToOne
    private MotivationCategoryJpaEntity motivationCategory;

    private MotivationGrade grade;

    @Data
    public static class MotivationKey implements Serializable {
        private UserJpaEntity user;
        private MotivationCategoryJpaEntity motivationCategory;
    }
}
