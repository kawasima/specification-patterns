package net.unit8.example.trust.adapter.persistence;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "motivation_categories")
@Data
public class MotivationCategoryJpaEntity {
    @Id
    private String id;

    private String name;
}
