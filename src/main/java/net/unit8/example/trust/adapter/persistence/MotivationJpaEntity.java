package net.unit8.example.trust.adapter.persistence;

import lombok.Data;
import net.unit8.example.trust.domain.MotivationGrade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "motivations")
@Data
public class MotivationJpaEntity {
    @Id
    @ManyToOne
    private UserJpaEntity user;

    @Id
    @ManyToOne
    private MotivationCategoryJpaEntity motivationCategory;

    private MotivationGrade grade;

}
